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
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class DeleteBookFromCartAction extends ActionSupport {

    private String bookID;

    public DeleteBookFromCartAction() {
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    @Action(value = "/guest/deleteBookFromCart",
            results = {
                @Result(name = "success", location = "/guest/guestCart.jsp", type = "redirect")
            })
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("shoppingCart");
        cart.removeFromCart(bookID);
        return "success";
    }
    @Action(value = "/user/deleteBookFromCart",
            results = {
                @Result(name = "success", location = "/user/userCart.jsp", type = "redirect")
            })
    public String delete() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("shoppingCart");
        cart.removeFromCart(bookID);
        return "success";
    }
    @Action(value = "deleteBookFromCart",
            results = {
                @Result(name = "success", location = "/user/userCart.jsp", type = "redirect")
            })
    public String deleteÀfterLogin() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("shoppingCart");
        cart.removeFromCart(bookID);
        return "success";
    }

}
