package com.cmos.ipg.controller;

import com.cmos.ipg.entity.Command;
import com.cmos.ipg.mapper.CommandMapper;
import com.cmos.ipg.utils.DataTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by jackl on 2016/5/19.
 */
@RestController
@RequestMapping("/api")
public class CommandController {
    @Autowired
    CommandMapper commandMapper;
    @Autowired
    DataTool dataTool;

    @RequestMapping(value = "/qc/{eventId}",method = RequestMethod.GET)
    public Command hello(@PathVariable("eventId") int  eventId){
        return commandMapper.findOneByEventId(eventId);
    }

    @RequestMapping(value = "/control",method = RequestMethod.POST)
    public String send(@RequestBody Command c){
        Command command=new Command();
        command.setEventId(dataTool.getCurrentSeconds());
        command.setCommandType(c.getCommandType());
        command.setNum(c.getNum());
        command.setAction(c.getAction());
        command.setParam(c.getParam());
        command.setCommandStatus((short) -1);
        command.setActionDate(new Date());
        commandMapper.save(command);
        return "success:"+new Date().getTime();
    }

}
