/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectpatternlocktry4;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Home
 */
public class filing {
    public void createfolder()
    {
        Path path=Paths.get("c:\\PatternLock\\LockedImages");
        boolean checkdir=Files.exists(path);
        if(!checkdir)
        {
            try{
                Files.createDirectory(path);
                //apply lock to file
            } catch (Exception e){
                System.out.println("Could not create directory");
            }
        }
        
    }
    public void selectpicture()
    {
        new view7().setVisible(true);
    }
    
}
