package edu.poly.bottomsheetdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.poly.bottomsheetdialog.model.Order;
import edu.poly.bottomsheetdialog.model.Product;

public class MainActivity extends AppCompatActivity {
    private Button btnBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBottomSheet = findViewById(R.id.open_bottom_sheet);

        btnBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOpenBottomSheetDialogFragment();
            }
        });
    }

    private void onClickOpenBottomSheetDialogFragment() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Bim bim 1x5"));
        productList.add(new Product("Xúc xích 1x10"));
        productList.add(new Product("Trà sữa 1x2"));

        Order order = new Order("500 VNĐ", productList, "Chương Mỹ, Hà Nội, Việt Nam");
        MyBottomSheetDialogFragment sheetDialogFragment = MyBottomSheetDialogFragment.newInstance(order);
        sheetDialogFragment.show(getSupportFragmentManager(), sheetDialogFragment.getTag());
        sheetDialogFragment.setCancelable(false);
    }
}