package com.anjuke.dw.user_behaviour.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anjuke.dw.user_behaviour.dao.ActionLogDao;
import com.anjuke.dw.user_behaviour.dao.ActionLogLkpDao;
import com.anjuke.dw.user_behaviour.model.ActionLog;


@Controller
public class UserController {

    private static final int PAGE_SIZE = 20;
    private static final DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ActionLogDao actionLogDao;
    @Autowired
    private ActionLogLkpDao actionLogLkpDao;

    @RequestMapping("/user")
    public String index(ModelMap model,
            @RequestParam(value = "date", required = false) String dateString,
            @RequestParam(value = "uniqid", required = false) String uniqid,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Date date = null;
        try {
            date = dfDate.parse(dateString);
        } catch (Exception e) {}
        if (date == null) {
            date = DateUtils.addDays(new Date(), -1);
        }

        int totalPage = 0;
        try {
            Integer[] count = new Integer[1];
            actionLogDao.findByUniqid(date, uniqid, null, null, count);
            totalPage = (int) Math.ceil((double) count[0] / PAGE_SIZE);
        } catch (DataAccessException e) {
            if (!e.getMessage().contains("doesn't exist")) {
                throw e;
            }
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        int offset = (page - 1) * PAGE_SIZE;

        if (totalPage >= 1) {
            List<ActionLog> actionLogList = actionLogDao.findByUniqid(
                    date, uniqid, offset, PAGE_SIZE, null);
            model.addAttribute("actionLogList", actionLogList);
        }
        
        model.addAttribute("pageLkp", actionLogLkpDao.getByType(ActionLogLkpDao.TYPE_PAGE));
        model.addAttribute("actionLkp", actionLogLkpDao.getByType(ActionLogLkpDao.TYPE_ACTION));

        model.addAttribute("date", dfDate.format(date));
        model.addAttribute("uniqid", uniqid);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);

        return "user";
    }

}
