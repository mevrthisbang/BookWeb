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
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class UpdateAccountAction extends ActionSupport {

    private String currentPassword, newPassword, confirm, firstname, lastname, email, phone, gender, address, description, role;
    private AccountDTO account;
    private final String SUCCESS = "success";
    private String updateSuccess;
    private final String CUSTOMERERROR = "customerError";
    private final String CUSTOMERINVALID = "customerInvalid";
    private final String EMPLOYEEERROR = "employeeError";
    private final String EMPLOYEEINVALID = "employeeInvalid";

    public UpdateAccountAction() {
    }

    public String getUpdateSuccess() {
        return updateSuccess;
    }

    public void setUpdateSuccess(String updateSuccess) {
        this.updateSuccess = updateSuccess;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        AccountDTO accountSession = (AccountDTO) session.get("USER");
        String url = "";
        if (accountSession.getRole().equals("customer")) {
            url = CUSTOMERERROR;
        } else if (accountSession.getRole().equals("employee")) {
            url = EMPLOYEEERROR;
        }
        HttpServletRequest request = ServletActionContext.getRequest();
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
        if (!currentPassword.equals(dao.getPasswordByUsername(accountSession.getUsername()))) {
            errorObj.setCurrentPasswordError("Password not match with your password. Please check again");
            valid = false;
        }
        if (!newPassword.isEmpty() && newPassword.length() < 6 || !newPassword.isEmpty() && newPassword.length() > 20) {
            errorObj.setNewPasswordError("Password must be 6-20 characters");
            valid = false;
        }
        if (!confirm.equals(newPassword)) {
            errorObj.setConfirmPasswordError("Confirm must match new password");
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
        if (!email.equals(accountSession.getEmail())&&dao.existedEmail(email)) {
            errorObj.setEmailError("Email is existed");
            valid = false;
        }
        if (!phone.equals(accountSession.getPhone())&&dao.existedPhone(phone)) {
            errorObj.setPhoneError("Phone is existed");
            valid = false;
        }
        if (!newPassword.isEmpty()) {
            currentPassword = newPassword;
        }
        account = new AccountDTO(accountSession.getUsername(), firstname, lastname, email, phone, gender, address, description);
        account.setPassword(currentPassword);
        if (valid) {
            if (dao.updateAccount(account)) {
                url = SUCCESS;
                updateSuccess="Update Profile Successfully";
            } else {
                request.setAttribute("ERROR", "Update failed");
            }
        } else {
            if (accountSession.getRole().equals("customer")) {
                url = CUSTOMERINVALID;
            } else if (accountSession.getRole().equals("employee")) {
                url = EMPLOYEEINVALID;
            }
            request.setAttribute("INVALID", errorObj);
        }
        return url;
    }

    @Action(value = "/user/updateAccount",
            results = {
                @Result(name = "success", type = "redirectAction", location = "loadAccountByPrimaryKey", params = {"username", "${#session.USER.username}",
                "updateSuccess", "Update Profile Successfully"})
                ,
                @Result(name = "customerInvalid", location = "/user/userManageProfile.jsp")
                ,
                @Result(name = "customerError", location = "/user/userManageProfile.jsp")
            })
    public String updateAccountCustomer() throws Exception {
        String url = CUSTOMERERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        AccountDTO accountSession = (AccountDTO) session.get("USER");
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
        if (!currentPassword.equals(dao.getPasswordByUsername(accountSession.getUsername()))) {
            errorObj.setCurrentPasswordError("Password not match with your password. Please check again");
            valid = false;
        }
        if (!newPassword.isEmpty() && newPassword.length() < 6 || !newPassword.isEmpty() && newPassword.length() > 20) {
            errorObj.setNewPasswordError("Password must be 6-20 characters");
            valid = false;
        }
        if (!confirm.equals(newPassword)) {
            errorObj.setConfirmPasswordError("Confirm must match new password");
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
        if (!email.equals(accountSession.getEmail())&&dao.existedEmail(email)) {
            errorObj.setEmailError("Email is existed");
            valid = false;
        }
        if (!phone.equals(accountSession.getPhone())&&dao.existedPhone(phone)) {
            errorObj.setPhoneError("Phone is existed");
            valid = false;
        }
        if (!newPassword.isEmpty()) {
            currentPassword = newPassword;
        }
        account = new AccountDTO(accountSession.getUsername(), firstname, lastname, email, phone, gender, address, description);
        account.setPassword(currentPassword);
        if (valid) {
            if (dao.updateAccount(account)) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Update failed");
            }
        } else {
            url = CUSTOMERINVALID;
            request.setAttribute("INVALID", errorObj);
        }
        return url;
    }

    @Action(value = "/employee/updateAccount",
            results = {
                @Result(name = "success", type = "redirectAction", location = "loadAccountByPrimaryKey", params = {"username", "${#session.USER.username}",
                "updateSuccess", "Update Profile Successfully"})
                ,
                @Result(name = "employeeInvalid", location = "/employee/employeeManageProfile.jsp")
                ,
                @Result(name = "employeeError", location = "/employee/employeeManageProfile.jsp")
            })
    public String updateAccountEmployee() throws Exception {
        String url = EMPLOYEEERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        AccountDTO accountSession = (AccountDTO) session.get("USER");
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
        if (!currentPassword.equals(dao.getPasswordByUsername(accountSession.getUsername()))) {
            errorObj.setCurrentPasswordError("Password not match with your password. Please check again");
            valid = false;
        }
        if (!newPassword.isEmpty() && newPassword.length() < 6 || !newPassword.isEmpty() && newPassword.length() > 20) {
            errorObj.setNewPasswordError("Password must be 6-20 characters");
            valid = false;
        }
        if (!confirm.equals(newPassword)) {
            errorObj.setConfirmPasswordError("Confirm must match new password");
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
        if (!email.equals(accountSession.getEmail())&&dao.existedEmail(email)) {
            errorObj.setEmailError("Email is existed");
            valid = false;
        }
        if (!phone.equals(accountSession.getPhone())&&dao.existedPhone(phone)) {
            errorObj.setPhoneError("Phone is existed");
            valid = false;
        }
        if (!newPassword.isEmpty()) {
            currentPassword = newPassword;
        }
        account = new AccountDTO(accountSession.getUsername(), firstname, lastname, email, phone, gender, address, description);
        account.setPassword(currentPassword);
        if (valid) {
            if (dao.updateAccount(account)) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Update failed");
            }
        } else {
            url = EMPLOYEEINVALID;
            request.setAttribute("INVALID", errorObj);
        }
        return url;
    }

}
