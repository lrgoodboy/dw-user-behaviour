package com.anjuke.dw.user_behaviour.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anjuke.dw.user_behaviour.dao.ActionLogDao;
import com.anjuke.dw.user_behaviour.model.ActionLog;


@Controller
public class FilterController {
    
    @Autowired
    private ActionLogDao actionLogDao;

    @RequestMapping("/filter")
    public String index(ModelMap model) {
        
        List<ActionLog> actionLogList = actionLogDao.findByFilters(DateUtils.addDays(new Date(), -1));
        model.addAttribute("actionLogList", actionLogList);
        
        return "filter";
    }
    
}
