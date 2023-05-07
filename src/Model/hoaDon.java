/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class hoaDon {
    private int idHDV;
    private String NgayLap;
    private int idNV;
    private int TongTien;

    public int getIdHDV() {
        return idHDV;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public int getIdNV() {
        return idNV;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setIdHDV(int idHDV) {
        this.idHDV = idHDV;
    }

    public void setNgayLap(String NgayLap) {
        this.NgayLap = NgayLap;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public hoaDon(int idHDV, String NgayLap, int idNV, int TongTien) {
        this.idHDV = idHDV;
        this.NgayLap = NgayLap;
        this.idNV = idNV;
        this.TongTien = TongTien;
    }

    public hoaDon(int idHDV) {
        this.idHDV = idHDV;
    }

    
}
