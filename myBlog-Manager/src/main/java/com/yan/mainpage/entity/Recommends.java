package com.yan.mainpage.entity;

import java.util.Date;

/**
 * @author Sam
 * @date 24/07/2020 - 1:04 pm
 */
public class Recommends {
    private int bid;
    private String title;
    private Date UpdatedTime;
    private String UpdatedTimeStr;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdatedTime() {
        return UpdatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        UpdatedTime = updatedTime;
    }

    public String getUpdatedTimeStr() {
        return UpdatedTimeStr;
    }

    public void setUpdatedTimeStr(String updatedTimeStr) {
        UpdatedTimeStr = updatedTimeStr;
    }
}
