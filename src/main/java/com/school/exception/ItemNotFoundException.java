package com.school.exception;

public class ItemNotFoundException extends Exception {

    private String parentItem;

    private String childItem;

    public ItemNotFoundException(String parentItem, String childItem) {
        this.parentItem = parentItem;
        this.childItem = childItem;
    }

    public String getParentItem() {
        return parentItem;
    }

    public void setParentItem(String parentItem) {
        this.parentItem = parentItem;
    }

    public String getChildItem() {
        return childItem;
    }

    public void setChildItem(String childItem) {
        this.childItem = childItem;
    }
}
