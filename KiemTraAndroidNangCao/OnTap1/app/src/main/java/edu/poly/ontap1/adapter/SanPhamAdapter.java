package edu.poly.ontap1.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.poly.ontap1.R;
import edu.poly.ontap1.models.SanPham;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {
    private Context context;
    private ArrayList<SanPham> list;

    public SanPhamAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<SanPham> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sanPham = list.get(position);
        if(sanPham == null) {
            return;
        }

        holder.tvMa.setText("Mã sản phẩm: " + sanPham.getMaSp_ph26023());
        holder.tvName.setText("Tên sản phẩm: " + sanPham.getTenSp_ph26023());
        holder.tvLoai.setText("Mã loại: " + sanPham.getMaLoai_ph26023());
        holder.tvSoLuong.setText("Mã số lượng: " + sanPham.getSlNhap_ph26023());
        holder.tvNgay.setText("Mã ngày nhập: " + sanPham.getNgayNhap_ph26023());
        holder.tvGia.setText("Mã đơn giá: " + sanPham.getDgNhap_ph26023() + " VND");
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMa, tvName, tvLoai, tvSoLuong, tvNgay, tvGia;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMa = itemView.findViewById(R.id.tv_ma);
            tvName = itemView.findViewById(R.id.tv_ten);
            tvLoai = itemView.findViewById(R.id.tv_loai);
            tvSoLuong = itemView.findViewById(R.id.tv_soLuong);
            tvNgay = itemView.findViewById(R.id.tv_ngayNhap);
            tvGia = itemView.findViewById(R.id.tv_dgNhap);
        }
    }
}
