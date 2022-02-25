package com.example.application.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CounterRepository
{
    /*
    U1 Chart Data
     */
    @Select("SELECT machine_output FROM current LIMIT 1")
    int getCurrentMachineOut ();

    @Select("SELECT trimmer_input FROM current LIMIT 1")
    int getCurrentTrimmerIn ();

    @Select("SELECT trimmer_output FROM current LIMIT 1")
    int getCurrentTrimmerOut ();

    @Select("SELECT leaktester_input FROM current LIMIT 1")
    int getCurrentLeaktesterIn ();

    @Select("SELECT leaktester_output FROM current LIMIT 1")
    int getCurrentLeaktesterOut ();


    @Select("SELECT machine_output FROM shift1 LIMIT 1")
    int getShift1MachineOut();

    @Select("SELECT trimmer_input FROM shift1 LIMIT 1")
    int getShift1TrimmerIn();

    @Select("SELECT trimmer_output FROM shift1 LIMIT 1")
    int getShift1TrimmerOut();

    @Select("SELECT leaktester_input FROM shift1 LIMIT 1")
    int getShift1LeaktesterIn();

    @Select("SELECT leaktester_output FROM shift1 LIMIT 1")
    int getShift1LeaktesterOut();


    @Select("SELECT machine_output FROM shift2 LIMIT 1")
    int getShift2MachineOut();

    @Select("SELECT trimmer_input FROM shift2 LIMIT 1")
    int getShift2TrimmerIn();

    @Select("SELECT trimmer_output FROM shift2 LIMIT 1")
    int getShift2TrimmerOut();

    @Select("SELECT leaktester_input FROM shift2 LIMIT 1")
    int getShift2LeaktesterIn();

    @Select("SELECT leaktester_output FROM shift2 LIMIT 1")
    int getShift2LeaktesterOut();


    /*
    U2 chart data
     */
    @Select("SELECT machine_output FROM current LIMIT 1 OFFSET 1")
    int getU2CurrentMachineOut ();

    @Select("SELECT trimmer_input FROM current LIMIT 1 OFFSET 1")
    int getU2CurrentTrimmerIn ();

    @Select("SELECT trimmer_output FROM current LIMIT 1 OFFSET 1")
    int getU2CurrentTrimmerOut ();

    @Select("SELECT leaktester_input FROM current LIMIT 1 OFFSET 1")
    int getU2CurrentLeaktesterIn ();

    @Select("SELECT leaktester_output FROM current LIMIT 1 OFFSET 1")
    int getU2CurrentLeaktesterOut ();


    @Select("SELECT machine_output FROM shift1 LIMIT 1 OFFSET 1")
    int getU2Shift1MachineOut();

    @Select("SELECT trimmer_input FROM shift1 LIMIT 1 OFFSET 1")
    int getU2Shift1TrimmerIn();

    @Select("SELECT trimmer_output FROM shift1 LIMIT 1 OFFSET 1")
    int getU2Shift1TrimmerOut();

    @Select("SELECT leaktester_input FROM shift1 LIMIT 1 OFFSET 1")
    int getU2Shift1LeaktesterIn();

    @Select("SELECT leaktester_output FROM shift1 LIMIT 1 OFFSET 1")
    int getU2Shift1LeaktesterOut();


    @Select("SELECT machine_output FROM shift2 LIMIT 1 OFFSET 1")
    int getU2Shift2MachineOut();

    @Select("SELECT trimmer_input FROM shift2 LIMIT 1 OFFSET 1")
    int getU2Shift2TrimmerIn();

    @Select("SELECT trimmer_output FROM shift2 LIMIT 1 OFFSET 1")
    int getU2Shift2TrimmerOut();

    @Select("SELECT leaktester_input FROM shift2 LIMIT 1 OFFSET 1")
    int getU2Shift2LeaktesterIn();

    @Select("SELECT leaktester_output FROM shift2 LIMIT 1 OFFSET 1")
    int getU2Shift2LeaktesterOut();


    /*
    U3 chart data
     */
    @Select("SELECT machine_output FROM current ORDER BY id LIMIT 1 OFFSET 2")
    int getU3CurrentMachineOut ();

    @Select("SELECT trimmer_input FROM current ORDER BY id LIMIT 1 OFFSET 2")
    int getU3CurrentTrimmerIn ();

    @Select("SELECT trimmer_output FROM current ORDER BY id LIMIT 1 OFFSET 2")
    int getU3CurrentTrimmerOut ();

    @Select("SELECT leaktester_input FROM current LIMIT 1 OFFSET 2")
    int getU3CurrentLeaktesterIn ();

    @Select("SELECT leaktester_output FROM current LIMIT 1 OFFSET 2")
    int getU3CurrentLeaktesterOut ();


    @Select("SELECT machine_output FROM shift1 LIMIT 1 OFFSET 2")
    int getU3Shift1MachineOut();

    @Select("SELECT trimmer_input FROM shift1 LIMIT 1 OFFSET 2")
    int getU3Shift1TrimmerIn();

    @Select("SELECT trimmer_output FROM shift1 LIMIT 1 OFFSET 2")
    int getU3Shift1TrimmerOut();

    @Select("SELECT leaktester_input FROM shift1 LIMIT 1 OFFSET 2")
    int getU3Shift1LeaktesterIn();

    @Select("SELECT leaktester_output FROM shift1 LIMIT 1 OFFSET 2")
    int getU3Shift1LeaktesterOut();


    @Select("SELECT machine_output FROM shift2 LIMIT 1 OFFSET 2")
    int getU3Shift2MachineOut();

    @Select("SELECT trimmer_input FROM shift2 LIMIT 1 OFFSET 2")
    int getU3Shift2TrimmerIn();

    @Select("SELECT trimmer_output FROM shift2 LIMIT 1 OFFSET 2")
    int getU3Shift2TrimmerOut();

    @Select("SELECT leaktester_input FROM shift2 LIMIT 1 OFFSET 2")
    int getU3Shift2LeaktesterIn();

    @Select("SELECT leaktester_output FROM shift2 LIMIT 1 OFFSET 2")
    int getU3Shift2LeaktesterOut();
}
