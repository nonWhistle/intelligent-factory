package com.example.application.repositories;

import com.example.application.views.production.Machine;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.Date;
import java.util.List;

@Mapper
public interface TrendRepo
{
    //Uniloy 1

    //Returns all the values in the u1_output column of machine_efficiency table.
    @Select("SELECT u1_output FROM u1_efficiency ORDER BY u1_date")
    List<Double> getU1Total();

    //Returns all the values in the date column of machine_efficiency table.
    @Select("SELECT u1_date FROM u1_efficiency ORDER BY u1_date")
    List<Date> getU1Dates();

    //Inserts data into the machine efficiency table for uniloy 1
    @Insert("INSERT INTO u1_efficiency(u1_output, u1_date) VALUES(#{u1_output}, #{u1_date})")
    void insertForU1(Machine machine);


    //Uniloy2

    //Returns all the values in the u2_output column of machine_efficiency table.
    @Select("SELECT u2_output FROM u2_efficiency ORDER BY u2_date")
    List<Double> getU2Total();

    //Returns all the values in the u2_date column of machine_efficiency table.
    @Select("SELECT u2_date FROM u2_efficiency ORDER BY u2_date")
    List<Date> getU2Dates();

    //Inserts data into the machine efficiency table for uniloy 1
    @Insert("INSERT INTO u2_efficiency(u2_output, u2_date) VALUES(#{u2_output}, #{u2_date})")
    void insertForU2(Machine machine);


}
