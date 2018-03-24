/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        LwjglApplicationConfiguration cfg
                = new LwjglApplicationConfiguration();
        cfg.title = "MemoryLeak";
        cfg.width = 960;
        cfg.height = 540;
        cfg.useGL30 = false;
        cfg.resizable = false;

        new LwjglApplication(new MemoryLeak(), cfg);
    }

}
