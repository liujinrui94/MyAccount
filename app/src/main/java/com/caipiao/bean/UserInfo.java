package com.caipiao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserInfo {

    @Id
    private Long id;
    private String userName;
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1760313096)
    public UserInfo(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }


}
