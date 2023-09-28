package com.example.cqrs.command.model;

import javax.persistence.*;

@Entity
@Table(name = "costs")
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "costn")
    private String costn;

    @Column(name = "unit")
    private String unit;

    @Column(name = "visibility")
    private String visibility;

    @Column(name = "themes")
    private String themes;

    // Constructors, getters, and setters

    public Cost() {
        // Default constructor
    }

    public Cost(String costn, String unit, String visibility, String themes) {
        this.costn = costn;
        this.unit = unit;
        this.visibility = visibility;
        this.themes = themes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
