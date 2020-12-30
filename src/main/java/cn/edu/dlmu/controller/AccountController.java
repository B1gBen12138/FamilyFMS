package cn.edu.dlmu.controller;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import cn.edu.dlmu.pojo.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/accountService")
public class AccountController {

	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService accountService;


	@RequestMapping("/list")
	public ModelAndView list(HttpSession s) {
		try {
			return new ModelAndView("account/list")
					.addObject("users", accountService.queryAll());
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(String id) {
		try {
			return new ModelAndView("account/edit")
					.addObject("user", id == null ? new Account() : accountService.queryById(Integer.parseInt(id)));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/update")
	public ModelAndView update(Account user) {
		try {
			System.out.println("UserController.update " + user);
			accountService.update(user);
			return new ModelAndView("redirect:/user/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/del")
	public ModelAndView del(int id) {
		try {
			System.out.println("UserController.del " + id);
			accountService.delete(id);
			return new ModelAndView("redirect:/account/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/modifyName")
	@ResponseBody
	public Boolean modifyName(String name, String oldName) {
		try {
			System.out.println("UserController.modifyName+:" + name + oldName);
			Account u = accountService.queryByLoginName(name);
			return u == null || u.getName().equals(oldName);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}


}
