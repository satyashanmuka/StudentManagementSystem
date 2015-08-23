package com.ku.eecs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ku.eecs.beans.UserBean;
import com.ku.eecs.dao.LoginDAO;

/**
 * Servlet implementation class AccountSettingsController
 */
public class AccountSettingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountSettingsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//To display the account settings page
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UserBean beanFromSession = (UserBean)request.getSession().getAttribute("userBean");
		if(request.getParameter("currentPassword")!=null && !request.getParameter("currentPassword").equalsIgnoreCase("") && !request.getParameter("currentPassword").equals(beanFromSession.getPassword()))
		{
			request.setAttribute("status","failure");
			RequestDispatcher rd = request.getRequestDispatcher("JSP/accountSettings.jsp");
			rd.forward(request, response);
			return;
			
		}
		
		//To update the account settings
		beanFromSession.setUserName(request.getParameter("displayName").trim());
		beanFromSession.setEmailId(request.getParameter("emailId"));
		if(request.getParameter("currentPassword")!=null && !request.getParameter("currentPassword").equalsIgnoreCase("") && request.getParameter("newPassword")!=null && !request.getParameter("newPassword").equalsIgnoreCase(""))
		{
			beanFromSession.setPassword(request.getParameter("newPassword"));
		}
		
		LoginDAO dao = new LoginDAO();
		dao.updateUserDetails(beanFromSession);
		request.getSession().setAttribute("userBean",beanFromSession );
		request.getSession().setAttribute("theName", beanFromSession.getUserName());
		request.setAttribute("status","success");
		RequestDispatcher rd = request.getRequestDispatcher("JSP/accountSettings.jsp");
		rd.forward(request, response);
	}

}
