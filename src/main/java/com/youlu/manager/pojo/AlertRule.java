package com.youlu.manager.pojo;

import javax.persistence.Id;

public class AlertRule {
    @Id
    private Integer id;

    private String alertType;

    private String alertMode;

    private String alertReceive;

    private String status;

    private String createTime;

    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType == null ? null : alertType.trim();
    }

    public String getAlertMode() {
        return alertMode;
    }

    public void setAlertMode(String alertMode) {
        this.alertMode = alertMode == null ? null : alertMode.trim();
    }

    public String getAlertReceive() {
        return alertReceive;
    }

    public void setAlertReceive(String alertReceive) {
        this.alertReceive = alertReceive == null ? null : alertReceive.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    @Override
    public String toString() {
        return "AlertRule{" +
                "id=" + id +
                ", alertType='" + alertType + '\'' +
                ", alertMode='" + alertMode + '\'' +
                ", alertReceive='" + alertReceive + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}