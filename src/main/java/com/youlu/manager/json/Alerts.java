/**
  * Copyright 2020 json.cn 
  */
package com.youlu.manager.json;
import java.util.Date;

/**
 * Auto-generated: 2020-12-08 16:42:57
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Alerts {

    private String status;
    private Labels labels;
    private Annotations annotations;
    private String startsAt;
    private String endsAt;
    private String generatorURL;
    private String fingerprint;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setLabels(Labels labels) {
         this.labels = labels;
     }
     public Labels getLabels() {
         return labels;
     }

    public void setAnnotations(Annotations annotations) {
         this.annotations = annotations;
     }
     public Annotations getAnnotations() {
         return annotations;
     }

    public void setStartsAt(String startsAt) {
         this.startsAt = startsAt;
     }
     public String getStartsAt() {
         return startsAt;
     }

    public void setEndsAt(String endsAt) {
         this.endsAt = endsAt;
     }
     public String getEndsAt() {
         return endsAt;
     }

    public void setGeneratorURL(String generatorURL) {
         this.generatorURL = generatorURL;
     }
     public String getGeneratorURL() {
         return generatorURL;
     }

    public void setFingerprint(String fingerprint) {
         this.fingerprint = fingerprint;
     }
     public String getFingerprint() {
         return fingerprint;
     }

    @Override
    public String toString() {
        return "Alerts{" +
                "status='" + status + '\'' +
                ", labels=" + labels +
                ", annotations=" + annotations +
                ", startsAt=" + startsAt +
                ", endsAt=" + endsAt +
                ", generatorURL='" + generatorURL + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                '}';
    }
}