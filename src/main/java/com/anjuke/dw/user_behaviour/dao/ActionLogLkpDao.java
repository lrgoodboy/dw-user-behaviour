package com.anjuke.dw.user_behaviour.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class ActionLogLkpDao extends JdbcDaoSupport {
    
    public static final int TYPE_PAGE = 1;
    public static final int TYPE_ACTION = 2;
    
    public Map<String, String> getByType(int type) {
        
        SqlRowSet rs = getJdbcTemplate().queryForRowSet(
                "SELECT log_id, log_name FROM zj_dw_mobile_action_log_lkp WHERE log_type = ?",
                type);
        
        Map<String, String> map = new HashMap<String, String>();
        while (rs.next()) {
            map.put(rs.getString("log_id"), rs.getString("log_name"));
        }
        
        return map;
    }

}
