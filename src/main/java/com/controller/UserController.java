package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.OrgDao;
import com.model.User;

@Controller
public class UserController {

	@Autowired
	private OrgDao orgDao;

	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String desig = request.getParameter("designation");
		HttpSession session = request.getSession();
		int oid = (Integer) session.getAttribute("Id");
		User user = new User();
		user.setName(name);
		user.setDesignation(desig);
		user.setOid(oid);
		int status = orgDao.addUser(user);
		ModelAndView mv = new ModelAndView();
		if (status > 0) {
			mv.setViewName("Welcome.jsp");
			mv.addObject("msg", "User Added successfully!");
		} else {
			mv.setViewName("AddUser.jsp");
			mv.addObject("msg", "Sorry! unable to add user");
		}
		return mv;
	}

}
