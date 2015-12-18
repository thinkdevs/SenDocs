package com.thinkdevs.sendocs.model;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Maxim Tikhanovskiy
 */
public class MessageModel {

    private final String subject;
    private final String date;
    private final String text;
    private final List<String> links = new ArrayList<>();

    public MessageModel(String subject, String text) {
        this.subject = subject;
        this.text = text;
        this.date = new Date().toString();
    }

    public MessageModel(String subject, String text, List<String> links) {
        this.subject = subject;
        this.text = text;
        this.date = new Date().toString();
        this.links.addAll(links);
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public List<String> getLinks() {
        return links;
    }

    public void addLink(String link){
        links.add(link);
    }
}
