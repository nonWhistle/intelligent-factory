package com.example.application.views.production;

public class Counter
{
    public int machine_output;
    public int trimmer_input;
    public int trimmer_output;
    public int leaktester_input;
    public int leaktester_output;

    public int getMachine_output() {
        return machine_output;
    }

    public void setMachine_output(int machine_output) {
        this.machine_output = machine_output;
    }

    public int getTrimmer_input() {
        return trimmer_input;
    }

    public void setTrimmer_input(int trimmer_input) {
        this.trimmer_input = trimmer_input;
    }

    public int getTrimmer_output() {
        return trimmer_output;
    }

    public void setTrimmer_output(int trimmer_output) {
        this.trimmer_output = trimmer_output;
    }

    public int getLeaktester_input() {
        return leaktester_input;
    }

    public void setLeaktester_input(int leaktester_input) {
        this.leaktester_input = leaktester_input;
    }

    public int getLeaktester_output() {
        return leaktester_output;
    }

    public void setLeaktester_output(int leaktester_output) {
        this.leaktester_output = leaktester_output;
    }
}
