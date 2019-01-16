/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectpatternlocktry4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Home
 */
public class view7 extends javax.swing.JFrame {
    final JFileChooser fc;
    BufferedImage image;
    File f=null;
    /**
     * Creates new form view7
     */
    public view7() {
//        initComponents();
        fc=new JFileChooser();
        fc.setCurrentDirectory(new File("c:\\Users\\Home\\Pictures"));
        fc.setFileFilter(new FileNameExtensionFilter("JPEG file", "jpg", "jpeg"));
        int returnvalue=fc.showOpenDialog(this);
        String str;
        if(returnvalue==JFileChooser.APPROVE_OPTION)
        {
            try{
                File fl=fc.getSelectedFile();
                image=ImageIO.read(fl);
                
                try{
                    str=getrandomstring();
                    FileInputStream files=new FileInputStream(fl.getAbsolutePath());
                    FileOutputStream outStream=new FileOutputStream("c:\\PatternLock\\LockedImages\\"+str+".jpg");
                    byte k[]="CooL2116NiTh5252".getBytes();
                    SecretKeySpec key=new SecretKeySpec(k,"AES");
                    Cipher enc=Cipher.getInstance("AES");
                    enc.init(Cipher.ENCRYPT_MODE, key);
                    CipherOutputStream cos=new CipherOutputStream(outStream, enc);
                    byte[] buf=new byte[1024];
                    int read;
                    while((read=files.read(buf))!=-1)
                        cos.write(buf, 0, read);
                    files.close();
                    outStream.flush();
                    cos.close();
                } catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Encryption not successful");
                }
                
//                str=getrandomstring();
//                f=new File("c:\\PatternLock\\LockedImages\\"+str+".jpg");
//                ImageIO.write(image, "jpg", f);
                fl.delete();
            } catch (IOException e){
                JOptionPane.showMessageDialog(null, "Failed to select image");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Failed to select image");
        }
    }
    public String getrandomstring() {
        String randstr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder rand = new StringBuilder();
        Random rnd = new Random();
        while (rand.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * randstr.length());
            rand.append(randstr.charAt(index));
        }
        String randStr = rand.toString();
        return randStr;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);
        setPreferredSize(new java.awt.Dimension(400, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(view7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(view7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(view7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view7().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
