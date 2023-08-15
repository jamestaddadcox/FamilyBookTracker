package com.techelevator.model;

import java.util.Date;

public class Prize {
    private int prizeId;
    private int familyId;
    private String name;
    private String description;
    private boolean milestone;
    private String userGroup;
    private String startDate;
    private String endDate;
    public Prize() {

    }

    public Prize(int prizeId, int familyId, String name, String description, String userGroup, String startDate, String endDate) {
        this.prizeId = prizeId;
        this.familyId = familyId;
        this.name = name;
        this.description = description;
        this.milestone = false;
        this.userGroup = userGroup;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMilestone() {
        return milestone;
    }

    public void setMilestone(boolean milestone) {
        this.milestone = milestone;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
