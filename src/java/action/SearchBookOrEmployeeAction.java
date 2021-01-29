/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.AccountDAO;
import dao.BookDAO;
import dto.AccountDTO;
import dto.BookDTO;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class SearchBookOrEmployeeAction extends ActionSupport {
    private String search, cboType;
    private final String SEARCHBOOK="searchBook";
    private final String SEARCHEMPLOYEE="searchEmployee";
    private List<BookDTO> listBooks;
    private List<AccountDTO> listEmployees;
    public SearchBookOrEmployeeAction() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCboType() {
        return cboType;
    }

    public void setCboType(String cboType) {
        this.cboType = cboType;
    }

    public List<BookDTO> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<BookDTO> listBooks) {
        this.listBooks = listBooks;
    }

    public List<AccountDTO> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(List<AccountDTO> listEmployees) {
        this.listEmployees = listEmployees;
    }
    public String execute() throws Exception {
        String url="";
        if(cboType.equals("book")){
            BookDAO dao=new BookDAO();
            listBooks=dao.getBooksByLikeName(search);
            url=SEARCHBOOK;
        }else if(cboType.equals("employee")){
            AccountDAO dao=new AccountDAO();
            listEmployees=dao.getEmployeesByLikeName(search);
            url=SEARCHEMPLOYEE;
        }
        return url;
    }
    @Action(value = "/manager/searchBookOrEmployee",
            results = {
                @Result(name = "searchBook", location = "/manager/managerHomePage.jsp"),
                @Result(name = "searchEmployee", location = "/manager/managerListEmployee.jsp")
            })
    public String searchBookOrEmployee() throws Exception {
        String url="";
        if(cboType.equals("book")){
            BookDAO dao=new BookDAO();
            listBooks=dao.getBooksByLikeName(search);
            url=SEARCHBOOK;
        }else if(cboType.equals("employee")){
            AccountDAO dao=new AccountDAO();
            listEmployees=dao.getEmployeesByLikeName(search);
            url=SEARCHEMPLOYEE;
        }
        return url;
    }
    
}
