/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import gui.VergerMain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JTable;

import java.util.Date;
import java.util.Objects;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tharusha
 */
public class Formulation extends javax.swing.JFrame {

    private JFileChooser jChooser;
    private Vector headers = new Vector();
    private Vector data = new Vector();
    private int tableWidth = 0;
    // set the tableWidth 
    private int tableHeight = 0;
    // set the tableHeight 
    private DefaultTableModel model = null;
    private JButton jbClick;

    private String getColID = null;
    private String getRowID = null;
    private File file;

    private boolean oldType = false;
    private boolean newType = false;

    public Formulation() {
        lookandfeels();
        initComponents();
        ImageIcon icon = new ImageIcon("Image/icon2.png");
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        jChooser = new JFileChooser();
        jbClick = new JButton("Select Excel File");
        buttonPanel.add(jbClick, BorderLayout.CENTER);

        // table = new JTable();
        jTable1.setAutoCreateRowSorter(true);
        model = new DefaultTableModel(data, headers);
        jTable1.setModel(model);
        // jTable1.setBackground(Color.pink);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // jTable1.setEnabled(false);
        jTable1.setRowHeight(25);
        jTable1.setRowMargin(4);
        tableWidth = model.getColumnCount() * 150;
        tableHeight = model.getRowCount() * 25;
        jTable1.setPreferredSize(new Dimension(tableWidth, tableHeight));

    }

