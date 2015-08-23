package com.ku.eecs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ku.eecs.beans.AccountRequestBean;
import com.ku.eecs.beans.DepartmentBean;


public class AccountRequestDAO 
{
	Connection con = null;
	Statement stmt=null;
	PreparedStatement pst_S = null;
	PreparedStatement pst_I=null;
	PreparedStatement pst_D=null;
	ResultSet rs1 = null;
	ResultSet rs2 = null;
	int updatedrows=0;
	
	public ArrayList<DepartmentBean> getDepartmentNames()
	{
		ArrayList<DepartmentBean> al = new ArrayList<DepartmentBean>();
		String query="select * from department";
		try		
		{
			con=DBConnection.getDBConnection();
			stmt=con.createStatement();
			rs1=stmt.executeQuery(query);

			while(rs1.next())
			{
				DepartmentBean db = new DepartmentBean();
				db.setDepartmentId(rs1.getInt(1));
				db.setDepartmentName(rs1.getString(2));
				db.setDirector(rs1.getString(3));
				al.add(db);
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}

	public int storeRequestDetails(AccountRequestBean bean)
	{
		try {
			// Establishing connection by calling getConnection method from DBConnection class
			con=DBConnection.getDBConnection();
			
			//This query inserts the details of student account with the  student bean values as input parameter
			String query2="insert into accountrequest(username,password,email,kuId,kuOnlineId,lastName,firstName,departmentId) values(?,?,?,?,?,?,?,?)";
			
			// insertion is performed only when student is present in student main table
			// prepared statement is used to execute the query repeatedly
			pst_I=con.prepareStatement(query2);
			pst_I.setString(1,bean.getUserName());
			pst_I.setString(2,bean.getPassword());
			pst_I.setString(3,bean.getEmailId());
			pst_I.setString(4,bean.getKuId());
			pst_I.setString(5,bean.getKuOnlineId());
			pst_I.setString(6,bean.getLastName());
			pst_I.setString(7,bean.getFirstName());
			pst_I.setInt(8,bean.getDepartmentId());
			updatedrows=pst_I.executeUpdate();
	} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		return updatedrows;
	}

	public ArrayList<AccountRequestBean> getAllAccountRequests()
	{
		ArrayList<AccountRequestBean> al = new ArrayList<AccountRequestBean>();
		String query="select * from accountrequest order by requesteddate asc";
		try		
		{
			con=DBConnection.getDBConnection();
			stmt=con.createStatement();
			rs1=stmt.executeQuery(query);

			while(rs1.next())
			{
				AccountRequestBean ab = new AccountRequestBean();
				ab.setRequestId(rs1.getInt(1));
				ab.setFirstName(rs1.getString("firstName"));
				ab.setLastName(rs1.getString("lastName"));
				ab.setKuId(rs1.getString("kuId"));
				ab.setKuOnlineId(rs1.getString("kuOnlineId"));
				ab.setEmailId(rs1.getString("email"));
				ab.setRequestedDate(rs1.getDate("requestedDate").toString());
				al.add(ab);
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}

	public AccountRequestBean getAccountRequestById(int requestId)
	{
		AccountRequestBean ab = new AccountRequestBean();
		String query="select * from accountrequest a INNER JOIN department d on a.departmentId = d.departmentId where requestId=?";
		try		
		{
			con=DBConnection.getDBConnection();
			pst_S=con.prepareStatement(query);
			pst_S.setInt(1, requestId);
			rs1=pst_S.executeQuery();

			while(rs1.next())
			{
				ab.setRequestId(rs1.getInt("requestId"));
				ab.setFirstName(rs1.getString("firstName"));
				ab.setLastName(rs1.getString("lastName"));
				ab.setKuId(rs1.getString("kuId"));
				ab.setKuOnlineId(rs1.getString("kuOnlineId"));
				ab.setEmailId(rs1.getString("email"));
				ab.setDepartmentName(rs1.getString("departmentName"));
				ab.setRequestedDate(rs1.getDate("requestedDate").toString());
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pst_S.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ab;
	}

	public void updateAccountRequest(int requestId, String action) 
	{
		String query1 = "select * from accountrequest where requestId=?";
		String query2 = "delete from accountrequest where requestId=?";
		String query3 = "insert into user(username,password,email,kuId,kuOnlineId,lastName,firstName,role,departmentId) values(?,?,?,?,?,?,?,?,?) ";
		try		
		{
			con=DBConnection.getDBConnection();
			if(action.equalsIgnoreCase("approve"))
			{
				pst_S=con.prepareStatement(query1);
				pst_I = con.prepareStatement(query3);
				pst_D = con.prepareStatement(query2);
				
				pst_S.setInt(1, requestId);
				rs1=pst_S.executeQuery();

				while(rs1.next())
				{
					pst_I.setString(1,rs1.getString("username"));
					pst_I.setString(2,rs1.getString("password"));
					pst_I.setString(3,rs1.getString("email"));
					pst_I.setString(4,rs1.getString("kuId"));
					pst_I.setString(5,rs1.getString("kuOnlineId"));
					pst_I.setString(6,rs1.getString("lastname"));
					pst_I.setString(7,rs1.getString("firstname"));
					pst_I.setString(8,"student");
					pst_I.setInt(9,rs1.getInt("departmentId"));
				}
				updatedrows=pst_I.executeUpdate();
				if(updatedrows>0)
				{
					pst_D.setInt(1,requestId);
					pst_D.executeUpdate();
				}
			}
			else if(action.equalsIgnoreCase("reject"))
			{
				pst_D = con.prepareStatement(query2);
				pst_D.setInt(1,requestId);
				pst_D.executeUpdate();
			}
			

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pst_D.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
