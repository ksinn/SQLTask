/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My;

import TasKer.Core.TaskConnection;
import TasKer.Core.UserSQLException;
import TasKer.Exam.Answer;
import TasKer.Exam.CheckedAnswer;
import TasKer.Exam.Checker;
import TasKer.Core.InvalidAnswer;
import TasKer.Core.InvalidTask;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public class SQLChecker implements Checker {

    private static final Logger log = Logger.getLogger(SQLChecker.class.getName());

    private UserSQLException exeption;
    private ArrayList resultArray;

    @Override
    public CheckedAnswer check(Answer answ) throws Exception {
        try {
            if (valid(answ)) {
                SQLAnswer answer = (SQLAnswer) answ;
                SQLTask task = (SQLTask) answer.getTask();
                if (task.valid()) {
                    SQLCheckedAnswer chekedAnswer;
                    int executed = compear(((SQLTaskList) task.getList()).getSchema(), task.getAnswer(), answer.getQuery());
                    chekedAnswer = new SQLCheckedAnswer(executed == 1, answer);
                    chekedAnswer.setException(exeption);
                    chekedAnswer.setResultArray(resultArray);
                    return chekedAnswer;
                } else {
                    throw new InvalidTask(task.getException());
                }
            } else {
                throw new InvalidAnswer();
            }
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public boolean valid(Answer answer) {
        return answer instanceof SQLAnswer;
    }

    private int compear(String schema, String answerQuery, String userQuery) throws NamingException, UserSQLException, SQLException {
        answerQuery = answerQuery.replaceAll(";", "");
        userQuery = userQuery.replaceAll(";", "");
        try {
            String sql = "((" + answerQuery + ") except (" + userQuery + ")) union ((" + userQuery + ") except (" + answerQuery + "))";
            try {                
                ArrayList list = execute(schema, sql);
                int res = list.size() == 1 ? 1 : 0;
                this.resultArray = execute(schema, userQuery);
                if(res == 1 && isOrdered(userQuery))
                    res = this.compearOrder(schema, resultArray, answerQuery)?1:0;
                return res;
            } catch (UserSQLException ex) {
                try {
                    this.resultArray = execute(schema, userQuery);
                    return 0;
                } catch (UserSQLException e) {
                    this.exeption = e;
                    return -1;
                }
            }
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }

    }

    private ArrayList execute(String schema, String query) throws NamingException, UserSQLException, SQLException {
        try {
            TaskConnection conn = new TaskConnection(schema);
            return conn.exequtQuery(query);
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    private boolean isOrdered(String query) {
        return query.toLowerCase().contains(" order by ");
    }

    private boolean compearOrder(String schema, ArrayList<ArrayList> userResult, String answerQuery) throws NamingException, UserSQLException, SQLException {
        answerQuery = answerQuery.replaceAll(";", "");
        try {
            ArrayList<ArrayList> listAnswer = execute(schema, answerQuery);
            if (listAnswer.size() != userResult.size()) {
                return false;
            }
            for (int i = 0; i < listAnswer.size(); i++) {
                for (int j = 0; j < listAnswer.get(i).size(); j++) {
                    if (!listAnswer.get(i).get(j).equals(userResult.get(i).get(j))) {
                        return false;
                    }
                }
            }
            return true;

        } catch (NamingException | SQLException ex) {
            log.error(null, ex);
            throw ex;
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }

    }

}
