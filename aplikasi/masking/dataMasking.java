/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.masking;

/**
 *
 * @author ASUS
 */


import aplikasi.masking.*;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.DataFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class dataMasking extends javax.swing.JFrame {
    private int waktuMulai = 0;
    
    void hapus_order(){
        mps001.setText("0");
        mps002.setText("0");
        mpe010.setText("0");
        mpe012.setText("0");
    }

    void cari(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("jenis_masking");
        tbl.addColumn("id_masking");
        tbl.addColumn("qty_masking");
        
        try {
            String sql = "select * from tb_masking where id_masking='"+cari.getText()+"'";
            Connection con = (Connection) koneksi.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
        while (rs.next()){
            tbl.addRow(new Object[] {
                rs.getString("jenis_masking"),
                rs.getString("id_masking"),
                rs.getString("qty_masking")
            });
            tabelMasking.setModel(tbl);
        }
        }catch(Exception e){
            
        }
    }
    
    public void total(){
        int jumlahBaris = tabelMasking.getRowCount();
        int totalQtyMasking = 0;
        int jumlahM;
        for ( int i = 0; i < jumlahBaris; i++){
            jumlahM = Integer.parseInt(tabelMasking.getValueAt(i, 3).toString());
            totalQtyMasking = totalQtyMasking + jumlahM;
        }
        totalMasking.setText("" + totalQtyMasking);
    }
    void kebutuhan_bawah(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("id");
        tbl.addColumn("kebutuhan_masking");
        tbl.addColumn("masking_bawah");
        
        try {
          Statement st = (Statement) koneksi.getConnection().createStatement();
          ResultSet rs = st.executeQuery("select * from tb_ppic where masking_bawah='" +ComboBoxMasking.getSelectedItem()+"'");
          while (rs.next()){
              tbl.addRow(new Object[]{
              rs.getString("id"),
              rs.getString("kebutuhan_masking"),
              rs.getString("masking_bawah")
              });
              kebutuhanBawah.setModel(tbl);
               int total = 0;
              for (int i = 0; i <tbl.getRowCount(); i++){
              int masking = Integer.parseInt((String)tbl.getValueAt(i, 1));
              total += masking;
              }
              pemakaianBawah.setText("" + total);
        cari.setText("");
          }
        }catch(Exception e){
        }
    }
    void kebutuhan_Atas(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("id");
        tbl.addColumn("kebutuhan_masking");
        tbl.addColumn("masking_atas");
        
        try {
          Statement st = (Statement) koneksi.getConnection().createStatement();
          ResultSet rs = st.executeQuery("select * from tb_ppic where masking_atas='" +ComboBoxMasking.getSelectedItem()+"'");
          while (rs.next()){
              tbl.addRow(new Object[]{
              rs.getString("id"),
              rs.getString("kebutuhan_masking"),
              rs.getString("masking_atas")
              });
              kebutuhanAtas.setModel(tbl);
               int total = 0;
              for (int i = 0; i <tbl.getRowCount(); i++){
              int masking = Integer.parseInt((String)tbl.getValueAt(i, 1));
              total += masking;
              }
              pemakaianAtas.setText("" + total);
              cari.setText("");
          }
        }catch(Exception e){
        }
    }
    
    void cek_masking(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("jenis_masking");
        tbl.addColumn("id_masking");
        tbl.addColumn("qty_masking");
        
        try {
          Statement st = (Statement) koneksi.getConnection().createStatement();
          ResultSet rs = st.executeQuery("select * from tb_masking where jenis_masking='" +ComboBoxMasking.getSelectedItem()+"'");
          while (rs.next()){
              tbl.addRow(new Object[]{
              rs.getString("jenis_masking"),
              rs.getString("id_masking"),
              rs.getString("qty_masking")
              });
              tabelMasking.setModel(tbl);
              
              int total = 0;
              int qty_masking;
              qty_masking = rs.getRow();
              for (int i = 0; i <tbl.getRowCount(); i++){
              int masking = Integer.parseInt((String)tbl.getValueAt(i, 2));
              total += masking;
              }
               totalMasking.setText("" + total);
               qtymasking.setText("" + qty_masking);
          }
        }catch(Exception e){
        }
    }
       
    public void table(){      
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("jenis_masking");
        tbl.addColumn("id_masking");
        tbl.addColumn("qty_masking");
        
        try {
          Statement st = (Statement) koneksi.getConnection().createStatement();
          ResultSet rs = st.executeQuery("select * from tb_masking");
          
          while (rs.next()){
              tbl.addRow(new Object[]{
              rs.getString("jenis_masking"),
              rs.getString("id_masking"),
              rs.getString("qty_masking")
              });
              tabelMasking.setModel(tbl);
              qtymasking.setText("");
          }
        }catch(Exception e){
        }
    }
    public dataMasking() {
        initComponents();
        table ();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
                (screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2
        );
        jamRealTime();
    }
    
    
    private void jamRealTime(){
        new Thread(){
            @Override
            public void run(){
                while(waktuMulai == 0){
                    Calendar kalender = new GregorianCalendar();
                    int jam     = kalender.get(Calendar.HOUR_OF_DAY);
                    int menit   = kalender.get(Calendar.MINUTE);
                    int detik   = kalender.get(Calendar.SECOND);
                    String jamRealTime = jam + ":" + menit + ":" + detik ; 
                    tampilJam.setText(" " + jamRealTime);
                }
            }
        }.start();
    }
    
    void hapus(){


        totalMasking.setText("");
        ComboBoxMasking.setSelectedIndex(0);
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tampilJam = new javax.swing.JLabel();
        panell = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ComboBoxMasking = new javax.swing.JComboBox<>();
        totalMasking = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cekMasking = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelMasking = new javax.swing.JTable();
        qtymasking = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        kebutuhanBawah = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        kebutuhanAtas = new javax.swing.JTable();
        pemakaianAtas = new javax.swing.JTextField();
        pemakaianBawah = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        totalKebutuhanMaskingTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        mps001 = new javax.swing.JTextField();
        mps002 = new javax.swing.JTextField();
        mpe012 = new javax.swing.JTextField();
        mpe010 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PT. ASTARI NIAGARA INTERNASIONAL");
        setBackground(new java.awt.Color(102, 255, 102));

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        tampilJam.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(tampilJam, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1083, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tampilJam, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addContainerGap())
        );

        panell.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/masking/LOGO-ASTARI-footer.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel4.setText("DATA MASKING");

        jLabel10.setText("Jenis Masking");

        ComboBoxMasking.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH", "MPS 001", "MPS 002", "MPE 010", "MPE 012" }));
        ComboBoxMasking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxMaskingActionPerformed(evt);
            }
        });

        totalMasking.setEnabled(false);

        jLabel7.setText("Jumlah Masking");

        cekMasking.setText("Cek");
        cekMasking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cekMaskingMouseClicked(evt);
            }
        });
        cekMasking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekMaskingActionPerformed(evt);
            }
        });

        tabelMasking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tabelMasking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMaskingMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelMasking);

        qtymasking.setEnabled(false);

        jLabel8.setText("Panjang Total Masking");

        jButton3.setText("Cari");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jLabel1.setText("Kekurangan Masking");

        jTextField1.setEnabled(false);

        kebutuhanBawah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        kebutuhanBawah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kebutuhanBawahMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(kebutuhanBawah);

        kebutuhanAtas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        kebutuhanAtas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kebutuhanAtasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(kebutuhanAtas);

        pemakaianAtas.setEnabled(false);

        pemakaianBawah.setEnabled(false);

        jLabel5.setText("Pemakaian Masking Bawah");

        jLabel6.setText("Pemakaian Masking Atas");

        totalKebutuhanMaskingTxt.setEnabled(false);

        jLabel9.setText("Total Kebutuhan");

        jLabel11.setText("Kebutuhan Masking Atas");

        jLabel12.setText("Kebutuhan Masking Bawah");

        jPanel2.setBackground(new java.awt.Color(51, 204, 0));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel13.setText("Form Order");

        jLabel14.setText("MPS 001");

        jLabel15.setText("MPS 002");

        jLabel16.setText("MPE 010");

        mps001.setText("0");

        mps002.setText("0");
        mps002.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mps002ActionPerformed(evt);
            }
        });

        mpe012.setText("0");
        mpe012.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mpe012ActionPerformed(evt);
            }
        });

        mpe010.setText("0");

        jLabel17.setText("MPE 012");

        jButton1.setText("ORDER");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(mps002, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mps001, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mpe010, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mpe012, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(121, 121, 121))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(mps001, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(mps002, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(mpe010, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mpe012, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(45, 45, 45)
                .addComponent(jButton1)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jButton4.setText("Kembali");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panellLayout = new javax.swing.GroupLayout(panell);
        panell.setLayout(panellLayout);
        panellLayout.setHorizontalGroup(
            panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panellLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panellLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(54, 54, 54)
                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalMasking, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qtymasking, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panellLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(totalKebutuhanMaskingTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panellLayout.createSequentialGroup()
                                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panellLayout.createSequentialGroup()
                                        .addGap(81, 81, 81)
                                        .addComponent(pemakaianAtas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panellLayout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(jLabel6)))
                                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panellLayout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(pemakaianBawah, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panellLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5))))))
                    .addGroup(panellLayout.createSequentialGroup()
                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(86, 86, 86)
                        .addComponent(ComboBoxMasking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cekMasking, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panellLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panellLayout.createSequentialGroup()
                            .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel12)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1)
                            .addComponent(jButton3))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panellLayout.createSequentialGroup()
                            .addGap(416, 416, 416)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panellLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(411, 411, 411))
        );
        panellLayout.setVerticalGroup(
            panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panellLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(ComboBoxMasking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cekMasking))
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panellLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panellLayout.createSequentialGroup()
                                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panellLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel8)
                                        .addGap(6, 6, 6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(totalMasking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5)))
                                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panellLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel7))
                                    .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(qtymasking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pemakaianAtas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(pemakaianBawah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(totalKebutuhanMaskingTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panellLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panellLayout.createSequentialGroup()
                                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panellLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panell, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxMaskingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxMaskingActionPerformed

    }//GEN-LAST:event_ComboBoxMaskingActionPerformed

    private void cekMaskingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekMaskingActionPerformed

    }//GEN-LAST:event_cekMaskingActionPerformed

    private void cekMaskingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cekMaskingMouseClicked
     cek_masking();
     kebutuhan_Atas();
     kebutuhan_bawah();
       int atas;
       int bawah;
       int order;
       int totalA;
       int totalB;
        atas = Integer.parseInt(pemakaianAtas.getText());
        bawah = Integer.parseInt(pemakaianBawah.getText());
        order = Integer.parseInt(totalMasking.getText());
        totalA = (atas+bawah);
        totalB = (totalA-order);
        totalKebutuhanMaskingTxt.setText(""+ totalA);
        jTextField1.setText(""+ totalB);
    }//GEN-LAST:event_cekMaskingMouseClicked

    private void tabelMaskingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMaskingMouseClicked
