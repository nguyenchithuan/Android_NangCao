package edu.poly.bottomsheet_recycleview_interface;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class MyBottomSheetFragment extends BottomSheetDialogFragment {
    private ArrayList<ItemObject> list;
    private IClickListener iClickListener;

    public MyBottomSheetFragment(ArrayList<ItemObject> list, IClickListener iClickListener) {
        this.list = list;
        this.iClickListener = iClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet, null);
        bottomSheetDialog.setContentView(view);

        RecyclerView rcvData = view.findViewById(R.id.rcv_data);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvData.setLayoutManager(layoutManager);

        ItemAdapter itemAdapter = new ItemAdapter(list, new IClickListener() {
            @Override
            public void clickItem(ItemObject itemObject) {
                iClickListener.clickItem(itemObject);
            }
        });

        rcvData.setAdapter(itemAdapter);

        return bottomSheetDialog;
    }
}
