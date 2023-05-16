package Controler;



import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Model.TaiKhoan;
import Controler.Client;
import Model.User;
import Model.Ve;
import Model.dichVu;
import Model.hoaDon;
import Model.khuVuc;
import Model.staff;
import Model.troChoi;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class SocketHandler implements Runnable {
    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;
    private int ID_Server;
    private int RoleDF = 0;
    public int idTTCN;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
    

    public SocketHandler() {
        
    }
    public List<troChoi> getgame(String[] message){
        List<troChoi> game = new ArrayList<>();
        for(int i=1; i<message.length; i=i+4){
            game.add(new troChoi(Integer.parseInt(message[i]), 
                Integer.parseInt(message[i+1]), 
                message[i+2], 
                Integer.parseInt(message[i+3])
            ));
        }
        return game;
    }
    public List<TaiKhoan> getUser(String[] message){
    List<TaiKhoan> user = new ArrayList<>();
    for(int i=1; i<message.length; i=i+11){
        user.add(new TaiKhoan(
            Integer.parseInt(message[i]), 
            message[i+1],
            message[i+2],
            Integer.parseInt(message[i+3]),
            message[i+4], 
            message[i+5], 
            message[i+6],
            Integer.parseInt(message[i+7]),
            message[i+8],
            Integer.parseInt(message[i+9]),
            Integer.parseInt(message[i+10])   
        ));
    }
    return user;
}

    public List<khuVuc> getArena(String[] message){
    List<khuVuc> arena = new ArrayList<>();
    for(int i=1; i<message.length; i=i+4){
        arena.add(new khuVuc(
       Integer.parseInt(message[i]), 
            message[i+1],
     Time.valueOf(message[i+2]),
   Time.valueOf(message[i+3])
             
        ));
    }
    return arena;
}
     public List<Ve> getVe(String[] message){
    List<Ve> ve = new ArrayList<>();
    for(int i=1; i<message.length; i=i+3){
        ve.add(new Ve(
       Integer.parseInt(message[i]), 
            message[i+1],
     Integer.parseInt(message[i+2])  
        ));
    }
    return ve;
}
     public List<hoaDon> getIDHDVe(String[] message){
     List<hoaDon> hd = new ArrayList<>();
   
        hd.add(new hoaDon(
       Integer.parseInt(message[1]) 
        ));
    
    return hd;
}
    
     public List<dichVu> getservice(String[] message){
    List<dichVu> service = new ArrayList<>();
    for(int i=1; i<message.length; i=i+4){
        service.add(new dichVu(
        Integer.parseInt(message[i]),
                message[i+1],
                Integer.parseInt(message[i+2]),
                Integer.parseInt(message[i+3])
        ));
    }
    return  service;
}
     
     public List<User> getTTCN(String[] message){
    List<User> TK = new ArrayList<>();
    for(int i=1; i<message.length; i=i+11){
        TK.add(new User(
                Integer.parseInt(message[i]),
                message[i+1],
                message[i+2],
                Integer.parseInt(message[i+3]),
                message[i+4],
                message[i+5],
                message[i+6],
                Integer.parseInt(message[i+7]),
                message[i+8],   
                Integer.parseInt(message[i+9]),
                Integer.parseInt(message[i+10])
        ));
    }
    return  TK;
}
     
     public List<hoaDon> getBillAdmin(String[] message){
    List<hoaDon> TK = new ArrayList<>();
    for(int i=1; i<message.length; i=i+4){
        TK.add(new hoaDon(
                Integer.parseInt(message[i]),
                 message[i+1],
                Integer.parseInt(message[i+2]),
                Integer.parseInt(message[i+3])         
        ));
    }
    return  TK;
}
     
     

     
     
    @Override
    public void run() {
        try {
            // Gửi yêu cầu kết nối tới Server đang lắng nghe
            socketOfClient = new Socket("localhost", 7777);
            System.out.println("Kết nối thành công!");
            // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            String message;
            while (true) {
                message = is.readLine();
                System.out.println(message);
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split("=");
                if (messageSplit[0].equals("server-send-id")) {
                    ID_Server = Integer.parseInt(messageSplit[1]);
                }

                //Đăng nhập thành công
                if (messageSplit[0].equals("login-success")) {
                    idTTCN = Integer.parseInt(messageSplit[1]);
                 
                    try {
                    Client.socketHandler.write("show-user-info"+ "="+  idTTCN);
                    } catch (IOException ex) {
                    throw new RuntimeException(ex);
                    }
                    Client.taikhoan = getUserFromString(1,messageSplit);
                  //   Client. = getUserFromString(1,messageSplit);
                    Client.Login.loginSuccess();
                    
                    
                }
                //Thông tin tài khoản sai
                if (messageSplit[0].equals("wrong-user")) {
                    Client.Login.wrongUser();
                }
                 // get game
                if(messageSplit[0].equals("return-get-game")){
                    if(Client.AdminForm!=null){
                        Client.AdminForm.setDataToTableTroChoi(getgame(messageSplit));
                    }
                    if(Client.Technician!=null){
                        Client.Technician.setDataToTableTroChoi(getgame(messageSplit));
                    }
                }
                // delete game
                if(messageSplit[0].equals("del-game-success")){
                    if(Client.AdminForm!=null){
                       JOptionPane.showMessageDialog(Client.AdminForm, "Xóa Trò Chơi Thành Công");
                       
                    }
                }
                // find game 
                 if(messageSplit[0].equals("return-find-game")){
                    if(Client.AdminForm!=null){
                        Client.AdminForm.setDataToTableTroChoi(getgame(messageSplit));
                    }
                    if(Client.Technician!=null){
                        Client.Technician.setDataToTableTroChoi(getgame(messageSplit));
                    }
                }
                 // get User
                if(messageSplit[0].equals("return-get-User")){
                    if(Client.AdminForm!=null){
                       Client.AdminForm.setTableUser(getUser(messageSplit));
                       
                    }
                }
                
                // add user
                 if(messageSplit[0].equals("add-user-success")){
                    if(Client.AdminForm!=null){
                       JOptionPane.showMessageDialog(Client.AdminForm, "Thêm Tài Khoản Thành Công");
                       
                    }
                }
                 if(messageSplit[0].equals("duplicate-username")){
                    if(Client.AdminForm!=null){
                       JOptionPane.showMessageDialog(Client.AdminForm, "Thêm Tài Khoản Thất Bại. Tên Tài Khoản không được trùng");
                       
                    }
                }
                // find user
                if(messageSplit[0].equals("return-find-user")){
                    if(Client.AdminForm!=null){
                       Client.AdminForm.setTableUser(getUser(messageSplit));
                       
                    }
                }
                
                   // update user
                if(messageSplit[0].equals("update-staff-success")){
                    if(Client.AdminForm!=null){
                        JOptionPane.showMessageDialog(Client.AdminForm, "Cập Nhật Thông Tin Nhân Viên Thành Công!");
                       
                    }
                }       
                
                // xoa user
                if(messageSplit[0].equals("del-user-success")){
                    if(Client.AdminForm!=null){
                        JOptionPane.showMessageDialog(Client.AdminForm, "Xóa Nhân Viên Thành Công");
                       
                    }
                }
                 
                // show khu vuc
                 if(messageSplit[0].equals("return-get-arena")){
                    if(Client.AdminForm!=null){
                        Client.AdminForm.setJcbKhuVuc(getArena(messageSplit));   
                    }else if(Client.ServiceStaff!=null){
                        Client.ServiceStaff.setJcbKhuVuc(getArena(messageSplit));
                    }
                    else if(Client.Staff!=null){
                        Client.Staff.setJcbKhuVuc(getArena(messageSplit));
                       
                    }
                    else if(Client.Technician!=null){
                        Client.Technician.setJcbKhuVuc(getArena(messageSplit));
                       
                    }
                }
                // add khu vực
                if(messageSplit[0].equals("add-zone-success")){
                    if(Client.AdminForm!=null){
                        JOptionPane.showMessageDialog(Client.AdminForm, "Thêm Khu Vực Thành Công");
                       
                    }
                }
                // update khu vục
                 if(messageSplit[0].equals("change-arena-comple")){
                    if(Client.AdminForm!=null){
                        JOptionPane.showMessageDialog(Client.AdminForm, "Sửa Khu Vực Thành Công");
                       
                    }
                }
                 
                // xóa khu vực
                if(messageSplit[0].equals("del-area-success")){
                    if(Client.AdminForm!=null){
                        JOptionPane.showMessageDialog(Client.AdminForm, "Xóa Khu Vực Thành Công");
                       
                    }
                }
                
                // Tìm Kiếm Khu Vực
                if(messageSplit[0].equals("return-find-area")){
                    if(Client.AdminForm!=null){
                        Client.AdminForm.setJcbKhuVuc(getArena(messageSplit));
                       
                    }
                }
                
                
               // hiển thị dịch vụ
                if(messageSplit[0].equals("return-get-service")){
                    if(Client.AdminForm!=null){
                        Client.AdminForm.setTableDichVu(getservice(messageSplit));
                    }
                    if(Client.ServiceStaff!=null){
                        Client.ServiceStaff. setJcbDV(getservice(messageSplit));
                    }
                }
                // add dịch vụ
                if(messageSplit[0].equals("add-service-success")){
                    if(Client.AdminForm!=null){
                         JOptionPane.showMessageDialog(Client.AdminForm, "Thêm Dịch Vụ Thành Công"); 
                    }
                }
                  
                // xoa dich vu
                if(messageSplit[0].equals("del-service-success")){
                    if(Client.AdminForm!=null){
                         JOptionPane.showMessageDialog(Client.AdminForm, "Xóa Dịch Vụ Thành Công"); 
                    }
                }
                // tim kiem dich vu
                if(messageSplit[0].equals("return-find-service")){
                    if(Client.AdminForm!=null){
                        Client.AdminForm.setTableDichVu(getservice(messageSplit));
                    }
                    if(Client.ServiceStaff!=null){
                        Client.ServiceStaff. setTableDichVu(getservice(messageSplit));
                    }
                }
                // cập nhật dịch vụ
                if(messageSplit[0].equals("change-service-complete")){
                    if(Client.AdminForm!=null){
                         JOptionPane.showMessageDialog(Client.AdminForm, "Cập Nhật Dịch Vụ Thành Công"); 
                    }
                }
                
                // lấy id hoa don dich vu
                if(messageSplit[0].equals("add-service-bill-success")){

                    if(Client.ServiceStaff!=null){
                          Client.ServiceStaff.IDHDDV.add(new String(messageSplit[1]));
                          Client.ServiceStaff.addCTHDDV();
                          
                          
                    }
                }
                
                // show ve
                 if(messageSplit[0].equals("return-get-ticket")){
                    if(Client.AdminForm!=null){
                        Client.AdminForm. setDataToTableVe(getVe(messageSplit));
                    }
                     if(Client.Staff!=null){
                        Client.Staff. setJcbVe(getVe(messageSplit));
                    }
                }
                // tim kiem ve
                
                 if(messageSplit[0].equals("return-find-ticket")){
                    if(Client.AdminForm!=null){
                        Client.AdminForm. setDataToTableVe(getVe(messageSplit));
                    }
                    if(Client.Staff!=null){
                        Client.Staff. setDataToTableVe(getVe(messageSplit));
//                        Client.Staff.addCTDHV();
                        
                    }
                }
                 
                // cap nhat ve
                if(messageSplit[0].equals("change-ticket-complete")){
                    if(Client.AdminForm!=null){
                         JOptionPane.showMessageDialog(Client.AdminForm, "Cập Nhật Vé Thành Công"); 
                    }
                }
                // xoa vé
                if(messageSplit[0].equals("del-ticket-success")){
                    if(Client.AdminForm!=null){
                         JOptionPane.showMessageDialog(Client.AdminForm, "Xóa Vé Thành Công"); 
                    }
                }
                
                // thêm vé
                if(messageSplit[0].equals("add-ticket-success")){
                    if(Client.AdminForm!=null){
                         JOptionPane.showMessageDialog(Client.AdminForm, "Thêm Vé Thành Công"); 
                    }
                }
                // Lấy ID hóa đơn vé
                
                if(messageSplit[0].equals("add-ticket-bill-success")){

                    
                    if(Client.Staff!=null){
                          Client.Staff.IDHDV.add(new String(messageSplit[1]));
                          
                          
                          
                    }
                }
                // thông tin cá nhân
                 if(messageSplit[0].equals("return-find-user")){
                    if(Client.AdminForm!=null){
                         Client.AdminForm. setTTCN(getTTCN(messageSplit));
                    }else  if(Client.ServiceStaff!=null){
                         Client.ServiceStaff. setTTCN(getTTCN(messageSplit));
                    }else  if(Client.Staff!=null){
                         Client.Staff. setTTCN(getTTCN(messageSplit));
                    }else  if(Client.Technician!=null){
                         Client.Technician. setTTCN(getTTCN(messageSplit));
                    }
                }
                 
                 // update tt ca nhan
                 if(messageSplit[0].equals("update-staff-info-success")){
                     
                      
                    if(Client.AdminForm!=null){
                        JOptionPane.showMessageDialog(Client.AdminForm, "Cập Nhật Thông Tin Thành Công"); 
                         try {
                            Client.socketHandler.write("show-user-info"+ "="+  idTTCN);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } 
                
                    }else if(Client.ServiceStaff!=null){
                        JOptionPane.showMessageDialog(Client.ServiceStaff, "Cập Nhật Thông Tin Thành Công"); 
                         try {
                            Client.socketHandler.write("show-user-info"+ "="+  idTTCN);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } 
                
                    }else if(Client.Staff!=null){
                        JOptionPane.showMessageDialog(Client.Staff, "Cập Nhật Thông Tin Thành Công"); 
                         try {
                            Client.socketHandler.write("show-user-info"+ "="+  idTTCN);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } 
                
                    }else if(Client.Technician!=null){
                        JOptionPane.showMessageDialog(Client.Technician, "Cập Nhật Thông Tin Thành Công"); 
                         try {
                            Client.socketHandler.write("show-user-info"+ "="+  idTTCN);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } 
                
                    }
                }
                 
                 // show thong ke vé
                
                  if(messageSplit[0].equals("show-bill-admin")){
                    if(Client.AdminForm!=null){
                         Client.AdminForm. setDataToTableThongKe(getBillAdmin(messageSplit));
                    }else if(Client.ServiceStaff!=null){
                         Client.ServiceStaff. setDataToTableThongKe(getBillAdmin(messageSplit));
                    }else if(Client.Staff!=null){
                         Client.Staff. setDataToTableThongKe(getBillAdmin(messageSplit));
                         
                    }
                    
                }        
                
                
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public TaiKhoan getUserFromString(int start, String[] message) {
        return new TaiKhoan(Integer.parseInt(message[start]),
                message[start+1],
                message[start+2],
                Integer.parseInt(message[start + 3])
        );
    }
  
    
    

    public void write(String message) throws IOException {
        os.write(message);
        os.newLine();
        os.flush();
    }
}
