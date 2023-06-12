package edu.poly.bottomsheetdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import edu.poly.bottomsheetdialog.model.Order;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private static final String KEY_ORDER = "order";
    private Order order;
    private TextView tvPrice, tvProduct, tvAddress;
    private Button btnCancle;

    public static MyBottomSheetDialogFragment newInstance(Order order) {
        MyBottomSheetDialogFragment myBottomSheetDialogFragment = new MyBottomSheetDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_ORDER, order);
        myBottomSheetDialogFragment.setArguments(bundle);
        return myBottomSheetDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            order = (Order) bundle.get(KEY_ORDER);
        }
    }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet, null);
            bottomSheetDialog.setContentView(view);


            initView(view);
            setDataOrder();

            btnCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            return bottomSheetDialog;
        }

        private void initView(View view) {
            tvPrice = view.findViewById(R.id.tv_price);
            tvAddress = view.findViewById(R.id.tv_address);
            tvProduct = view.findViewById(R.id.tv_produce);
            btnCancle = view.findViewById(R.id.btn_cancel);
        }

        private void setDataOrder() {
            if(order == null) {
                return;
            }
            tvPrice.setText(order.getPrice());
            tvProduct.setText(order.getListProductName());
            tvAddress.setText(order.getAddress());
    }
}
