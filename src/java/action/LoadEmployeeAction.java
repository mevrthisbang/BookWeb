/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.AccountDAO;
import dto.AccountDTO;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class LoadEmployeeAction extends ActionSupport {
    private List<AccountDTO> listEmployees;
    private final String SUCCESS="success";
    public LoadEmployeeAction() {
    }

    public List<AccountDTO> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(List<AccountDTO> listEmployees) {
        this.listEmployees = listEmployees;
    }
    
    public String execute() throws Exception {
        AccountDAO dao=new AccountDAO();
        listEmployees=dao.getAllEmployee();
        return SUCCESS;
    }
    @Action(value = "/manager/loadEmployee",
            results = {
                @Result(name = "success", location = "/manager/managerListEmployee.jsp")
            })
    public String loadEmployee() throws Exception {
        AccountDAO dao=new AccountDAO();
        listEmployees=dao.getAllEmployee();
        return SUCCESS;
    }
    
}
