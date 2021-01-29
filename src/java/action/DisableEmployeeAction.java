/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.AccountDAO;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class DisableEmployeeAction extends ActionSupport {
    private String username;
    private final String SUCCESS="success";
    private final String ERROR="error";
    private String disableError;
    public DisableEmployeeAction() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisableError() {
        return disableError;
    }

    public void setDisableError(String disableError) {
        this.disableError = disableError;
    }
    
    public String execute() throws Exception {
        String url=ERROR;
        AccountDAO dao=new AccountDAO();
        if(dao.disableEmployee(username)){
            url=SUCCESS;
        }else{
            disableError="Can't disable. Employee is still working";
        }
        return url;
    }
    @Action(value = "/manager/disableEmployee",
            results = {
                @Result(name = "success", type = "redirectAction", location = "loadEmployee")
                ,
                @Result(name = "error", type = "redirectAction", location = "loadEmployee", params = {"disableError", "Can't disable. Employee is still working"})
            })
    public String disableAccount() throws Exception {
        String url=ERROR;
        AccountDAO dao=new AccountDAO();
        if(dao.disableEmployee(username)){
            url=SUCCESS;
        }
        return url;
    }
    
}
