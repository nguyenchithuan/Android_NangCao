package edu.poly.bottomsheet_recycleview_interface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private ArrayList<ItemObject> list;
    private IClickListener iClickListener;

    public ItemAdapter(ArrayList<ItemObject> list, IClickListener iClickListener) {
        this.list = list;
        this.iClickListener = iClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemObject itemObject = list.get(position);
        if(itemObject == null) {
            return;
        }

        holder.tvItem.setText(itemObject.getName());
        holder.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListener.clickItem(itemObject);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItem;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }
}
