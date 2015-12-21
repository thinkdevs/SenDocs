package com.thinkdevs.sendocs.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @author Maxim Tikhanovskiy
 */
public class FileSender {

    private final String URL_FROM;
    private final String URL_TO;

    public FileSender(String URL_FROM, String URL_TO) {
        this.URL_FROM = URL_FROM;
        this.URL_TO = URL_TO;
    }

    private void send(String file) {
        try (FileChannel sourceChannel = new FileInputStream(URL_FROM + file).getChannel();
             FileChannel destinyChannel = new FileOutputStream(URL_TO + file).getChannel()) {
            destinyChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(List<String> files){
        for(String file : files){
            send(file);
        }
    }
}
