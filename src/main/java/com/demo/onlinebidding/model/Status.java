package com.demo.onlinebidding.model;

public enum Status {
    RUNNING("running"),
    OVER("over");
    
    private String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Status getValue(String label) {
        if (label != null) {
            for (Status value : Status.values()) {
                if (label.equalsIgnoreCase(value.name()) || label.equalsIgnoreCase(value.getLabel())) {
                    return value;
                }
            }
        }
        return null;
    }
}
