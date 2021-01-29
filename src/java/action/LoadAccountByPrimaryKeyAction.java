/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.AccountDAO;
import dto.AccountDTO;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class LoadAccountByPrimaryKeyAction extends ActionSupport {

    private final String ERROR = "error";
    private String username;
    private static final String CUSTOMER = "customerTag";
    private static final String EMPLOYEE = "employeeTag";
    private static final String MANAGER = "managerTag";
    private AccountDTO account;

    public LoadAccountByPrimaryKeyAction() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public String execute() throws Exception {
        String url = ERROR;
        Map session=ActionContext.getContext().getSession();
        AccountDTO accountSession=(AccountDTO) session.get("USER");
        AccountDAO dao = new AccountDAO();
        account = dao.getAccountByPrimaryKey(username);
        if (accountSession.getRole().equals("customer")) {
            url = CUSTOMER;
        } else if (accountSession.getRole().equals("employee")) {
            url = EMPLOYEE;
        }else if (accountSession.getRole().equals("manager")) {
            url = MANAGER;
        }
        return url;
    }
    @Action(value = "/user/loadAccountByPrimaryKey",
            results = {
                @Result(name = "customerTag", location = "/user/userManageProfile.jsp")
            }
    )
    public String loadByPrimaryKeyUser() throws Exception{
        AccountDAO dao = new AccountDAO();
        account = dao.getAccountByPrimaryKey(username);
        return CUSTOMER;
    }
    @Action(value = "/employee/loadAccountByPrimaryKey",
            results = {
                @Result(name = "employeeTag", location = "/employee/employeeManageProfile.jsp")
            }
    )
    public String loadByPrimaryKeyEmployee() throws Exception{
        AccountDAO dao = new AccountDAO();
        account = dao.getAccountByPrimaryKey(username);
        return EMPLOYEE;
    }
    @Action(value = "/manager/loadAccountByPrimaryKey",
            results = {
                @Result(name = "managerTag", location = "/manager/managerEmployeeFormUpdate.jsp")
            }
    )
    public String loadByPrimaryKeyManager() throws Exception{
        AccountDAO dao = new AccountDAO();
        account = dao.getAccountByPrimaryKey(username);
        return MANAGER;
    }

}
