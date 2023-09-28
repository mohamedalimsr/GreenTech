package com.example.cqrs.command.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CreateCostCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "costn")
    private String costn;

    @Column(name = "unit")
    private String unit;

    @Column(name = "visibility")
    private String visibility;

    @Column(name = "themes")
    private String themes;

        /*@Column(name = "processName")
        private String processName;

        @Column(name = "unitName")
        private String unitName;*/

    public CreateCostCommand() {

    }

    public CreateCostCommand(String costn, String unit, String visibility, String themes /*String processName, String unitName*/) {
        super();
        this.costn = costn;
        this.unit = unit;
        this.visibility = visibility;
        this.themes = themes;
            /*this.processName = processName;
            this.unitName = unitName;*/
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCostn() {
        return costn;
    }
    public void setCostn(String cost) {
        this.costn = cost;
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
    public String getThemes() {return themes;}
    public void setThemes(String themes) {
        this.themes = themes;}
        /*public String getProcessName() {return processName;}
        public void setProcessName(String processName) {this.processName = processName;}
        public String getUnitName() {return unitName;}
        public void setUnitName(String unitName) {this.unitName = unitName;}*/
}

