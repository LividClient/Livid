package club.Livid.client;

import club.Livid.client.module.ModuleManager;
import club.Livid.client.utilities.FontRenderer.FontUtil;
import club.Livid.client.utilities.file.FileManager;
import com.sun.media.jfxmedia.logging.Logger;

public enum Livid {

    instance;

    public String CLIENT_NAME = "Livid";
    public String CLIENT_VERSION = "1.0";
    public FileManager fileManager;
    public ModuleManager moduleManager;

    public void init() {
        fileManager = new FileManager();
        moduleManager = new ModuleManager();
        FontUtil.bootstrap();
    }

    public String getCLIENT_NAME() {
        return CLIENT_NAME;
    }

    public void setCLIENT_NAME(String CLIENT_NAME) {
        this.CLIENT_NAME = CLIENT_NAME;
    }

    public String getCLIENT_VERSION() {
        return CLIENT_VERSION;
    }

    public void setCLIENT_VERSION(String CLIENT_VERSION) {
        this.CLIENT_VERSION = CLIENT_VERSION;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public void setModuleManager(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public void log(String s){
        Logger.logMsg(0, "[Livid LOGS]: " + s);
    }

}
