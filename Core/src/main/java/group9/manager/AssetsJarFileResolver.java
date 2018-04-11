/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.manager;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

/**
 *
 * @author jonas
 */
public class AssetsJarFileResolver implements FileHandleResolver {

    @Override
    public FileHandle resolve(String string) {
        return new JarFileHandleStream(string);
    }

}
