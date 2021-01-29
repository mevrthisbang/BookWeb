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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author mevrthisbang
 */
public class LoginAction extends ActionSupport {

    private String email, password;
    private String fromGuest;
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    private static final String CUSTOMERCART = "customerCart";

    public LoginAction() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFromGuest() {
        return fromGuest;
    }

    public void setFromGuest(String fromGuest) {
        this.fromGuest = fromGuest;
    }

    @Override
    public String execute() throws Exception {
        String url = ERROR;
        AccountDAO dao = new AccountDAO();
        AccountDTO user = dao.checkLogin(email, password);
        HttpServletRequest request = ServletActionContext.getRequest();
        if (user != null) {
            Map session = ActionContext.getContext().getSession();
            session.put("USER", user);
            url = SUCCESS;
            if (user.getRole().equals("customer")) {
                if (!fromGuest.isEmpty()) {
                    url = CUSTOMERCART;
                }
            }
        }else{
            request.setAttribute("ERROR", "Invalid username and password");
        }
        return url;
    }
}
