package com.advert.model;

public class OptimizationProps {
    private int threshold;
    private String sourceEvent;
    private String measuredEvent;
    private float ratioThreshold;

    public OptimizationProps(int threshold, String sourceEvent, String measuredEvent, float ratioThreshold) {
        this.threshold = threshold;
        this.sourceEvent = sourceEvent;
        this.measuredEvent = measuredEvent;
        this.ratioThreshold = ratioThreshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public String getSourceEvent() {
        return sourceEvent;
    }

    public void setSourceEvent(String sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    public String getMeasuredEvent() {
        return measuredEvent;
    }

    public void setMeasuredEvent(String measuredEvent) {
        this.measuredEvent = measuredEvent;
    }

    public float getRatioThreshold() {
        return ratioThreshold;
    }

    public void setRatioThreshold(float ratioThreshold) {
        this.ratioThreshold = ratioThreshold;
    }
}
