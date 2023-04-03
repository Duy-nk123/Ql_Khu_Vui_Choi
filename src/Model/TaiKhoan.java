/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class TaiKhoan {
    private int idTK;
    private String Username;
    private String Password;
    private int Role;

    public int getIdTK() {
        return idTK;
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
    
    public TaiKhoan(int idTK, String Username, String Password, int Role){
    this.idTK = idTK;
    this.Username = Username;
    this.Password = Password;
    this.Role = Role;
}
}
