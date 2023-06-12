package edu.poly.ontap1.models;

public class LoaiSp {
    private int maLoai_ph26023;
    private String tenLoai_ph26023;

    public LoaiSp() {
    }

    public LoaiSp(String tenLoai_ph26023) {
        this.tenLoai_ph26023 = tenLoai_ph26023;
    }

    public LoaiSp(int maLoai_ph26023, String tenLoai_ph26023) {
        this.maLoai_ph26023 = maLoai_ph26023;
        this.tenLoai_ph26023 = tenLoai_ph26023;
    }

    public int getMaLoai_ph26023() {
        return maLoai_ph26023;
    }

    public void setMaLoai_ph26023(int maLoai_ph26023) {
        this.maLoai_ph26023 = maLoai_ph26023;
    }

    public String getTenLoai_ph26023() {
        return tenLoai_ph26023;
    }

    public void setTenLoai_ph26023(String tenLoai_ph26023) {
        this.tenLoai_ph26023 = tenLoai_ph26023;
    }
}