    /**
     * public void getData(String InventoryName, Date date, int quantity, Double
     * pricePreUnit) { //getData(String InventoryName,Date date,int
     * quantity,Double pricePreUnit){ DefaultTableModel model =
     * (DefaultTableModel) jTable1.getModel(); Vector row = new Vector();
     *
     * row.add(InventoryName); row.add(date.toString()); row.add(quantity);
     * row.add(pricePreUnit); row.add(quantity * pricePreUnit);
     *
     * }
     *
     */
    private void fillData(File file) {
        int index = -1;
        HSSFWorkbook workbook = null;
        XSSFWorkbook newworkbook = null;
        FileInputStream inputStream = null;

        oldType = file.getName().endsWith("xls");
        newType = file.getName().endsWith("xlsx");

        System.out.println(file);

        try {

            if (oldType == true) {

                inputStream = new FileInputStream(file);
                workbook = new HSSFWorkbook(inputStream);

                String[] strs = new String[workbook.getNumberOfSheets()];
                //get all sheet names from selected workbook
                for (int i = 0; i < strs.length; i++) {
                    strs[i] = workbook.getSheetName(i);

                }

                JFrame frame = new JFrame("Input Dialog");
                String selectedsheet = (String) JOptionPane.showInputDialog(frame, "Which worksheet you want to import ?", "Select Worksheet",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        strs,
                        strs[0]);

                if (selectedsheet != null) {
                    for (int i = 0; i < strs.length; i++) {
                        if (workbook.getSheetName(i).equalsIgnoreCase(selectedsheet)) {
                            index = i;
                        }

                    }

                    HSSFSheet sheet = workbook.getSheetAt(index);
                    HSSFRow row = sheet.getRow(0);
                    headers.clear();
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        HSSFCell cell1 = row.getCell(i);
                        headers.add(cell1.toString());
                    }
                    data.clear();
                    for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                        Vector d = new Vector();
                        row = sheet.getRow(j);
                        int noofrows = row.getLastCellNum();
                        for (int i = 0; i < noofrows; i++) {
                            //To handle empty excel cells
                            HSSFCell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
                            System.out.println(cell.getCellType());
                            d.add(cell.toString());

                        }
                        d.add("\n");
                        data.add(d);
                    }

                } else {
                    return;
                }
            } else if (newType == true) {

                inputStream = new FileInputStream(file);
                newworkbook = new XSSFWorkbook(inputStream);

                String[] strs = new String[newworkbook.getNumberOfSheets()];
                //get all sheet names from selected workbook
                for (int i = 0; i < strs.length; i++) {
                    strs[i] = newworkbook.getSheetName(i);

                }

                JFrame frame = new JFrame("Input Dialog");
                String selectedsheet = (String) JOptionPane.showInputDialog(frame, "Which worksheet you want to import ?", "Select Worksheet",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        strs,
                        strs[0]);

                if (selectedsheet != null) {
                    for (int i = 0; i < strs.length; i++) {
                        if (newworkbook.getSheetName(i).equalsIgnoreCase(selectedsheet)) {
                            index = i;
                        }

                    }

                    XSSFSheet sheet = newworkbook.getSheetAt(index);
                    XSSFRow row = sheet.getRow(0);
                    headers.clear();
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        XSSFCell cell1 = row.getCell(i);
                        headers.add(cell1.toString());
                    }
                    data.clear();
                    for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                        Vector d = new Vector();
                        row = sheet.getRow(j);
                        int noofrows = row.getLastCellNum();
                        for (int i = 0; i < noofrows; i++) {
                            //To handle empty excel cells
                            XSSFCell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
                            System.out.println(cell.getCellType());
                            d.add(cell.toString());

                        }
                        d.add("\n");
                        data.add(d);
                    }

                } else {
                    return;
                }
            }
            setOpenFileName();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {

                    inputStream.close();

                }
                if (workbook != null) {
                    workbook.close();
                }
                if (newworkbook != null) {
                    newworkbook.close();
                }
            } catch (Exception e) {
            }
        }

    }

    /////////////  Update Button /////////
    private void ExportDataToExcel() {

        FileOutputStream fileOutputStream = null;
        HSSFWorkbook fWorkbook = null;
        XSSFWorkbook newfWorkbook = null;

        oldType = file.getName().endsWith("xls");
        newType = file.getName().endsWith("xlsx");
        try {

            if (oldType == true) {

                fWorkbook = new HSSFWorkbook();
                HSSFSheet fSheet;
                fSheet = fWorkbook.createSheet("new Sheet");
                HSSFFont sheetTitleFont = fWorkbook.createFont();
                /// Save wena thana path eka
                //File file = new File("/media/harsha/New Volume/FileCreateTest/ReadWrite.xlsx");

                ///////// Add Product & Code To File Path Startrs /////////////
                String product = jComboBox1.getSelectedItem().toString();
                String code = jComboBox2.getSelectedItem().toString();
                //   System.out.println("Product : " + product + " & Code : " + code);

                String[] ffpath = file.toString().split("/");
                //File eke name eka one nam...
                String fileName = ffpath[ffpath.length - 1];

                String temp = "";
                for (int i = 0; i < ffpath.length - 1; i++) {

                    temp = temp + "/" + ffpath[i];

                }

                String localFilePath = temp.substring(1, temp.length()) + "/" + product + "_" + code + ".xls";
                System.out.println("Local Path : " + localFilePath);

                File file = new File(localFilePath);

                String serverPath = "/media/harsha/New Volume/FileCreateTest/server/" + product + "_" + code + "_" + ".xls";
                //  System.out.println("File Name : " + fileName);

                File serverFile = new File(serverPath);

                ///////// Add Product & Code To File Path Ends /////////////
                HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
                //   sheetTitleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                //sheetTitleFont.setColor();
                TableModel emodel = jTable1.getModel();

                TableColumnModel model1 = jTable1.getTableHeader().getColumnModel();
                HSSFRow fRow1 = fSheet.createRow((short) 0);
                for (int i = 0; i < model1.getColumnCount(); i++) {
                    HSSFCell cell = fRow1.createCell((short) i);
                    cell.setCellValue(model1.getColumn(i).getHeaderValue().toString());

                }

                ////////////////////
                ////////////////
                for (int i = 1; i < emodel.getRowCount() + 1; i++) {
                    HSSFRow fRow = fSheet.createRow((short) i);
                    for (int j = 0; j < emodel.getColumnCount(); j++) {
                        HSSFCell cell = fRow.createCell((short) j);
                        // cell.setCellValue(model.getValueAt(i, j).toString());
                        cell.setCellValue(Objects.toString(emodel.getValueAt(i - 1, j), ""));
                        cell.setCellStyle(cellStyle);
                    }
                }

                if (jComboBox3.getSelectedIndex() == 1) {
                    fileOutputStream = new FileOutputStream(file);
                    try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
                        fWorkbook.write(bos);
                        JOptionPane.showMessageDialog(null, "Save Successfully.");
                    }
                } else if (jComboBox3.getSelectedIndex() == 2) {
                    fileOutputStream = new FileOutputStream(serverFile);
                    try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
                        fWorkbook.write(bos);
                        JOptionPane.showMessageDialog(null, "Save Successfully.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pleace select Save location.");

                }
            } else if (newType == true) {

                fWorkbook = new HSSFWorkbook();
                HSSFSheet fSheet;
                fSheet = fWorkbook.createSheet("new Sheet");
                HSSFFont sheetTitleFont = fWorkbook.createFont();
                /// Save wena thana path eka
                //File file = new File("/media/harsha/New Volume/FileCreateTest/ReadWrite.xlsx");

                ///////// Add Product & Code To File Path Startrs /////////////
                String product = jComboBox1.getSelectedItem().toString();
                String code = jComboBox2.getSelectedItem().toString();
                //   System.out.println("Product : " + product + " & Code : " + code);

                String[] ffpath = file.toString().split("/");
                //File eke name eka one nam...
                String fileName = ffpath[ffpath.length - 1];

                String temp = "";
                for (int i = 0; i < ffpath.length - 1; i++) {

                    temp = temp + "/" + ffpath[i];

                }

                String localFilePath = temp.substring(1, temp.length()) + "/" + product + "_" + code + ".xlsx";
                System.out.println("Local Path : " + localFilePath);

                File file = new File(localFilePath);

                String serverPath = "/media/harsha/New Volume/FileCreateTest/server/" + product + "_" + code + "_" + ".xlsx";
                //  System.out.println("File Name : " + fileName);

                File serverFile = new File(serverPath);

                ///////// Add Product & Code To File Path Ends /////////////
                HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
                //   sheetTitleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                //sheetTitleFont.setColor();
                TableModel emodel = jTable1.getModel();

                TableColumnModel model1 = jTable1.getTableHeader().getColumnModel();
                HSSFRow fRow1 = fSheet.createRow((short) 0);
                for (int i = 0; i < model1.getColumnCount(); i++) {
                    HSSFCell cell = fRow1.createCell((short) i);
                    cell.setCellValue(model1.getColumn(i).getHeaderValue().toString());

                }

                ////////////////////
                ////////////////
                for (int i = 1; i < emodel.getRowCount() + 1; i++) {
                    HSSFRow fRow = fSheet.createRow((short) i);
                    for (int j = 0; j < emodel.getColumnCount(); j++) {
                        HSSFCell cell = fRow.createCell((short) j);
                        // cell.setCellValue(model.getValueAt(i, j).toString());
                        cell.setCellValue(Objects.toString(emodel.getValueAt(i - 1, j), ""));
                        cell.setCellStyle(cellStyle);
                    }
                }

                if (jComboBox3.getSelectedIndex() == 1) {
                    fileOutputStream = new FileOutputStream(file);
                    try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
                        fWorkbook.write(bos);
                        JOptionPane.showMessageDialog(null, "Save Successfully.");
                    }
                } else if (jComboBox3.getSelectedIndex() == 2) {
                    fileOutputStream = new FileOutputStream(serverFile);
                    try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
                        fWorkbook.write(bos);
                        JOptionPane.showMessageDialog(null, "Save Successfully.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pleace select Save location.");

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to Save the Document", "Error..", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (fWorkbook != null) {
                    fWorkbook.close();
                }
                if (newfWorkbook != null) {
                    newfWorkbook.close();
                }

            } catch (Exception e) {
            }
        }

    }

    private void setOpenFileName() {
        String[] ffpath = file.toString().split("/");
        String fileName = ffpath[ffpath.length - 1];
        jLabel2.setText("File : " + fileName);
    }

    private void ChangeTableTitels() {

        try {
            if (getColID != null) {

                int textCount = jText_Titel.getText().length();

                if (textCount != 0) {
                    JTableHeader header = jTable1.getTableHeader();
                    TableColumnModel colMod = header.getColumnModel();
                    TableColumn tabCol = colMod.getColumn(Integer.parseInt(getColID));
                    tabCol.setHeaderValue(jText_Titel.getText());
                    header.repaint();
                    getColID = null;
                    temporarySave();
                    JOptionPane.showMessageDialog(null, "Tilel Change Successfully to " + jText_Titel.getText());
                    System.out.println("The title is change..");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter Title name.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select the Colum that want to change title.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to Change the Titel", "Error..", JOptionPane.ERROR_MESSAGE);
        }

    }

    ////////// Temporary File Controll Starts //////////////////////////////////////////////////////
    private void temporarySave() {

        HSSFWorkbook fWorkbook = null;
        XSSFWorkbook newfWorkbook = null;
        FileOutputStream fileOutputStream = null;

        oldType = file.getName().endsWith("xls");
        newType = file.getName().endsWith("xlsx");

        try {

            if (oldType == true) {

                fWorkbook = new HSSFWorkbook();
                HSSFSheet fSheet;
                fSheet = fWorkbook.createSheet("new Sheet");
                HSSFFont sheetTitleFont = fWorkbook.createFont();
                File file = new File("src/files/temporary_data.xls");

                HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
                //   sheetTitleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                //sheetTitleFont.setColor();
                TableModel emodel = jTable1.getModel();

                TableColumnModel model1 = jTable1.getTableHeader().getColumnModel();
                HSSFRow fRow1 = fSheet.createRow((short) 0);
                for (int i = 0; i < model1.getColumnCount(); i++) {
                    HSSFCell cell = fRow1.createCell((short) i);
                    cell.setCellValue(model1.getColumn(i).getHeaderValue().toString());

                }
                for (int i = 1; i < emodel.getRowCount() + 1; i++) {
                    HSSFRow fRow = fSheet.createRow((short) i);
                    for (int j = 0; j < emodel.getColumnCount(); j++) {
                        HSSFCell cell = fRow.createCell((short) j);
                        // cell.setCellValue(model.getValueAt(i, j).toString());
                        cell.setCellValue(Objects.toString(emodel.getValueAt(i - 1, j), ""));
                        cell.setCellStyle(cellStyle);
                    }
                }

                fileOutputStream = new FileOutputStream(file);
                try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
                    fWorkbook.write(bos);
                }

                /// Calling the "temporaryLoad" method and passing the temporary file path.
                String pathset = "src/files/temporary_data.xls";
                File temporaryFilePath = new File(pathset);
                temporaryLoad(temporaryFilePath);

            } else if (newType == true) {

                newfWorkbook = new XSSFWorkbook();
                XSSFSheet fSheet;
                fSheet = newfWorkbook.createSheet("new Sheet");
                XSSFFont sheetTitleFont = newfWorkbook.createFont();
                File file = new File("src/files/temporary_data_new.xlsx");

                XSSFCellStyle cellStyle = newfWorkbook.createCellStyle();
                //   sheetTitleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                //sheetTitleFont.setColor();
                TableModel emodel = jTable1.getModel();

                TableColumnModel model1 = jTable1.getTableHeader().getColumnModel();
                XSSFRow fRow1 = fSheet.createRow((short) 0);
                for (int i = 0; i < model1.getColumnCount(); i++) {
                    XSSFCell cell = fRow1.createCell((short) i);
                    cell.setCellValue(model1.getColumn(i).getHeaderValue().toString());

                }
                for (int i = 1; i < emodel.getRowCount() + 1; i++) {
                    XSSFRow fRow = fSheet.createRow((short) i);
                    for (int j = 0; j < emodel.getColumnCount(); j++) {
                        XSSFCell cell = fRow.createCell((short) j);
                        // cell.setCellValue(model.getValueAt(i, j).toString());
                        cell.setCellValue(Objects.toString(emodel.getValueAt(i - 1, j), ""));
                        cell.setCellStyle(cellStyle);
                    }
                }

                fileOutputStream = new FileOutputStream(file);
                try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
                    newfWorkbook.write(bos);
                }

                /// Calling the "temporaryLoad" method and passing the temporary file path.
                String pathset = "src/files/temporary_data_new.xlsx";
                File temporaryFilePath = new File(pathset);
                temporaryLoad(temporaryFilePath);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : TemporaryFileSave", "Errror..", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (fWorkbook != null) {
                    fWorkbook.close();
                }
                if (newfWorkbook != null) {
                    newfWorkbook.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
            }
        }

    }

    private void temporaryLoad(File file) {

        int index = -1;
        HSSFWorkbook workbook = null;
        XSSFWorkbook newworkbook = null;
        FileInputStream inputStream = null;

        //  oldType = file.getName().endsWith("xls");
        //  newType = file.getName().endsWith("xlsx");
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        try {

            if (oldType == true) {

                inputStream = new FileInputStream(file);
                workbook = new HSSFWorkbook(inputStream);
                inputStream.close();

                System.out.println(file);

                String[] strs = new String[workbook.getNumberOfSheets()];
                //get all sheet names from selected workbook
                for (int i = 0; i < strs.length; i++) {
                    strs[i] = workbook.getSheetName(i);

                }
                /**
                 * JFrame frame = new JFrame("Input Dialog"); String
                 * selectedsheet = (String) JOptionPane.showInputDialog(frame,
                 * "Which worksheet you want to import ?", "Select Worksheet",
                 * JOptionPane.QUESTION_MESSAGE, null, strs, strs[0]);
                 */
                String selectedsheet = "new Sheet";

                //  System.out.println(selectedsheet);
                if (selectedsheet != null) {
                    for (int i = 0; i < strs.length; i++) {
                        if (workbook.getSheetName(i).equalsIgnoreCase(selectedsheet)) {
                            index = i;
                        }

                    }

                    HSSFSheet sheet = workbook.getSheetAt(index);
                    HSSFRow row = sheet.getRow(0);
                    headers.clear();
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        HSSFCell cell1 = row.getCell(i);
                        headers.add(cell1.toString());
                    }
                    data.clear();
                    for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                        Vector d = new Vector();
                        row = sheet.getRow(j);
                        int noofrows = row.getLastCellNum();
                        for (int i = 0; i < noofrows; i++) {
                            //To handle empty excel cells
                            HSSFCell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            //System.out.println(cell.getCellType());
                            d.add(cell.toString());

                        }
                        d.add("\n");
                        data.add(d);

                    }

                } else {
                    System.out.println("Null Sheet Found..");
                    return;
                }
                System.out.println("temporaryLoad()-OLD");
            } else if (newType == true) {

                inputStream = new FileInputStream(file);
                newworkbook = new XSSFWorkbook(inputStream);

                System.out.println(file);

                String[] strs = new String[newworkbook.getNumberOfSheets()];
                //get all sheet names from selected workbook
                for (int i = 0; i < strs.length; i++) {
                    strs[i] = newworkbook.getSheetName(i);

                }
                /**
                 * JFrame frame = new JFrame("Input Dialog"); String
                 * selectedsheet = (String) JOptionPane.showInputDialog(frame,
                 * "Which worksheet you want to import ?", "Select Worksheet",
                 * JOptionPane.QUESTION_MESSAGE, null, strs, strs[0]);
                 */
                String selectedsheet = "new Sheet";

                //  System.out.println(selectedsheet);
                if (selectedsheet != null) {
                    for (int i = 0; i < strs.length; i++) {
                        if (newworkbook.getSheetName(i).equalsIgnoreCase(selectedsheet)) {
                            index = i;
                        }

                    }

                    XSSFSheet sheet = newworkbook.getSheetAt(index);
                    XSSFRow row = sheet.getRow(0);
                    headers.clear();
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        XSSFCell cell1 = row.getCell(i);
                        headers.add(cell1.toString());
                    }
                    data.clear();
                    for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                        Vector d = new Vector();
                        row = sheet.getRow(j);
                        int noofrows = row.getLastCellNum();
                        for (int i = 0; i < noofrows; i++) {
                            //To handle empty excel cells
                            XSSFCell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            //System.out.println(cell.getCellType());
                            d.add(cell.toString());

                        }
                        d.add("\n");
                        data.add(d);

                    }

                } else {
                    System.out.println("Null Sheet Found..");
                    return;
                }
                System.out.println("temporaryLoad()-NEW");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : TemporaryFileLoad", "Errror..", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
            }
        }

    }

    private void addColumn() {
        try {
            if (file != null) {
                model = (DefaultTableModel) jTable1.getModel();

                String newName = JOptionPane.showInputDialog("Please insert a colum name : ");

                if (newName != null) {
                    model.addColumn(newName);

                    System.out.println(newName);

                    model.fireTableDataChanged();

                    int lastcol = model.getColumnCount() - 1;
                    System.out.println(lastcol);

                    JTableHeader header = jTable1.getTableHeader();
                    TableColumnModel colMod = header.getColumnModel();
                    TableColumn tabCol = colMod.getColumn(lastcol);
                    tabCol.setHeaderValue(newName);
                    header.repaint();

                    temporarySave();

                    model = new DefaultTableModel(data, headers);
                    tableWidth = model.getColumnCount() * 150;
                    tableHeight = model.getRowCount() * 25;
                    jTable1.setPreferredSize(new Dimension(tableWidth, tableHeight));
                    jTable1.setModel(model);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter Column Name..");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please Import a file.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : Unable to Make a new Column.", "Errror..", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addRow() {
        try {

            if (file != null) {
                model = (DefaultTableModel) jTable1.getModel();
                //   Vector rowcolumn = new Vector();
                int cnt = model.getColumnCount();
                Object[] emptyRow = new Object[cnt];
                /**
                 * for (int i = 0; i < cnt; i++) { emptyRow[i] = "aaa";
                 * rowcolumn.add(i, "harsha"); }
                 *
                 */

                //   System.out.println(emptyRow.toString());
                model.addRow(emptyRow);
                model.fireTableDataChanged();

                //  jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                // System.out.println(cnt);
                System.out.println("Add row ends.....");
                int ffff = model.getRowCount();
                System.out.println(ffff);

                temporarySave();

                model = new DefaultTableModel(data, headers);
                tableWidth = model.getColumnCount() * 150;
                tableHeight = model.getRowCount() * 25;
                jTable1.setPreferredSize(new Dimension(tableWidth, tableHeight));
                jTable1.setModel(model);
            } else {
                JOptionPane.showMessageDialog(null, "Please Import a file.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : Unable to Make a new Row.", "Errror..", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRow() {
        try {
            if (getRowID != null) {

                //// Confirm Message Dialog
                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Delete selected row.?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.NO_OPTION) {
                    System.out.println("No button clicked");
                } else if (response == JOptionPane.YES_OPTION) {
                    System.out.println("Yes button clicked");
                    //// Deleting the row
                    ((DefaultTableModel) jTable1.getModel()).removeRow(Integer.parseInt(getRowID));
                    getRowID = null;
                    temporarySave();

                } else if (response == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select a row.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : Unable to Delete the Row.", "Errror..", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteColum() {

        try {
            if (getColID != null) {

                //// Confirm Message Dialog
                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Delete selected Column.?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.NO_OPTION) {
                    System.out.println("No button clicked");
                } else if (response == JOptionPane.YES_OPTION) {
                    System.out.println("Yes button clicked");
                    /// Delete Column
                    TableColumn col = jTable1.getColumnModel().getColumn(Integer.parseInt(getColID));
                    jTable1.removeColumn(col);
                    getColID = null;
                    temporarySave();

                } else if (response == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select a Column.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : Unable to Delete the Column.", "Errror..", JOptionPane.ERROR_MESSAGE);
        }

    }

    protected void loadFileFromServer(String serverPath) {

        // oldType = serverPath.getName().endsWith("xls");
        //  newType = severFile.getName().endsWith("xlsx");
        file = new File(serverPath);

        if (file.getName().endsWith("xls")) {
            oldType = true;
            newType = false;
        } else if (file.getName().endsWith("xlsx")) {
            oldType = false;
            newType = true;
        } else {
            JOptionPane.showMessageDialog(null, "Please Select Only Excel Files.", "Error..", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("OldType : " + oldType + " NewType : " + newType);
        fillData(file);

        System.out.println("Data eka awaa  " + file);

        model = new DefaultTableModel(data, headers);
        tableWidth = model.getColumnCount() * 150;
        tableHeight = model.getRowCount() * 25;
        jTable1.setPreferredSize(new Dimension(tableWidth, tableHeight));
        jTable1.setModel(model);

    }

    private static void lookandfeels() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
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
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton_save = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnBack = new javax.swing.JButton();
        jButton_deleteRow = new javax.swing.JButton();
        jButton_addCol = new javax.swing.JButton();
        jButton_deleteCol = new javax.swing.JButton();
        jButton_changeTitel = new javax.swing.JButton();
        jButton_addRow = new javax.swing.JButton();
        btnSave6 = new javax.swing.JButton();
        jText_Titel = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton_viewFiles = new javax.swing.JButton();
        jButton_test = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulation");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

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

        jButton_save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_save.setText("Save");
        jButton_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saveActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product Selection", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Formulation+code", "Item 2", "Item 3", "Item 4" }));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lab/Left_20px.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jButton_deleteRow.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_deleteRow.setText("Delete Row");
        jButton_deleteRow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_deleteRowMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_deleteRowMouseEntered(evt);
            }
        });
        jButton_deleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteRowActionPerformed(evt);
            }
        });

        jButton_addCol.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_addCol.setText("Add Col.");
        jButton_addCol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addColActionPerformed(evt);
            }
        });

        jButton_deleteCol.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_deleteCol.setText("Delete Col.");
        jButton_deleteCol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_deleteColMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_deleteColMouseEntered(evt);
            }
        });
        jButton_deleteCol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteColActionPerformed(evt);
            }
        });

        jButton_changeTitel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_changeTitel.setText("Change Titel");
        jButton_changeTitel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_changeTitelActionPerformed(evt);
            }
        });

        jButton_addRow.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_addRow.setText("Add Row");
        jButton_addRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addRowActionPerformed(evt);
            }
        });

        btnSave6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSave6.setText("Import File");
        btnSave6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave6ActionPerformed(evt);
            }
        });

        jLabel1.setText("Enter Titel Name :");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Save Location", "Local ", "Server" }));

        jButton_viewFiles.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_viewFiles.setText("View Files");
        jButton_viewFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_viewFilesActionPerformed(evt);
            }
        });

        jButton_test.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_test.setText("test");
        jButton_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_testActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnSave6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jText_Titel, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_changeTitel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_addRow, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_addCol, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_deleteRow, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_deleteCol, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_save, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_viewFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jButton_test, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jComboBox2)
                    .addComponent(jComboBox3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton_save, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_addCol, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_deleteRow, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_deleteCol, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_addRow, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSave6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_changeTitel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jText_Titel)
                            .addComponent(jButton_viewFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_test, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

    private void jButton_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saveActionPerformed

        try {
            if (file != null) {

                int j1 = jComboBox1.getSelectedIndex();
                int j2 = jComboBox2.getSelectedIndex();

                if (j1 != 0 && j2 != 0) {

                    JDialog.setDefaultLookAndFeelDecorated(true);
                    int response = JOptionPane.showConfirmDialog(null, "Do you want Save changes.?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (response == JOptionPane.NO_OPTION) {
                        System.out.println("No button clicked");
                    } else if (response == JOptionPane.YES_OPTION) {
                        System.out.println("Yes button clicked");
                        //// Save Changes
                        ExportDataToExcel();

                    } else if (response == JOptionPane.CLOSED_OPTION) {
                        System.out.println("JOptionPane closed");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Please Select Product Name & Code..");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please Import a file before Update..");
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jButton_saveActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

            JTable sour = (JTable) evt.getSource();
            getRowID = String.valueOf(sour.rowAtPoint(evt.getPoint()));
            getColID = String.valueOf(sour.columnAtPoint(evt.getPoint()));

            System.out.println(getColID);
            System.out.println(dtm.getColumnName(Integer.parseInt(getColID)));

            jText_Titel.setText(dtm.getColumnName(Integer.parseInt(getColID)));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        VergerMain vm = new VergerMain("admin");
        vm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void jButton_deleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteRowActionPerformed
        deleteRow();
    }//GEN-LAST:event_jButton_deleteRowActionPerformed

    private void jButton_addColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addColActionPerformed
        addColumn();
    }//GEN-LAST:event_jButton_addColActionPerformed

    private void jButton_deleteColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteColActionPerformed
        deleteColum();
    }//GEN-LAST:event_jButton_deleteColActionPerformed

    private void jButton_changeTitelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_changeTitelActionPerformed
        ChangeTableTitels();
    }//GEN-LAST:event_jButton_changeTitelActionPerformed

    private void jButton_addRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addRowActionPerformed
        addRow();
    }//GEN-LAST:event_jButton_addRowActionPerformed

    private void btnSave6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave6ActionPerformed
        try {
            jChooser.showOpenDialog(null);
            jChooser.setDialogTitle("Select only Excel workbooks");
            file = jChooser.getSelectedFile();

            if (file == null) {
                JOptionPane.showMessageDialog(null, "Please select any Excel file.", "Help", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if (file.getName().endsWith("xls") || file.getName().endsWith("xlsx")) {

                fillData(file);
                model = new DefaultTableModel(data, headers);
                tableWidth = model.getColumnCount() * 150;
                tableHeight = model.getRowCount() * 25;
                jTable1.setPreferredSize(new Dimension(tableWidth, tableHeight));
                jTable1.setModel(model);

                /// Null kale nattan update validation eka wada karanne nathuwa yanwa. (file path ekak set wenawa)
            } else {
                JOptionPane.showMessageDialog(null, "Please select only Excel file (.xls or .xlsx)", "Error", JOptionPane.ERROR_MESSAGE);
                file = null;
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSave6ActionPerformed

    private void jButton_deleteRowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_deleteRowMouseEntered
        jButton_deleteRow.setBackground(Color.red);
    }//GEN-LAST:event_jButton_deleteRowMouseEntered

    private void jButton_deleteRowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_deleteRowMouseExited
        jButton_deleteRow.setBackground(null);
    }//GEN-LAST:event_jButton_deleteRowMouseExited

    private void jButton_deleteColMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_deleteColMouseEntered
        jButton_deleteCol.setBackground(Color.red);
    }//GEN-LAST:event_jButton_deleteColMouseEntered

    private void jButton_deleteColMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_deleteColMouseExited
        jButton_deleteCol.setBackground(null);
    }//GEN-LAST:event_jButton_deleteColMouseExited

    private void jButton_viewFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_viewFilesActionPerformed
        ViewSaveFiles vsf = new ViewSaveFiles();
        vsf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton_viewFilesActionPerformed

    private void jButton_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_testActionPerformed
        file = new File("/media/harsha/New Volume/FileCreateTest/server/Item 4_Item 2_oops.xls");
        oldType = true;
        fillData(this.file);
    }//GEN-LAST:event_jButton_testActionPerformed

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
                new Formulation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave6;
    private javax.swing.JButton jButton_addCol;
    private javax.swing.JButton jButton_addRow;
    private javax.swing.JButton jButton_changeTitel;
    private javax.swing.JButton jButton_deleteCol;
    private javax.swing.JButton jButton_deleteRow;
    private javax.swing.JButton jButton_save;
    private javax.swing.JButton jButton_test;
    private javax.swing.JButton jButton_viewFiles;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jText_Titel;
    // End of variables declaration//GEN-END:variables
}
