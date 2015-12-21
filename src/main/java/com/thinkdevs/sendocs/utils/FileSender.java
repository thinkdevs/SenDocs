package com.thinkdevs.sendocs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @author Maxim Tikhanovskiy
 */
public class FileSender {

    private final String URL_TO;

    public FileSender(String URL_TO) {
        this.URL_TO = URL_TO;
    }

    private void send(File file) {
        try (FileChannel sourceChannel = new FileInputStream(file).getChannel();
             FileChannel destinyChannel = new FileOutputStream(URL_TO + file.getName()).getChannel()) {
            destinyChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(List<String> files){
        for(String file : files){
            send(new File(file));
        }
    }
}
