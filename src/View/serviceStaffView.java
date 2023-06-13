/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controler.Client;
import Model.User;
import Model.Ve;
import Model.dichVu;
import Model.hoaDon;
import Model.khuVuc;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author My PC
 */
public class serviceStaffView extends javax.swing.JFrame {

    private List<hoaDon> listHoaDonStatics;
    private List<User> listTTStatics;
    private List<khuVuc> listKhuVucStatics;
    private List<dichVu> listDichVuStatics;
    public ArrayList<String> IDHDDV = new ArrayList<>();

    private DefaultTableModel tableModel;
    DefaultTableModel ve;

    int logoutID;
    int sumTK;
    // giá dịch vụ ở add HDDV
    int giaDV;
    int IDDV;
    int SW = 1;
    // tính ttổng hóa đơn
    int tongbill;
    int sum;
    String giamGia;

    // lấy ngày 
    Date currentDate = new Date();
    int day = currentDate.getDate();
    int month = currentDate.getMonth() + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để lấy tháng thực tế
    int year = currentDate.getYear() + 1900; // Năm được tính từ 1900, nên cộng thêm 1900 để lấy năm thực tế
    String ngayHienTai = (year + "-" + month + "-" + day);

    /**
     * Creates new form serviceStaffView
     */
    public serviceStaffView() {
        initComponents();
        sendGetDichVu();
        jTextField15.setText("0");
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

        this.listDichVuStatics = service;

        int i = 0;
        for (dichVu services : listDichVuStatics) {
            giaDV = services.getGiaDV();
            IDDV = services.getIdDichvu();
            i++;
        }
        String a = jTextField13.getText();
        if (a != null && !a.equals("")) {
            bill_print();
            add();

        } else {
//           addCTDV();
        }
    }

    public void setJcbDV(List<dichVu> ve) {
        jComboBox4.removeAllItems();
        this.listDichVuStatics = ve;
        int i = 0;
        for (dichVu Ves : listDichVuStatics) {
            jComboBox4.addItem(String.valueOf(Ves.getTenDV()));
            i++;
        }
    }

    public void setJcbKhuVuc(List<khuVuc> arena) {

        jcbKhuTTCN.removeAllItems();

        this.listKhuVucStatics = arena;
        int i = 0;
        for (khuVuc arenas : listKhuVucStatics) {

            jcbKhuTTCN.addItem(String.valueOf(arenas.getIdKhu()));

            i++;
        }

    }

    public void bill_print() {

        String loaiVe = (String) jComboBox4.getSelectedItem();
        DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
        int i = 0;
        while (i < Integer.parseInt(jTextField13.getText())) {

            bill.setText(bill.getText() + "\tKhu Vui Chơi TNIT \n");
            bill.setText(bill.getText() + "\t Tên NV:" + HT.getText() + "  (" + MNV.getText() + ")" + "\n");
            bill.setText(bill.getText() + "\t Điện Thoại:+84 3689999999, \n");
            bill.setText(bill.getText() + "\t Ngày Lập:" + ngayHienTai + "\n");
            bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");
            bill.setText(bill.getText() + "\tDịch Vụ: " + loaiVe + "\n");
            bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");
            bill.setText(bill.getText() + "\tGiá: " + giaDV + " \n");
            bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");
            bill.setText(bill.getText() + "==========================================\n");
            bill.setText(bill.getText() + "\tThanks For Your Business...!" + "\n");
            bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");
            bill.setText(bill.getText() + "\tSoftware by TNIT" + "\n");
            bill.setText(bill.getText() + "\t" + "\n");
            bill.setText(bill.getText() + "\t" + "\n");
            i++;
        }

//           
    }

