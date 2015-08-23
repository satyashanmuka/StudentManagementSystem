package com.ku.eecs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ku.eecs.beans.UserBean;
import com.ku.eecs.dao.LoginDAO;

/**
 * Servlet implementation class BrowseUsersController
 */
public class BrowseUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseUsersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("retrieveAll"))
		{
			LoginDAO dao = new LoginDAO();
			ArrayList<UserBean> users = dao.getAllUsers();
			request.setAttribute("users",users);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/browseStudents.jsp");
			rd.forward(request, response);
			
		}
		if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("editAccountDetails"))
		{
			LoginDAO dao = new LoginDAO();
			UserBean user = dao.getUserDetailsById(Integer.parseInt(request.getParameter("userId")));
			request.setAttribute("user",user);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/editStudentDetails.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UserBean bean = new UserBean();
		
		//To update the account settings
		bean.setUserId(Integer.parseInt(request.getParameter("userId")));
		bean.setFirstName(request.getParameter("firstName").trim());
		bean.setLastName(request.getParameter("lastName").trim());
		bean.setEmailId(request.getParameter("emailId").trim());
		
		LoginDAO dao = new LoginDAO();
		dao.updateUserAccountDetails(bean);
		request.setAttribute("status","success");
		RequestDispatcher rd = request.getRequestDispatcher("JSP/editStudentDetails.jsp");
		rd.forward(request, response);
	}

}
