/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.BookDAO;
import dto.BookDTO;
import dto.CartObj;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class AddToCartAction extends ActionSupport {

    private String bookID;
    private String search;
    private String quantity;
    private String fromBookDetail;
    private final String SEARCHBOOK = "searchBookPage";
    private final String BOOKDETAIL = "bookDetailPage";

    public AddToCartAction() {
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFromBookDetail() {
        return fromBookDetail;
    }

    public void setFromBookDetail(String fromBookDetail) {
        this.fromBookDetail = fromBookDetail;
    }

    @Override
    public String execute() throws Exception {
        String url = SEARCHBOOK;
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("shoppingCart");
        if (!session.containsKey("shoppingCart")) {
            cart = new CartObj();
        }
        BookDAO dao = new BookDAO();
        BookDTO book = dao.getBookByID(bookID);
        if (quantity != null && !quantity.isEmpty()) {
            int qtt = Integer.parseInt(quantity);
            book.setQuantity(qtt);
        } else {
            book.setQuantity(1);
        }
        if (fromBookDetail != null && !fromBookDetail.isEmpty()) {
            url = BOOKDETAIL;
        }
        cart.addToCart(book);
        session.put("shoppingCart", cart);
        return url;
    }

    @Action(value = "/guest/addToCart",
            results = {
                @Result(name = "searchBookPage", type = "redirectAction", location = "searchBook", params = {"search", "${search}"})
                ,
                @Result(name = "bookDetailPage", type = "redirectAction", location = "bookByID", params = {"bookID", "${bookID}"})
            })
    public String addTOCartGuest() throws Exception {
        String url = SEARCHBOOK;
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("shoppingCart");
        if (!session.containsKey("shoppingCart")) {
            cart = new CartObj();
        }
        BookDAO dao = new BookDAO();
        BookDTO book = dao.getBookByID(bookID);
        if (quantity != null && !quantity.isEmpty()) {
            int qtt = Integer.parseInt(quantity);
            book.setQuantity(qtt);
        } else {
            book.setQuantity(1);
        }
        if (fromBookDetail != null && !fromBookDetail.isEmpty()) {
            url = BOOKDETAIL;
        }
        cart.addToCart(book);
        session.put("shoppingCart", cart);
        return url;
    }

    @Action(value = "/user/addToCart",
            results = {
                @Result(name = "searchBookPage", type = "redirectAction", location = "searchBook", params = {"search", "${search}"})
                ,
                @Result(name = "bookDetailPage", type = "redirectAction", location = "bookByID", params = {"bookID", "${bookID}"})
            })
    public String addTOCartCustomer() throws Exception {
        String url = SEARCHBOOK;
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("shoppingCart");
        if (!session.containsKey("shoppingCart")) {
            cart = new CartObj();
        }
        BookDAO dao = new BookDAO();
        BookDTO book = dao.getBookByID(bookID);
        if (quantity != null && !quantity.isEmpty()) {
            int qtt = Integer.parseInt(quantity);
            book.setQuantity(qtt);
        } else {
            book.setQuantity(1);
        }
        if (fromBookDetail != null && !fromBookDetail.isEmpty()) {
            url = BOOKDETAIL;
        }
        cart.addToCart(book);
        session.put("shoppingCart", cart);
        return url;
    }

}