    public void TK_print() {
        try {
            bill1.setText("\t       Khu Vui Chơi TNIT \n");
            bill1.setText(bill1.getText() + "\t       Tên NV:" + HT.getText() + "  (" + MNV.getText() + ")" + "\n");
            bill1.setText(bill1.getText() + "\t       Điện Thoại:+84 3689999999, \n");
            bill1.setText(bill1.getText() + "\t       Ngày Lập:" + ngayHienTai + "\n");

            bill1.setText(bill1.getText() + "---------------------------------------------------------------------------------------------\n");
            bill1.setText(bill1.getText() + "\tID       IDNhân Viên\t Ngày Tạo\tThành Tiền \n");
            bill1.setText(bill1.getText() + "---------------------------------------------------------------------------------------------\n");

            DefaultTableModel df = (DefaultTableModel) tbleTK.getModel();
            for (int i = 0; i < tbleTK.getRowCount(); i++) {

                String name = df.getValueAt(i, 0).toString();
                String qt = df.getValueAt(i, 1).toString();
                String prct = df.getValueAt(i, 2).toString();
                String prc = df.getValueAt(i, 3).toString();

                bill1.setText(bill1.getText() + "\t" + name + "\t" + qt + "\t" + prct + "\t" + prc + " \n");

            }
            bill1.setText(bill1.getText() + "---------------------------------------------------------------------------------------------\n");
            bill1.setText(bill1.getText() + "\t Tổng Tiền: " + sumTK + ".000 \n");
            bill1.setText(bill1.getText() + "===================================================\n");
            bill1.setText(bill1.getText() + "\tThanks For Your Business...!" + "\n");
            bill1.setText(bill1.getText() + "---------------------------------------------------------------------------------------------\n");
            bill1.setText(bill1.getText() + "\tSoftware by TNIT" + "\n");

            bill1.print();

        } catch (PrinterException ex) {

            Logger.getLogger(staffView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tongBill() {
        tongbill = 0;
        sum = 0;
        giamGia = jTextField15.getText();

        // Tính tổng các giá trị trong cột thứ i
        int i = 3; // thay bằng số thứ tự của cột bạn muốn tính tổng
        int numRows = jTable1.getRowCount();

        for (int row = 0; row < numRows; row++) {
            sum += Integer.parseInt(jTable1.getValueAt(row, i).toString());
        }
        tongbill += sum - (sum * Integer.parseInt(giamGia) / 100);

    }

    private void Xoa() {
        bill.setText("");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    }

    public void bill() {

        bill.setText(bill.getText() + "\tKhu Vui Chơi TNIT \n");
        bill.setText(bill.getText() + "\t Tên NV:" + HT.getText() + "  (" + MNV.getText() + ")" + "\n");
        bill.setText(bill.getText() + "\t Điện Thoại:+84 3689999999, \n");
        bill.setText(bill.getText() + "\t Ngày Lập:" + ngayHienTai + "\n");

        bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");
        bill.setText(bill.getText() + "\tLoại Vé \tSố Lượng \tThành Tiền \n");
        bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");

        DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < jTable1.getRowCount(); i++) {

            String name = df.getValueAt(i, 1).toString();
            String qt = df.getValueAt(i, 2).toString();
            String prc = df.getValueAt(i, 3).toString();

            bill.setText(bill.getText() + "\t" + name + "\t" + qt + "\t" + prc + " \n");

        }
        bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");
        bill.setText(bill.getText() + "\t Tổng Tiền: " + sum + ".000 \n");
        bill.setText(bill.getText() + "\t Giảm Giá: " + giamGia + "% \n");
        bill.setText(bill.getText() + "\t Tổng Tiền thanh toán: " + tongbill + ".000\n");
        bill.setText(bill.getText() + "==========================================\n");
        bill.setText(bill.getText() + "\tThanks For Your Business...!" + "\n");
        bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");
        bill.setText(bill.getText() + "\tSoftware by TNIT" + "\n");

        jTextField15.setText("0");

    }

    public void addHDDV() {
        sumTK = 0;
        // Tính tổng các giá trị trong cột thứ i
        int i = 3; // thay bằng số thứ tự của cột bạn muốn tính tổng
        int numRows = jTable1.getRowCount();
        for (int row = 0; row < numRows; row++) {
            sumTK += Integer.parseInt(jTable1.getValueAt(row, i).toString());
        }

        try {
            Client.socketHandler.write("add-service-bill" + "=" + ngayHienTai + "=" + logoutID + "=" + sumTK);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addCTHDDV() {

        String lastChar;
        if (IDHDDV.size() == 1) { // nếu chuỗi chỉ có 1 phần tử
            lastChar = IDHDDV.get(0);
        } else { // nếu chuỗi có nhiều hơn 1 phần tử
            lastChar = IDHDDV.get(IDHDDV.size() - 1);
        }

        DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < jTable1.getRowCount(); i++) {

            String id = df.getValueAt(i, 0).toString();
            String sl = df.getValueAt(i, 2).toString();
            String prc = df.getValueAt(i, 3).toString();

            try {
                Client.socketHandler.write("add-detail-service" + "=" + lastChar + "=" + id + "=" + sl + "=" + prc);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    private void tongThongKe() {
        sumTK = 0;
        // Tính tổng các giá trị trong cột thứ i
        int i = 3; // thay bằng số thứ tự của cột bạn muốn tính tổng
        int numRows = tbleTK.getRowCount();
        for (int row = 0; row < numRows; row++) {
            sumTK += Integer.parseInt(tbleTK.getValueAt(row, i).toString());
        }
    }

    public void setDataToTableThongKe(List<hoaDon> hoadon) {

        tableModel = (DefaultTableModel) tbleTK.getModel();
        this.listHoaDonStatics = hoadon;
        tableModel.setRowCount(0);

        int i = 0;
        for (hoaDon hd : listHoaDonStatics) {
            tableModel.addRow(new Object[]{
                hd.getIdHDV(),
                hd.getIdHDV(),
                hd.getNgayLap(),
                hd.getTongTien()
            });
            i++;
        }
    }

    private void add() {

        String loaiVe = (String) jComboBox4.getSelectedItem();
        String soLuong = jTextField13.getText();

        if (jTextField13.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ các dữ liệu.");
        } else {
            int thanhTien = 0;

            thanhTien = (Integer.parseInt(soLuong) * giaDV);

            String data[] = {String.valueOf(IDDV), loaiVe, soLuong, String.valueOf(thanhTien)};
            ve = (DefaultTableModel) jTable1.getModel();
            ve.addRow(data);

            jTextField13.setText("");
            jComboBox4.setSelectedIndex(0);

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
        jTable1 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbleTK = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
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
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        MK = new javax.swing.JTextField();
        TK = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane2.setRequestFocusEnabled(false);

        jPanel4.setBackground(new java.awt.Color(255, 102, 102));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Dịch Vụ", "Tên Dịch Vụ", "Số Lượng", "Thành Tiền "
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jButton5.setText("Xóa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Thêm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jComboBox4.setMinimumSize(new java.awt.Dimension(108, 40));
        jComboBox4.setPreferredSize(new java.awt.Dimension(108, 40));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel23.setText("Loại Dịch Vụ");
        jLabel23.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel23.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel24.setText("Số Lượng");
        jLabel24.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel24.setMinimumSize(new java.awt.Dimension(110, 25));

        jTextField13.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel26.setText("Giảm Giá");
        jLabel26.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel26.setMinimumSize(new java.awt.Dimension(110, 25));

        jTextField15.setPreferredSize(new java.awt.Dimension(96, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(187, Short.MAX_VALUE))
        );

        jButton7.setText("Xuất Hóa Đơn");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Thoát");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        bill.setColumns(20);
        bill.setRows(5);
        jScrollPane3.setViewportView(bill);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 43, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Bán Vé", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 153, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        tbleTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "IDNV", "Ngày Lập", "Tổng Tiền"
            }
        ));
        jScrollPane4.setViewportView(tbleTK);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jButton15.setText("Thống Kê Ngày");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Thống Kê Quý");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Thống Kê");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quý 1", "Quý 2", "Quý 3", "Quý 4" }));
        jComboBox8.setMinimumSize(new java.awt.Dimension(108, 40));
        jComboBox8.setPreferredSize(new java.awt.Dimension(108, 40));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel25.setText("Quý");
        jLabel25.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel25.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel31.setText("Từ Ngày");
        jLabel31.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel31.setMinimumSize(new java.awt.Dimension(110, 25));

        jTextField17.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel32.setText("Đến Ngày");
        jLabel32.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel32.setMinimumSize(new java.awt.Dimension(110, 25));

        jTextField18.setPreferredSize(new java.awt.Dimension(96, 40));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jButton18.setText("Xuất Báo Cáo");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Thoát");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        bill1.setColumns(20);
        bill1.setRows(5);
        jScrollPane2.setViewportView(bill1);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addGap(25, 74, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jButton11.setText("Trở Lại");

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
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        jLabel22.setBackground(new java.awt.Color(102, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/avatarNhanVien.jpg"))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel33.setText("Tài Khoản");
        jLabel33.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel33.setMinimumSize(new java.awt.Dimension(110, 25));

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
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addGap(26, 26, 26)
                            .addComponent(MK, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(TK, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(24, 24, 24)
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
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jcbKhuTTCN, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Thông Tin Cá Nhân", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Xoa();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbleTK.getModel();
        model.setRowCount(0);
        try {
            Client.socketHandler.write("show-service-bill-by-day" + "=" + ngayHienTai + "=" + ngayHienTai);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        bill1.setText("");
        DefaultTableModel model = (DefaultTableModel) tbleTK.getModel();
        model.setRowCount(0);
        jTextField17.setText("");
        jTextField18.setText("");
    }//GEN-LAST:event_jButton19ActionPerformed

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

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            Client.socketHandler.write("offline" + "=" + logoutID);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        Client.closeView(Client.View.SERVICESTAFF);
        Client.openView(Client.View.LOGIN);

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbleTK.getModel();
        model.setRowCount(0);

        int a = 0;
        if (jComboBox8.getSelectedItem().equals("Quý 1")) {
            a = 1;
        } else if (jComboBox8.getSelectedItem().equals("Quý 2")) {
            a = 2;
        } else if (jComboBox8.getSelectedItem().equals("Quý 3")) {
            a = 3;
        } else if (jComboBox8.getSelectedItem().equals("Quý 4")) {
            a = 4;
        }
        try {
            Client.socketHandler.write("show-service-bill-by-quarter" + "=" + a);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String loaiDV = (String) jComboBox4.getSelectedItem();
        try {
            Client.socketHandler.write("find-service" + "=" + loaiDV);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbleTK.getModel();
        model.setRowCount(0);

        try {
            Client.socketHandler.write("show-service-bill-by-day" + "=" + jTextField17.getText() + "=" + jTextField18.getText());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        if (tbleTK.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy mua sản phẩm!");
        } else {
            tongThongKe();
            TK_print();
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy mua sản phẩm!");
        } else {

            addHDDV();
            tongBill();
            bill();

            try {

                bill.print();

            } catch (PrinterException ex) {

                Logger.getLogger(staffView.class.getName()).log(Level.SEVERE, null, ex);
            }
            Xoa();
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int remove = jTable1.getSelectedRow();
        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi xóa!");
        } else if (remove == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi xóa!");
        } else {
            ve.removeRow(remove);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(serviceStaffView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(serviceStaffView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(serviceStaffView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(serviceStaffView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new serviceStaffView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField HT;
    private javax.swing.JTextField MK;
    private javax.swing.JTextField MNV;
    private javax.swing.JTextField TK;
    private javax.swing.JTextArea bill;
    private javax.swing.JTextArea bill1;
    private javax.swing.JTextField diachi;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jCbChucVu;
    private javax.swing.JComboBox<String> jCbGioiTinh;
    private javax.swing.JComboBox<String> jComboBox4;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JComboBox<String> jcbKhuTTCN;
    private javax.swing.JTextField ngaysinh;
    private javax.swing.JTextField sodienthoai;
    private javax.swing.JTable tbleTK;
    // End of variables declaration//GEN-END:variables
}
