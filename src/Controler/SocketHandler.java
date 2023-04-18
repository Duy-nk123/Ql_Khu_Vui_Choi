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
import Model.troChoi;
import java.util.ArrayList;
import java.util.List;


public class SocketHandler implements Runnable {
    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;
    private int ID_Server;
    private int RoleDF = 0;
    

    public SocketHandler() {
        
    }
    public List<troChoi> getgame(String[] message){
        List<troChoi> game = new ArrayList<>();
        System.out.println("chua get");
        for(int i=1; i<message.length; i=i+4){
            game.add(new troChoi(Integer.parseInt(message[i]), 
                Integer.parseInt(message[i+1]), 
                message[i+2], 
                Integer.parseInt(message[i+3])
            ));
            
            System.out.println("duy da ch0oi"+ game);
        }
        return game;
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
                    System.out.println("Đăng nhập thành công");
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
                    System.out.println("hhh1");
                    if(Client.AdminForm!=null){
                        System.out.println("hhh1");
                        Client.AdminForm.setDataToTableTroChoi(getgame(messageSplit));
                        System.out.println("hhh");
                    }
                }
               
                // get game
                
//                if(messageSplit[0].equals("return-get-game")){
//                    System.out.println("duy ok");
//                    Client.AdminForm.setDataToTableTroChoi(getgame(messageSplit));
//                    
//                }
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
//    public troChoi getGameFromString(int start, String[] message) {
//        return new troChoi(Integer.parseInt(message[start]), 
//                Integer.parseInt(message[start+1]), 
//                message[start+2], 
//                Integer.parseInt(message[start+3])
//        );
//    }
    
    

    public void write(String message) throws IOException {
        os.write(message);
        os.newLine();
        os.flush();
    }
}
