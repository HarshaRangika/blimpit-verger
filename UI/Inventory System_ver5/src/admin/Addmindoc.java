/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import controller.ApiConnector;
import controller.FileHandleingClient;
import controller.FileHandler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Tharusha
 */
public class Addmindoc extends javax.swing.JFrame {

    /**
     * Creates new form Addmindoc
     */
    private String jframeName;
    private int selectedRow = 0;
    private int selectedcolumn = 0;
    private String tempPathFile;
    private String severFilePath;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd");
    Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    private String y = String.format("%1$ty/%1$tm/%1$td", now);

    public Addmindoc(String jframeName) {
        initComponents();
        tempPathFile = "/home/neeshad/Desktop/Blimpit_GIT/blimpit-verger/TestFolder/temp/";
        severFilePath = "/home/neeshad/Desktop/Blimpit_GIT/blimpit-verger/TestFolder/";
        /// Load the existing Files
        lbltitle.setText(lbltitle.getText() + " " + jframeName);
        this.jframeName = jframeName;
        loadform();

    }

    public Addmindoc() {
        initComponents();
        this.jframeName = "No file Name";
    }

    private void loadform() {

        ApiConnector apiHandler = new ApiConnector();
        String get = apiHandler.get("http://localhost:8080/api/fileHandler/getDepartmentFiles?section=" + jframeName);
        // System.out.println(get);

        JSONParser jspaser = new JSONParser();
        JSONArray jsArray = new JSONArray();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        try {
            jsArray = (JSONArray) jspaser.parse(get);
            int sizeArray = jsArray.size();
            for (int i = 0; i < sizeArray; i++) {
                JSONObject jsObject = new JSONObject();
                Vector row = new Vector();
                jsObject = (JSONObject) jsArray.get(i);

                row.add(0, jsObject.get("fileName"));
                row.add(1, jsObject.get("filePath"));

                model.addRow(row);

            }

        } catch (ParseException ex) {
            Logger.getLogger(Addmindoc.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbltitle = new javax.swing.JLabel();
        btnback = new javax.swing.JButton();
        btnaddFiles = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnprint = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        btnarchive = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "File Name", "File Name"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(20);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        lbltitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbltitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitle.setText("Existing Files in ");

        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/Left_20px.png"))); // NOI18N

        btnaddFiles.setText("Add Files");
        btnaddFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddFilesActionPerformed(evt);
            }
        });

        btndelete.setText("Delete");

        btnprint.setText("Print");
        btnprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprintActionPerformed(evt);
            }
        });

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnarchive.setText("Archive");
        btnarchive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnarchiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(lbltitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnaddFiles)
                        .addGap(18, 18, 18)
                        .addComponent(btnprint)
                        .addGap(18, 18, 18)
                        .addComponent(btndelete))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnarchive)
                        .addGap(18, 18, 18)
                        .addComponent(btnOk)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltitle, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnaddFiles)
                        .addComponent(btndelete)
                        .addComponent(btnprint)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnarchive))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(486, 520));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddFilesActionPerformed

