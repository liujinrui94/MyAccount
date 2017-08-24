package com.ljr.jizhang.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/8/21 14:08
 * @description:
 */
@Entity
public class Account {
    @Id
    private Long id;

    private String clientName;//客户姓名

    private String projectName;//项目名称

    private long projectDate;//项目接收时间

    private String projectLocation;//项目地点

    private String clientPhone;//客户联系方式

    public String getClientPhone() {
        return this.clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getProjectLocation() {
        return this.projectLocation;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }

    public long getProjectDate() {
        return this.projectDate;
    }

    public void setProjectDate(long projectDate) {
        this.projectDate = projectDate;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 607206542)
    public Account(Long id, String clientName, String projectName,
            long projectDate, String projectLocation, String clientPhone) {
        this.id = id;
        this.clientName = clientName;
        this.projectName = projectName;
        this.projectDate = projectDate;
        this.projectLocation = projectLocation;
        this.clientPhone = clientPhone;
    }

    @Generated(hash = 882125521)
    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectDate=" + projectDate +
                ", projectLocation='" + projectLocation + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                '}';
    }
}
