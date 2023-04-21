/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class dichVu {
    private int idDichvu;
    private String tenDV;
    private int giaDV;
    private int idKhu;

    public int getIdDichvu() {
        return idDichvu;
    }

    public String getTenDV() {
        return tenDV;
    }

    public int getGiaDV() {
        return giaDV;
    }

    public int getIdKhu() {
        return idKhu;
    }

    public void setIdDichvu(int idDichvu) {
        this.idDichvu = idDichvu;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public void setGiaDV(int giaDV) {
        this.giaDV = giaDV;
    }

    public void setIdKhu(int idKhu) {
        this.idKhu = idKhu;
    }

    public dichVu(int idDichvu, String tenDV, int giaDV, int idKhu) {
        this.idDichvu = idDichvu;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.idKhu = idKhu;
    }
    
    

}
