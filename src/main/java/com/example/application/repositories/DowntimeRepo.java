package com.example.application.repositories;

import com.example.application.views.production.Machine;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DowntimeRepo
{
    //Inserts data into the u1_downtime table
    @Insert("INSERT INTO u1_downtime(u1_date, u1_runtime, downtime, u1_comments) " +
            "VALUES(#{u1_date}, #{u1_runTime}, #{downtime}, #{u1_comments})")
    void insertU1Downtime(Machine machine);

    //Returns a list of data from the selected columns of 31 rows.
    @Select("SELECT u1_date, u1_runtime, downtime, u1_comments FROM u1_downtime ORDER BY u1_date DESC LIMIT 31")
    List<Machine> getAll();

    //Updates the value in a selected cell.
    @Update("UPDATE u1_downtime SET u1_comments=#{u1_comments} WHERE u1_date=#{u1_date}")
    void updateU1Comment(Machine machine);
}
