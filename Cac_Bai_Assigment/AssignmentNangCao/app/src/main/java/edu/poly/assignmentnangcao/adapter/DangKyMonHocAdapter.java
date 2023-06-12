package edu.poly.assignmentnangcao.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import edu.poly.assignmentnangcao.R;
import edu.poly.assignmentnangcao.model.MonHoc;
import edu.poly.assignmentnangcao.model.ThongTin;
import edu.poly.assignmentnangcao.service.DangKyMonHocService;
import edu.poly.assignmentnangcao.service.DkAndHuyMonHocService;

public class DangKyMonHocAdapter extends RecyclerView.Adapter<DangKyMonHocAdapter.DangKyMonHocViewHolder> {
    private Context context;
    private ArrayList<MonHoc> list;
    private int id;
    private boolean isAll;

    public DangKyMonHocAdapter(Context context, int id, boolean isAll) {
        this.context = context;
        this.id = id;
        this.isAll = isAll;
    }

    public void setData(ArrayList<MonHoc> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public DangKyMonHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_dang_ky_mon_hoc, parent, false);
        return new DangKyMonHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DangKyMonHocViewHolder holder, int position) {
        MonHoc monHoc = list.get(position);
        if(monHoc == null) {
            return;
        }

        holder.tvCode.setText(monHoc.getCode());
        holder.tvName.setText(monHoc.getName());
        holder.tvTeacher.setText(monHoc.getTeacher());

        if(monHoc.getIsRegister() == id) {
            holder.btnDangKy.setText("Hủy đăng ký môn học");
            holder.btnDangKy.setBackgroundColor(Color.RED);
            holder.btnDangKy.setTextColor(Color.WHITE);
        } else {
            holder.btnDangKy.setText("Đăng ký môn học");
            holder.btnDangKy.setBackgroundColor(Color.BLUE);
            holder.btnDangKy.setTextColor(Color.WHITE);
        }

        holder.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DkAndHuyMonHocService.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("code", monHoc.getCode());
                bundle.putInt("isRegister", monHoc.getIsRegister());
                bundle.putBoolean("isAll", isAll);
                intent.putExtras(bundle);
                context.startService(intent);
            }
        });


        holder.tvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(monHoc.getListTT());
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

    public class DangKyMonHocViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCode, tvName, tvTeacher;
        private Button btnDangKy;

        public DangKyMonHocViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCode = itemView.findViewById(R.id.tv_code);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTeacher = itemView.findViewById(R.id.tv_teacher);
            btnDangKy = itemView.findViewById(R.id.btn_dangKy);
        }
    }

    public void showDialog(ArrayList<ThongTin> list) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null);
        builder.setView(view);

        ListView lvThongTin = view.findViewById(R.id.lvThongTin);

        // simple adapter cho nhanh không phải tạo adapter, nhưng danh sách phải chuyển sang hasdmap
        ArrayList<HashMap<String, Object>> listTT = new ArrayList<>();
        for (ThongTin tt: list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("date", "Ngày học: " + tt.getDate());
            hs.put("address", "Địa điểm: " + tt.getAddress());
            listTT.add(hs);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                context, listTT,
                android.R.layout.simple_list_item_2,
                new String[]{"date", "address"},
                new int[]{android.R.id.text1, android.R.id.text2});

        lvThongTin.setAdapter(adapter);

        builder.show();
    }
}


