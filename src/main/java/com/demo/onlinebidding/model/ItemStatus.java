package com.demo.onlinebidding.model;

public enum ItemStatus {
    RUNNING("running"),
    OVER("over");
    
    private String label;

    ItemStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static ItemStatus getValue(String label) {
        if (label != null) {
            for (ItemStatus value : ItemStatus.values()) {
                if (label.equalsIgnoreCase(value.name()) || label.equalsIgnoreCase(value.getLabel())) {
                    return value;
                }
            }
        }
        return null;
    }
}
