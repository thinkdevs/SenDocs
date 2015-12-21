package com.thinkdevs.sendocs;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Maxim Tikhanovskiy
 */
public class Settings {
    private static Settings ourInstance = new Settings();

    public static Settings getInstance() {
        return ourInstance;
    }

    private Settings() {
    }

    public static final String FROM_FOLDER = "T:\\Departments\\Electric_Department\\РЗиА\\Users\\ТЕКУЩИЕ ЗАКАЗЫ\\";
    public static final String ORDERS_REPOSITORY = "\\\\192.168.0.6\\KDProduction\\";
    public static final String RZIA_FOLDER = "РЗА\\";

}
