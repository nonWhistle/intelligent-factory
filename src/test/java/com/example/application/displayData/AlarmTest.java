package com.example.application.displayData;

import com.vaadin.flow.component.html.Label;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlarmTest {

    Alarm alarmUnderTest;

    @BeforeEach
    public void createAlarm()
    {
        alarmUnderTest = new Alarm();
    }

    @Test
    @DisplayName("Should assert true when machine efficiency is >2% but <10%")
    public void createAlarmLowEffLabel()
    {
        //given
        boolean lowEffExpectations = false;
        String testMachine = "Test Machine";
        Label lowEffLabel = alarmUnderTest.createAlarmLabel(testMachine, 100, 97);


        //when
        if(lowEffLabel.getText().equals("***low efficiency, " + testMachine +
                " has lost >2% of total bottles produced***")) {
            lowEffExpectations = true;
        }

        //then
        Assertions.assertTrue(lowEffExpectations);
    }

    @Test
    @DisplayName("Should assert true when machine efficiency is >10%")
    public void createVeryLowEffLabel()
    {
        //given
        boolean veryLowEffExpectations = false;
        String testMachine = "Test Machine";
        Label veryLowEffLabel = alarmUnderTest.createAlarmLabel(testMachine, 100, 89);

        //when
        if(veryLowEffLabel.getText().equals("***VERY LOW EFFICIENCY, " + testMachine +
                " has lost >10% of total bottles produced***")) {
            veryLowEffExpectations = true;
        }

        //then
        Assertions.assertTrue(veryLowEffExpectations);
    }

    @Test
    @DisplayName("Should assert true if the leaktester output > machine output")
    public void createBadCountLabel()
    {
        //given
        boolean veryLowEffExpectations = false;
        Label badCountLabel = alarmUnderTest.createAlarmLabel("testMachine", 1, 2);

        //when
        if(badCountLabel.getText().equals("The output count is greater than what has been produced, check the count sensors are working correctly.")) {
            veryLowEffExpectations = true;
        }

        //then
        Assertions.assertTrue(veryLowEffExpectations);
    }
}