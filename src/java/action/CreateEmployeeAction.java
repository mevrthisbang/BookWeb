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
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class CreateEmployeeAction extends ActionSupport {

    private String email, firstname, lastname, phone, gender, address, description;
    private AccountDTO account;
    private final String SUCCESS = "success";
    private final String ERROR = "error";
    private final String INVALID = "invalid";

    public CreateEmployeeAction() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    @Action(value = "/manager/createEmployee",
            results = {
                @Result(name = "success", type = "redirectAction", location = "loadEmployee")
                ,
                @Result(name = "error", location = "/manager/managerEmployeeForm.jsp")
                ,
                @Result(name = "invalid", location = "/manager/managerEmployeeForm.jsp")
            })
    public String execute() throws Exception {
        String url = ERROR;
        AccountDAO dao = new AccountDAO();
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
        if (firstname.isEmpty()) {
            errorObj.setFirstnameError("First name is not supposed to be empty");
            valid = false;
        }
        if (lastname.isEmpty()) {
            errorObj.setLastnameError("Last name is not supposed to be empty");
            valid = false;
        }
        if (!phone.isEmpty() && !phone.matches("[0-9]{10}")) {
            errorObj.setPhoneError("Phone must be 10 numbers");
            valid = false;
        }
        if (dao.existedEmail(email)) {
            errorObj.setEmailError("Email is existed");
            valid = false;
        }
        if (dao.existedPhone(phone)) {
            errorObj.setPhoneError("Phone is existed");
            valid = false;
        }
        String username = dao.getLastInsertUsername();
        if (username == null) {
            username = "A-1";
        } else {
            String[] tmp = username.split("-");
            int count = Integer.parseInt(tmp[1].trim()) + 1;
            username = "A-" + count;
        }
        account = new AccountDTO(username, firstname, lastname, email, phone, gender, address, description);
        account.setRole("employee");
        account.setIsStillWorking(true);
        if (valid) {
            if (dao.insertNewEmployee(account)) {
                url = SUCCESS;
            } else {
                ServletActionContext.getRequest().setAttribute("ERROR", "Create failed");
            }
        } else {
            url = INVALID;
            ServletActionContext.getRequest().setAttribute("INVALID", errorObj);
        }
        return url;
    }

}
