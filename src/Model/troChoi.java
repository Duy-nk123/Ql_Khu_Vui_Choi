/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class troChoi {
    private int idGame;
    private int idKhu;
    private String gameName;
    private int status;
  

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdKhu() {
        return idKhu;
    }

    public void setIdKhu(int idKhu) {
        this.idKhu = idKhu;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public troChoi(int idGame, int idKhu, String gameName, int status) {
        this.idGame = idGame;
        this.idKhu = idKhu;
        this.gameName = gameName;
        this.status = status;
    }

   
    
}
