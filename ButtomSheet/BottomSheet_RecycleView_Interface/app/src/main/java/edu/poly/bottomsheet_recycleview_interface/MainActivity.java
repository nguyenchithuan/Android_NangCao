package edu.poly.bottomsheet_recycleview_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_open_bottom_sheet);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOpenBottomSheetFragment();
            }
        });
    }

    private void clickOpenBottomSheetFragment() {
        ArrayList<ItemObject> list = new ArrayList<>();
        list.add(new ItemObject("Item 1"));
        list.add(new ItemObject("Item 2"));
        list.add(new ItemObject("Item 3"));
        list.add(new ItemObject("Item 4"));
        list.add(new ItemObject("Item 5"));
        MyBottomSheetFragment myBottomSheetFragment = new MyBottomSheetFragment(list, new IClickListener() {
            @Override
            public void clickItem(ItemObject itemObject) {
                Toast.makeText(MainActivity.this, itemObject.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        myBottomSheetFragment.show(getSupportFragmentManager(), myBottomSheetFragment.getTag());
    }
}