package cn.edu.dlmu.controller;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@RequestMapping("/tologin")
	public ModelAndView tologin(HttpSession session) {
		System.out.println("hello");
		session.invalidate();
		return new ModelAndView("login");
	}

	@RequestMapping("/login")
	public ModelAndView login(String name, String pwd, String errorMsg, HttpSession session) {
		try {
			Account account = accountService.queryByLoginName(name);
			System.out.println("Account Login:"+ account);

			if(account !=null && account .getPassword().equals(pwd)) {
				session.setAttribute("loginAccount", account );

				return new ModelAndView("index").addObject("loginAccount",account);
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

	@RequestMapping("/check")
	@ResponseBody
	public boolean check(String name,String password) {
		try {
			Account account = accountService.queryByLoginName(name);
			if(account != null){
				return false;
			}
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println();
			return false;
		}
	}

	@RequestMapping("/register")
	@ResponseBody
	public boolean register(String name,String password) {
		try {
			int ret = accountService.add(new Account(null, null, name, name, password, Boolean.FALSE, Boolean.FALSE));
			if(ret == 0){
				return false;
			}
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println();
			return false;
		}
	}

}
