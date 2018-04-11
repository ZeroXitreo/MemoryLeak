/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.manager;

import com.badlogic.gdx.files.FileHandleStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonas
 */
public class JarFileHandleStream extends FileHandleStream {

    private JarFile jarFile = null;
    private String jarRelResDir;

    public JarFileHandleStream(String path) {
        super(path);
        try {
            String[] args = path.split("!");
            jarRelResDir = args[1].substring(1);

            String jarFilePath = args[0];
            jarFile = new JarFile(jarFilePath);
        } catch (IOException ex) {
            Logger.getLogger(JarFileHandleStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public InputStream read() {

        InputStream is = null;
        try {
            is = jarFile.getInputStream(jarFile.getEntry(jarRelResDir));
        } catch (IOException ex) {
            Logger.getLogger(JarFileHandleStream.class.getName()).log(Level.SEVERE, null, ex);
        }

        return is;
    }

    @Override
    public OutputStream write(boolean overwrite) {
        return super.write(overwrite);
    }

}
