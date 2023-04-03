package Controler;

import Model.TaiKhoan;
import View.login;
import javax.swing.JFrame;

public class Client {
    public static TaiKhoan taikhoan;
    public static login login;
    public static SocketHandler socketHandler;

    public void initView() {
        login = new login();
        login.setVisible(true);
        socketHandler = new SocketHandler();
        Thread threadSocket = new Thread(socketHandler);
        threadSocket.start();
    }

    public static void main(String[] args) {
        new Client().initView();
    }
}
