package com.ku.eecs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ku.eecs.beans.UserBean;
import com.ku.eecs.dao.DBConnection;;

public class LoginDAO {
	Connection con=null;
	PreparedStatement pst_S=null;
	ResultSet rst=null;
	
	public boolean validateUserCredentials(String userName, String password) throws Exception
	{
		boolean flag=false;
		
		
		// Query to retrieve the details from login table
		String query1="select *from user where username=? and password=?";
		
		try
		{
			con=DBConnection.getDBConnection();
			
			pst_S=con.prepareStatement(query1);
			pst_S.setString(1,userName);
			pst_S.setString(2,password);
			
			rst=pst_S.executeQuery();
			if(rst.next())// if the record exists return true
			{
				flag=true;
			}
			else  // else return false
				return flag;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try{
			pst_S.close();
			con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return flag;
		
		
	}

	public UserBean getUserDetails(String userName, String password) 
	{
		UserBean ab = new UserBean();
		String query="select * from user where username=? and password=?";
		try		
		{
			con=DBConnection.getDBConnection();
			pst_S=con.prepareStatement(query);
			pst_S.setString(1,userName);
			pst_S.setString(2,password);
			rst=pst_S.executeQuery();

			while(rst.next())
			{
				ab.setUserId(rst.getInt("id"));
				ab.setUserName(rst.getString("username"));
				ab.setPassword(rst.getString("password"));
				ab.setFirstName(rst.getString("firstName"));
				ab.setLastName(rst.getString("lastName"));
				ab.setKuId(rst.getString("kuId"));
				ab.setKuOnlineId(rst.getString("kuOnlineId"));
				ab.setEmailId(rst.getString("email"));
				ab.setRole(rst.getString("role"));
				ab.setDepartmentId(rst.getInt("departmentId"));
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

	public ArrayList<UserBean> getAllUsers() 
	{
		ArrayList<UserBean> al = new ArrayList<UserBean>();
		String query="select u.id,u.username,u.kuId,u.kuOnlineId,u.firstName,u.lastName,u.email,d.departmentName from user u Inner Join Department d ON u.departmentId = d.departmentId where u.role=?";
		try		
		{
			con=DBConnection.getDBConnection();
			pst_S=con.prepareStatement(query);
			pst_S.setString(1,"student");
			rst=pst_S.executeQuery();

			while(rst.next())
			{
				UserBean ub = new UserBean();
				ub.setUserId(rst.getInt("id"));
				ub.setUserName(rst.getString("username"));
				ub.setKuId(rst.getString("kuId"));
				ub.setKuOnlineId(rst.getString("kuOnlineId"));
				ub.setEmailId(rst.getString("email"));
				ub.setFirstName(rst.getString("firstName"));
				ub.setLastName(rst.getString("lastName"));
				ub.setDepartmentName(rst.getString("departmentName"));
				
				al.add(ub);
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
		return al;
	}

	public void updateUserDetails(UserBean beanFromSession) 
	{
		String query3 = "update user set username=?,password=?,email=? WHERE id=?";
		PreparedStatement pst_I=null;
		try		
		{
			con=DBConnection.getDBConnection();

			pst_I = con.prepareStatement(query3);
			pst_I.setString(1,beanFromSession.getUserName());
			pst_I.setString(2,beanFromSession.getPassword());
			pst_I.setString(3,beanFromSession.getEmailId());
			
			pst_I.setInt(4,beanFromSession.getUserId());
			
			pst_I.executeUpdate();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pst_I.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public UserBean getUserDetailsById(int userId)
	{
		UserBean ub = new UserBean();
		String query="select u.id,u.username,u.kuId,u.kuOnlineId,u.firstName,u.lastName,u.email,d.departmentName from user u Inner Join Department d ON u.departmentId = d.departmentId where u.id=?";
		try		
		{
			con=DBConnection.getDBConnection();
			pst_S=con.prepareStatement(query);
			pst_S.setInt(1,userId);
			rst=pst_S.executeQuery();

			while(rst.next())
			{
				ub.setUserId(rst.getInt("id"));
				ub.setUserName(rst.getString("username"));
				ub.setKuId(rst.getString("kuId"));
				ub.setKuOnlineId(rst.getString("kuOnlineId"));
				ub.setEmailId(rst.getString("email"));
				ub.setFirstName(rst.getString("firstName"));
				ub.setLastName(rst.getString("lastName"));
				ub.setDepartmentName(rst.getString("departmentName"));
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
		return ub;
	}

	public void updateUserAccountDetails(UserBean bean) 
	{
		String query3 = "update user set firstName=?,lastName=?,email=? WHERE id=?";
		PreparedStatement pst_I=null;
		try		
		{
			con=DBConnection.getDBConnection();

			pst_I = con.prepareStatement(query3);
			pst_I.setString(1,bean.getFirstName());
			pst_I.setString(2,bean.getLastName());
			pst_I.setString(3,bean.getEmailId());
			
			pst_I.setInt(4,bean.getUserId());
			
			pst_I.executeUpdate();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pst_I.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
