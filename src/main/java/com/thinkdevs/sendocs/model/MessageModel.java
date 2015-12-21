package com.thinkdevs.sendocs.model;

import java.text.SimpleDateFormat;
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
        Date createDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm");
        this.date = simpleDateFormat.format(createDate);
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
