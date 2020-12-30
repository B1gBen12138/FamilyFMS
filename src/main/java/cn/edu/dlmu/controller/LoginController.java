package cn.edu.dlmu.controller;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller

public class LoginController {
	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService accountService;

	@RequestMapping("/")
	public ModelAndView hello() {
		return new ModelAndView("login");
	}

	@RequestMapping("/toLogin")
	public ModelAndView tologin(HttpSession session) {
		System.out.println("hello");
		session.invalidate();
		return new ModelAndView("login");
	}

	@RequestMapping("/login")
	public ModelAndView login(String name, String pwd, String errorMsg, HttpSession session) {
		try {
			System.out.println(name);
			Account account = accountService.queryByLoginName(name);
			System.out.println(name);
			if(account !=null && account .getPassword().equals(pwd)) {
				session.setAttribute("loginUser", account );
				return new ModelAndView("index");
			}else {
				return new ModelAndView("login")
						.addObject("name",name)
						.addObject("pwd",pwd)
						.addObject("flag", 2);
			}
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(errorMsg);
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}
}
