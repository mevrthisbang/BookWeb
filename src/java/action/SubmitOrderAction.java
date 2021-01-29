/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.OrderDAO;
import dto.AccountDTO;
import dto.CartObj;
import dto.OrderDTO;
import dto.OrderErrorObject;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class SubmitOrderAction extends ActionSupport {

    private String fullname;
    private String phone;
    private String address;
    private String paymentMethod;
    private final String ERROR = "error";
    private final String SUCCESS = "success";

    public SubmitOrderAction() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Action(value = "/user/submitOrder",
            results = {
                @Result(name = "success", location = "/user/userCart.jsp")
                ,
                @Result(name = "error", location = "/user/userOrderForm.jsp")
            }
    )
    public String execute() throws Exception {
        String url = ERROR;
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("shoppingCart");
        AccountDTO account = (AccountDTO) session.get("USER");
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = account.getUsername();
        OrderDAO dao = new OrderDAO();
        String orderID = dao.getLastOrderByUser(username);
        if (orderID == null) {
            orderID = "OD-" + username + "-1";
        } else {
            String[] tmp = orderID.split("-");
            int count = Integer.parseInt(tmp[3].trim());
            orderID = "OD-" + username + "-" + (count + 1);
        }
        double total = cart.getTotal();
        boolean valid = true;
        OrderErrorObject errorObj = new OrderErrorObject();
        if (fullname.trim().isEmpty()) {
            errorObj.setFullnameError("Fullname is not supposed to be empty");
            valid = false;
        }
        if (fullname.trim().length() < 12 || fullname.trim().length() > 50) {
            errorObj.setFullnameError("Fullname must 12-50 characters");
            valid = false;
        }
        if (address.trim().isEmpty()) {
            errorObj.setAddressError("Address is not supposed to be empty");
            valid = false;
        }
        if (!phone.trim().matches("[0-9]{10}")) {
            errorObj.setPhoneError("Phone's length is 10 numbers(from 0-9)");
            valid = false;
        }
        OrderDTO order = new OrderDTO(orderID, username, paymentMethod, phone, fullname, address, total);
        if (valid) {
            if (dao.insertNewOrder(order, cart.getCart())) {
                url = SUCCESS;
                session.remove("shoppingCart");
                request.setAttribute("SUCCESS", "Order successfully!!! Please wait for shipping");
            }
        } else {
            account.setLastname(fullname);
            account.setAddress(address);
            account.setPhone(phone);
            request.setAttribute("INVALID", errorObj);
            request.setAttribute("ORDER", account);
        }
        return url;
    }
    @Action(value = "/submitOrder",
            results = {
                @Result(name = "success", location = "/user/userCart.jsp")
                ,
                @Result(name = "error", location = "/user/userOrderForm.jsp")
            }
    )
    public String submitOrder() throws Exception {
        String url = ERROR;
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("shoppingCart");
        AccountDTO account = (AccountDTO) session.get("USER");
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = account.getUsername();
        OrderDAO dao = new OrderDAO();
        String orderID = dao.getLastOrderByUser(username);
        if (orderID == null) {
            orderID = "OD-" + username + "-1";
        } else {
            String[] tmp = orderID.split("-");
            int count = Integer.parseInt(tmp[3].trim());
            orderID = "OD-" + username + "-" + (count + 1);
        }
        double total = cart.getTotal();
        boolean valid = true;
        OrderErrorObject errorObj = new OrderErrorObject();
        if (fullname.trim().isEmpty()) {
            errorObj.setFullnameError("Fullname is not supposed to be empty");
            valid = false;
        }
        if (fullname.trim().length() < 12 || fullname.trim().length() > 50) {
            errorObj.setFullnameError("Fullname must 12-50 characters");
            valid = false;
        }
        if (address.trim().isEmpty()) {
            errorObj.setAddressError("Address is not supposed to be empty");
            valid = false;
        }
        if (!phone.trim().matches("[0-9]{10}")) {
            errorObj.setPhoneError("Phone's length is 10 numbers(from 0-9)");
            valid = false;
        }
        OrderDTO order = new OrderDTO(orderID, username, paymentMethod, phone, fullname, address, total);
        if (valid) {
            if (dao.insertNewOrder(order, cart.getCart())) {
                url = SUCCESS;
                session.remove("shoppingCart");
                request.setAttribute("SUCCESS", "Order successfully!!! Please wait for shipping");
            }
        } else {
            account.setLastname(fullname);
            account.setAddress(address);
            account.setPhone(phone);
            request.setAttribute("INVALID", errorObj);
            request.setAttribute("ORDER", account);
        }
        return url;
    }

}
