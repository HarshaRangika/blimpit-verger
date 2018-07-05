/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package production;

import controller.ApiConnector;
import controller.MaterialController;
import controller.ProductController;
import controller.StockController;
import gui.VergerMain;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;

import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import my_bin_delete_after.UpdateStocks2;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Tharusha
 */
public class Stocks extends javax.swing.JFrame {

    static String lf = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";

    private ArrayList<ProductController> proData;
    private ArrayList<StockController> stkData;
    private String proID = null;
    private String deleteID;

    public Stocks() {
        lookandfeels();
        initComponents();
        ImageIcon icon = new ImageIcon("Image/icon2.png");
        setIconImage(icon.getImage());

        LoadStock();
        loardProducts();

    }

    /**
     * public void getData(String InventoryName, Date date, int quantity, Double
     * pricePreUnit) { //getData(String InventoryName,Date date,int
     * quantity,Double pricePreUnit){ DefaultTableModel model =
     * (DefaultTableModel) tabLab.getModel(); Vector row = new Vector();
     *
     * row.add(InventoryName); row.add(date.toString()); row.add(quantity);
     * row.add(pricePreUnit); row.add(quantity * pricePreUnit);
     *
     * }
     *
     */
    private void LoadStock() {

        ApiConnector apihandler = new ApiConnector();
        String get = apihandler.get("http://localhost:8080/api/production/stockdata");

        JSONParser parser = new JSONParser();
        org.json.simple.JSONArray array = null;

        int arrayLength = 0;

        try {
            array = (org.json.simple.JSONArray) parser.parse(get);
            arrayLength = array.size();

            System.out.println(array);

            Vector data;
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            JSONObject jsondata = new JSONObject();

            StockController stk;
            stkData = new ArrayList<>();

            for (int i = 0; i < arrayLength; i++) {
                jsondata = (JSONObject) array.get(i);
                data = new Vector();
                stk = new StockController();

                data.add(jsondata.get("batchNo"));
                data.add(jsondata.get("quantuty"));
                data.add(jsondata.get("rmTracking"));
                data.add(jsondata.get("productionDate"));
                data.add(jsondata.get("becoming"));
                data.add(jsondata.get("document"));

                dtm.addRow(data);

                stk.setBatchNo(jsondata.get("batchNo").toString());
                stk.setQuantuty(Integer.parseInt(jsondata.get("quantuty").toString()));
                stk.setRmTracking(jsondata.get("rmTracking").toString());
                stk.setProductionDate(jsondata.get("productionDate").toString());
                stk.setBecoming(jsondata.get("becoming").toString());
                stk.setProductID(jsondata.get("productID").toString());
                stk.setDocument(jsondata.get("document").toString());

                stkData.add(stk);

            }

            for (StockController sc : stkData) {
                System.out.println("This is ArrayList. Batch No. = " + sc.getBatchNo() + " && PID = " + sc.getProductID());
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void loardSelectedStocks() {

        try {

            if (jComboBox1.getSelectedIndex() == 0) {

                LoadStock();

            } else {

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

                System.out.println(stkData.isEmpty());

                for (StockController sc : stkData) {
                    System.out.println("proID Key : " + proID + " == MatKEy : " + sc.getProductID());

                    if (proID.equals(sc.getProductID())) {

                        System.out.println("Samanaaaaai");

                        selectedData = new Vector();

                        selectedData.add(sc.getBatchNo());
                        selectedData.add(sc.getQuantuty());
                        selectedData.add(sc.getRmTracking());
                        selectedData.add(sc.getProductionDate());
                        selectedData.add(sc.getBecoming());
                        selectedData.add(sc.getDocument());

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
            org.json.simple.JSONArray array = null;

            int arrayLength = 0;

            try {
                array = (org.json.simple.JSONArray) parser.parse(get);
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
        btnback = new javax.swing.JButton();
        jButton_addStock = new javax.swing.JButton();
        jButton_deleteStock = new javax.swing.JButton();
        jButton_deleteStock1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Stock");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Batch No", "Quantity", "Raw Materials Tracking", "Production Date", "Becoming", "Document"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        jButton1.setText("Download");
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

        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/production/Left_20px.png"))); // NOI18N
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        jButton_addStock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_addStock.setText("Add Stock");
        jButton_addStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addStockActionPerformed(evt);
            }
        });

        jButton_deleteStock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_deleteStock.setText("Delete");
        jButton_deleteStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteStockActionPerformed(evt);
            }
        });

        jButton_deleteStock1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_deleteStock1.setText("Update Stocks");
        jButton_deleteStock1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteStock1ActionPerformed(evt);
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_deleteStock1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_deleteStock, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_addStock, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_addStock, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_deleteStock, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_deleteStock1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        ///ADD the required Code here

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

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        VergerMain vm = new VergerMain("admin");
        vm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    private void jButton_addStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addStockActionPerformed
        AddStocks astk = new AddStocks();
        astk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton_addStockActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        loardSelectedStocks();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton_deleteStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteStockActionPerformed

        try {

            if (deleteID != null) {
                ApiConnector apihandler = new ApiConnector();
                String delete = apihandler.delete("http://localhost:8080/api/production/deleteStk/" + deleteID);

                LoadStock();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton_deleteStockActionPerformed

    private void jButton_deleteStock1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteStock1ActionPerformed
        UpdateStocks us = new UpdateStocks(proID);
        // UpdateStocks2 us = new UpdateStocks2(proID);
        us.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton_deleteStock1ActionPerformed

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
                new Stocks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_addStock;
    private javax.swing.JButton jButton_deleteStock;
    private javax.swing.JButton jButton_deleteStock1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
