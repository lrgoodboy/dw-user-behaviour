package com.anjuke.dw.user_behaviour.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anjuke.dw.user_behaviour.dao.ActionLogDao;
import com.anjuke.dw.user_behaviour.model.ActionLog;


@Controller
public class FilterController {
    
    private static final DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
    
    @Autowired
    private ActionLogDao actionLogDao;

    @RequestMapping("/filter")
    public String index(ModelMap model,
            @RequestParam(value = "date", required = false) String dateString,
            @RequestParam(value = "os", required = false) String os,
            @RequestParam(value = "version", required = false) String version,
            @RequestParam(value = "channel", required = false) String channel) {
        
        Date date = null;
        try {
            date = dfDate.parse(dateString);
        } catch (Exception e) {}
        if (date == null) {
            date = DateUtils.addDays(new Date(), -1);
        }
        
        String versions = "ver6.0,5.3.1,5.3,5.2.1,5.2,5.1.2,5.1.1,5.1,5.0.1,5.0,4.6.3,4.6.2,4.6.1,4.6,4.5,4.4,4.1.2,4.1.1,4.1,4.0,3.7.3,3.7.2,3.7.1,3.7,3.6.1,3.6,3.5.1,3.4.1,3.3.1,3.2.2,3.2.1,3.2,3.1.3,3.1,3.0.1,3.0,2.1.2,2.0.3,2.0.2,2.0.1,2.0,1.2,1.1.1,1.1,1.0.1";
        model.addAttribute("versionList", versions.split(","));
        
        String channels = "A00,A01,A02,A08,A17,A18,A20,b00,b01,b03,b04,b05,b07,b100,b101,b102,b103,b112,b130,b132,b133,b135,b144,b148,b171,b172,b174,b178,b180,b186,b187,b188,b190,b191,b196,b198,b199,b200,b205,b206,b210,b22,b221,b23,b238,b239,b240,b241,b242,b243,b248,b25,b250,b251,b253,b254,b258,b30,b40,b49,b50,b51,b52,b56,b58,b59,b60,b61,b63,b64,b78,b81,b82,b92,b93,b95,b98,b99,d10,d52,d75,d85,d87,dev222222,E01";
        model.addAttribute("channelList", channels.split(","));
        
        List<ActionLog> actionLogList = actionLogDao.findByFilters(
                date, os, version, channel, null, null);
        model.addAttribute("actionLogList", actionLogList);
        
        model.addAttribute("date", dfDate.format(date));
        model.addAttribute("os", os);
        model.addAttribute("version", version);
        model.addAttribute("channel", channel);
        
        return "filter";
    }
    
}
