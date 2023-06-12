package edu.poly.kiemtraandroidnangcao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.poly.kiemtraandroidnangcao.R;
import edu.poly.kiemtraandroidnangcao.models.Bai3;


public class Bai3Adapter extends RecyclerView.Adapter<Bai3Adapter.Bai3ViewHolder> {
    private Context context;
    private ArrayList<Bai3> list;

    public Bai3Adapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Bai3> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Bai3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new Bai3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Bai3ViewHolder holder, int position) {
        Bai3 bai3 = list.get(position);
        if(bai3 == null) {
            return;
        }

        holder.tvName.setText("Sản phẩm: " + bai3.getName());
        holder.tvAmount.setText("Số lượng: " + bai3.getAmount());
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class Bai3ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvAmount;

        public Bai3ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_tenSp);
            tvAmount = itemView.findViewById(R.id.tv_soluong);
        }
    }
}
