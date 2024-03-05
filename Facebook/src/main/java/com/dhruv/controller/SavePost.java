package com.dhruv.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import com.dhruv.beans.Wpost;
import com.dhruv.database.DBHandler;


public class SavePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sender = session.getAttribute("email").toString();
		String msg = request.getParameter("msg");
		String dop = new Date().toString();
		Wpost wpost = new Wpost(0,sender,msg,dop);
        DBHandler db = new DBHandler();
        db.Save(wpost);
        response.sendRedirect("Welcome");

	
	}

}
