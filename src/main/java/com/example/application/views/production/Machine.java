package com.example.application.views.production;

import java.time.LocalDate;

public class Machine
{
    private double u1_output;
    private LocalDate u1_date;

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
}
