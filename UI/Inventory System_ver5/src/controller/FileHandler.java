/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Tharusha
 */
public class FileHandler {

    private String folderName;
    
    public FileHandler(String folderName){
        this.folderName=folderName;
        JOptionPane.showMessageDialog(null, "Folder :"+folderName);
    }
    
   //final JFileChooser fc = new JFileChooser(new File(folderName));
    final JFileChooser fc = new JFileChooser();
    String fileName;
    
    public String fileName(JFrame _dd) {
        int response = fc.showDialog(_dd, folderName);
        if (response == JFileChooser.APPROVE_OPTION) {
            
            fileName = fc.getSelectedFile().toString();
            //JOptionPane.showMessageDialog(null, fc.getSelectedFile().toString());
            //JOptionPane.showMessageDialog(null,fc.getSelectedFile().toString());     
            
        } else {
        
            //JOptionPane.showMessageDialog(null, "The File open Operation was Cancelled");
            fileName =  "The File open Operation was Cancelled";
        
        }

        return fileName;
    }

}
