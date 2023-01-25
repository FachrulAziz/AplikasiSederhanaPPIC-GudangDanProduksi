/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gudang;

/**
 *
 * @author ASUS
 */

import PPIC.*;
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
public class tambah_masking extends javax.swing.JFrame {
    private int waktuMulai = 0;
    
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
          
        long totalQtyMasking = 0;
        int qty_masking;
        int jumlahM;
        qty_masking = rs.getRow();
        for (int i = 0; i < tbl.getRowCount(); i++) {
            jumlahM = Integer.parseInt(rs.getString(3));
            totalQtyMasking = totalQtyMasking + jumlahM;
        }
          
        qtymasking.setText("" + qty_masking);
        totalMasking.setText("" + totalQtyMasking);
        cari.setText("");
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
    public tambah_masking() {
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

        idMasking.setText("");
        panjangMasking.setText("");
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        idMasking = new javax.swing.JTextField();
        panjangMasking = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

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
                .addContainerGap(539, Short.MAX_VALUE))
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
        jLabel4.setText("TAMBAH MASKING");

        jLabel5.setText("ID");

        jLabel6.setText("Panjang");

        panjangMasking.setEnabled(false);

        jButton1.setText("Hapus");
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

        jButton2.setText("Simpan");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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

        jLabel8.setText("Panjang Total");

        jButton3.setText("Cari");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("Edit");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Refresh");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panellLayout = new javax.swing.GroupLayout(panell);
        panell.setLayout(panellLayout);
        panellLayout.setHorizontalGroup(
            panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panellLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panellLayout.createSequentialGroup()
                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6))
                        .addGap(73, 73, 73)
                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panellLayout.createSequentialGroup()
                                .addComponent(ComboBoxMasking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cekMasking, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(idMasking, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panjangMasking, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panellLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panellLayout.createSequentialGroup()
                                    .addGap(533, 533, 533)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panellLayout.createSequentialGroup()
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(48, 48, 48)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panellLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panellLayout.createSequentialGroup()
                                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panellLayout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(33, 33, 33))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panellLayout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(qtymasking)
                                            .addComponent(totalMasking, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panellLayout.createSequentialGroup()
                                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(jButton3))))))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panellLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel4)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        panellLayout.setVerticalGroup(
            panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panellLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(ComboBoxMasking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cekMasking))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idMasking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panjangMasking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panellLayout.createSequentialGroup()
                        .addComponent(totalMasking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qtymasking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jLabel8))
                .addGap(29, 29, 29)
                .addGroup(panellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(panell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
          if(panjangMasking.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Jenis Masking Belum dipilih");
        } else if (idMasking.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "ID Masking Belum diisi");
        } else {
         {
            String sql = "INSERT INTO tb_masking VALUES ('" + ComboBoxMasking.getSelectedItem() + "','"
                    + idMasking.getText() + "','" + panjangMasking.getText()+"')";
                    try{
         Connection con = (Connection) koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Berhasil Menyimpan");
            hapus();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ID Masking Sudah Terpakai");
        }
        }
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
           String sql = "delete from tb_masking where id_masking='" + cari.getText()+"'";
           Connection con = (Connection) koneksi.getConnection();
           PreparedStatement pst = con.prepareStatement(sql);
           pst.execute();
           JOptionPane.showMessageDialog(null, "Berhasil Menghapus");

        } catch (Exception e){
            
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ComboBoxMaskingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxMaskingActionPerformed
        switch(ComboBoxMasking.getSelectedIndex()){
            case 0:
                panjangMasking.setText("");
                
            break;
            case 1: 
            panjangMasking.setText("700");
            break;
            case 2:
                panjangMasking.setText("700");
            break;
            case 3:
                panjangMasking.setText("700");
            break;
            default:
                panjangMasking.setText("700");
            break;           
        }
    }//GEN-LAST:event_ComboBoxMaskingActionPerformed

    private void cekMaskingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekMaskingActionPerformed

    }//GEN-LAST:event_cekMaskingActionPerformed

    private void cekMaskingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cekMaskingMouseClicked
     cek_masking();
    }//GEN-LAST:event_cekMaskingMouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        try {
          String sql = "update tb_masking set jenis_masking='"+ComboBoxMasking.getSelectedItem()+"',id_masking='"+idMasking.getText()+"',qty_masking='"+panjangMasking.getText()+"' where id_masking='"+cari.getText()+"'";
            Connection con = (Connection) koneksi.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Edit");
            hapus();
        }catch(Exception e) {
        }
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabelMaskingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMaskingMouseClicked
        int baris = tabelMasking.rowAtPoint(evt.getPoint());
        String jenis = tabelMasking.getValueAt(baris, 0).toString();
        ComboBoxMasking.setSelectedItem(jenis);
        String id = tabelMasking.getValueAt(baris, 1).toString();
        idMasking.setText(id);
        String qty = tabelMasking.getValueAt(baris, 2).toString();
        panjangMasking.setText(qty); 
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

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        table();
        hapus ();
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
            java.util.logging.Logger.getLogger(tambah_masking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tambah_masking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tambah_masking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tambah_masking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tambah_masking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxMasking;
    private javax.swing.JTextField cari;
    private javax.swing.JButton cekMasking;
    private javax.swing.JTextField idMasking;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panell;
    private javax.swing.JTextField panjangMasking;
    private javax.swing.JTextField qtymasking;
    private javax.swing.JTable tabelMasking;
    private javax.swing.JLabel tampilJam;
    private javax.swing.JTextField totalMasking;
    // End of variables declaration//GEN-END:variables

    private Object getDesktopPane() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
