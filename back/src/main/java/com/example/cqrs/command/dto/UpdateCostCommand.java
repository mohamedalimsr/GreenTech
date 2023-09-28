package com.example.cqrs.command.dto;

public class UpdateCostCommand {
    private String costn;
    private String unit;
    private String visibility;
    private String themes;

    // Constructors, getters, and setters

    public UpdateCostCommand() {
        // Default constructor
    }

    public UpdateCostCommand(String costn, String unit, String visibility, String themes) {
        this.costn = costn;
        this.unit = unit;
        this.visibility = visibility;
        this.themes = themes;
    }

    public String getCostn() {
        return costn;
    }

    public void setCostn(String costn) {
        this.costn = costn;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getThemes() {
        return themes;
    }

    public void setThemes(String themes) {
        this.themes = themes;
    }
}
