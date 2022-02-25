package com.example.application.displayData;

import com.example.application.views.production.Machine;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class DowntimeData
{
    public void createDowntimeData()
    {
        LocalDate date = LocalDate.of(2021, 02, 24);

        for(int i = 0; i < 365; i++) {
            LocalDate dt = date.plusDays(i);
            Random random = new Random();
            int hour = random.nextInt(13) + 10;
            int minute = random.nextInt(60);
            int second = random.nextInt(59) + 1;

            LocalTime runTime = LocalTime.of(hour, minute, second);
            LocalTime shiftTime = LocalTime.of(23, 59, 59);

            Machine machine = new Machine();
            machine.setU1_date(dt);
            machine.setU1_runTime(runTime);
            machine.setU1_shiftTime(shiftTime);
            machine.setDowntime();

            //Machine machine1 = new Machine();
            //machine1.setU1_comment("Parts not stocked, had to order in which caused extended downtime");

            //downtimeRepo.insertU1Downtime(machine);
            //downtimeRepo.insertU1Comment("Parts not stocked, had to order in which caused extended downtime");

            //System.out.println(dt + ", " + runTime + ", " + machine.getDowntime());
        }
    }
}
