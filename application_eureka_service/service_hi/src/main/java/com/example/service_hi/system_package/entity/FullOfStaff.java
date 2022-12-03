package com.example.service_hi.system_package.entity;


/**
 * 满员整编对应得班组详细信息实体
 */
public class FullOfStaff {
    //班组HID
    private String workSteamHid;
    //班组名称
    private String workName;
    //是否一线班组
    private String isFrontLine;
    //班组一级专业分类
    private String majorOneLevel;
    //班组二级专业分类
    private String majorType;
    //定编人数
    private Integer staffNum;
    //在岗人数
    private Integer onDutyNum;
    //组织路径
    private String orgPath;

    public String getWorkSteamHid() {
        return workSteamHid;
    }

    public void setWorkSteamHid(String workSteamHid) {
        this.workSteamHid = workSteamHid;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getIsFrontLine() {
        return isFrontLine;
    }

    public void setIsFrontLine(String isFrontLine) {
        this.isFrontLine = isFrontLine;
    }

    public String getMajorOneLevel() {
        return majorOneLevel;
    }

    public void setMajorOneLevel(String majorOneLevel) {
        this.majorOneLevel = majorOneLevel;
    }

    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType;
    }

    public Integer getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(Integer staffNum) {
        this.staffNum = staffNum;
    }

    public Integer getOnDutyNum() {
        return onDutyNum;
    }

    public void setOnDutyNum(Integer onDutyNum) {
        this.onDutyNum = onDutyNum;
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }
}
