/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class staff {
    private int idNV;
    private String hoTen;
    private String ngaySinh;
    private String SDT;
    private String diaChi;
    private String gioiTinh;
    private String chucVu;
    private String loaiVe;
    private String soLuong;
    private String ngayTao;
    

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public void setLoaiVe(String loaiVe) {
        this.loaiVe = loaiVe;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
    

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    

    public int getIdNV() {
        return idNV;
    }

    public String getLoaiVe() {
        return loaiVe;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getChucVu() {
        return chucVu;
    }
    

    public staff(int idNV, String hoTen, String ngaySinh, String SDT, String diaChi, String gioiTinh, String chucVu) {
        this.idNV = idNV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
    }
    
    
}
