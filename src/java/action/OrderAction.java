/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dto.AccountDTO;
import dto.CartObj;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class OrderAction extends ActionSupport {

    public OrderAction() {
    }

    @Action(value = "/guest/order",
            results = {
                @Result(name = "success", location = "/login.jsp", type = "redirect", params = {"fromGuest", "Yes"})
            })
    public String execute() throws Exception {
        return "success";
    }

    @Action(value = "/order",
            results = {
                @Result(name = "success", location = "/user/userOrderForm.jsp")
                ,
                @Result(name = "error", location = "/user/userCart.jsp")

            }
    )
    public String order() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("shoppingCart");
        HttpServletRequest request = ServletActionContext.getRequest();
        if (cart == null || cart.getCart().isEmpty()) {
            request.setAttribute("ERROR", "Please add book to cart before order");
            return "error";
        }
        AccountDTO account = (AccountDTO) session.get("USER");
        account.setLastname(account.getLastname()+" "+account.getFirstname());
        request.setAttribute("ORDER", account);
        return "success";
    }

    @Action(value = "/user/order",
            results = {
                @Result(name = "success", location = "/user/userOrderForm.jsp", params = {"error", "Nothing's in cart. Please add book to cart first"})
                ,
                @Result(name = "error", location = "/user/userCart.jsp")

            }
    )
    public String orderAfterLogin() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("shoppingCart");
        HttpServletRequest request = ServletActionContext.getRequest();
        if (cart == null || cart.getCart().isEmpty()) {
            request.setAttribute("ERROR", "Please add book to cart before order");
            return "error";
        }
        AccountDTO account = (AccountDTO) session.get("USER");
        account.setLastname(account.getLastname()+" "+account.getFirstname());
        request.setAttribute("ORDER", account);
        return "success";
    }

}
