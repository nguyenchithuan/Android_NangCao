package com.example.myapplication.demo3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;

import java.io.IOException;
import java.io.InputStream;

public class Demo32Main2Activity extends AppCompatActivity {

    private static final String TAG = Demo32Main2Activity.class.getSimpleName();
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    private Uri uriContact;
    private String contactID;     // contacts unique ID
    TextView lab3TextViewContact;
    Button button;


    /**
     * Called when the activity is first created.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo32_main2);
        lab3TextViewContact = findViewById(R.id.lab3Tv);
        button = findViewById(R.id.demo32Btn1);
        button.setOnClickListener(v->{
            startActivityForResult(new Intent(Intent.ACTION_PICK,
                    ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
        });
    }

    public void onClickSelectContact(View btnSelectContact) {

        // using native contacts selection
        // Intent.ACTION_PICK = Pick an item from the data, returning what was selected.
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
            Log.d(TAG, "Response: " + data.toString());
            uriContact = data.getData();

          String contactName =   retrieveContactName();
          String contactNumber =   retrieveContactNumber();
          lab3TextViewContact.setText(contactName+ " - "+contactNumber);
//            retrieveContactPhoto();

        }
    }

    private void retrieveContactPhoto() {

        Bitmap photo = null;

        try {
            InputStream inputStream =
                    ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(),
                    ContentUris.withAppendedId(
                            ContactsContract.Contacts.CONTENT_URI, new Long(contactID)));

            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);
                ImageView imageView = (ImageView) findViewById(R.id.img_contact);
                imageView.setImageBitmap(photo);
            }

            assert inputStream != null;
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String retrieveContactNumber() {

        String contactNumber = null;

        // getting contacts ID
        Cursor cursorID = getContentResolver().query(uriContact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (cursorID.moveToFirst()) {

            contactID = cursorID.getString(cursorID.getColumnIndex(
                    ContactsContract.Contacts._ID));
        }
        cursorID.close();
        Log.d(TAG, "Contact ID: " + contactID);

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(
                    cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        cursorPhone.close();

        Log.d(TAG, "Contact Phone Number: " + contactNumber);
        return contactNumber;
    }

    private String retrieveContactName() {

        String contactName = null;

        // querying contact data store
        Cursor cursor = getContentResolver().query(uriContact,
                null, null, null, null);

        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

            contactName = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }

        cursor.close();

        Log.d(TAG, "Contact Name: " + contactName);
        return contactName;

    }
}
