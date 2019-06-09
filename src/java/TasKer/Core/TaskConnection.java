/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Core;

import TasKer.TasKer;
import static TasKer.TasKer.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public class TaskConnection implements TasKer {

    private static final Logger log = Logger.getLogger(TaskConnection.class.getName());

    private static final int ROW_LIMIT = 100;
    private static final String CONNECTION_NAME = "postgre";

    private Connection conn;
    private SQLException ex;
    private String schema;

    public TaskConnection(String schema) {
        this.schema = schema;
    }

    public ArrayList exequtQuery(String query) throws NamingException, UserSQLException, SQLException {
        try {
            try {
                this.conn = getConnection(CONNECTION_NAME);
                Statement stmt = conn.createStatement();
                stmt.setMaxRows(this.ROW_LIMIT);
                stmt.execute("SET search_path TO " + this.schema);
                try {
                    ResultSet rs = stmt.executeQuery(query);
                    return getResultArray(rs);
                } catch (SQLException ex) {
                    throw new UserSQLException(ex);
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }

    }

    public ArrayList getResultArray(ResultSet rs) throws SQLException {
        try {
            ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();

            ArrayList<Object> buf = new ArrayList<Object>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                buf.add(rs.getMetaData().getColumnName(i));
            }
            list.add(buf);

            while (rs.next()) {
                buf = new ArrayList<Object>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    buf.add(rs.getObject(i));
                }
                list.add(buf);
            }

            return list;
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

}
