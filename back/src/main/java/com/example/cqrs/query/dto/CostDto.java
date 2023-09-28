package com.example.cqrs.query.dto;

public class CostDto {
    private long id;
    private String costn;
    private String unit;
    private String visibility;
    private String themes;

    // Constructors

    public CostDto() {
        // Default constructor
    }

    public CostDto(long id, String costn, String unit, String visibility, String themes) {
        this.id = id;
        this.costn = costn;
        this.unit = unit;
        this.visibility = visibility;
        this.themes = themes;
    }

    // Getter and Setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
