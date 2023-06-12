package edu.poly.ontap1.models;

public class ChiTiet {
    private int maCTHD_ph26023;
    private int maSp_ph26023;
    private int maHd_ph26023;

    public ChiTiet() {
    }

    public ChiTiet(int maSp_ph26023, int maHd_ph26023) {
        this.maSp_ph26023 = maSp_ph26023;
        this.maHd_ph26023 = maHd_ph26023;
    }

    public ChiTiet(int maCTHD_ph26023, int maSp_ph26023, int maHd_ph26023) {
        this.maCTHD_ph26023 = maCTHD_ph26023;
        this.maSp_ph26023 = maSp_ph26023;
        this.maHd_ph26023 = maHd_ph26023;
    }

    public int getMaCTHD_ph26023() {
        return maCTHD_ph26023;
    }

    public void setMaCTHD_ph26023(int maCTHD_ph26023) {
        this.maCTHD_ph26023 = maCTHD_ph26023;
    }

    public int getMaSp_ph26023() {
        return maSp_ph26023;
    }

    public void setMaSp_ph26023(int maSp_ph26023) {
        this.maSp_ph26023 = maSp_ph26023;
    }

    public int getMaHd_ph26023() {
        return maHd_ph26023;
    }

    public void setMaHd_ph26023(int maHd_ph26023) {
        this.maHd_ph26023 = maHd_ph26023;
    }
}
