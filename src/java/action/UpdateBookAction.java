/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.BookDAO;
import dao.FileDAO;
import dto.AccountDTO;
import dto.BookDTO;
import dto.BookErrorObject;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author mevrthisbang
 */
public class UpdateBookAction extends ActionSupport {

    private String title, author, issuers, publishingCompany, categoryID, size, coverType, shortDescription, description, price, numOfPage, releasedDate, thumbnailHidden, mainHidden, search ;
    private boolean chkStillProducing;
    private File thumbnailImg;
    private File[] mainImg;
    private String bookID;
    private BookDTO book;
    private String[] listImg;
    private final String SUCCESS = "success";
    private final String MANAGERERROR = "managerError";
    private final String MANAGERINVALID = "managerInvalid";
    private final String EMPLOYEEERROR = "employeeError";
    private final String EMPLOYEEINVALID = "employeeInvalid";

    public UpdateBookAction() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIssuers() {
        return issuers;
    }

    public void setIssuers(String issuers) {
        this.issuers = issuers;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumOfPage() {
        return numOfPage;
    }

    public void setNumOfPage(String numOfPage) {
        this.numOfPage = numOfPage;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public File getThumbnailImg() {
        return thumbnailImg;
    }

    public void setThumbnailImg(File thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }

    public File[] getMainImg() {
        return mainImg;
    }

    public void setMainImg(File[] mainImg) {
        this.mainImg = mainImg;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public String getThumbnailHidden() {
        return thumbnailHidden;
    }

    public void setThumbnailHidden(String thumbnailHidden) {
        this.thumbnailHidden = thumbnailHidden;
    }

    public String getMainHidden() {
        return mainHidden;
    }

    public void setMainHidden(String mainHidden) {
        this.mainHidden = mainHidden;
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

    public boolean isChkStillProducing() {
        return chkStillProducing;
    }

    public void setChkStillProducing(boolean chkStillProducing) {
        this.chkStillProducing = chkStillProducing;
    }

    private boolean validDate(int day, int month, int year) {
        int maxd = 31;
        if (day < 1 || day > 31 || month < 1 || month > 12) {
            return false;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxd = 30;
        } else if (month == 2) {
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                maxd = 29;
            } else {
                maxd = 28;
            }
        }
        return day <= maxd;
    }

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        AccountDTO accountSession = (AccountDTO) session.get("USER");
        String url = "";
        if (accountSession.getRole().equals("manager")) {
            url = MANAGERERROR;
        } else if (accountSession.getRole().equals("employee")) {
            url = EMPLOYEEERROR;
        }
        boolean valid = true;
        BookErrorObject errorObj = new BookErrorObject();
        if (title.isEmpty()) {
            errorObj.setTitleError("Title is not supposed to be empty");
            valid = false;
        }
        if (author.isEmpty()) {
            errorObj.setAuthorError("Author is not supposed to be empty");
            valid = false;
        }
        if (issuers.isEmpty()) {
            errorObj.setIssuersError("Issuers is not supposed to be empty");
            valid = false;
        }
        if (publishingCompany.isEmpty()) {
            errorObj.setPublishingCompanyError("Publishing Company is not supposed to be empty");
            valid = false;
        }
        if (size.isEmpty()) {
            errorObj.setSizeError("Size is not supposed to be empty");
            valid = false;
        }
        if (coverType.isEmpty()) {
            errorObj.setCoverTypeError("Cover Type is not supposed to be empty");
            valid = false;
        }
        if (description.isEmpty()) {
            errorObj.setDescriptionError("Description is not supposed to be empty");
            valid = false;
        }
        if (shortDescription.isEmpty()) {
            errorObj.setShortDescriptionError("Short Description is not supposed to be empty");
            valid = false;
        }
        int numberOfPage = 0;
        try {
            numberOfPage = Integer.parseInt(numOfPage);
            if (numberOfPage <= 0) {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            errorObj.setNumOfPageError("Please input integer number");
            valid = false;
        } catch (Exception e) {
            errorObj.setNumOfPageError("Number of page must >0");
            valid = false;
        }
        double priceDB = 0;
        try {
            priceDB = Double.parseDouble(price);
            if (priceDB <= 0) {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            errorObj.setPriceError("Please input number");
            valid = false;
        } catch (Exception e) {
            errorObj.setPriceError("Price must >0");
            valid = false;
        }
        if (!releasedDate.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            errorObj.setReleasedDate("Input date must format like dd/MM/YYYY");
            valid = false;
        }
        String[] dateComponents = releasedDate.split("/");
        int day = 0, month = 0, year = 0;
        try {
            day = Integer.parseInt(dateComponents[0]);
            month = Integer.parseInt(dateComponents[1]);
            year = Integer.parseInt(dateComponents[2]);
        } catch (NumberFormatException e) {
            errorObj.setReleasedDate("Please input number for day, month and year");
            valid = false;
        }
        if (!validDate(day, month, year)) {
            errorObj.setReleasedDate("Invalid date, please check again");
            valid = false;
        }
        
        HttpServletRequest request = ServletActionContext.getRequest();
        String thumbnailDB = "";
        if (thumbnailImg != null) {
            String filePath = request.getSession().getServletContext().getRealPath("/") + "themes\\images\\books\\" + bookID.split("-")[0] + bookID.split("-")[1] + ".jpg";
            byte[] imgByte = FileDAO.readImg(thumbnailImg.getAbsolutePath());
            FileDAO.writeImg(filePath, imgByte);
            thumbnailDB = "themes/images/books/" + bookID.split("-")[0] + bookID.split("-")[1] + ".jpg";
        }

        String mainImgDB = "";
        if (mainImg != null && mainImg.length > 0) {
            for (int i = 0; i < mainImg.length; i++) {
                String filePathForMain = request.getSession().getServletContext().getRealPath("/") + "themes\\images\\books\\" + bookID.split("-")[0] + bookID.split("-")[1] + "_" + (i + 1) + ".jpg";
                byte[] imgByteForMain = FileDAO.readImg(mainImg[i].getAbsolutePath());
                FileDAO.writeImg(filePathForMain, imgByteForMain);
                mainImgDB += "themes/images/books/" + bookID.split("-")[0] + bookID.split("-")[1] + "_" + (i + 1) + ".jpg,";
            }
        }
        if (thumbnailImg == null) {
            mainImgDB = mainHidden;
        }
        if (mainImg == null || mainImg.length <= 0) {
            thumbnailDB = thumbnailHidden;
        }
        listImg=mainImgDB.split(",");
        book = new BookDTO(bookID, title, author, description, mainImgDB, categoryID, priceDB);
        book.setCoverType(coverType);
        book.setIssuers(issuers);
        book.setNumOfPage(numberOfPage);
        book.setPublishingCompany(publishingCompany);
        if (!releasedDate.isEmpty()) {
            Date releasedDateDB = new SimpleDateFormat("dd/MM/yyyy").parse(releasedDate);
            book.setReleasedDate(releasedDateDB);
        }
        book.setThumbnailImg(thumbnailDB);
        book.setShortDescription(shortDescription);
        book.setSize(size);
        book.setStillProducing(chkStillProducing);
        if (valid) {
            BookDAO dao = new BookDAO();
            if (dao.updateBook(book)) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Insert failed");
            }
        } else {
            if (accountSession.getRole().equals("manager")) {
                url = MANAGERINVALID;
            } else if (accountSession.getRole().equals("employee")) {
                url = EMPLOYEEINVALID;
            }
            request.setAttribute("INVALID", errorObj);
        }
        return url;
    }
    
    @Action(value = "/employee/updateBook",
            results = {
                @Result(name = "success", type = "redirectAction", location = "searchBook", params = {"search", "${search}"})
                ,
                @Result(name = "employeeError", location = "/employee/employeeBookFormUpdate.jsp")
                ,
                @Result(name = "employeeInvalid", location = "/employee/employeeBookFormUpdate.jsp")
            })
    public String updateBookEmployee() throws Exception {
        String url = EMPLOYEEERROR;
        boolean valid = true;
        BookErrorObject errorObj = new BookErrorObject();
        if (title.isEmpty()) {
            errorObj.setTitleError("Title is not supposed to be empty");
            valid = false;
        }
        if (author.isEmpty()) {
            errorObj.setAuthorError("Author is not supposed to be empty");
            valid = false;
        }
        if (issuers.isEmpty()) {
            errorObj.setIssuersError("Issuers is not supposed to be empty");
            valid = false;
        }
        if (publishingCompany.isEmpty()) {
            errorObj.setPublishingCompanyError("Publishing Company is not supposed to be empty");
            valid = false;
        }
        if (size.isEmpty()) {
            errorObj.setSizeError("Size is not supposed to be empty");
            valid = false;
        }
        if (coverType.isEmpty()) {
            errorObj.setCoverTypeError("Cover Type is not supposed to be empty");
            valid = false;
        }
        if (description.isEmpty()) {
            errorObj.setDescriptionError("Description is not supposed to be empty");
            valid = false;
        }
        if (shortDescription.isEmpty()) {
            errorObj.setShortDescriptionError("Short Description is not supposed to be empty");
            valid = false;
        }
        int numberOfPage = 0;
        try {
            numberOfPage = Integer.parseInt(numOfPage);
            if (numberOfPage <= 0) {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            errorObj.setNumOfPageError("Please input integer number");
            valid = false;
        } catch (Exception e) {
            errorObj.setNumOfPageError("Number of page must >0");
            valid = false;
        }
        double priceDB = 0;
        try {
            priceDB = Double.parseDouble(price);
            if (priceDB <= 0) {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            errorObj.setPriceError("Please input number");
            valid = false;
        } catch (Exception e) {
            errorObj.setPriceError("Price must >0");
            valid = false;
        }
        if (!releasedDate.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            errorObj.setReleasedDate("Input date must format like dd/MM/YYYY");
            valid = false;
        }
        String[] dateComponents = releasedDate.split("/");
        int day = 0, month = 0, year = 0;
        try {
            day = Integer.parseInt(dateComponents[0]);
            month = Integer.parseInt(dateComponents[1]);
            year = Integer.parseInt(dateComponents[2]);
        } catch (NumberFormatException e) {
            errorObj.setReleasedDate("Please input number for day, month and year");
            valid = false;
        }
        if (!validDate(day, month, year)) {
            errorObj.setReleasedDate("Invalid date, please check again");
            valid = false;
        }
        
        HttpServletRequest request = ServletActionContext.getRequest();
        String thumbnailDB = "";
        if (thumbnailImg != null) {
            String filePath = request.getSession().getServletContext().getRealPath("/") + "themes\\images\\books\\" + bookID.split("-")[0] + bookID.split("-")[1] + ".jpg";
            byte[] imgByte = FileDAO.readImg(thumbnailImg.getAbsolutePath());
            FileDAO.writeImg(filePath, imgByte);
            thumbnailDB = "themes/images/books/" + bookID.split("-")[0] + bookID.split("-")[1] + ".jpg";
        }

        String mainImgDB = "";
        if (mainImg != null && mainImg.length > 0) {
            for (int i = 0; i < mainImg.length; i++) {
                String filePathForMain = request.getSession().getServletContext().getRealPath("/") + "themes\\images\\books\\" + bookID.split("-")[0] + bookID.split("-")[1] + "_" + (i + 1) + ".jpg";
                byte[] imgByteForMain = FileDAO.readImg(mainImg[i].getAbsolutePath());
                FileDAO.writeImg(filePathForMain, imgByteForMain);
                mainImgDB += "themes/images/books/" + bookID.split("-")[0] + bookID.split("-")[1] + "_" + (i + 1) + ".jpg,";
            }
        }
        if (thumbnailImg == null) {
            mainImgDB = mainHidden;
        }
        if (mainImg == null || mainImg.length <= 0) {
            thumbnailDB = thumbnailHidden;
        }
        listImg=mainImgDB.split(",");
        book = new BookDTO(bookID, title, author, description, mainImgDB, categoryID, priceDB);
        book.setCoverType(coverType);
        book.setIssuers(issuers);
        book.setNumOfPage(numberOfPage);
        book.setPublishingCompany(publishingCompany);
        if (!releasedDate.isEmpty()) {
            Date releasedDateDB = new SimpleDateFormat("dd/MM/yyyy").parse(releasedDate);
            book.setReleasedDate(releasedDateDB);
        }
        book.setThumbnailImg(thumbnailDB);
        book.setShortDescription(shortDescription);
        book.setSize(size);
        book.setStillProducing(chkStillProducing);
        if (valid) {
            BookDAO dao = new BookDAO();
            if (dao.updateBook(book)) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Insert failed");
            }
        } else {
            url = EMPLOYEEINVALID;
            request.setAttribute("INVALID", errorObj);
        }
        return url;
    }

    @Action(value = "/manager/updateBook",
            results = {
                @Result(name = "success", type = "redirectAction", location = "searchBook", params = {"search", "${search}"})
                ,
                @Result(name = "managerError", location = "/manager/managerBookFormUpdate.jsp")
                ,
                @Result(name = "managerInvalid", location = "/manager/managerBookFormUpdate.jsp")
            })
    public String createBookManager() throws Exception {
         String url = MANAGERERROR;
        boolean valid = true;
        BookErrorObject errorObj = new BookErrorObject();
        if (title.isEmpty()) {
            errorObj.setTitleError("Title is not supposed to be empty");
            valid = false;
        }
        if (author.isEmpty()) {
            errorObj.setAuthorError("Author is not supposed to be empty");
            valid = false;
        }
        if (issuers.isEmpty()) {
            errorObj.setIssuersError("Issuers is not supposed to be empty");
            valid = false;
        }
        if (publishingCompany.isEmpty()) {
            errorObj.setPublishingCompanyError("Publishing Company is not supposed to be empty");
            valid = false;
        }
        if (size.isEmpty()) {
            errorObj.setSizeError("Size is not supposed to be empty");
            valid = false;
        }
        if (coverType.isEmpty()) {
            errorObj.setCoverTypeError("Cover Type is not supposed to be empty");
            valid = false;
        }
        if (description.isEmpty()) {
            errorObj.setDescriptionError("Description is not supposed to be empty");
            valid = false;
        }
        if (shortDescription.isEmpty()) {
            errorObj.setShortDescriptionError("Short Description is not supposed to be empty");
            valid = false;
        }
        int numberOfPage = 0;
        try {
            numberOfPage = Integer.parseInt(numOfPage);
            if (numberOfPage <= 0) {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            errorObj.setNumOfPageError("Please input integer number");
            valid = false;
        } catch (Exception e) {
            errorObj.setNumOfPageError("Number of page must >0");
            valid = false;
        }
        double priceDB = 0;
        try {
            priceDB = Double.parseDouble(price);
            if (priceDB <= 0) {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            errorObj.setPriceError("Please input number");
            valid = false;
        } catch (Exception e) {
            errorObj.setPriceError("Price must >0");
            valid = false;
        }
        if (!releasedDate.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            errorObj.setReleasedDate("Input date must format like dd/MM/YYYY");
            valid = false;
        }
        String[] dateComponents = releasedDate.split("/");
        int day = 0, month = 0, year = 0;
        try {
            day = Integer.parseInt(dateComponents[0]);
            month = Integer.parseInt(dateComponents[1]);
            year = Integer.parseInt(dateComponents[2]);
        } catch (NumberFormatException e) {
            errorObj.setReleasedDate("Please input number for day, month and year");
            valid = false;
        }
        if (!validDate(day, month, year)) {
            errorObj.setReleasedDate("Invalid date, please check again");
            valid = false;
        }
        
        HttpServletRequest request = ServletActionContext.getRequest();
        String thumbnailDB = "";
        if (thumbnailImg != null) {
            String filePath = request.getSession().getServletContext().getRealPath("/") + "themes\\images\\books\\" + bookID.split("-")[0] + bookID.split("-")[1] + ".jpg";
            byte[] imgByte = FileDAO.readImg(thumbnailImg.getAbsolutePath());
            FileDAO.writeImg(filePath, imgByte);
            thumbnailDB = "themes/images/books/" + bookID.split("-")[0] + bookID.split("-")[1] + ".jpg";
        }

        String mainImgDB = "";
        if (mainImg != null && mainImg.length > 0) {
            for (int i = 0; i < mainImg.length; i++) {
                String filePathForMain = request.getSession().getServletContext().getRealPath("/") + "themes\\images\\books\\" + bookID.split("-")[0] + bookID.split("-")[1] + "_" + (i + 1) + ".jpg";
                byte[] imgByteForMain = FileDAO.readImg(mainImg[i].getAbsolutePath());
                FileDAO.writeImg(filePathForMain, imgByteForMain);
                mainImgDB += "themes/images/books/" + bookID.split("-")[0] + bookID.split("-")[1] + "_" + (i + 1) + ".jpg,";
            }
        }
        if (thumbnailImg == null) {
            mainImgDB = mainHidden;
        }
        if (mainImg == null || mainImg.length <= 0) {
            thumbnailDB = thumbnailHidden;
        }
        listImg=mainImgDB.split(",");
        book = new BookDTO(bookID, title, author, description, mainImgDB, categoryID, priceDB);
        book.setCoverType(coverType);
        book.setIssuers(issuers);
        book.setNumOfPage(numberOfPage);
        book.setPublishingCompany(publishingCompany);
        if (!releasedDate.isEmpty()) {
            Date releasedDateDB = new SimpleDateFormat("dd/MM/yyyy").parse(releasedDate);
            book.setReleasedDate(releasedDateDB);
        }
        book.setThumbnailImg(thumbnailDB);
        book.setShortDescription(shortDescription);
        book.setSize(size);
        book.setStillProducing(chkStillProducing);
        if (valid) {
            BookDAO dao = new BookDAO();
            if (dao.insertBook(book)) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Insert failed");
            }
        } else {
            url = MANAGERINVALID;
            request.setAttribute("INVALID", errorObj);
        }
        return url;
    }

}
