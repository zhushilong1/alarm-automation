/**
  * Copyright 2020 json.cn 
  */
package com.youlu.manager.json;

/**
 * Auto-generated: 2020-12-08 16:42:57
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class CommonLabels {

    private String alertname;
    private String appname;
    private String group;
    private String instance;
    private String job;
    private String name;
    private String severity;
    public void setAlertname(String alertname) {
         this.alertname = alertname;
     }
     public String getAlertname() {
         return alertname;
     }

    public void setAppname(String appname) {
         this.appname = appname;
     }
     public String getAppname() {
         return appname;
     }

    public void setGroup(String group) {
         this.group = group;
     }
     public String getGroup() {
         return group;
     }

    public void setInstance(String instance) {
         this.instance = instance;
     }
     public String getInstance() {
         return instance;
     }

    public void setJob(String job) {
         this.job = job;
     }
     public String getJob() {
         return job;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setSeverity(String severity) {
         this.severity = severity;
     }
     public String getSeverity() {
         return severity;
     }

    @Override
    public String toString() {
        return "CommonLabels{" +
                "alertname='" + alertname + '\'' +
                ", appname='" + appname + '\'' +
                ", group='" + group + '\'' +
                ", instance='" + instance + '\'' +
                ", job='" + job + '\'' +
                ", name='" + name + '\'' +
                ", severity='" + severity + '\'' +
                '}';
    }
}