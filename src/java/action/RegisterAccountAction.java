/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.AccountDAO;
import dto.AccountDTO;
import dto.AccountErrorObject;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author mevrthisbang
 */
public class RegisterAccountAction extends ActionSupport {

    private String firstname, lastname, email, password, confirm, phone, gender;
    private AccountDTO account;
    private final String ERROR = "error";
    private final String INVALID = "invalid";
    private final String SUCCESS = "success";

    public RegisterAccountAction() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public String execute() throws Exception {
        String url = ERROR;
        boolean valid = true;
        AccountErrorObject errorObj = new AccountErrorObject();
        if (email.isEmpty()) {
            errorObj.setEmailError("Email is not supposed to be empty");
            valid = false;
        }
        if (!email.trim().matches("\\w+@\\w+[.]\\w+([.]\\w+)?")) {
            errorObj.setEmailError("Email must be like abc@abc.abc");
            valid = false;
        }
        if (password.length() < 6 || password.length() > 20) {
            errorObj.setNewPasswordError("Password must be 6-20 characters");
            valid = false;
        }
        if (!confirm.equals(password)) {
            errorObj.setConfirmPasswordError("Confirm must match new password");
            valid = false;
        }
        if (firstname.isEmpty()) {
            errorObj.setLastnameError("First name and Last name is required");
            valid = false;
        }
        if (lastname.isEmpty()) {
            errorObj.setLastnameError("First name and Last name is required");
            valid = false;
        }
        if (!phone.matches("[0-9]{10}")) {
            errorObj.setPhoneError("Phone must be 10 numbers");
            valid = false;
        }
        AccountDAO dao = new AccountDAO();
        if (dao.existedEmail(email)) {
            errorObj.setEmailError("Email is existed");
            valid = false;
        }
        if (dao.existedPhone(phone)) {
            errorObj.setPhoneError("Phone is existed");
            valid = false;
        }
        String username;
        username = dao.getLastInsertUsername();
        if (username == null) {
            username = "A-1";
        } else {
            String[] tmp = username.split("-");
            int count = Integer.parseInt(tmp[1].trim()) + 1;
            username = "A-" + count;
        }
        account = new AccountDTO(username, firstname, lastname, email, phone, gender, "", "");
        account.setPassword(password);
        account.setRole("customer");
        HttpServletRequest request = ServletActionContext.getRequest();
        if (valid) {
            if (dao.insertNewAccount(account)) {
                url = SUCCESS;
            }
        } else {
            url = INVALID;
            request.setAttribute("INVALID", errorObj);
        }
        return url;
    }

}
