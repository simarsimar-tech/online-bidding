package com.demo.onlinebidding.model;

public enum BidStatus {

    ACCEPTED("accepted"),
    REJECTED("rejected");

    private String label;

    BidStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static BidStatus getValue(String label) {
        if (label != null) {
            for (BidStatus value : BidStatus.values()) {
                if (label.equalsIgnoreCase(value.name()) || label.equalsIgnoreCase(value.getLabel())) {
                    return value;
                }
            }
        }
        return null;
    }
}
