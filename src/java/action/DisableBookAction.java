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
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class DisableBookAction extends ActionSupport {
    private String bookID, search;
    private String disableError;
    private final String SUCCESS = "success";
    private final String MANAGERERROR = "managerError";
    private final String EMPLOYEEERROR = "employeeError";
    public DisableBookAction() {
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getDisableError() {
        return disableError;
    }

    public void setDisableError(String disableError) {
        this.disableError = disableError;
    }

   
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        AccountDTO accountSession = (AccountDTO) session.get("USER");
        String url = "";
        if (accountSession.getRole().equals("manager")) {
            url = MANAGERERROR;
        } else if (accountSession.getRole().equals("employee")) {
            url = EMPLOYEEERROR;
        }
        BookDAO dao=new BookDAO();
        if(dao.disableBook(bookID)){
            url=SUCCESS;
        }else{
            disableError= "Product is still producting. Can't disable";
        }
        return url;
    }
    @Action(value = "/employee/disableBook",
            results = {
                @Result(name = "success", type = "redirectAction", location = "searchBook", params = {"search", "${search}"})
                ,
                @Result(name = "employeeError", type = "redirectAction",location = "bookByID", params={"bookID", "%{bookID}",
                "disableError", "Product is still producting. Can't disable"})
            })
    public String disableBookEmployee() throws Exception{
        String url = EMPLOYEEERROR;
        BookDAO dao=new BookDAO();
        if(dao.disableBook(bookID)){
            url=SUCCESS;
        }
        return url;
    }
    @Action(value = "/manager/disableBook",
            results = {
                @Result(name = "success", type = "redirectAction", location = "searchBook", params = {"search", "${search}"})
                ,
                @Result(name = "managerError", type = "redirectAction",location = "bookByID", params={"bookID", "%{bookID}",
                "disableError", "Product is still producting. Can't disable"}
                        )
            })
    public String disableBookManager() throws Exception{
        String url = MANAGERERROR;
        BookDAO dao=new BookDAO();
        if(dao.disableBook(bookID)){
            url=SUCCESS;
        }
        return url;
    }
}
