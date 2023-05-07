/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controler.Client;

import Model.User;
import Model.Ve;
import Model.hoaDon;
import Model.khuVuc;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author My PC
 */
public class staffView extends javax.swing.JFrame {
      private List<hoaDon> listHoaDonStatics;
        private List<User> listTTStatics;
         private List<khuVuc> listKhuVucStatics;
          private List<Ve> listVeStatics;
          
          
           
    private DefaultTableModel tableModel;

    DefaultTableModel ve;
    
    int  updategame;
    int giaVe;
    int idHDV;
    int idVe;
    int tongbill;
    int sum ;
    String giamGia;
    String ngayhientai;
    Object soluong ;
    Object thanhtien;
    
   

    /**
     * Creates new form staffView
     */
    public staffView() {
        initComponents();
        try {
                Client.socketHandler.write("show-arena");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        try {
                Client.socketHandler.write("show-ticket");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        
        
        // lấy ngày hiện tại
        
        
       

                Date currentDate = new Date();
                int day = currentDate.getDate();
                int month = currentDate.getMonth() + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để lấy tháng thực tế
                int year = currentDate.getYear() + 1900; // Năm được tính từ 1900, nên cộng thêm 1900 để lấy năm thực tế
                txtNgayTao.setText(year+"-" + month+"-" +day);
               
               
          txtGiamGia.setText("0");
         
        
    }
    public void bill_print(){
 
        try {
            bill.setText("\tKhu Vui Chơi TNIT \n");
            bill.setText(bill.getText() + "\t Tên NV:"+ HT.getText()+"  ("+ MNV.getText()+")"+"\n");
            bill.setText(bill.getText() + "\t Điện Thoại:+84 3689999999, \n");
            bill.setText(bill.getText() + "\t Ngày Lập:"+ txtNgayTao.getText()+ "\n" );
            
            bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");
            bill.setText(bill.getText() + "\tLoại Vé \tSố Lượng \tThành Tiền \n");
            bill.setText(bill.getText() +"------------------------------------------------------------------------------\n");
            
            DefaultTableModel df = (DefaultTableModel) tbBanVe.getModel();
            for (int i = 0; i < tbBanVe.getRowCount(); i++) {
                
                String name = df.getValueAt(i, 0).toString();
                String qt = df.getValueAt(i, 1).toString();
                String prc = df.getValueAt(i, 3).toString();
                
                bill.setText(bill.getText()+"\t"+ name+"\t"+qt+"\t"+prc+" \n");
                
            }
            bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");
            bill.setText(bill.getText()+"\t Tổng Tiền: "+ sum +".000 \n" );
            bill.setText(bill.getText()+"\t Giảm Giá: "+ giamGia +"% \n" );
            bill.setText(bill.getText()+"\t Tổng Tiền thanh toán: "+ tongbill+".000\n");
            bill.setText(bill.getText() + "==========================================\n");
            bill.setText(bill.getText() +"\tThanks For Your Business...!"+"\n");
            bill.setText(bill.getText() + "------------------------------------------------------------------------------\n");
            bill.setText(bill.getText() +"\tSoftware by TNIT"+"\n");
            
            
            bill.print();
            
        } catch (PrinterException ex) {
            
            Logger.getLogger(staffView.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 }
    
    
        public void setJcbVe(List<Ve> ve){
            cbLoaiVe.removeAllItems();
        this.listVeStatics = ve;
        int i=0;
        for(Ve Ves : listVeStatics){  
            cbLoaiVe.addItem(String.valueOf(Ves.getLoaiVe()));  
            i++;
        }
      
    }
   
     public void setDataToTableVe(List<Ve> ve){
         
        this.listVeStatics = ve;
        int i=0;
        for(Ve ves : listVeStatics){ 
            giaVe = ves.getGiaVe(); 
            idVe = ves.getIdVe();
            i++;
        }
         System.out.println(idVe);
     String a =   txtSoLuong.getText();
       if(a!= null && !a.equals("")){
           add();
       }else{
          
       }
    }

      public void setIDHDV(List<hoaDon> hd){
         
        this.listHoaDonStatics = hd;
        int i=0;
        for(hoaDon ves : listHoaDonStatics){ 
            idHDV = ves.getIdHDV();
            i++;
        }
          System.out.println("id hóa dơn vé "+ idHDV);
        
    }
    public void setTTCN(List<User> TTCN){ 
        this.listTTStatics = TTCN;
        int i=0;
        String cv= null;
        int kv ;
        for(User TT : listTTStatics){ 
            String gioitinh = Integer.toString(TT.getGioiTinh());
            if(gioitinh.equals("1")){
                gioitinh = "Nam";
            }else{
                 gioitinh = "Nữ";
            }
            
            if(TT.getRole()==0){
                cv = " ADMIN";
            }else if(TT.getRole()==1){
                cv = "Nhân Viên Bán Vé";
            }else if(TT.getRole()==2){
                cv = "Nhân Viên Dịch Vụ";
            }else if(TT.getRole()==3){
                cv = "Kĩ Thuật Viên";
            }
           MNV.setText(Integer.toString(TT.getIdUser()));
           HT.setText(TT.getHoTen());
           ngaysinh.setText(TT.getNgaySinh());
           sodienthoai.setText(TT.getSDT());
           diachi.setText(TT.getDiaChi());
             for( i = 0; i< jCbGioiTinh.getItemCount();i++){
                if(gioitinh.equals(String.valueOf(jCbGioiTinh.getItemAt(i)))){
                   jCbGioiTinh.setSelectedIndex(i);
                }
            }
               for(i = 0; i< jCbChucVu.getItemCount();i++){
                if(cv.equals(String.valueOf(jCbChucVu.getItemAt(i)))){
                    jCbChucVu.setSelectedIndex(i);
                }
            }
               kv = TT.getIdKhu();
               for( i = 0; i< jcbKhuTTCN.getItemCount();i++){
                if(Integer.toString(kv).equals(String.valueOf(jcbKhuTTCN.getItemAt(i)))){
                    jcbKhuTTCN.setSelectedIndex(i);
                } 
            } 
               TK.setText(TT.getUsername());
               MK.setText(TT.getPassword());           
        }
            
            i++;
        }
    
    public void setDataToTableThongKe(List<hoaDon> hoadon){
           
       
         tableModel = (DefaultTableModel) jTable2.getModel(); 
        this.listHoaDonStatics = hoadon;
        tableModel.setRowCount(0);
        
        int i=0;
        for(hoaDon hd : listHoaDonStatics){ 
            tableModel.addRow(new Object[]{
               hd.getIdHDV(),
               hd.getIdHDV(),
               hd.getNgayLap(),
               hd.getTongTien()
            });
            i++;
        }
    }
    public void setJcbKhuVuc(List<khuVuc> arena){
        
            jcbKhuTTCN.removeAllItems();
      
        this.listKhuVucStatics = arena;
        int i=0;
        for(khuVuc arenas : listKhuVucStatics){ 
            jcbKhuTTCN.addItem(String.valueOf(arenas.getIdKhu()));
            i++;
        }
    }
    private void NhapVaoBang() {
        ve = new DefaultTableModel();
        ve.setColumnIdentifiers(new String[]{"Loại Vé", "Số Lượng", "Ngày Tạo", "Giá tiền"});
        tbBanVe.setModel(ve);
    }
    private void add(){
        
        String loaiVe = (String) cbLoaiVe.getSelectedItem();
        String soLuong = txtSoLuong.getText();
        String ngayTao = txtNgayTao.getText();
        
                if (txtSoLuong.getText().equals("") || txtNgayTao.getText().equals("")
                || txtGiamGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ các dữ liệu.");
        } else {
            int thanhTien = 0;
            if (loaiVe.equals("Vé Thường")) {
                thanhTien = (Integer.parseInt(soLuong) * giaVe) ;
            } else {
                thanhTien = (Integer.parseInt(soLuong) * giaVe);
            }

            String data[] = {loaiVe, soLuong, ngayTao, String.valueOf(thanhTien)};
            ve = (DefaultTableModel) tbBanVe.getModel();
            ve.addRow(data); 
            xoaTrang();
            tongBill();
        }
    }
    private void addHDV(){
        String IDNV = MNV.getText();
        String ngayTao = txtNgayTao.getText();
        try {
                Client.socketHandler.write("add-ticket-bill"+"="+ ngayTao+"="+IDNV+"="+tongbill);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }
    private void tongBill(){
        tongbill=0;
        sum=0;
         giamGia = txtGiamGia.getText();
   
    // Tính tổng các giá trị trong cột thứ i
    int i = 3; // thay bằng số thứ tự của cột bạn muốn tính tổng
    int numRows = tbBanVe.getRowCount();
   
    for (int row = 0; row < numRows; row++) {
        sum += Integer.parseInt(tbBanVe.getValueAt(row, i).toString());
    }
    tongbill = sum - (sum * Integer.parseInt(giamGia) / 100);
        
    

    }
   
    private void addCTDHV(){
        try {
                Client.socketHandler.write("add-detail-ticket"+ "="+ idHDV+ "="+ idVe+ "="+soluong+ "="+thanhtien);
        
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }
    private void CTHDV(){
        int row = tbBanVe.getRowCount();
        for(int i =0; i<= 0;i++){
            Object loaive = tbBanVe.getValueAt(i, 0);
            soluong = tbBanVe.getValueAt(i, 1);
            thanhtien = tbBanVe.getValueAt(i, 3);
            try {
                Client.socketHandler.write("find-ticket"+ "="+ loaive);
        
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
             addCTDHV();
        }
    }
    
    private void xoaTrang() {

        cbLoaiVe.setSelectedIndex(0);
       txtSoLuong.setText(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBanVe = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        btnThemVe = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbLoaiVe = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
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

        tbBanVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loại Vé", "Số Lượng", "Ngày Tạo", "Thành Tiền"
            }
        ));
        jScrollPane1.setViewportView(tbBanVe);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cbLoaiVe.setMinimumSize(new java.awt.Dimension(108, 40));
        cbLoaiVe.setPreferredSize(new java.awt.Dimension(108, 40));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel23.setText("Loại Vé");
        jLabel23.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel23.setMinimumSize(new java.awt.Dimension(110, 25));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel24.setText("Số Lượng");
        jLabel24.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel24.setMinimumSize(new java.awt.Dimension(110, 25));

        txtSoLuong.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel25.setText("Ngày Tạo");
        jLabel25.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel25.setMinimumSize(new java.awt.Dimension(110, 25));

        txtNgayTao.setPreferredSize(new java.awt.Dimension(96, 40));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel26.setText("Giảm Giá");
        jLabel26.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel26.setMinimumSize(new java.awt.Dimension(110, 25));

        txtGiamGia.setPreferredSize(new java.awt.Dimension(96, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(cbLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbLoaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
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
                .addGap(31, 31, 31)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemVe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnThemVe, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(25, 29, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
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

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jButton2.setText("Thống Kê");

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

        jButton14.setText("Thoát");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, Short.MAX_VALUE)
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
                .addContainerGap(49, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        bill.setText("");
        DefaultTableModel model = (DefaultTableModel) tbBanVe.getModel();
        model.setRowCount(0);

        xoaTrang();
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnThemVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVeActionPerformed
        String loaiVe = (String) cbLoaiVe.getSelectedItem();
       
        try {
                Client.socketHandler.write("find-ticket"+ "="+ loaiVe);
        
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
       
        
    }//GEN-LAST:event_btnThemVeActionPerformed
  
    
    
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int remove = tbBanVe.getSelectedRow();
        if (tbBanVe.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, " Hãy thêm thông tin vào bảng trước khi xóa!");
        } else if (remove == -1) {
            JOptionPane.showMessageDialog(rootPane, " Hãy chọn 1 dòng trong bảng trước khi xóa!");
        } else {
            ve.removeRow(remove);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String GT = (String) jCbGioiTinh.getSelectedItem();
        if(GT.equals("Nam")){
            GT = "1";
        }else{
            GT = "2";
        }

        String CV = (String) jCbChucVu.getSelectedItem();
        if(CV.equals("ADMIN")){
            CV = "0";
        }else if(CV.equals("Nhân Viên Bán Vé")){
            CV = "1";
        }else if(CV.equals("Nhân Viên Dịch Vụ")){
            CV = "2";
        }else if(CV.equals("Kĩ Thuật Viên")){
            CV= "3";
        }

        try {
            Client.socketHandler.write("update-staff-info"+ "="+
                MNV.getText()+ "="+
                TK.getText()+ "="+
                MK.getText() + "="+
                CV+ "="+
                HT.getText()+ "="+
                ngaysinh.getText()+ "="+
                sodienthoai.getText()+ "="+
                GT+ "="+
                diachi.getText()+ "="+"0"+"="+
                jcbKhuTTCN.getSelectedItem()  );
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
           try {
                Client.socketHandler.write("show-service-bill-by-day");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
           try {
                Client.socketHandler.write("show-service-bill-by-quarter");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       tongBill();
       addHDV();
       CTHDV();
        bill_print();
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(staffView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(staffView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(staffView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(staffView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new staffView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField HT;
    private javax.swing.JTextField MK;
    private javax.swing.JTextField MNV;
    private javax.swing.JTextField TK;
    private javax.swing.JTextArea bill;
    private javax.swing.JButton btnThemVe;
    private javax.swing.JComboBox<String> cbLoaiVe;
    private javax.swing.JTextField diachi;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jCbChucVu;
    private javax.swing.JComboBox<String> jCbGioiTinh;
    private javax.swing.JColorChooser jColorChooser1;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JComboBox<String> jcbKhuTTCN;
    private javax.swing.JTextField ngaysinh;
    private javax.swing.JTextField sodienthoai;
    private javax.swing.JTable tbBanVe;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
