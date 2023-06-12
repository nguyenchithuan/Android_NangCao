package edu.poly.kiemtraandroidnangcao.models;

import java.util.Date;

public class SanPham {
    private Integer maSp_ph26023;
    private String tenSp_ph26023;
    private Integer maLoai_ph26023;
    private int slNhap_ph26023;
    private Date ngayNhap_ph26023;
    private int dgNhap_ph26023;

    public SanPham() {
    }

    public SanPham(String tenSp_ph26023, Integer maLoai_ph26023, int slNhap_ph26023, Date ngayNhap_ph26023, int dgNhap_ph26023) {
        this.tenSp_ph26023 = tenSp_ph26023;
        this.maLoai_ph26023 = maLoai_ph26023;
        this.slNhap_ph26023 = slNhap_ph26023;
        this.ngayNhap_ph26023 = ngayNhap_ph26023;
        this.dgNhap_ph26023 = dgNhap_ph26023;
    }

    public SanPham(Integer maSp_ph26023, String tenSp_ph26023, Integer maLoai_ph26023, int slNhap_ph26023, Date ngayNhap_ph26023, int dgNhap_ph26023) {
        this.maSp_ph26023 = maSp_ph26023;
        this.tenSp_ph26023 = tenSp_ph26023;
        this.maLoai_ph26023 = maLoai_ph26023;
        this.slNhap_ph26023 = slNhap_ph26023;
        this.ngayNhap_ph26023 = ngayNhap_ph26023;
        this.dgNhap_ph26023 = dgNhap_ph26023;
    }

    public Integer getMaSp_ph26023() {
        return maSp_ph26023;
    }

    public void setMaSp_ph26023(Integer maSp_ph26023) {
        this.maSp_ph26023 = maSp_ph26023;
    }

    public String getTenSp_ph26023() {
        return tenSp_ph26023;
    }

    public void setTenSp_ph26023(String tenSp_ph26023) {
        this.tenSp_ph26023 = tenSp_ph26023;
    }

    public Integer getMaLoai_ph26023() {
        return maLoai_ph26023;
    }

    public void setMaLoai_ph26023(Integer maLoai_ph26023) {
        this.maLoai_ph26023 = maLoai_ph26023;
    }

    public int getSlNhap_ph26023() {
        return slNhap_ph26023;
    }

    public void setSlNhap_ph26023(int slNhap_ph26023) {
        this.slNhap_ph26023 = slNhap_ph26023;
    }

    public Date getNgayNhap_ph26023() {
        return ngayNhap_ph26023;
    }

    public void setNgayNhap_ph26023(Date ngayNhap_ph26023) {
        this.ngayNhap_ph26023 = ngayNhap_ph26023;
    }

    public int getDgNhap_ph26023() {
        return dgNhap_ph26023;
    }

    public void setDgNhap_ph26023(int dgNhap_ph26023) {
        this.dgNhap_ph26023 = dgNhap_ph26023;
    }

    public void setSlNhap_Ph26023(int anInt) {
    }
}