//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd");
//        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
//        String y = String.format("%1$ty/%1$tm/%1$td", now);
        FileHandler filehandler = new FileHandler(jframeName, "/home/neeshad/Desktop/Blimpit_GIT/blimpit-verger/TestFolder/Local/" + jframeName);
        String filePath = filehandler.fileName(this);
        System.out.println(filePath);

        if (!jframeName.equals("No file Name")) {

            FileHandleingClient fhc = new FileHandleingClient();
            fhc.uploadFiles(filePath, "http://localhost:8080/api/fileHandler/fileUploader?destination=" + jframeName);

            String Data[] = filePath.split("/");
            String fileName = Data[Data.length - 1];
            System.out.println(fileName);
            fileName = fileName.substring(0, fileName.indexOf("."));
            System.out.println(fileName);

            // Add to the document db
//            JSONObject jsobj = new JSONObject();
//            jsobj.put("fileId", (Data[Data.length - 1].substring(0, Data[Data.length - 1].indexOf("."))));
//            jsobj.put("fileName", (Data[Data.length - 1].substring(0, Data[Data.length - 1].indexOf("."))));
//            jsobj.put("filePath", severFilePath + jframeName + "/" + (Data[Data.length - 1].substring(0, Data[Data.length - 1].indexOf("."))));
//            jsobj.put("section", jframeName);
//            jsobj.put("creationDate", y);
//
//            ApiConnector apiConnector = new ApiConnector();
//            apiConnector.post("http://localhost:8080/api/fileHandler/createDbEntry", jsobj);

        }

        //jTable1.getModel().setValueAt(fileName, jTable1.getModel().getRowCount()-1, 0);
    }//GEN-LAST:event_btnaddFilesActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnprintActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        JTable source = (JTable) evt.getSource();
        this.selectedRow = source.rowAtPoint(evt.getPoint());
        this.selectedcolumn = source.columnAtPoint(evt.getPoint());
        String fileName;

        String data[] = model.getValueAt(selectedRow, 1).toString().split("/");
        int lengthofdata = data.length;
        int index = data[lengthofdata - 1].indexOf(".");

        System.out.println("length :" + lengthofdata);
        System.out.println(data[lengthofdata - 1].indexOf("."));
        //System.out.println(data[lengthofdata - 1].substring(0, data[lengthofdata - 1].indexOf(".")));

        if (index > 1) {
            fileName = data[lengthofdata - 1].substring(0, data[lengthofdata - 1].indexOf("."));
            System.out.println(data[lengthofdata - 1].substring(0, data[lengthofdata - 1].indexOf(".")));
        } else if (index < 0) {
            fileName = data[lengthofdata - 1];
            System.out.println(data[lengthofdata - 1]);
        } else {
            fileName = data[lengthofdata - 1].substring(0);
            System.out.println(data[lengthofdata - 1].substring(0));
        }   
        
        
                      
        FileHandleingClient filehandelingClient = new FileHandleingClient();
        String fileFromServer = filehandelingClient.getFileFromServer("http://localhost:8080/api/fileHandler/getFile?section="+jframeName+"&name="+fileName, "/home/neeshad/Desktop/Blimpit_GIT/blimpit-verger/TestFolder/temp/");
        System.out.println(fileFromServer);
        
        FileHandler fileHandler = new FileHandler(jframeName, "/home/neeshad/Desktop/BlimpitGIT/blimpit-verger/TestFolder/temp/");
        fileHandler.openFile("/home/neeshad/Desktop/BlimpitGIT/blimpit-verger/TestFolder/temp/"+data[data.length-1]);

//        System.out.println("table value :" + model.getValueAt(row, 1));
//        FileHandler file = new FileHandler(jframeName, model.getValueAt(row, 1).toString());
//        System.out.println(file.openFile(model.getValueAt(row, 1).toString()));
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnarchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnarchiveActionPerformed

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        ApiConnector apiConnector = new ApiConnector();
        System.out.println("Row : " + selectedRow);
        System.out.println("Column :" + selectedcolumn);

        System.out.println("File Path : " + model.getValueAt(selectedRow, 1));

        JSONObject jsobj = new JSONObject();
        jsobj.put("fileId", model.getValueAt(selectedRow, 0).toString());
        jsobj.put("fileName", model.getValueAt(selectedRow, 0).toString());
        jsobj.put("filePath", model.getValueAt(selectedRow, 1).toString());
        jsobj.put("section", jframeName);
        jsobj.put("creationDate", y);

        System.out.println(jsobj);

        String post = apiConnector.post("http://localhost:8080/api/fileHandler/moveFile", jsobj);

    }//GEN-LAST:event_btnarchiveActionPerformed

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
            java.util.logging.Logger.getLogger(Addmindoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Addmindoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Addmindoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Addmindoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Addmindoc("Factory").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnaddFiles;
    private javax.swing.JButton btnarchive;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnprint;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbltitle;
    // End of variables declaration//GEN-END:variables
}
