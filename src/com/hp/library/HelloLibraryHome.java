package com.hp.library;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.hp.bookdb.Book;
import com.hp.bookdb.BookDatabase;
import com.opensymphony.xwork2.ActionSupport;

public class HelloLibraryHome extends ActionSupport{
	private String authorName;
	private String title;
	private String ISBN;
	private boolean deleteOpt = false;
	
	public boolean isDeleteOpt() {
		return deleteOpt;
	}

	public void setDeleteOpt(boolean deleteOpt) {
		this.deleteOpt = deleteOpt;
	}

	private BookDatabase bd = new BookDatabase();
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public static String getNewString(String input){
		String result = "";
		try {
			result = new String(input.getBytes("iso-8859-1"),"utf-8");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String execute() throws Exception {
		bd.searchAuthors(this.authorName);
		if (bd.getBooks().size() == 0) {
			return "FAIL";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("books", bd);
		return "SUCCESS";
	}
	
	public String bookDetail () {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		BookDatabase bookDetail = (BookDatabase)session.getAttribute("bddel");
		bookDetail.bookDetails(this.title, this.ISBN);
		session.setAttribute("book_author", bookDetail);
		return "SUCCESS";
	}
	
	public String deleteBook () {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		BookDatabase bdDel = (BookDatabase)session.getAttribute("bddel");
		bdDel.bookDelete(this.title, this.ISBN);
		session.setAttribute("books", bdDel);
		return "SUCCESS";
	}
}
