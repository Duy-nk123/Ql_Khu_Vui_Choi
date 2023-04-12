/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class TaiKhoan {
    private int idUser;
    private String Username;
    private String Password;
    private int Role;
    private String HoTen;
    private Date NgaySinh;
    private String SDT;
    private int GioiTinh;
    private String DiaChi;
    private int Luong;
    private int idKhu;

    public int getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public int getRole() {
        return Role;
    }

    public String getHoTen() {
        return HoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public int getLuong() {
        return Luong;
    }

    public int getIdKhu() {
        return idKhu;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setLuong(int Luong) {
        this.Luong = Luong;
    }

    public void setIdKhu(int idKhu) {
        this.idKhu = idKhu;
    }

    public TaiKhoan(int idUser, String Username, String Password, int Role) {
        this.idUser = idUser;
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
    }

   
}
