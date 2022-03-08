package com.example.application.displayData;

import com.example.application.repositories.CounterRepository;
import com.vaadin.flow.component.html.Label;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlarmTest {

    private LowOEEAlarm alarmUnderTest;
    private CounterRepository counterRepository;

    @BeforeEach
    public void createAlarm()
    {
        alarmUnderTest = new LowOEEAlarm(counterRepository);
    }

    @Test
    @DisplayName("Should assert true when machine efficiency is >90% but <98%")
    public void createAlarmLowEffLabel()
    {
        //when
        String testMachine = "Test Machine";
        Label lowEffLabel = alarmUnderTest.createAlarmLabel(testMachine, 100, 97);

        //then
        Assertions.assertEquals(lowEffLabel.getText(), "***low efficiency, " + testMachine +
                " has lost >2% of total bottles produced***");
    }

    @Test
    @DisplayName("Should assert true when machine efficiency is <90%")
    public void createVeryLowEffLabel()
    {
        //when
        String testMachine = "Test Machine";
        Label veryLowEffLabel = alarmUnderTest.createAlarmLabel(testMachine, 100, 89);

        //then
        Assertions.assertEquals(veryLowEffLabel.getText(), "***VERY LOW EFFICIENCY, " + testMachine +
                " has lost >10% of total bottles produced***");
    }

    @Test
    @DisplayName("Should assert true if the leaktester output > machine output")
    public void createBadCountLabel()
    {
        //when
        Label badCountLabel = alarmUnderTest.createAlarmLabel("testMachine", 1, 2);

        //then
        Assertions.assertEquals(badCountLabel.getText(), "The output count is greater than what has been produced, " +
                "check the count sensors are working correctly.");
    }
}