package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.OrgDao;
import com.model.Organization;

@Controller
public class Organization_Controller {
	@RequestMapping("/RegisterOrg")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String add = request.getParameter("add");
		String city = request.getParameter("city");
		Organization org = new Organization();
		org.setName(name);
		org.setEmail(email);
		org.setPassword(pass);
		org.setAddress(add);
		org.setCity(city);
		boolean status = OrgDao.checkEmail(email);
		ModelAndView mv = new ModelAndView();
		if (status) {
			mv.setViewName("Login.jsp");
			mv.addObject("msg", "Account Already Exist...Please Login");
		} else {
			int status1 = OrgDao.save(org);
			if (status1 > 0) {
				mv.setViewName("Login.jsp");
				mv.addObject("msg", "Registration successful!");
			} else {
				mv.setViewName("index.jsp");
				mv.addObject("msg", "Sorry! Unable to Register");
			}
		}
		return mv;
	}

	@RequestMapping("/LoginOrg")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		ResultSet rs = OrgDao.login(email, pass);
		ModelAndView mv = new ModelAndView();
		if (rs.next()) {
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("Id", rs.getInt(1));
			mv.setViewName("Welcome.jsp");
			mv.addObject("msg", "Login Successful");

		} else {
			mv.setViewName("Login.jsp");
			mv.addObject("msg", "Email ID or Password does not match..");
		}
		return mv;
	}

	@RequestMapping("/LogoutServlet")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Login.jsp");
		mv.addObject("msg", "Logout Successful.");
		return mv;
	}
}
