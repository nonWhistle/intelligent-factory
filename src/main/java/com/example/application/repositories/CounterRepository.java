package com.example.application.repositories;

import com.example.application.views.production.Counter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CounterRepository
{
    @Select("SELECT * FROM uniloy_1")
    List<Counter> findAll();

    @Select("SELECT machine_output FROM uniloy_1 LIMIT 1")
    int getMachineOut();

    @Select("SELECT trimmer_input FROM uniloy_1 LIMIT 1")
    int getTrimmerIn();

    @Select("SELECT trimmer_output FROM uniloy_1 LIMIT 1")
    int getTrimmerOut();

    @Select("SELECT leaktester_input FROM uniloy_1 LIMIT 1")
    int getLeaktesterIn();

    @Select("SELECT leaktester_output FROM uniloy_1 LIMIT 1")
    int getLeaktesterOut();
}
