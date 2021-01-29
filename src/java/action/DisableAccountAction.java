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
import dto.AccountErrorObject;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author mevrthisbang
 */
public class DisableAccountAction extends ActionSupport {

    private String currentPassword, email;
    private final String SUCCESS = "success";
    private final String ERROR = "error";
    private final String INVALID = "invalid";

    public DisableAccountAction() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String execute() throws Exception {
        String url = ERROR;
        Map session = ActionContext.getContext().getSession();
        AccountDTO accountSession = (AccountDTO) session.get("USER");
        HttpServletRequest request = ServletActionContext.getRequest();
        AccountDAO dao = new AccountDAO();
        boolean valid = true;
        AccountErrorObject errorObj = new AccountErrorObject();
        if (!email.equals(accountSession.getEmail())) {
            errorObj.setEmailError("Email's not match with login's email");
            valid = false;
        }
        if (!currentPassword.equals(dao.getPasswordByUsername(accountSession.getUsername()))) {
            errorObj.setCurrentPasswordError("Password's not match with account");
            valid = false;
        }
        if (valid) {
            if (dao.disableAccount(accountSession.getUsername())) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Disable failed");
            }
        } else {
            url = INVALID;
            request.setAttribute("INVALID", errorObj);
        }
        return url;
    }

}
