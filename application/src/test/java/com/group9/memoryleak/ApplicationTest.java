package com.group9.memoryleak;

import java.io.IOException;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import junit.framework.Test;
import static junit.framework.TestCase.assertEquals;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.util.Lookup;
import services.iEntityProcessingService;
import services.iGamePluginServices;
import services.iPostEntityProcessingService;

public class ApplicationTest extends NbTestCase {

    private static final String ADD_MODULES_UPDATES_FILE = "/Users/jonas/Desktop/UpdateCenter/LoadModules/updates.xml";
    private static final String REMOVE_MODULES_UPDATES_FILE = "/Users/jonas/Desktop/UpdateCenter/UnloadModules/updates.xml";
    private static final String ORIGINAL_UPDATES_FILE = "/Users/jonas/Desktop/UpdateCenter/AllModules/updates.xml";

    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.WARNING). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false).
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }

    public ApplicationTest(String n) {
        super(n);
    }

    public void testApplication() throws InterruptedException, IOException {
        // SETUP
        List<iEntityProcessingService> processors = new CopyOnWriteArrayList<>();
        List<iGamePluginServices> plugins = new CopyOnWriteArrayList<>();
        List<iPostEntityProcessingService> postProcessors = new CopyOnWriteArrayList<>();

        waitForUpdate(processors, plugins, postProcessors, 10000);

        //Modules loaded
        //Size should be 6 plugins, 8 processors, and 2 post processors.
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("Seven processors", 8, processors.size());
        assertEquals("Two post processors", 2, postProcessors.size());

        // TEST: Unload modules via UC
        copy(get(REMOVE_MODULES_UPDATES_FILE), get(ORIGINAL_UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins, postProcessors, 10000);

        // ASSERTS: Modules unloaded
        //Size should be 0 plugins, 0 processors, and 0 post processors.
        assertEquals("Zero plugins", 0, plugins.size());
        assertEquals("Zero processors", 0, processors.size());
        assertEquals("Zero post processors", 0, postProcessors.size());

        // TEST: Load Modules via UC
        copy(get(ADD_MODULES_UPDATES_FILE), get(ORIGINAL_UPDATES_FILE), REPLACE_EXISTING);

        waitForUpdate(processors, plugins, postProcessors, 10000);

        // ASSERTS: Modules Loaded
        //Size should be 6 plugins, 8 processors, and 2 post processors.
        assertEquals("Six plugins", 6, plugins.size());
        assertEquals("Eight processors", 8, processors.size());
        assertEquals("Two post processors", 2, postProcessors.size());
    }

    private void waitForUpdate(List<iEntityProcessingService> processors, List<iGamePluginServices> plugins, List<iPostEntityProcessingService> postProcessors, long millis) throws InterruptedException {
        // Needs time for silentUpdater to install all modules
        Thread.sleep(millis);

        processors.clear();
        processors.addAll(Lookup.getDefault().lookupAll(iEntityProcessingService.class));

        plugins.clear();
        plugins.addAll(Lookup.getDefault().lookupAll(iGamePluginServices.class));

        postProcessors.clear();
        postProcessors.addAll(Lookup.getDefault().lookupAll(iPostEntityProcessingService.class));
    }

}
