/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.classes;

/**
 *
 * @author jonas
 */
public class StaticValues {

    static boolean iGamePluginServicesStart = false;
    static boolean iGamePluginServicesStop = false;
    static boolean iEntityProcessing = false;
    static boolean iPostEntityProcessing = false;

    public static boolean isIGameStarted() {
        return iGamePluginServicesStart;
    }

    public static void setIGameStarted(boolean iGamePluginServicesStart) {
        StaticValues.iGamePluginServicesStart = iGamePluginServicesStart;
    }

    public static boolean isIGameStopCalled() {
        return iGamePluginServicesStop;
    }

    public static void setIGameStopped(boolean iGamePluginServicesStop) {
        StaticValues.iGamePluginServicesStop = iGamePluginServicesStop;
    }

    public static boolean isEntityCalled() {
        return iEntityProcessing;
    }

    public static void setIEntity(boolean iEntityProcessing) {
        StaticValues.iEntityProcessing = iEntityProcessing;
    }

    public static boolean isPostEntityCalled() {
        return iPostEntityProcessing;
    }

    public static void setIPostEntity(boolean iPostEntityProcessing) {
        StaticValues.iPostEntityProcessing = iPostEntityProcessing;
    }
}
