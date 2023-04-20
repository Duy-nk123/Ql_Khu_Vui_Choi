/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Time;

/**
 *
 * @author Admin
 */
public class khuVuc {
    private int idKhu;
    private String TenKhu;
    private Time GioMoCua;
    private Time GioDongCua;
    // Getter
    public int getIdKhu() {return idKhu;}
    public String getTenKhu() {return TenKhu;}
    public Time getGioMoCua() {return GioMoCua;}
    public Time getGioDongCua() {return GioDongCua;}
    // Setter
    public void setIdKhu(int idKhu) {this.idKhu = idKhu;}
    public void setTenKhu(String tenKhu) {TenKhu = tenKhu;}
    public void setGioMoCua(Time gioMoCua) {GioMoCua = gioMoCua;}
    public void setGioDongCua(Time gioDongCua) {GioDongCua = gioDongCua;}

    public khuVuc(int idKhu, String TenKhu, Time GioMoCua, Time GioDongCua){
        this.idKhu = idKhu;
        this.TenKhu = TenKhu;
        this.GioMoCua = GioMoCua;
        this.GioDongCua = GioDongCua;
    }
    public khuVuc(String TenKhu, Time GioMoCua, Time GioDongCua){
        this.TenKhu = TenKhu;
        this.GioMoCua = GioMoCua;
        this.GioDongCua = GioDongCua;
    }
    
}
