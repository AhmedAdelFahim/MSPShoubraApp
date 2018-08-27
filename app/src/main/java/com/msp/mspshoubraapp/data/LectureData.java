package com.msp.mspshoubraapp.data;

import java.util.ArrayList;

public class LectureData {

    private String startTime, endTime;

    private ArrayList<IntervalData> intervalData;

    public LectureData(String startTime, String endTime, ArrayList<IntervalData> intervalData) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.intervalData = intervalData;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public ArrayList<IntervalData> getIntervalData() {
        return intervalData;
    }
}
