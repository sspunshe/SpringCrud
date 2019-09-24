package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.model.Organization;
import com.model.User;

@Repository
public class OrgDao {

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/orguser", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public int save(Organization org) throws SQLException {
		int status = 0;
		Connection con = new OrgDao().getConnection();

		PreparedStatement ps = con
				.prepareStatement("insert into organization(Name,Email, Password, Address, City) Values(?,?,?,?,?)");
		ps.setString(1, org.getName());
		ps.setString(2, org.getEmail());
		ps.setString(3, org.getPassword());
		ps.setString(4, org.getAddress());
		ps.setString(5, org.getCity());
		status = ps.executeUpdate();
		con.close();

		return status;

	}

	public ResultSet login(String email, String pass) throws SQLException {
		ResultSet rs = null;
		Connection con = new OrgDao().getConnection();

		PreparedStatement ps = con.prepareStatement("Select * from organization where Email = ? and Password = ?");
		ps.setString(1, email);
		ps.setString(2, pass);
		rs = ps.executeQuery();
		return rs;
	}

	public boolean checkEmail(String email) throws SQLException {
		ResultSet rs = null;
		boolean status = false;
		Connection con = new OrgDao().getConnection();

		PreparedStatement ps = con.prepareStatement("Select * from organization where Email = ?");
		ps.setString(1, email);
		rs = ps.executeQuery();
		if (rs.next())
			status = true;
		con.close();

		return status;
	}

	public List<User> getUser(int id) {
		List<User> list = new ArrayList<User>();
		Connection con = new OrgDao().getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select * from user where Oid =" + id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString(2));
				user.setDesignation(rs.getString(3));
				list.add(user);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addUser(User user) {
		int status = 0;
		Connection con = new OrgDao().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into user(Name,Designation,Oid) Values(?,?,?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getDesignation());
			ps.setInt(3, user.getOid());
			status = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

}
