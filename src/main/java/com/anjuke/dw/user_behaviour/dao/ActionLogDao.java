package com.anjuke.dw.user_behaviour.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.anjuke.dw.user_behaviour.model.ActionLog;


@Repository
public class ActionLogDao extends JdbcDaoSupport {
    
    private static final String SELECT_FIELDS =
            "id, machine, app_version, pm, unique_id, page_id, action_id, log_time";
    
    private static final DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat dfDailyTable = new SimpleDateFormat("yyyyMMdd");
    
    public List<ActionLog> findByFilters(Date date, String os, String version, String channel,
            Integer offset, Integer limit) {
        
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        
        sql.append("SELECT ").append(SELECT_FIELDS);
        sql.append(" FROM zj_dw_mobile_action_log_search");
        sql.append(" WHERE log_dt = ?");
        params.add(dfDate.format(date));
        
        if (os != null && !os.isEmpty()) {
            sql.append(" AND machine = ?");
            params.add(os);        
        } else {
            sql.append(" AND machine IN ('android', 'ios')");
        }
        
        if (version != null && !version.isEmpty()) {
            sql.append(" AND app_version = ?");
            params.add(version);
        }
        
        if (channel != null && !channel.isEmpty()) {
            sql.append(" AND pm = ?");
            params.add(channel);
        }
        
        if (offset == null) {
            offset = 0;
        }
        
        if (limit == null) {
            limit = 20;
        }
        
        sql.append(" LIMIT ").append(offset).append(", ").append(limit);        
        
        return getJdbcTemplate().query(sql.toString(), params.toArray(), rowMapper);        
    }
    
    public List<ActionLog> findByUniqid(Date date, String uniqid, Integer offset, Integer limit) {
        
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        
        sql.append("SELECT ").append(SELECT_FIELDS);
        sql.append(" FROM zj_dw_mobile_action_log_").append(dfDailyTable.format(date));
        sql.append(" WHERE 1 = 1");
        
        if (uniqid != null && !uniqid.isEmpty()) {
            sql.append(" AND unique_id LIKE ?");
            params.add(uniqid + "%");
        }        
        
        if (offset == null) {
            offset = 0;
        }
        
        if (limit == null) {
            limit = 20;
        }
        
        sql.append(" ORDER BY log_time");
        sql.append(" LIMIT ").append(offset).append(", ").append(limit);        
        
        return getJdbcTemplate().query(sql.toString(), params.toArray(), rowMapper);    
    }
    
    private RowMapper<ActionLog> rowMapper = new RowMapper<ActionLog>() {

        public ActionLog mapRow(ResultSet rs, int rowNum) throws SQLException {
            ActionLog row = new ActionLog();
            row.setId(rs.getInt("id"));
            row.setMachine(rs.getString("machine"));
            row.setAppVersion(rs.getString("app_version"));
            row.setPm(rs.getString("pm"));
            row.setUniqueId(rs.getString("unique_id"));
            row.setPageId(rs.getString("page_id"));
            row.setActionId(rs.getString("action_id"));
            row.setLogTime(rs.getString("log_time"));
            return row;
        }
        
    };

}
