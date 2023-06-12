package edu.poly.kiemtraandroidnangcao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.poly.kiemtraandroidnangcao.R;
import edu.poly.kiemtraandroidnangcao.dao.DuLieuDao;
import edu.poly.kiemtraandroidnangcao.models.Bai4;

public class Bai4Adapter extends RecyclerView.Adapter<Bai4Adapter.Bai3ViewHolder> {
    private Context context;
    private ArrayList<Bai4> list;

    public Bai4Adapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Bai4> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Bai3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_bai4, parent, false);
        return new Bai3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Bai3ViewHolder holder, int position) {
        Bai4 bai4 = list.get(position);
        if(bai4 == null) {
            return;
        }
        DuLieuDao dao = new DuLieuDao(context);

        holder.tvTenLoai.setText("Mã loại: " + bai4.getMaLoai());
        holder.tvTenSp.setText("Mã sp: " + bai4.getMaSanPham());
        holder.tvSoLuong.setText("Số lượng: " + bai4.getSoLuong());
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class Bai3ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenLoai, tvTenSp, tvSoLuong;

        public Bai3ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoai = itemView.findViewById(R.id.tv_tenLoai);
            tvTenSp = itemView.findViewById(R.id.tv_tenSp);
            tvSoLuong = itemView.findViewById(R.id.tv_count);
        }
    }
}
