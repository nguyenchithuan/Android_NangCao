package edu.poly.kiemtraandroidnangcao.models;

public class Bai4 {
    private int maLoai;
    private int maSanPham;
    private int soLuong;

    public Bai4() {
    }

    public Bai4(int maLoai, int maSanPham, int soLuong) {
        this.maLoai = maLoai;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
