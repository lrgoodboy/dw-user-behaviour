package com.anjuke.dw.user_behaviour.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    
    private static final DateFormat dfDailyTable = new SimpleDateFormat("yyyyMMdd");
    
    public List<ActionLog> findByFilters(Date date) {
        
        return getJdbcTemplate().query(
                "SELECT " + SELECT_FIELDS + " FROM zj_dw_mobile_action_log_" + dfDailyTable.format(date)
                + " WHERE 1 = 1 LIMIT 20",
                rowMapper);        
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
