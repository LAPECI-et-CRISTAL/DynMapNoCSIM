package GUI;
import java.io.File;
import java.io.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zahoussem
 */
public class filechoose {
   String Path;
    filechoose(JFrame x)
    {
        
        String filename = File.separator+"tmp";
JFileChooser fc = new JFileChooser(new File(filename));
//File f=new File("c:/users/zahoussem/Desktop");
//fc.setCurrentDirectory(f);
fc.showOpenDialog(x);
File selFile = fc.getSelectedFile();
try {
Path=selFile.getAbsolutePath();
    
}
    catch (java.lang.NullPointerException ex)
    {
        javax.swing.JOptionPane.showMessageDialog(null,"vous devez au moin choisir un fichier","erreur",2);
        x.setVisible(false);
    }}
}
