package com.example.service_hi.wisdom_hr.work_date.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkDate {
    private Date start;
    private Date end;
    private String workMan;
    private String teamMain;
    private String workType;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getWorkMan() {
        return workMan;
    }

    public void setWorkMan(String workMan) {
        this.workMan = workMan;
    }

    public String getTeamMain() {
        return teamMain;
    }

    public void setTeamMain(String teamMain) {
        this.teamMain = teamMain;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startStr=formatter.format(start);
        String endStr=formatter.format(end);
        return "WorkDate{" +
                "start=" + startStr +
                ", end=" + endStr +
                ", workMan='" + workMan + '\'' +
                ", teamMain='" + teamMain + '\'' +
                ", workType='" + workType + '\'' +
                '}';
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }
}