/*        int baris = tabelMasking.rowAtPoint(evt.getPoint());
        String jenis = tabelMasking.getValueAt(baris, 0).toString();
        ComboBoxMasking.setSelectedItem(jenis);
        String id = tabelMasking.getValueAt(baris, 1).toString();
        idMasking.setText(id);
        String qty = tabelMasking.getValueAt(baris, 2).toString();
        panjangMasking.setText(qty); */
    }//GEN-LAST:event_tabelMaskingMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        cari();
        try{
        Connection kon = DriverManager.getConnection("jdbc:mysql://localhost:3306/skripsi","root","");
        ResultSet as = kon.createStatement().executeQuery("select * from tb_masking where id_masking='"+cari.getText()+"'");
       
        }catch (SQLException ex){
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void kebutuhanBawahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kebutuhanBawahMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kebutuhanBawahMouseClicked

    private void kebutuhanAtasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kebutuhanAtasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kebutuhanAtasMouseClicked

    private void mps002ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mps002ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mps002ActionPerformed

    private void mpe012ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mpe012ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mpe012ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if(mps001.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "MPS 001 belum diisi");
        } else if (mps002.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "MPS 002 belum diisi");
        } else if (mpe010.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "MPE 010 belum diisi");
        } else if (mpe012.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "MPE 012 belum diisi");
        } else {
         {
            String sql = "INSERT INTO tb_order VALUES ('"+mps001.getText()+"','"+mps002.getText()+"','"+mpe010.getText()+"','"+mpe012.getText()+"')";
                    try{
         Connection con = (Connection) koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Berhasil mengirim");
            hapus_order();
        } catch (Exception e){
        }
        }
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        proses bd = new proses();
        bd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(dataMasking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dataMasking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dataMasking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dataMasking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dataMasking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxMasking;
    private javax.swing.JTextField cari;
    private javax.swing.JButton cekMasking;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable kebutuhanAtas;
    private javax.swing.JTable kebutuhanBawah;
    private javax.swing.JTextField mpe010;
    private javax.swing.JTextField mpe012;
    private javax.swing.JTextField mps001;
    private javax.swing.JTextField mps002;
    private javax.swing.JPanel panell;
    private javax.swing.JTextField pemakaianAtas;
    private javax.swing.JTextField pemakaianBawah;
    private javax.swing.JTextField qtymasking;
    private javax.swing.JTable tabelMasking;
    private javax.swing.JLabel tampilJam;
    private javax.swing.JTextField totalKebutuhanMaskingTxt;
    private javax.swing.JTextField totalMasking;
    // End of variables declaration//GEN-END:variables

    private Object getDesktopPane() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
