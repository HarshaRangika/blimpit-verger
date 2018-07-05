/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_bin_delete_after;

import production.*;
import com.toedter.calendar.JTextFieldDateEditor;
import controller.ApiConnector;
import controller.Client;
import controller.ProductController;
import controller.StockController;
import gui.VergerMain;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static my_bin_delete_after.Formulation_old.lf;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author harsha
 */
public class UpdateStocks2 extends javax.swing.JFrame {

    private ArrayList<ProductController> proData;
    private ArrayList<StockController> stkData;
    private String proID = null;
    private String product = null;
    private String selectedRow = null;

    public UpdateStocks2(String productID) {
        lookandfeels();
        initComponents();
        ImageIcon icon = new ImageIcon("Image/icon2.png");
        setIconImage(icon.getImage());

        LoadStock(productID);
        loardProducts();

        JTextFieldDateEditor editor = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        editor.setEditable(false);
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_becoming = new javax.swing.JTextField();
        jTextField_batchNo = new javax.swing.JTextField();
        jTextField_quantity = new javax.swing.JTextField();
        jTextField_rmMaterial = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update Stock 2 (Test Form)");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Batch No", "Quantity", "Raw Materials Tracking", "Production Date", "Becoming"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Product" }));
        jComboBox1.setMinimumSize(new java.awt.Dimension(10, 32));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel6.setText("Product Name");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel1.setText("Batch No.");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel2.setText("Quantity");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel3.setText("Raw Materials Tracking");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel4.setText("Production Date");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel5.setText("Becoming");

        jTextField_becoming.setMinimumSize(new java.awt.Dimension(10, 32));

        jTextField_batchNo.setMinimumSize(new java.awt.Dimension(10, 32));

        jTextField_quantity.setMinimumSize(new java.awt.Dimension(10, 32));
        jTextField_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_quantityActionPerformed(evt);
            }
        });
        jTextField_quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_quantityKeyTyped(evt);
            }
        });

        jTextField_rmMaterial.setMinimumSize(new java.awt.Dimension(10, 32));

        jDateChooser1.setDateFormatString("MM/dd/yyyy");
        jDateChooser1.setMinimumSize(new java.awt.Dimension(10, 32));

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/production/Left_20px.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Powerd By");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Blimp_50x.png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_batchNo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField_quantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextField_rmMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jTextField_becoming, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addGap(964, 964, 964)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel1))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(jTextField_batchNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_quantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField_rmMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_becoming, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel12)
                .addGap(0, 0, 0)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        setSize(new java.awt.Dimension(1184, 587));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LoadStock(String productID) {

        ApiConnector apihandler = new ApiConnector();
        String get = apihandler.get("http://localhost:8080/api/production/stockdata");

        System.out.println(get);

        JSONParser parser = new JSONParser();
        org.json.simple.JSONArray array = null;

        int arrayLength = 0;

        try {
            array = (org.json.simple.JSONArray) parser.parse(get);
            arrayLength = array.size();

            // System.out.println(array);
            Vector data;
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            JSONObject jsondata = new JSONObject();

            StockController stk;
            stkData = new ArrayList<>();

            for (int i = 0; i < arrayLength; i++) {
                jsondata = (JSONObject) array.get(i);
                if (productID != null) {
                    if (productID.equals(jsondata.get("productID").toString())) {

                        data = new Vector();
                        stk = new StockController();

                        data.add(jsondata.get("batchNo"));
                        data.add(jsondata.get("quantuty"));
                        data.add(jsondata.get("rmTracking"));
                        data.add(jsondata.get("productionDate"));
                        data.add(jsondata.get("becoming"));

                        dtm.addRow(data);

                        stk.setBatchNo(jsondata.get("batchNo").toString());
                        stk.setQuantuty(Integer.parseInt(jsondata.get("quantuty").toString()));
                        stk.setRmTracking(jsondata.get("rmTracking").toString());
                        stk.setProductionDate(jsondata.get("productionDate").toString());
                        stk.setBecoming(jsondata.get("becoming").toString());
                        stk.setProductID(jsondata.get("productID").toString());

                        stkData.add(stk);
                    }
                }else{
                    data = new Vector();
                        stk = new StockController();

                        data.add(jsondata.get("batchNo"));
                        data.add(jsondata.get("quantuty"));
                        data.add(jsondata.get("rmTracking"));
                        data.add(jsondata.get("productionDate"));
                        data.add(jsondata.get("becoming"));

                        dtm.addRow(data);

                        stk.setBatchNo(jsondata.get("batchNo").toString());
                        stk.setQuantuty(Integer.parseInt(jsondata.get("quantuty").toString()));
                        stk.setRmTracking(jsondata.get("rmTracking").toString());
                        stk.setProductionDate(jsondata.get("productionDate").toString());
                        stk.setBecoming(jsondata.get("becoming").toString());
                        stk.setProductID(jsondata.get("productID").toString());

                        stkData.add(stk);
                }

            }

            //   for (StockController sc : stkData) {
            //     System.out.println("This is ArrayList. Batch No. = " + sc.getBatchNo() + " && PID = " + sc.getProductID());
            //  }
        } catch (ParseException ex) {
            ex.printStackTrace();
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            int a = jTable1.getSelectedRow();

            selectedRow = String.valueOf(a);

            String batchNo = dtm.getValueAt(a, 0).toString();

            for (StockController sc : stkData) {
                if (batchNo.equals(sc.getBatchNo())) {
                    for (ProductController pcc : proData) {
                        if (sc.getProductID().equals(pcc.getProductID())) {
                            Date dd = new Date(dtm.getValueAt(a, 3).toString());

                            jComboBox1.setSelectedItem(pcc.getProductName());
                            jTextField_batchNo.setText(dtm.getValueAt(a, 0).toString());
                            jTextField_quantity.setText(dtm.getValueAt(a, 1).toString());
                            jTextField_rmMaterial.setText(dtm.getValueAt(a, 2).toString());
                            jDateChooser1.setDate(dd);
                            jTextField_becoming.setText(dtm.getValueAt(a, 4).toString());
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_quantityActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Stocks stk = new Stocks();
        stk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged


    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            if (selectedRow == null) {
                JOptionPane.showMessageDialog(null, "Please select the row that you want to change..");
            } else if (jTextField_batchNo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Batch No. is empty.");

            } else if (jTextField_quantity.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Quantity is empty.");

            } else if (jTextField_rmMaterial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rar Material is empty.");

            } else if (jDateChooser1.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Date is empty.");

            } else if (jTextField_becoming.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Becoming is empty.");

            } else if (jComboBox1.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Product Name is empty.");

            } else {
                org.json.simple.JSONObject jobject = new org.json.simple.JSONObject();
                Client clnt = new Client();

                product = jComboBox1.getSelectedItem().toString();

                for (ProductController pd : proData) {
                    if (pd.getProductName() == product) {
                        proID = pd.getProductID();
                        System.out.println("proID = " + proID);
                    }
                }

                System.out.println("product = " + product);

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date todate = jDateChooser1.getDate();
                String stringDate = df.format(todate);

                jobject.put("batchNo", jTextField_batchNo.getText().toString());
                jobject.put("quantuty", jTextField_quantity.getText().toString());
                jobject.put("rmTracking", jTextField_rmMaterial.getText().toString());
                jobject.put("productionDate", stringDate);
                jobject.put("becoming", jTextField_becoming.getText().toString());
                jobject.put("productID", proID);

                System.out.println("JOBJECT === " + jobject);
                clnt.sendData("http://localhost:8080/api/production/updateStk", jobject);

                loardProducts();
//            LoadStock();

                selectedRow = null;

                UpdateStocks2 upstock = new UpdateStocks2("");
                upstock.setVisible(true);
                this.dispose();
            }

//            proID = null;
//            product = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_quantityKeyTyped

        char enter = evt.getKeyChar();
        if (!(Character.isDigit(enter))) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextField_quantityKeyTyped

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
            java.util.logging.Logger.getLogger(UpdateStocks2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateStocks2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateStocks2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateStocks2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateStocks2("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_batchNo;
    private javax.swing.JTextField jTextField_becoming;
    private javax.swing.JTextField jTextField_quantity;
    private javax.swing.JTextField jTextField_rmMaterial;
    // End of variables declaration//GEN-END:variables
}
