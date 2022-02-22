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
    //Returns all the values in the u1_output column of machine_total table.
    @Select("SELECT u1_output FROM machine_efficiency ORDER BY u1_date")
    List<Double> getU1Total();

    //Returns all the values in the date column of machine_total table.
    @Select("SELECT u1_date FROM machine_efficiency ORDER BY u1_date")
    List<Date> getU1Dates();

    //Inserts data into the machine efficiency table for uniloy 1
    @Insert("INSERT INTO machine_efficiency(u1_output, u1_date) VALUES(#{u1_output}, #{u1_date})")
    void create(Machine machine);
}
