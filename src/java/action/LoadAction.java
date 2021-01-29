/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.BookDAO;
import dao.CategoryDAO;
import dto.AccountDTO;
import dto.BookDTO;
import dto.CategoryDTO;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author mevrthisbang
 */
public class LoadAction extends ActionSupport {

    private static final String GUEST = "guest";
    private static final String CUSTOMER = "customerTag";
    private static final String EMPLOYEE = "employeeTag";
    private static final String MANAGER = "managerTag";
    private List<BookDTO> listBooks;
    private List<CategoryDTO> listCategories;

    public LoadAction() {
    }

    public List<BookDTO> getListBooks() {
        return listBooks;
    }

    public List<CategoryDTO> getListCategories() {
        return listCategories;
    }

    @Override
    public String execute() throws Exception {
        String url = GUEST;
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
        BookDAO bookDAO = new BookDAO();
        listBooks = bookDAO.getAllBooks();
        CategoryDAO cateDAO = new CategoryDAO();
        listCategories = cateDAO.getAllCategories();
        Map context = ServletActionContext.getContext().getApplication();
        context.put("listCategories", listCategories);
        return url;
    }

}
