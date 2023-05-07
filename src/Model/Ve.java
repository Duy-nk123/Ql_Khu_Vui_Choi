/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Ve {
    private int idVe;
    private String LoaiVe;
    private int GiaVe;

    public int getIdVe() {
        return idVe;
    }

    public String getLoaiVe() {
        return LoaiVe;
    }

    public int getGiaVe() {
        return GiaVe;
    }

    public void setIdVe(int idVe) {
        this.idVe = idVe;
    }

    public void setLoaiVe(String LoaiVe) {
        this.LoaiVe = LoaiVe;
    }

    public void setGiaVe(int GiaVe) {
        this.GiaVe = GiaVe;
    }

    public Ve(int idVe, String LoaiVe, int GiaVe) {
        this.idVe = idVe;
        this.LoaiVe = LoaiVe;
        this.GiaVe = GiaVe;
    }

    public Ve(String LoaiVe, int GiaVe) {
        this.LoaiVe = LoaiVe;
        this.GiaVe = GiaVe;
    }

    public Ve(int idVe) {
        this.idVe = idVe;
    }
    
    
}
