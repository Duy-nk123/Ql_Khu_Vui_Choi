package Controler;

import Model.TaiKhoan;
import Model.troChoi;
import View.adminForm;
import View.login;
import View.serviceStaffView;
import View.staffView;
import View.techniciansMenu;
import javax.swing.JFrame;

public class Client {
 
    
     public enum View{
       LOGIN,
       ADMIN,
       SERVICESTAFF,
       STAFF,
       TECHNISTANS
    }
     // CÁC FORM GIAO DIỆN
    public static login Login;
    public static adminForm AdminForm;
    public static serviceStaffView ServiceStaff;
    public static staffView Staff;
    public static techniciansMenu Technician;
    
    
    public static TaiKhoan taikhoan;
    public static troChoi trochoi;
    public static SocketHandler socketHandler;
    
    
//    public static JFrame getVisibleJFrame(){
//        
//        if(AdminForm!=null&&AdminForm.isVisible()){
//            return AdminForm;
//        }
//        return Login;
//    }
    public void initView() {
        Login = new login();
        Login.setVisible(true);
        socketHandler = new SocketHandler();
        Thread threadSocket = new Thread(socketHandler);
        threadSocket.start();
    }
    public static void openView(View viewName){
        if(viewName != null){
            switch(viewName){
                case LOGIN:
                    Login = new login();
                    Login.setVisible(true);
                    break;
                case ADMIN:
                    AdminForm = new adminForm();
                    AdminForm.setVisible(true);
                    break;
                case SERVICESTAFF:
                    ServiceStaff = new serviceStaffView();
                    ServiceStaff.setVisible(true);
                    break;
                case STAFF:
                    Staff = new staffView();
                    Staff.setVisible(true);
                    break;
                case TECHNISTANS:
                    Technician = new techniciansMenu();
                    Technician.setVisible(true);
                    break;
            }
        }
    }
    public static void closeView(View viewName){
        if(viewName != null){
            switch(viewName){
                case LOGIN:
                    Login.dispose();
                    break;
                case ADMIN:
                    AdminForm.dispose();
                    break;
                case SERVICESTAFF:
                    ServiceStaff.dispose();
                    break;
                case STAFF:
                    Staff.dispose();
                    break;
                case TECHNISTANS:
                    Technician.dispose();
                    break;
            }
        }
    }
   

    public static void main(String[] args) {
        new Client().initView();
    }
}
