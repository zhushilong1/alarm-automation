/**
  * Copyright 2020 json.cn 
  */
package com.youlu.manager.json;
import java.util.List;
import java.util.Date;

/**
 * Auto-generated: 2020-12-08 16:42:57
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class JsonRootBean {

    private String receiver;
    private String status;
    private List<Alerts> alerts;
    private GroupLabels groupLabels;
    private CommonLabels commonLabels;
    private CommonAnnotations commonAnnotations;
    private String externalURL;
    private String version;
    private String groupKey;
    private int truncatedAlerts;
    public void setReceiver(String receiver) {
         this.receiver = receiver;
     }
     public String getReceiver() {
         return receiver;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setAlerts(List<Alerts> alerts) {
         this.alerts = alerts;
     }
     public List<Alerts> getAlerts() {
         return alerts;
     }

    public void setGroupLabels(GroupLabels groupLabels) {
         this.groupLabels = groupLabels;
     }
     public GroupLabels getGroupLabels() {
         return groupLabels;
     }

    public void setCommonLabels(CommonLabels commonLabels) {
         this.commonLabels = commonLabels;
     }
     public CommonLabels getCommonLabels() {
         return commonLabels;
     }

    public void setCommonAnnotations(CommonAnnotations commonAnnotations) {
         this.commonAnnotations = commonAnnotations;
     }
     public CommonAnnotations getCommonAnnotations() {
         return commonAnnotations;
     }

    public void setExternalURL(String externalURL) {
         this.externalURL = externalURL;
     }
     public String getExternalURL() {
         return externalURL;
     }

    public void setVersion(String version) {
         this.version = version;
     }
     public String getVersion() {
         return version;
     }

    public void setGroupKey(String groupKey) {
         this.groupKey = groupKey;
     }
     public String getGroupKey() {
         return groupKey;
     }

    public void setTruncatedAlerts(int truncatedAlerts) {
         this.truncatedAlerts = truncatedAlerts;
     }
     public int getTruncatedAlerts() {
         return truncatedAlerts;
     }

    @Override
    public String toString() {
        return "JsonRootBean{" +
                "receiver='" + receiver + '\'' +
                ", status='" + status + '\'' +
                ", alerts=" + alerts +
                ", groupLabels=" + groupLabels +
                ", commonLabels=" + commonLabels +
                ", commonAnnotations=" + commonAnnotations +
                ", externalURL='" + externalURL + '\'' +
                ", version='" + version + '\'' +
                ", groupKey='" + groupKey + '\'' +
                ", truncatedAlerts=" + truncatedAlerts +
                '}';
    }
}