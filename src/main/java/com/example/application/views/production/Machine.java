package com.example.application.views.production;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Machine
{
    private double u1_output;
    private LocalDate u1_date;
    private LocalTime u1_runTime;
    private LocalTime u1_shiftTime;
    private LocalTime downtime;
    private String u1_comments;

    private double u2_output;
    private LocalDate u2_date;
    private LocalTime u2_startTime;
    private LocalTime u2_offTime;

    public double getU1_output() {
        return u1_output;
    }

    public void setU1_output(double u1_output) {
        this.u1_output = u1_output;
    }

    public LocalDate getU1_date() {
        return u1_date;
    }

    public void setU1_date(LocalDate u1_date) {
        this.u1_date = u1_date;
    }

    public LocalTime getU1_runTime() {
        return u1_runTime;
    }

    public void setU1_runTime(LocalTime u1_runTime) {
        this.u1_runTime = u1_runTime;
    }

    public LocalTime getU1_shiftTime() {
        return u1_shiftTime;
    }

    public void setU1_shiftTime(LocalTime u1_shiftTime) {
        this.u1_shiftTime = u1_shiftTime;
    }

    public LocalTime getDowntime() {
        return downtime;
    }

    public void setDowntime() {
        long dt = u1_runTime.until(u1_shiftTime, ChronoUnit.SECONDS);
        downtime = LocalTime.of(00,00,00).plusSeconds(dt);
    }

    public String getU1_comments() {
        return u1_comments;
    }

    public void setU1_comments(String u1_comments) {
        this.u1_comments = u1_comments;
    }



    public double getU2_output() {
        return u2_output;
    }

    public void setU2_output(double u2_output) {
        this.u2_output = u2_output;
    }

    public LocalDate getU2_date() {
        return u2_date;
    }

    public void setU2_date(LocalDate u2_date) {
        this.u2_date = u2_date;
    }

    public LocalTime getU2_startTime() {
        return u2_startTime;
    }

    public void setU2_startTime(LocalTime u2_startTime) {
        this.u2_startTime = u2_startTime;
    }

    public LocalTime getU2_offTime() {
        return u2_offTime;
    }

    public void setU2_offTime(LocalTime u2_offTime) {
        this.u2_offTime = u2_offTime;
    }
}
