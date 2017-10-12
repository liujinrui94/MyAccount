package com.ljr.jizhang.bean;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/10/12 13:12
 * @description:
 */
public class SscBean {

    String code;
    String issue;
    String date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "SscBean{" +
                "code='" + code + '\'' +
                ", issue='" + issue + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
