/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.BookDAO;
import dto.AccountDTO;
import dto.BookDTO;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class LoadBookByCategoryAction extends ActionSupport {
    private List<BookDTO> listBooks;
    private String cateID;
    private final String GUEST = "guestTag";
    private final String CUSTOMER = "customerTag";
    private final String EMPLOYEE = "employeeTag";
    private final String MANAGER = "managerTag";
    public LoadBookByCategoryAction() {
    }

    public List<BookDTO> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<BookDTO> listBooks) {
        this.listBooks = listBooks;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }
    
    public String execute() throws Exception {
        String url=GUEST;
        BookDAO dao=new BookDAO();
        listBooks=dao.getBooksByCategory(cateID);
        Map session = ActionContext.getContext().getSession();
        AccountDTO account = (AccountDTO) session.get("USER");
        if (account != null) {
            if (account.getRole().equals("customer")) {
                url = CUSTOMER;
            } else if (account.getRole().equals("employee")) {
                url = EMPLOYEE;
            } else if (account.getRole().equals("manager")) {
                url = MANAGER;
            }
        }
        return url;
    }
    @Action(value = "/guest/listBookByCategory",
            results = {
                @Result(name = "guestTag", location = "/guest/guestHomePage.jsp")
            })
    public String loadBookByCategoryGuest() throws Exception{
        BookDAO dao=new BookDAO();
        listBooks=dao.getBooksByCategory(cateID);
        return GUEST;
    }
    @Action(value = "/user/listBookByCategory",
            results = {
                @Result(name = "customerTag", location = "/user/userHomePage.jsp")
            })
    public String loadBookByCategoryCustomer() throws Exception{
        BookDAO dao=new BookDAO();
        listBooks=dao.getBooksByCategory(cateID);
        return CUSTOMER;
    }
    @Action(value = "/employee/listBookByCategory",
            results = {
                @Result(name = "employeeTag", location = "/employee/employeeHomePage.jsp")
            })
    public String loadBookByCategoryEmployee() throws Exception{
        BookDAO dao=new BookDAO();
        listBooks=dao.getBooksByCategory(cateID);
        return EMPLOYEE;
    }
    @Action(value = "/manager/listBookByCategory",
            results = {
                @Result(name = "managerTag", location = "/manager/managerHomePage.jsp")
            })
    public String loadBookByCategoryManager() throws Exception{
        BookDAO dao=new BookDAO();
        listBooks=dao.getBooksByCategory(cateID);
        return MANAGER;
    }
}
