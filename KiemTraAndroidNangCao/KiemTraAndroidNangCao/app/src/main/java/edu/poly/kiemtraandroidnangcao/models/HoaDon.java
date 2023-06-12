package edu.poly.kiemtraandroidnangcao.models;

import java.util.Date;

public class HoaDon {
    private int maHd_ph26023;
    private Date ngayXuat_ph26023;

    public HoaDon() {
    }

    public HoaDon(Date ngayXuat_ph26023) {
        this.ngayXuat_ph26023 = ngayXuat_ph26023;
    }

    public HoaDon(int maHd_ph26023, Date ngayXuat_ph26023) {
        this.maHd_ph26023 = maHd_ph26023;
        this.ngayXuat_ph26023 = ngayXuat_ph26023;
    }

    public int getMaHd_ph26023() {
        return maHd_ph26023;
    }

    public void setMaHd_ph26023(int maHd_ph26023) {
        this.maHd_ph26023 = maHd_ph26023;
    }

    public Date getNgayXuat_ph26023() {
        return ngayXuat_ph26023;
    }

    public void setNgayXuat_ph26023(Date ngayXuat_ph26023) {
        this.ngayXuat_ph26023 = ngayXuat_ph26023;
    }

}
