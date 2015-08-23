package com.ku.eecs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ku.eecs.beans.AccountRequestBean;
import com.ku.eecs.beans.DepartmentBean;
import com.ku.eecs.dao.AccountRequestDAO;

/**
 * Servlet implementation class AccountRequestController
 */
public class AccountRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRequestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//To load the department details for account request page initially
		if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("load"))
		{
			AccountRequestDAO dao = new AccountRequestDAO();
			ArrayList<DepartmentBean> departmentNames = dao.getDepartmentNames();
			request.setAttribute("status", "initialLoad");
			request.setAttribute("departmentNames", departmentNames);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/accountRequest.jsp");
			rd.forward(request, response);
		}
		//to retrieve all the account requests to display them for admin
		else if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("retreive"))
		{
			AccountRequestDAO dao = new AccountRequestDAO();
			ArrayList<AccountRequestBean> accountRequests = dao.getAllAccountRequests();
			request.setAttribute("accountRequests",accountRequests);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/displayAccountRequests.jsp");
			rd.forward(request, response);
		}
		//to forward the page to account request details of single student
		else if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("processRequest"))
		{
			AccountRequestDAO dao = new AccountRequestDAO();
			AccountRequestBean accountRequest = dao.getAccountRequestById(Integer.parseInt(request.getParameter("requestId")));
			request.setAttribute("accountRequest",accountRequest);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/processAccountRequest.jsp");
			rd.forward(request, response);
		}
		//to approve a account request
		else if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("approve"))
		{
			AccountRequestDAO dao = new AccountRequestDAO();
			dao.updateAccountRequest(Integer.parseInt(request.getParameter("requestId")),"approve");
			RequestDispatcher rd = request.getRequestDispatcher("/AccountRequestServlet?operation=retreive");
			rd.forward(request, response);
		}
		//to reject a account request
		else if(request.getParameter("operation")!=null && request.getParameter("operation").equalsIgnoreCase("reject"))
		{
			AccountRequestDAO dao = new AccountRequestDAO();
			dao.updateAccountRequest(Integer.parseInt(request.getParameter("requestId")),"reject");
			RequestDispatcher rd = request.getRequestDispatcher("/AccountRequestServlet?operation=retreive");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//used to store the new account request details
		AccountRequestDAO dao = new AccountRequestDAO();
		AccountRequestBean bean = new AccountRequestBean();
		bean.setUserName(request.getParameter("userName"));
		bean.setPassword(request.getParameter("password"));
		bean.setFirstName(request.getParameter("firstName"));
		bean.setLastName(request.getParameter("lastName"));
		bean.setKuId(request.getParameter("kuId"));
		bean.setKuOnlineId(request.getParameter("kuOnlineId"));
		bean.setEmailId(request.getParameter("emailId"));
		bean.setDepartmentId(Integer.parseInt(request.getParameter("department")));
		int updatedRows = dao.storeRequestDetails(bean);
		if(updatedRows>0)
		{
			request.setAttribute("status", "success");
			RequestDispatcher rd = request.getRequestDispatcher("JSP/accountRequest.jsp");
			rd.forward(request, response);
		}
		else
		{
			request.setAttribute("status", "failure");
			RequestDispatcher rd = request.getRequestDispatcher("JSP/accountRequest.jsp");
			rd.forward(request, response);
		}
	}

}
