package com.techelevator.model;

import java.util.Date;

public class Prize {
    private int prizeId;
    private int familyId;
    private String name;
    private String description;
    private boolean milestone;
    // limit to Adult, Children, Both:
    private int userGroup;
    private Date startDate;
    private Date endDate;

    public Prize(int prizeId, int familyId, String name, String description, int userGroup, Date startDate) {
        this.prizeId = prizeId;
        this.familyId = familyId;
        this.name = name;
        this.description = description;
        this.milestone = false;
        this.userGroup = userGroup;
        this.startDate = startDate;
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

    public int getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(int userGroup) {
        this.userGroup = userGroup;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
