package com.caipiao.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BPFile implements Serializable {

    private String path;
    private String filename;

    private String adddate;

    private String type;


    public String getType() {

        return path.substring(path.lastIndexOf(".") + 1);
    }

    public void setType(String type) {


        this.type = type;
    }

    public String getAdddate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(Long.parseLong(adddate) * 1000);
        return sdf.format(date);
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getPath() {

        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    @Override
    public String toString() {
        return "BPFile{" +
                "path='" + path + '\'' +
                ", filename='" + filename + '\'' +
                ", adddate='" + adddate + '\'' +
                '}';
    }
}