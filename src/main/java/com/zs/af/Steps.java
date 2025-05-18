package com.zs.af;

public class Steps {
    String type;
    String xpath;
    String action;
    String key;

    @Override
    public String toString() {
        return "Steps{" +
                "type='" + type + '\'' +
                ", xpath='" + xpath + '\'' +
                ", action='" + action + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public Steps(String type, String xpath, String action, String key) {
        this.type = type;
        this.xpath = xpath;
        this.action = action;
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
