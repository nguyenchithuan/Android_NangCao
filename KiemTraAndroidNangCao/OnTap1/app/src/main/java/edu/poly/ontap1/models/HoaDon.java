package edu.poly.ontap1.models;

public class HoaDon {
    private int maHd_ph26023;
    private int slXuat_ph26023;
    private String ngayXuat_ph26023;
    private int dgXuat_ph26023;

    public HoaDon() {
    }

    public HoaDon(int slXuat_ph26023, String ngayXuat_ph26023, int dgXuat_ph26023) {
        this.slXuat_ph26023 = slXuat_ph26023;
        this.ngayXuat_ph26023 = ngayXuat_ph26023;
        this.dgXuat_ph26023 = dgXuat_ph26023;
    }

    public HoaDon(int maHd_ph26023, int slXuat_ph26023, String ngayXuat_ph26023, int dgXuat_ph26023) {
        this.maHd_ph26023 = maHd_ph26023;
        this.slXuat_ph26023 = slXuat_ph26023;
        this.ngayXuat_ph26023 = ngayXuat_ph26023;
        this.dgXuat_ph26023 = dgXuat_ph26023;
    }

    public int getMaHd_ph26023() {
        return maHd_ph26023;
    }

    public void setMaHd_ph26023(int maHd_ph26023) {
        this.maHd_ph26023 = maHd_ph26023;
    }

    public int getSlXuat_ph26023() {
        return slXuat_ph26023;
    }

    public void setSlXuat_ph26023(int slXuat_ph26023) {
        this.slXuat_ph26023 = slXuat_ph26023;
    }

    public String getNgayXuat_ph26023() {
        return ngayXuat_ph26023;
    }

    public void setNgayXuat_ph26023(String ngayXuat_ph26023) {
        this.ngayXuat_ph26023 = ngayXuat_ph26023;
    }

    public int getDgXuat_ph26023() {
        return dgXuat_ph26023;
    }

    public void setDgXuat_ph26023(int dgXuat_ph26023) {
        this.dgXuat_ph26023 = dgXuat_ph26023;
    }
}
