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
public class Annotations {

    private String description;
    private String summary;
    private String value;
    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setSummary(String summary) {
         this.summary = summary;
     }
     public String getSummary() {
         return summary;
     }

    public void setValue(String value) {
         this.value = value;
     }
     public String getValue() {
         return value;
     }

    @Override
    public String toString() {
        return "Annotations{" +
                "description='" + description + '\'' +
                ", summary='" + summary + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}