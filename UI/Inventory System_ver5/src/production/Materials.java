/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package production;

import controller.ApiConnector;
import controller.MaterialController;
import controller.ProductController;
import lab.*;
import gui.VergerMain;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;

import java.util.Date;
import java.util.Random;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Tharusha
 */
public class Materials extends javax.swing.JFrame {

    static String lf = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";

    private ArrayList<ProductController> proData;
    private ArrayList<MaterialController> matData;
    private String proID = null;
    private String deleteID;

    public Materials() {
        lookandfeels();
        initComponents();
        ImageIcon icon = new ImageIcon("Image/icon2.png");
        setIconImage(icon.getImage());

        LoadMaterials();
        loardProducts();

    }

    public void LoadMaterials() {

        ApiConnector apihandler = new ApiConnector();
        String get = apihandler.get("http://localhost:8080/api/production/rmdata");

        JSONParser parser = new JSONParser();
        JSONArray array = null;

        int arrayLength = 0;

        try {
            array = (JSONArray) parser.parse(get);
            arrayLength = array.size();

            System.out.println(array);

            Vector data;
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            JSONObject jsondata = new JSONObject();

            MaterialController mtc;
            matData = new ArrayList<>();

            for (int i = 0; i < arrayLength; i++) {
                jsondata = (JSONObject) array.get(i);
                data = new Vector();
                mtc = new MaterialController();

                data.add(jsondata.get("rmId"));
                data.add(jsondata.get("quantity"));
                data.add(jsondata.get("origin"));
                data.add(jsondata.get("grade"));
                data.add(jsondata.get("packing"));
                data.add(jsondata.get("storagePortion"));
                data.add(jsondata.get("buyingVoucherPicture"));

                dtm.addRow(data);

                mtc.setRmId(jsondata.get("rmId").toString());
                mtc.setQuantity(Integer.parseInt(jsondata.get("quantity").toString()));
                mtc.setOrigin(jsondata.get("origin").toString());
                mtc.setGrade(jsondata.get("grade").toString());
                mtc.setPacking(jsondata.get("packing").toString());
                mtc.setStoragePortion(Integer.parseInt(jsondata.get("storagePortion").toString()));
                mtc.setBuyingVoucherPicture(jsondata.get("buyingVoucherPicture").toString());
                mtc.setProductID(jsondata.get("productID").toString());

                matData.add(mtc);

            }

            for (MaterialController mt : matData) {
                System.out.println("This is ArrayList. RM ID = " + mt.getRmId() + " && PID = " + mt.getProductID());
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }

    private void loardSelectedMaterials() {

        try {
            
            if(jComboBox1.getSelectedIndex() == 0){
            
            LoadMaterials();
            
        }else {
            
            DefaultTableModel dtm2 = (DefaultTableModel) jTable1.getModel();
        Vector selectedData;

        dtm2.setRowCount(0);

        System.out.println("Row Selected...");
        String product = jComboBox1.getSelectedItem().toString();

        for (ProductController pd : proData) {
            if (pd.getProductName() == product) {
                proID = pd.getProductID();
                System.out.println("proID == " + proID);

            }
        }

        System.out.println(matData.isEmpty());

        for (MaterialController mc : matData) {
            System.out.println("proID Key : " + proID + " == MatKEy : " + mc.getProductID());

            if (proID.equals(mc.getProductID())) {

                System.out.println("Samanaaaaai");

                selectedData = new Vector();

                selectedData.add(mc.getRmId());
                selectedData.add(mc.getQuantity());
                selectedData.add(mc.getOrigin());
                selectedData.add(mc.getGrade());
                selectedData.add(mc.getPacking());
                selectedData.add(mc.getStoragePortion());
                selectedData.add(mc.getBuyingVoucherPicture());

                dtm2.addRow(selectedData);
            }

        }

        jTable1.setModel(dtm2);
            
        }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loardProducts() {
        try {

            ApiConnector apihandler = new ApiConnector();
            String get = apihandler.get("http://localhost:8080/api/production/getPro");

            JSONParser parser = new JSONParser();
            JSONArray array = null;

            int arrayLength = 0;

            try {
                array = (JSONArray) parser.parse(get);
                arrayLength = array.size();

                //   System.out.println(array);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            // Vector data;
            JSONObject jsondata = new JSONObject();

            ProductController pdc;
            proData = new ArrayList<>();

            for (int i = 0; i < arrayLength; i++) {
                jsondata = (JSONObject) array.get(i);
                //   data = new Vector();            
                pdc = new ProductController();
                pdc.setProductID(jsondata.get("productID").toString());
                pdc.setProductName(jsondata.get("productName").toString());

                proData.add(pdc);
                jComboBox1.addItem(jsondata.get("productName").toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void lookandfeels() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
                javax.swing.UIManager.setLookAndFeel(lf);
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VergerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VergerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VergerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VergerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnBack = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Raw Materials");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RM ID No.", "Quantity", "Origin", "Grade", "Packing", "Storage Portion", "Buying Voucher Picture"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Add New Material");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Powerd By");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Blimp_50x.png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 0, 0)
                .addComponent(jLabel13))
        );

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product Selection" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/production/Left_20px.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Update Material");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1223, 593));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddMaterials addm = new AddMaterials();
        addm.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

       try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

            int a = jTable1.getSelectedRow();
            deleteID = dtm.getValueAt(a, 0).toString();

            System.out.println(deleteID);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        VergerMain vm = new VergerMain("admin");
        vm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked

        loardSelectedMaterials();

    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        loardSelectedMaterials();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            ApiConnector apiconn = new ApiConnector();
            String delete = apiconn.delete("http://localhost:8080/api/production/deleteMat/"+deleteID);
            
            LoadMaterials();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        UpdateMaterials upm = new UpdateMaterials(proID);
        upm.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        lookandfeels();
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Materials().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
