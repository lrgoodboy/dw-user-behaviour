package com.anjuke.dw.user_behaviour.model;

public class ActionLog {

    private Integer id;
    private String machine;
    private String appVersion;
    private String pm;
    private String uniqueId;
    private String pageId;
    private String actionId;
    private String logTime;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMachine() {
        return machine;
    }
    public void setMachine(String machine) {
        this.machine = machine;
    }
    public String getAppVersion() {
        return appVersion;
    }
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
    public String getPm() {
        return pm;
    }
    public void setPm(String pm) {
        this.pm = pm;
    }
    public String getUniqueId() {
        return uniqueId;
    }
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    public String getPageId() {
        return pageId;
    }
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    public String getActionId() {
        return actionId;
    }
    public void setActionId(String actionId) {
        this.actionId = actionId;
    }
    public String getLogTime() {
        return logTime;
    }
    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }
    
}
