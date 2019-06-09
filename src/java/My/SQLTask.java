/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My;

import TasKer.Core.TaskConnection;
import TasKer.Core.UserSQLException;
import TasKer.Tasks.TaskEntety;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public class SQLTask extends TaskEntety {

    private static final Logger log = Logger.getLogger(SQLTask.class.getName());

    protected String answer;
    protected String question;
    protected String img;

    private SQLException exception;
    private ArrayList executeResult;

    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = super._getParams();
        list.put("question", this.question);
        list.put("answer", this.answer);
        list.put("img", this.img);
        return list;
    }

    @Override
    protected void _setParams(HashMap<String, Object> list) throws Exception {
        try {
            super._setParams(list);
            this.question = (String) list.get("question");
            this.answer = (String) list.get("answer");
            this.img = (String) list.get("img");
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    protected boolean _isCorrect() {

        if (this.question == null || this.question.isEmpty()) {
            return false;
        }
        if (this.answer == null || this.answer.isEmpty()) {
            return false;
        }

        return true;
    }

    public SQLTask() {

    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getImg() {
        return this.img;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer.toLowerCase();
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean valid() throws Exception {
        try {
            execute();
            if (this.exception != null) {
                return false;
            }
            if (this.executeResult.size() == 0) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    public void execute() throws NamingException, SQLException {
        try {
            TaskConnection conn = null;
            conn = new TaskConnection(((SQLTaskList) this.list).getSchema());
            try {
                this.executeResult = conn.exequtQuery(this.answer);
            } catch (UserSQLException ex) {
                this.exception = ex;
            }
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    public ArrayList getExecuteResult() {
        return this.executeResult;
    }

    public SQLException getException() {
        return this.exception;
    }

}
