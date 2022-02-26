package com.example.application.repositories;

import com.example.application.views.production.MachinesOn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MachinesOnRepo
{
    //Inserts data into the machine_on_off table
    @Insert("INSERT INTO machine_on_off(machine, on_off) " +
            "VALUES(#{machine}, #{onOff})")
    void insertToMachineOnOff(MachinesOn machineOn);

    //Returns all data from the selected columns in machine_on_off.
    @Select("SELECT on_off FROM machine_on_off WHERE machine=#{machine}")
    boolean getSelected(MachinesOn machinesOn);

}
