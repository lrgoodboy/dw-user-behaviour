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
public class UserController {
    
    private static final DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
    
    @Autowired
    private ActionLogDao actionLogDao;

    @RequestMapping("/user")
    public String index(ModelMap model,
            @RequestParam("date") String dateString,
            @RequestParam("uniqid") String uniqid) {
        
        Date date = null;
        try {
            date = dfDate.parse(dateString);
        } catch (Exception e) {}
        if (date == null) {
            date = DateUtils.addDays(new Date(), -1);
        }
        
        List<ActionLog> actionLogList = actionLogDao.findByUniqid(
                date, uniqid, null, null);
        model.addAttribute("actionLogList", actionLogList);
        
        model.addAttribute("date", dfDate.format(date));
        model.addAttribute("uniqid", uniqid);
        
        return "user";
    }
    
}
