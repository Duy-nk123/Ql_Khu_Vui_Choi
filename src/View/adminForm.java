/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controler.Client;
import Model.TaiKhoan;
import Model.User;
import Model.Ve;
import Model.dichVu;
import Model.hoaDon;
import Model.khuVuc;
import Model.troChoi;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class adminForm extends javax.swing.JFrame {

    private List<troChoi> listTroChoiStatics;
    private List<TaiKhoan> listTaiKhoanStatics;
    private List<User> listTTStatics;
    private List<khuVuc> listKhuVucStatics;
    private List<dichVu> listDichVuStatics;
    private List<Ve> listVeStatics;
    private List<hoaDon> listHoaDonStatics;
    private DefaultTableModel tableModel;

    // nhan vien
    // get thong tin nhan vien
    String txtHtNv;
    String txtTkNv;
    String txtMkNv;
    String jcbKvNv;
    String jcbCvNv;
    // get khu vuc
    String idKhu;
    String NameKhu;
    String GMC;
    String GDC;
    // get dich vu
    String dv;
    String giaDV;
    String khuDV;
    String idDV;
    // get ve
    String idVe;
    String loaiVe;
    String giaVe;

    /**
     * Creates new form adminForm
     */
    int logoutID;
    String IDNV;
    int sumTK;
    // lấy ngày 
    Date currentDate = new Date();
    int day = currentDate.getDate();
    int month = currentDate.getMonth() + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để lấy tháng thực tế
    int year = currentDate.getYear() + 1900; // Năm được tính từ 1900, nên cộng thêm 1900 để lấy năm thực tế
    String ngayHienTai = (year + "-" + month + "-" + day);

    // get game
    public void sendgetgame() {

        // get game
        try {
            Client.socketHandler.write("show-game");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void sendGetUser() {
        try {
            Client.socketHandler.write("show-user");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void sendGetKhu() {
        try {
            Client.socketHandler.write("show-arena");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void sendGetDichVu() {
        try {
            Client.socketHandler.write("show-service");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void sendGetVe() {
        try {
            Client.socketHandler.write("show-ticket");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void sendGetGame() {
        try {
            Client.socketHandler.write("show-game");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void xoaTrangNV() {
        txtHTNV.setText("");
        txtTKNV.setText("");
        txtMKNV.setText("");

    }

    public void xoaTrangGame() {
        txtIDGame.setText("");
        txtNameGame.setText("");
    }

    public void xoaTrangKhu() {
        txtIDKhu.setText("");
        txtTenKhu.setText("");
        txtGMC.setText("");
        txtGDC.setText("");
    }

    public void xoaTrangDichVu() {
        txtDV.setText("");
        txtGiaDV.setText("");
        txtIDDV.setText("");
    }

    public void xoaTrangVe() {
        txtGiaVe.setText("");
        txtLoaiVe.setText("");
        txtIDVe.setText("");
    }

    public adminForm() {
        initComponents();

        sendgetgame();
        sendGetUser();
        sendGetKhu();
        sendGetDichVu();
        sendGetVe();
        sendGetGame();

        txtIDGame.setEditable(false);// không chỉnh sửa được txt 
        txtIDKhu.setEditable(false);
        txtIDVe.setEditable(false);
        txtIDDV.setEditable(false);
        MNV.setEditable(false);
        

    }

    public void BTNFalse() {
        btnThemVe6.setEnabled(false);

    }

    public void BTNTrue() {
        btnThemVe6.setEnabled(true);
        jButton26.setEnabled(true);
    }

    public void setTableUser(List<TaiKhoan> user) {
        tableModel = (DefaultTableModel) tableUser.getModel();
        this.listTaiKhoanStatics = user;
        tableModel.setRowCount(0);
        int i = 0;
        for (TaiKhoan Users : listTaiKhoanStatics) {
            String cv = null;
            if (Users.getRole() == 0) {
                cv = " ADMIN";
            } else if (Users.getRole() == 1) {
                cv = "Nhân Viên Bán Vé";
            } else if (Users.getRole() == 2) {
                cv = "Nhân Viên Dịch Vụ";
            } else if (Users.getRole() == 3) {
                cv = "Kĩ Thuật Viên";
            }
            tableModel.addRow(new Object[]{
                Users.getIdUser(),
                Users.getHoTen(),
                cv,
                Users.getIdKhu(),
                Users.getSDT()
            });
            i++;
            
        }
        
         
    }

    public void setTTCN(List<User> TTCN) {
        this.listTTStatics = TTCN;
        int i = 0;
        String cv = null;
        int kv;
        for (User TT : listTTStatics) {
            String gioitinh = Integer.toString(TT.getGioiTinh());
            if (gioitinh.equals("1")) {
                gioitinh = "Nam";
            } else {
                gioitinh = "Nữ";
            }

            if (TT.getRole() == 0) {
                cv = " ADMIN";
            } else if (TT.getRole() == 1) {
                cv = "Nhân Viên Bán Vé";
            } else if (TT.getRole() == 2) {
                cv = "Nhân Viên Dịch Vụ";
            } else if (TT.getRole() == 3) {
                cv = "Kĩ Thuật Viên";
            }
            MNV.setText(Integer.toString(TT.getIdUser()));

            HT.setText(TT.getHoTen());
            ngaysinh.setText(TT.getNgaySinh());
            sodienthoai.setText(TT.getSDT());
            diachi.setText(TT.getDiaChi());
            for (i = 0; i < jCbGioiTinh.getItemCount(); i++) {
                if (gioitinh.equals(String.valueOf(jCbGioiTinh.getItemAt(i)))) {
                    jCbGioiTinh.setSelectedIndex(i);
                }
            }
            for (i = 0; i < jCbChucVu.getItemCount(); i++) {
                if (cv.equals(String.valueOf(jCbChucVu.getItemAt(i)))) {
                    jCbChucVu.setSelectedIndex(i);
                }
            }
            kv = TT.getIdKhu();
            for (i = 0; i < jcbKhuTTCN.getItemCount(); i++) {
                if (Integer.toString(kv).equals(String.valueOf(jcbKhuTTCN.getItemAt(i)))) {
                    jcbKhuTTCN.setSelectedIndex(i);
                }
            }
            TK.setText(TT.getUsername());
            MK.setText(TT.getPassword());
            logoutID = TT.getIdUser();
        }

        i++;
    }

    public void setTableDichVu(List<dichVu> service) {
        tableModel = (DefaultTableModel) tbdichvu.getModel();
        this.listDichVuStatics = service;
        tableModel.setRowCount(0);
        int i = 0;
        for (dichVu services : listDichVuStatics) {
            tableModel.addRow(new Object[]{
                services.getIdDichvu(),
                services.getTenDV(),
                services.getIdKhu(),
                services.getGiaDV()
            });
            i++;
        }

    }

    public void setDataToTableTroChoi(List<troChoi> game) {
        String TTGame;

        tableModel = (DefaultTableModel) tbTroChoi3.getModel();
        this.listTroChoiStatics = game;
        tableModel.setRowCount(0);
        int i = 0;
        for (troChoi games : listTroChoiStatics) {
            int ttGame = games.getStatus();
            if (ttGame == 0) {
                TTGame = "Tốt";
            } else {
                TTGame = "Bảo Trì";
            }
            tableModel.addRow(new Object[]{
                games.getIdGame(),
                games.getIdKhu(),
                games.getGameName(),
                TTGame
            });
            i++;
        }

    }

    public void TK_print() {
        Object quy = jComboBox8.getSelectedItem();
        Object loai = BLHD.getSelectedItem();
        try {
            bill1.setText("\t       Khu Vui Chơi TNIT \n");
            bill1.setText(bill1.getText() + "\t       Thống kê:" + loai + "  (" + quy + ")" + "\n");
            bill1.setText(bill1.getText() + "\t       Tên NV:" + HT.getText() + "  (" + MNV.getText() + ")" + "\n");
            bill1.setText(bill1.getText() + "\t       Điện Thoại:+84 3689999999, \n");
            bill1.setText(bill1.getText() + "\t       Ngày Lập:" + ngayHienTai + "\n");

            bill1.setText(bill1.getText() + "---------------------------------------------------------------------------------------------\n");
            bill1.setText(bill1.getText() + "\tID       IDNhân Viên\t Ngày Tạo\tThành Tiền \n");
            bill1.setText(bill1.getText() + "---------------------------------------------------------------------------------------------\n");

            DefaultTableModel df = (DefaultTableModel) ThongKe.getModel();
            for (int i = 0; i < ThongKe.getRowCount(); i++) {

                String name = df.getValueAt(i, 0).toString();
                String qt = df.getValueAt(i, 1).toString();
                String prct = df.getValueAt(i, 2).toString();
                String prc = df.getValueAt(i, 3).toString();

                bill1.setText(bill1.getText() + "\t" + name + "\t" + qt + "\t" + prct + "\t" + prc + " \n");

            }
            bill1.setText(bill1.getText() + "---------------------------------------------------------------------------------------------\n");
            bill1.setText(bill1.getText() + "\t Tổng Tiền: " + sumTK + ".000 VND\n");
            bill1.setText(bill1.getText() + "===================================================\n");
            bill1.setText(bill1.getText() + "\tThanks For Your Business...!" + "\n");
            bill1.setText(bill1.getText() + "---------------------------------------------------------------------------------------------\n");
            bill1.setText(bill1.getText() + "\tSoftware by TNIT" + "\n");

            bill1.print();

        } catch (PrinterException ex) {

            Logger.getLogger(staffView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tongThongKe() {
        sumTK = 0;
        // Tính tổng các giá trị trong cột thứ i
        int i = 3; // thay bằng số thứ tự của cột bạn muốn tính tổng
        int numRows = ThongKe.getRowCount();
        for (int row = 0; row < numRows; row++) {
            sumTK += Integer.parseInt(ThongKe.getValueAt(row, i).toString());
        }
    }

    public void setDataToTableVe(List<Ve> ve) {
        tableModel = (DefaultTableModel) tbBanVe.getModel();
        this.listVeStatics = ve;
        tableModel.setRowCount(0);

        int i = 0;
        for (Ve ves : listVeStatics) {
            tableModel.addRow(new Object[]{
                ves.getIdVe(),
                ves.getLoaiVe(),
                ves.getGiaVe()
            });
            i++;
        }
    }

    public void setDataToTableThongKe(List<hoaDon> hoadon) {

        tableModel = (DefaultTableModel) ThongKe.getModel();
        this.listHoaDonStatics = hoadon;
        tableModel.setRowCount(0);

        int i = 0;
        for (hoaDon hd : listHoaDonStatics) {
            tableModel.addRow(new Object[]{
                hd.getIdHDV(),
                hd.getIdNV(),
                hd.getNgayLap(),
                hd.getTongTien()
            });
            i++;
        }
    }

    public void setJcbKhuVuc(List<khuVuc> arena) {

        jCbKhuGame.removeAllItems();
        jcbKVNV.removeAllItems();
        jcbKhuDV.removeAllItems();
        jcbKhuTTCN.removeAllItems();
        tableModel = (DefaultTableModel) tbleKhuVuc.getModel();
        tableModel.setRowCount(0);

        this.listKhuVucStatics = arena;
        int i = 0;
        for (khuVuc arenas : listKhuVucStatics) {
            jCbKhuGame.addItem(String.valueOf(arenas.getIdKhu()));
            jcbKVNV.addItem(String.valueOf(arenas.getIdKhu()));
            jcbKhuDV.addItem(String.valueOf(arenas.getIdKhu()));
            jcbKhuTTCN.addItem(String.valueOf(arenas.getIdKhu()));
            tableModel.addRow(new Object[]{
                arenas.getIdKhu(),
                arenas.getTenKhu(),
                arenas.getGioMoCua(),
                arenas.getGioDongCua()
            });

            i++;
        }

    }

    public void getKhuVuc() {
        idKhu = txtIDKhu.getText();
        NameKhu = txtTenKhu.getText();
        GMC = txtGMC.getText();
        GDC = txtGDC.getText();
    }

    public void getDichVu() {
        giaDV = txtGiaDV.getText();
        dv = txtDV.getText();
        idDV = txtIDDV.getText();
        khuDV = jcbKhuDV.getSelectedItem().toString();
    }

    public void getVe() {
        idVe = txtIDVe.getText();
        loaiVe = txtLoaiVe.getText();
        giaVe = txtGiaVe.getText();
    }

    public void getTTNhanVien() {
        txtHtNv = txtHTNV.getText();
        txtTkNv = txtTKNV.getText();
        txtMkNv = txtMKNV.getText();
        jcbKvNv = jcbKVNV.getSelectedItem().toString();

        jcbCvNv = jcbCVNV.getSelectedItem().toString();
        if (jcbCvNv.equals("ADMIN")) {
            jcbCvNv = "0";
        } else if (jcbCvNv.equals("Nhân Viên Bán Vé")) {
            jcbCvNv = "1";
        } else if (jcbCvNv.equals("Nhân Viên Dịch Vụ")) {
            jcbCvNv = "2";
        } else if (jcbCvNv.equals("Kĩ Thuật Viên")) {
            jcbCvNv = "3";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBanVe = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnThemVe = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtLoaiVe = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtGiaVe = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtIDVe = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        btnThemVe1 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ThongKe = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        BLHD = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        bill1 = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jcbKhuTTCN = new javax.swing.JComboBox<>();
        diachi = new javax.swing.JTextField();
        jCbChucVu = new javax.swing.JComboBox<>();
        jCbGioiTinh = new javax.swing.JComboBox<>();
        sodienthoai = new javax.swing.JTextField();
        ngaysinh = new javax.swing.JTextField();
        HT = new javax.swing.JTextField();
        MNV = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        MK = new javax.swing.JTextField();
        TK = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txtDV = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtGiaDV = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jcbKhuDV = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        txtIDDV = new javax.swing.JTextField();
        btnThemVe8 = new javax.swing.JButton();
        btnThemVe9 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbdichvu = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        btnThemVe4 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        txtTenKhu = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtGMC = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtGDC = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtIDKhu = new javax.swing.JTextField();
        btnThemVe5 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbleKhuVuc = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jcbKVNV = new javax.swing.JComboBox<>();
        jcbCVNV = new javax.swing.JComboBox<>();
        txtMKNV = new javax.swing.JTextField();
        txtTKNV = new javax.swing.JTextField();
        txtHTNV = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        btnThemVe6 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        txtNameGame = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jCbKhuGame = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        jCbTinhTrang = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        txtIDGame = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        btnThemVe7 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbTroChoi3 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setRequestFocusEnabled(false);

        jPanel4.setBackground(new java.awt.Color(255, 102, 102));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        tbBanVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Loại Vé", "Giá Vé"
            }
        ));
        jScrollPane1.setViewportView(tbBanVe);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setText("Sửa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Xóa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnThemVe.setText("Thêm");
        btnThemVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVeActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel26.setText("Loại Vé");
        jLabel26.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel26.setMinimumSize(new java.awt.Dimension(110, 25));

        txtLoaiVe.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel27.setText("Giá Vé");
        jLabel27.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel27.setMinimumSize(new java.awt.Dimension(110, 25));

        txtGiaVe.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel31.setText("ID");
        jLabel31.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel31.setMinimumSize(new java.awt.Dimension(110, 25));

        txtIDVe.setPreferredSize(new java.awt.Dimension(96, 40));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDVe, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(96, 96, 96))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(134, 134, 134))
        );

        jButton8.setText("Lưu");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btnThemVe1.setText("Tìm Kiếm Theo Loại");
        btnThemVe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVe1ActionPerformed(evt);
            }
        });

        jButton15.setText("Trở Lại");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemVe1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemVe1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemVe, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Vé", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 153, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        ThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "IDNV", "Ngày Lập", "Thành Tiền"
            }
        ));
        jScrollPane2.setViewportView(ThongKe);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel28.setText("Từ Ngày");
        jLabel28.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel28.setMinimumSize(new java.awt.Dimension(110, 25));

        jTextField17.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel29.setText("Đến Ngày");
        jLabel29.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel29.setMinimumSize(new java.awt.Dimension(110, 25));

        jTextField18.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel30.setText("Quý");
        jLabel30.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel30.setMinimumSize(new java.awt.Dimension(110, 25));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quý 1", "Quý 2", "Quý 3", "Quý 4" }));
        jComboBox8.setMinimumSize(new java.awt.Dimension(108, 40));
        jComboBox8.setPreferredSize(new java.awt.Dimension(108, 40));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel40.setText("Loại Hóa Đơn");
        jLabel40.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel40.setMinimumSize(new java.awt.Dimension(110, 25));

        BLHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hóa Đơn Vé", "Hóa Đơn Dịch Vụ" }));
        BLHD.setMinimumSize(new java.awt.Dimension(108, 40));
        BLHD.setPreferredSize(new java.awt.Dimension(108, 40));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BLHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jButton2.setText("Thống Kê");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton9.setText("Thống Kê Ngày");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Thống Kê Quý");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton13.setText("In Danh Sách");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Thoát");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        bill1.setColumns(20);
        bill1.setRows(5);
        jScrollPane3.setViewportView(bill1);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Thống Kê", jPanel5);

        jPanel9.setBackground(new java.awt.Color(255, 204, 102));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel14.setText("Mã Nhân Viên");
        jLabel14.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel14.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel15.setText("Họ và Tên");
        jLabel15.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel15.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel16.setText("Ngày Sinh");
        jLabel16.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel16.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel17.setText("Số Điện Thoại");
        jLabel17.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel17.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel18.setText("Giới Tính");
        jLabel18.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel18.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel19.setText("Chức Vụ");
        jLabel19.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel19.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel20.setText("Địa chỉ ");
        jLabel20.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel20.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel21.setText("Khu");
        jLabel21.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel21.setMinimumSize(new java.awt.Dimension(110, 25));

        jcbKhuTTCN.setMinimumSize(new java.awt.Dimension(108, 40));
        jcbKhuTTCN.setPreferredSize(new java.awt.Dimension(108, 40));

        diachi.setMinimumSize(new java.awt.Dimension(96, 40));
        diachi.setPreferredSize(new java.awt.Dimension(96, 40));

        jCbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "Nhân Viên Bán Vé", "Nhân Viên Dịch Vụ", "Kĩ Thuật Viên" }));
        jCbChucVu.setMinimumSize(new java.awt.Dimension(108, 40));
        jCbChucVu.setPreferredSize(new java.awt.Dimension(108, 40));

        jCbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jCbGioiTinh.setMinimumSize(new java.awt.Dimension(108, 40));
        jCbGioiTinh.setPreferredSize(new java.awt.Dimension(108, 40));

        sodienthoai.setMinimumSize(new java.awt.Dimension(96, 40));
        sodienthoai.setPreferredSize(new java.awt.Dimension(96, 40));

        ngaysinh.setMinimumSize(new java.awt.Dimension(96, 40));
        ngaysinh.setPreferredSize(new java.awt.Dimension(96, 40));

        HT.setMinimumSize(new java.awt.Dimension(96, 40));
        HT.setPreferredSize(new java.awt.Dimension(96, 40));

        MNV.setPreferredSize(new java.awt.Dimension(96, 40));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setText("Cập Nhật");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton12.setText("Đăng Xuất");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        jLabel22.setBackground(new java.awt.Color(102, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/avatarNhanVien.jpg"))); // NOI18N

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel32.setText("Tài Khoản");
        jLabel32.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel32.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel34.setText("Mật Khẩu");
        jLabel34.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel34.setMinimumSize(new java.awt.Dimension(110, 25));

        MK.setMinimumSize(new java.awt.Dimension(96, 40));
        MK.setPreferredSize(new java.awt.Dimension(96, 40));

        TK.setPreferredSize(new java.awt.Dimension(96, 40));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(140, 140, 140)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(HT, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(MNV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(jCbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(jCbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(jcbKhuTTCN, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(MK, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(TK, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbKhuTTCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel22)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Thông Tin Cá Nhân", jPanel9);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel37.setText("Dịch Vụ");
        jLabel37.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel37.setMinimumSize(new java.awt.Dimension(110, 25));

        txtDV.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel38.setText("Giá Dịch Vụ");
        jLabel38.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel38.setMinimumSize(new java.awt.Dimension(110, 25));

        txtGiaDV.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel45.setText("Khu");
        jLabel45.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel45.setMinimumSize(new java.awt.Dimension(110, 25));

        jcbKhuDV.setMinimumSize(new java.awt.Dimension(108, 40));
        jcbKhuDV.setPreferredSize(new java.awt.Dimension(108, 40));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel39.setText("ID");
        jLabel39.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel39.setMinimumSize(new java.awt.Dimension(110, 25));

        txtIDDV.setPreferredSize(new java.awt.Dimension(96, 40));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDDV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(jcbKhuDV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGiaDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbKhuDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );

        btnThemVe8.setText("Tìm Kiếm Theo Tên");
        btnThemVe8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVe8ActionPerformed(evt);
            }
        });

        btnThemVe9.setText("Thêm");
        btnThemVe9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVe9ActionPerformed(evt);
            }
        });

        jButton31.setText("Sửa");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setText("Xóa");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setText("Lưu");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setText("Trở lại");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemVe8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemVe9, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(241, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemVe8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemVe9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        tbdichvu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Dịch Vụ", "Khu Vực", "Giá "
            }
        ));
        jScrollPane5.setViewportView(tbdichvu);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Dịch Vụ", jPanel1);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jButton22.setText("Sửa");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText("Xóa");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        btnThemVe4.setText("Thêm");
        btnThemVe4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVe4ActionPerformed(evt);
            }
        });

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel41.setText("Tên Khu");
        jLabel41.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel41.setMinimumSize(new java.awt.Dimension(110, 25));

        txtTenKhu.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel42.setText("Giờ Mở Cửa");
        jLabel42.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel42.setMinimumSize(new java.awt.Dimension(110, 25));

        txtGMC.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel46.setText("Giờ Đóng Cửa");
        jLabel46.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel46.setMinimumSize(new java.awt.Dimension(110, 25));

        txtGDC.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel49.setText("ID Khu");
        jLabel49.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel49.setMinimumSize(new java.awt.Dimension(110, 25));

        txtIDKhu.setPreferredSize(new java.awt.Dimension(96, 40));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(41, 41, 41)
                        .addComponent(txtIDKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGMC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGDC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKhu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(57, 57, 57))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDKhu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(138, Short.MAX_VALUE))
        );

        btnThemVe5.setText("Tìm Kiếm Theo Tên");
        btnThemVe5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVe5ActionPerformed(evt);
            }
        });

        jButton24.setText("Lưu");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton29.setText("Trở lại");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemVe5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnThemVe4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btnThemVe5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemVe4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        tbleKhuVuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "Tên Khu", "Giờ Mở Cửa", "Giờ Đóng Cửa"
            }
        ));
        jScrollPane6.setViewportView(tbleKhuVuc);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Khu", jPanel6);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel23.setText("Họ Tên");
        jLabel23.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel23.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel24.setText("Tài Khoản");
        jLabel24.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel24.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel25.setText("Mật Khẩu");
        jLabel25.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel25.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel33.setText("Chức Vụ");
        jLabel33.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel33.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel35.setText("Khu");
        jLabel35.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel35.setMinimumSize(new java.awt.Dimension(110, 25));

        jcbKVNV.setMinimumSize(new java.awt.Dimension(108, 40));
        jcbKVNV.setPreferredSize(new java.awt.Dimension(108, 40));

        jcbCVNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "Nhân Viên Bán Vé", "Nhân Viên Dịch Vụ", "Kĩ Thuật Viên" }));
        jcbCVNV.setMinimumSize(new java.awt.Dimension(108, 40));
        jcbCVNV.setPreferredSize(new java.awt.Dimension(108, 40));

        txtMKNV.setMinimumSize(new java.awt.Dimension(96, 40));
        txtMKNV.setPreferredSize(new java.awt.Dimension(96, 40));

        txtTKNV.setMinimumSize(new java.awt.Dimension(96, 40));
        txtTKNV.setPreferredSize(new java.awt.Dimension(96, 40));

        txtHTNV.setPreferredSize(new java.awt.Dimension(96, 40));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setText("Xóa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton16.setText("Lưu");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton7.setText("Tìm Kiếm Theo Tên");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton17.setText("Sửa");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Thêm Mới");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton30.setText("Trở lại");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );

        jLabel36.setBackground(new java.awt.Color(102, 255, 255));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/avatarNhanVien.jpg"))); // NOI18N

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên Nhân Viên", "Chức Vụ", "Khu Vực", "Số Điện Thoại"
            }
        ));
        jScrollPane4.setViewportView(tableUser);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(0, 51, Short.MAX_VALUE)
                        .addComponent(jLabel36)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(txtTKNV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(txtHTNV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(jcbCVNV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(txtMKNV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jcbKVNV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHTNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTKNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMKNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbCVNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbKVNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel36)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Nhân Viên", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jButton25.setText("Sửa");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setText("Xóa");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        btnThemVe6.setText("Thêm");
        btnThemVe6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVe6ActionPerformed(evt);
            }
        });

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel43.setText("Tên Trò Chơi");
        jLabel43.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel43.setMinimumSize(new java.awt.Dimension(110, 25));

        txtNameGame.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel47.setText("Khu Vực");
        jLabel47.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel47.setMinimumSize(new java.awt.Dimension(110, 25));

        jCbKhuGame.setMinimumSize(new java.awt.Dimension(108, 40));
        jCbKhuGame.setPreferredSize(new java.awt.Dimension(108, 40));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel48.setText("Tình Trạng");
        jLabel48.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel48.setMinimumSize(new java.awt.Dimension(110, 25));

        jCbTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tốt", "Bảo Trì" }));
        jCbTinhTrang.setMinimumSize(new java.awt.Dimension(108, 40));
        jCbTinhTrang.setPreferredSize(new java.awt.Dimension(108, 40));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel44.setText("ID Trò Chơi");
        jLabel44.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel44.setMinimumSize(new java.awt.Dimension(110, 25));

        txtIDGame.setPreferredSize(new java.awt.Dimension(96, 40));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(54, 54, 54))
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)))
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIDGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCbTinhTrang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCbKhuGame, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNameGame, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCbKhuGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCbTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jButton27.setText("Lưu");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        btnThemVe7.setText("Tìm Kiếm Theo Tên");
        btnThemVe7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVe7ActionPerformed(evt);
            }
        });

        jButton28.setText("Trở Lại");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemVe6, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemVe7, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnThemVe7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemVe6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbTroChoi3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Khu Vực", "Tên Trò Chơi", "Tình Trạng"
            }
        ));
        tbTroChoi3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTroChoi3MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbTroChoi3);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Trò Chơi", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbTroChoi3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTroChoi3MouseClicked

    }//GEN-LAST:event_tbTroChoi3MouseClicked

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        xoaTrangGame();
        sendgetgame();
        BTNTrue();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void btnThemVe7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVe7ActionPerformed
        String findNameGame = txtNameGame.getText();
         if(findNameGame.equals("")){
             JOptionPane.showMessageDialog(Client.AdminForm, "Tìm Kiếm Không Thành Công! \n Hãy Nhập Tên Trò Chơi Trước Khi Tìm Kiếm!");
        }else{
        try {
            Client.socketHandler.write("find-game" + "=" + findNameGame);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        BTNFalse();
         }
    }//GEN-LAST:event_btnThemVe7ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        String idGame = txtIDGame.getText();
        String nameGame = txtNameGame.getText();
        
        String kvGame = jCbKhuGame.getSelectedItem().toString();
        String tinhtrangGame = jCbTinhTrang.getSelectedItem().toString();
        if(nameGame.equals("")||idGame.equals("")){
             JOptionPane.showMessageDialog(Client.AdminForm, "Vui Lòng Sửa Trò Chơi Trước Khi lưu");
        }else{
        int ttGame;
        if (tinhtrangGame.equals("Tốt")) {
            ttGame = 0;
        } else {
            ttGame = 0;
        }

        try {
            Client.socketHandler.write("update-game" + "=" + idGame + "=" + kvGame + "=" + nameGame + "=" + ttGame);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        xoaTrangGame();
        sendgetgame();
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void btnThemVe6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVe6ActionPerformed
        String nameGame = txtNameGame.getText();
        if (nameGame.equals("")) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm Tên Trò Chơi!");
        } else {
            String kvGame = jCbKhuGame.getSelectedItem().toString();
            String tinhtrangGame = jCbTinhTrang.getSelectedItem().toString();
            int ttGame;
            if (tinhtrangGame.equals("Tốt")) {
                ttGame = 0;
            } else {
                ttGame = 0;
            }

            try {
                Client.socketHandler.write("add-game" + "=" + kvGame + "=" + nameGame + "=" + ttGame);
                JOptionPane.showMessageDialog(rootPane, " Thêm Thành Công!");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        xoaTrangGame();
        sendgetgame();
    }//GEN-LAST:event_btnThemVe6ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed

        int deletegame = tbTroChoi3.getSelectedRow();
        if (tbTroChoi3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi xóa!");
        } else if (deletegame == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi xóa!");
        } else {
            String updateGameId = tbTroChoi3.getValueAt(deletegame, 0).toString();
            try {
                Client.socketHandler.write("delete-game" + "=" + updateGameId);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        sendgetgame();

    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed

        int updategame = tbTroChoi3.getSelectedRow();
        if (tbTroChoi3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi sửa!");
        } else if (updategame == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi sửa!");
        } else {
            String updateGameId = tbTroChoi3.getValueAt(updategame, 0).toString();
            String updateGameName = tbTroChoi3.getValueAt(updategame, 2).toString();
            String updateKVGame = tbTroChoi3.getValueAt(updategame, 1).toString();
            String updateTTGame = tbTroChoi3.getValueAt(updategame, 3).toString();
            txtNameGame.setText(updateGameName);
            for (int i = 0; i < jCbKhuGame.getItemCount(); i++) {
                if (updateKVGame.equals(String.valueOf(jCbKhuGame.getItemAt(i)))) {
                    jCbKhuGame.setSelectedIndex(i);
                }
            }
            txtIDGame.setText(updateGameId);

            for (int i = 0; i < jCbTinhTrang.getItemCount(); i++) {
                if (updateTTGame.equals(String.valueOf(jCbTinhTrang.getItemAt(i)))) {
                    jCbTinhTrang.setSelectedIndex(i);
                }
            }

        }

    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        jButton18.setEnabled(true);
        sendGetUser();
        xoaTrangNV();

    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        getTTNhanVien();
        if (txtHtNv.equals("") || txtTkNv.equals("") || txtMkNv.equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ các dữ liệu.");
        } else {
            try {
                Client.socketHandler.write("register" + "=" + txtTkNv + "=" + txtMkNv + "=" + jcbCvNv + "=" + txtHtNv + "=" + jcbKvNv);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            xoaTrangNV();
            sendGetUser();
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        int updateus = tableUser.getSelectedRow();
        if (tableUser.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi sửa!");
        } else if (updateus == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi sửa!");
        } else {
            IDNV = tableUser.getValueAt(updateus, 0).toString();
            String updatename = tableUser.getValueAt(updateus, 1).toString();
            String updatechucvu = tableUser.getValueAt(updateus, 2).toString();
            String updatekhuvuc = tableUser.getValueAt(updateus, 2).toString();
            txtHTNV.setText(updatename);
            System.out.println("duýa" + updatename);
            if (updatechucvu.equals("0")) {
                updatechucvu = "ADMIN";
            } else if (updatechucvu.equals("1")) {
                updatechucvu = "Nhân Viên Bán Vé";
            } else if (updatechucvu.equals("2")) {
                updatechucvu = "Nhân Viên Dịch Vụ";
            } else if (updatechucvu.equals("3")) {
                updatechucvu = "Kĩ Thuật Viên";
            }

            for (int i = 0; i < jcbCVNV.getItemCount(); i++) {
                if (updatechucvu.equals(String.valueOf(jcbCVNV.getItemAt(i)))) {
                    jcbCVNV.setSelectedIndex(i);
                }
            }

            for (int i = 0; i < jcbKVNV.getItemCount(); i++) {
                if (updatekhuvuc.equals(String.valueOf(jcbKVNV.getItemAt(i)))) {
                    jcbKVNV.setSelectedIndex(i);
                }
            }

        }
        jButton18.setEnabled(false);

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String findNV = txtHTNV.getText();
         if(findNV.equals("")){
             JOptionPane.showMessageDialog(Client.AdminForm, "Tìm Kiếm Không Thành Công! \\n Hãy Nhập Tên Nhân Viên Trước Khi Tìm Kiếm!");
        }else{
        if(findNV.equals("")){
            JOptionPane.showMessageDialog(Client.AdminForm, "Vui Lòng Nhập Tên Nhân Viên");
            
        }else{
        try {
            Client.socketHandler.write("find-user" + "=" + findNV);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        }
         }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if (txtHTNV.getText().equals("")||txtMKNV.getText().equals("")||txtTKNV.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm đầy đủ thông tin!");
        } else {
            String updateName = txtHTNV.getText();
            Object updatechucvu = jcbCVNV.getSelectedItem();

            if (updatechucvu.equals("ADMIN")) {
                updatechucvu = "0";
            } else if (updatechucvu.equals("Nhân Viên Bán Vé")) {
                updatechucvu = "1";
            } else if (updatechucvu.equals("Nhân Viên Dịch Vụ")) {
                updatechucvu = "2";
            } else if (updatechucvu.equals("Kĩ Thuật Viên")) {
                updatechucvu = "3";
            }
            Object updatekhuvuc = jcbKVNV.getSelectedItem();

            try {
                Client.socketHandler.write("edit-staff-info" + "=" + IDNV + "=" + updatechucvu + "=" + updatekhuvuc);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        jButton18.setEnabled(true);

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int deleteUser = tableUser.getSelectedRow();
        if (tableUser.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi xóa!");
        } else if (deleteUser == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi xóa!");
        } else {
            String updateUserId = tableUser.getValueAt(deleteUser, 0).toString();
            try {
                Client.socketHandler.write("delete-user" + "=" + updateUserId);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        sendGetUser();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed

        xoaTrangKhu();
        sendGetKhu();
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        getKhuVuc();
        if (NameKhu.equals("") || GMC.equals("") || GDC.equals("")) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm đầy đủ thông tin!");
        } else {
            try {
                Client.socketHandler.write("update-arena" + "=" + idKhu + "=" + NameKhu + "=" + GMC + "=" + GDC);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            sendGetKhu();
            xoaTrangKhu();
        }
        btnThemVe4.setEnabled(true);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void btnThemVe5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVe5ActionPerformed
        String findKV = txtTenKhu.getText();
         if(findKV.equals("")){
             JOptionPane.showMessageDialog(Client.AdminForm, "Tìm Kiếm Không Thành Công! \n Hãy Nhập Tên Khu Vực Trước Khi Tìm Kiếm!");
        }else{
        try {
            Client.socketHandler.write("find-area" + "=" + findKV);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
         }
    }//GEN-LAST:event_btnThemVe5ActionPerformed

    private void btnThemVe4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVe4ActionPerformed

        getKhuVuc();
        if (NameKhu.equals("") || GMC.equals("") || GDC.equals("")) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm đầy đủ thông tin!");
        } else {
            try {
                Client.socketHandler.write("add-zone" + "=" + NameKhu + "=" + GMC + "=" + GDC);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            sendGetKhu();
            xoaTrangKhu();
        }
    }//GEN-LAST:event_btnThemVe4ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        int deleteKV = tbleKhuVuc.getSelectedRow();
        if (tbleKhuVuc.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi xóa!");
        } else if (deleteKV == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi xóa!");
        } else {
            String deleteKhuVuc = tbleKhuVuc.getValueAt(deleteKV, 0).toString();
            try {
                Client.socketHandler.write("delete-area" + "=" + deleteKhuVuc);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        sendGetKhu();
        xoaTrangKhu();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        int updatekv = tbleKhuVuc.getSelectedRow();
        if (tbleKhuVuc.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi sửa!");
        } else if (updatekv == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi sửa!");
        } else {
            String updateid = tbleKhuVuc.getValueAt(updatekv, 0).toString();
            String updatename = tbleKhuVuc.getValueAt(updatekv, 1).toString();
            String updateGMC = tbleKhuVuc.getValueAt(updatekv, 2).toString();
            String updateGDC = tbleKhuVuc.getValueAt(updatekv, 2).toString();
            txtIDKhu.setText(updateid);
            txtTenKhu.setText(updatename);
            txtGMC.setText(updateGMC);
            txtGDC.setText(updateGDC);
        }
        btnThemVe4.setEnabled(false);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            Client.socketHandler.write("offline" + "=" + logoutID);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        Client.closeView(Client.View.ADMIN);
        Client.openView(Client.View.LOGIN);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnThemVe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVe1ActionPerformed
        String findVe = txtLoaiVe.getText();
        if(findVe.equals("")){
             JOptionPane.showMessageDialog(Client.AdminForm, "Tìm Kiếm Không Thành Công! \n Hãy Nhập Tên Vé Trước Khi Tìm Kiếm!");
        }else{

        try {
            Client.socketHandler.write("find-ticket" + "=" + findVe);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        xoaTrangVe();
        }
    }//GEN-LAST:event_btnThemVe1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        getVe();

        if (loaiVe.equals("") || giaVe.equals("")) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm đầy đủ thông tin!");
        } else {
            try {
                Client.socketHandler.write("update-ticket" + "=" + idVe + "=" + loaiVe + "=" + giaVe);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            sendGetVe();
            xoaTrangVe();
        }
        btnThemVe.setEnabled(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnThemVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVeActionPerformed
        getVe();
        if (loaiVe.equals("") || giaVe.equals("")) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm đầy đủ thông tin!");
        } else {
            try {
                Client.socketHandler.write("add-ticket" + "=" + loaiVe + "=" + giaVe);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            xoaTrangVe();
            sendGetVe();

        }
    }//GEN-LAST:event_btnThemVeActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int deleteVe = tbBanVe.getSelectedRow();
        if (tbBanVe.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi xóa!");
        } else if (deleteVe == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi xóa!");
        } else {
            String deleteve = tbBanVe.getValueAt(deleteVe, 0).toString();
            try {
                Client.socketHandler.write("delete-ticket" + "=" + deleteve);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        sendGetVe();
        xoaTrangVe();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int updateVe = tbBanVe.getSelectedRow();
        if (tbBanVe.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi sửa!");
        } else if (updateVe == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi sửa!");
        } else {
            String updateId = tbBanVe.getValueAt(updateVe, 0).toString();
            String updateLoaiVe = tbBanVe.getValueAt(updateVe, 1).toString();
            String updateGia = tbBanVe.getValueAt(updateVe, 2).toString();
            txtIDVe.setText(updateId);
            txtLoaiVe.setText(updateLoaiVe);
            txtGiaVe.setText(updateGia);
        }
        btnThemVe.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnThemVe8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVe8ActionPerformed
        String findDV = txtDV.getText();
         if(findDV.equals("")){
             JOptionPane.showMessageDialog(Client.AdminForm, "Tìm Kiếm Không Thành Công! \\n Hãy Nhập Tên Dịch Vụ Trước Khi Tìm Kiếm!");
        }else{
        try {
            Client.socketHandler.write("find-service" + "=" + findDV);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        xoaTrangDichVu();
         }
    }//GEN-LAST:event_btnThemVe8ActionPerformed

    private void btnThemVe9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVe9ActionPerformed
        getDichVu();
        if (dv.equals("") || giaDV.equals("")) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm đầy đủ thông tin!");
        } else {
            try {
                Client.socketHandler.write("add-sevice" + "=" + dv + "=" + giaDV + "=" + khuDV);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            xoaTrangDichVu();
            sendGetDichVu();

        }
    }//GEN-LAST:event_btnThemVe9ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        int updatedv = tbdichvu.getSelectedRow();
        if (tbdichvu.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi sửa!");
        } else if (updatedv == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi sửa!");
        } else {
            String updateId = tbdichvu.getValueAt(updatedv, 0).toString();
            String updateName = tbdichvu.getValueAt(updatedv, 1).toString();
            String updateKv = tbdichvu.getValueAt(updatedv, 2).toString();
            String updateGia = tbdichvu.getValueAt(updatedv, 2).toString();
            txtIDDV.setText(updateId);
            txtDV.setText(updateName);
            for (int i = 0; i < jcbKhuDV.getItemCount(); i++) {
                if (updateKv.equals(String.valueOf(jcbKhuDV.getItemAt(i)))) {
                    jcbKhuDV.setSelectedIndex(i);
                }
            }

            txtGiaDV.setText(updateGia);
        }
        btnThemVe9.setEnabled(false);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        int deleteKV = tbdichvu.getSelectedRow();
        if (tbdichvu.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi xóa!");
        } else if (deleteKV == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi xóa!");
        } else {
            String deleteDV = tbdichvu.getValueAt(deleteKV, 0).toString();
            try {
                Client.socketHandler.write("delete-service" + "=" + deleteDV);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        sendGetDichVu();
        xoaTrangDichVu();
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        if(txtIDDV.getText().equals("")){
            JOptionPane.showMessageDialog(Client.AdminForm, "Thao Tác Không Thành Công Vui Lòng Thực Hiện Lại!");
        }else{
        getDichVu();
        if (khuDV.equals("") || dv.equals("") || giaDV.equals("")) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm đầy đủ thông tin!");
        } else {
            try {
                Client.socketHandler.write("update-service" + "=" + idDV + "=" + dv + "=" + giaDV + "=" + khuDV);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            sendGetDichVu();
            xoaTrangDichVu();
            btnThemVe9.setEnabled(true);
        }
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        xoaTrangDichVu();
        sendGetDichVu();
        btnThemVe9.setEnabled(true);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        xoaTrangVe();
        sendGetVe();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String GT = (String) jCbGioiTinh.getSelectedItem();
        if (GT.equals("Nam")) {
            GT = "1";
        } else {
            GT = "2";
        }

        String CV = (String) jCbChucVu.getSelectedItem();
        if (CV.equals("ADMIN")) {
            CV = "0";
        } else if (CV.equals("Nhân Viên Bán Vé")) {
            CV = "1";
        } else if (CV.equals("Nhân Viên Dịch Vụ")) {
            CV = "2";
        } else if (CV.equals("Kĩ Thuật Viên")) {
            CV = "3";
        }

        try {
            Client.socketHandler.write("update-staff-info" + "="
                    + MNV.getText() + "="
                    + TK.getText() + "="
                    + MK.getText() + "="
                    + CV + "="
                    + HT.getText() + "="
                    + ngaysinh.getText() + "="
                    + sodienthoai.getText() + "="
                    + GT + "="
                    + diachi.getText() + "=" + "0" + "="
                    + jcbKhuTTCN.getSelectedItem());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        DefaultTableModel model = (DefaultTableModel) ThongKe.getModel();
        model.setRowCount(0);
        String a = (String) BLHD.getSelectedItem();
        if (a.equals("Hóa Đơn Vé")) {
            try {
                Client.socketHandler.write("show-ticket-bill-by-day" + "=" + ngayHienTai + "=" + ngayHienTai);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (a.equals("Hóa Đơn Dịch Vụ")) {
            try {
                Client.socketHandler.write("show-service-bill-by-day" + "=" + ngayHienTai + "=" + ngayHienTai);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        DefaultTableModel model = (DefaultTableModel) ThongKe.getModel();
        model.setRowCount(0);
        String a = (String) BLHD.getSelectedItem();
        int b = 0;
        if (jComboBox8.getSelectedItem().equals("Quý 1")) {
            b = 1;
        } else if (jComboBox8.getSelectedItem().equals("Quý 2")) {
            b = 2;
        } else if (jComboBox8.getSelectedItem().equals("Quý 3")) {
            b = 3;
        } else if (jComboBox8.getSelectedItem().equals("Quý 4")) {
            b = 4;
        }
        
                 
            
       
        if (a.equals("Hóa Đơn Vé")) {
            try {
                Client.socketHandler.write("show-ticket-bill-by-quarter" + "=" + b);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (a.equals("Hóa Đơn Dịch Vụ")) {
            try {
                Client.socketHandler.write("show-service-bill-by-quarter" + "=" + b);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        if (ThongKe.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "In Danh Sách Không Thành Công! \n Hãy Thống Kê Trước Khi In!");
        } else {
            tongThongKe();
            TK_print();
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        bill1.setText("");
        DefaultTableModel model = (DefaultTableModel) ThongKe.getModel();
        model.setRowCount(0);
        jTextField17.setText("");
        jTextField18.setText("");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTextField17.getText().equals("")|| jTextField18.getText().equals("")){
             JOptionPane.showMessageDialog(Client.AdminForm, "Vui Lòng Nhập Ngày!");
        }else{
        DefaultTableModel model = (DefaultTableModel) ThongKe.getModel();
        model.setRowCount(0);
        if (!jTextField17.getText().isEmpty() && !jTextField17.getText().isEmpty()) { // kiểm tra xem giá trị có tồn tại hay không

            try {
                Client.socketHandler.write("show-ticket-bill-by-day" + "=" + jTextField17.getText() + "=" + jTextField18.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(adminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BLHD;
    private javax.swing.JTextField HT;
    private javax.swing.JTextField MK;
    private javax.swing.JTextField MNV;
    private javax.swing.JTextField TK;
    private javax.swing.JTable ThongKe;
    private javax.swing.JTextArea bill1;
    private javax.swing.JButton btnThemVe;
    private javax.swing.JButton btnThemVe1;
    private javax.swing.JButton btnThemVe4;
    private javax.swing.JButton btnThemVe5;
    private javax.swing.JButton btnThemVe6;
    private javax.swing.JButton btnThemVe7;
    private javax.swing.JButton btnThemVe8;
    private javax.swing.JButton btnThemVe9;
    private javax.swing.JTextField diachi;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jCbChucVu;
    private javax.swing.JComboBox<String> jCbGioiTinh;
    private javax.swing.JComboBox<String> jCbKhuGame;
    private javax.swing.JComboBox<String> jCbTinhTrang;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JComboBox<String> jcbCVNV;
    private javax.swing.JComboBox<String> jcbKVNV;
    private javax.swing.JComboBox<String> jcbKhuDV;
    private javax.swing.JComboBox<String> jcbKhuTTCN;
    private javax.swing.JTextField ngaysinh;
    private javax.swing.JTextField sodienthoai;
    private javax.swing.JTable tableUser;
    private javax.swing.JTable tbBanVe;
    private javax.swing.JTable tbTroChoi3;
    private javax.swing.JTable tbdichvu;
    private javax.swing.JTable tbleKhuVuc;
    private javax.swing.JTextField txtDV;
    private javax.swing.JTextField txtGDC;
    private javax.swing.JTextField txtGMC;
    private javax.swing.JTextField txtGiaDV;
    private javax.swing.JTextField txtGiaVe;
    private javax.swing.JTextField txtHTNV;
    private javax.swing.JTextField txtIDDV;
    private javax.swing.JTextField txtIDGame;
    private javax.swing.JTextField txtIDKhu;
    private javax.swing.JTextField txtIDVe;
    private javax.swing.JTextField txtLoaiVe;
    private javax.swing.JTextField txtMKNV;
    private javax.swing.JTextField txtNameGame;
    private javax.swing.JTextField txtTKNV;
    private javax.swing.JTextField txtTenKhu;
    // End of variables declaration//GEN-END:variables
}
