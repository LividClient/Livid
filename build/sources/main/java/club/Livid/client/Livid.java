package club.Livid.client;

import club.Livid.client.utilities.FontRenderer.FontUtil;
import club.Livid.client.utilities.file.FileManager;

public enum Livid {

    instance;

    public String CLIENT_NAME = "Livid";
    public String CLIENT_VERSION = "1.0";
    public FileManager fileManager;

    public void init() {
        fileManager = new FileManager();
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
}
