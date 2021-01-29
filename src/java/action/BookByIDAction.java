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
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class BookByIDAction extends ActionSupport {

    private String bookID;
    private BookDTO book;
    private CategoryDTO category;
    private String[] listImg;
    private final String GUEST = "guestTag";
    private final String CUSTOMER = "customerTag";
    private final String EMPLOYEE = "employeeTag";
    private final String MANAGER = "managerTag";

    public BookByIDAction() {
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String[] getListImg() {
        return listImg;
    }

    public void setListImg(String[] listImg) {
        this.listImg = listImg;
    }

    public String execute() throws Exception {
        String url = GUEST;
        BookDAO dao = new BookDAO();
        book = dao.getBookByID(bookID);
        String mainImg = book.getMainImg();
        if (mainImg != null) {
            listImg = mainImg.split(",");
        }
        CategoryDAO cateDAO = new CategoryDAO();
        category = cateDAO.getCategoryByID(book.getCategoryID());
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

    @Action(value = "/guest/bookByID",
            results = {
                @Result(name = "guestTag", location = "/guest/guestBookDetail.jsp")
            })
    public String getBookByIDGuest() throws Exception {
        BookDAO dao = new BookDAO();
        book = dao.getBookByID(bookID);
        String mainImg = book.getMainImg();
        if (mainImg != null) {
            listImg = mainImg.split(",");
        }
        CategoryDAO cateDAO = new CategoryDAO();
        category = cateDAO.getCategoryByID(book.getCategoryID());
        return GUEST;
    }

    @Action(value = "/user/bookByID",
            results = {
                @Result(name = "customerTag", location = "/user/userBookDetail.jsp")
            })
    public String getBookByIDCustomer() throws Exception {
        BookDAO dao = new BookDAO();
        book = dao.getBookByID(bookID);
        String mainImg = book.getMainImg();
        if (mainImg != null) {
            listImg = mainImg.split(",");
        }
        CategoryDAO cateDAO = new CategoryDAO();
        category = cateDAO.getCategoryByID(book.getCategoryID());
        return CUSTOMER;
    }

    @Action(value = "/employee/bookByID",
            results = {
                @Result(name = "employeeTag", location = "/employee/employeeBookFormUpdate.jsp")
            })
    public String getBookByIDEmployee() throws Exception {
        BookDAO dao = new BookDAO();
        book = dao.getBookByID(bookID);
        String mainImg = book.getMainImg();
        if (mainImg != null) {
            listImg = mainImg.split(",");
        }
        CategoryDAO cateDAO = new CategoryDAO();
        category = cateDAO.getCategoryByID(book.getCategoryID());
        return EMPLOYEE;
    }

    @Action(value = "/manager/bookByID",
            results = {
                @Result(name = "managerTag", location = "/manager/managerBookFormUpdate.jsp")
            })
    public String getBookByIDManager() throws Exception {
        BookDAO dao = new BookDAO();
        book = dao.getBookByID(bookID);
        String mainImg = book.getMainImg();
        if (mainImg != null) {
            listImg = mainImg.split(",");
        }
        CategoryDAO cateDAO = new CategoryDAO();
        category = cateDAO.getCategoryByID(book.getCategoryID());
        return MANAGER;
    }

}
