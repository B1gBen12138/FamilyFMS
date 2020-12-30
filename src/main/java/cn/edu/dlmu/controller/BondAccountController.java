package cn.edu.dlmu.controller;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.service.BondAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequestMapping("/bondAccount")
@Controller
public class BondAccountController {

	@Autowired
	@Qualifier("bondAccountServiceImpl")
	BondAccountService bas;

	@RequestMapping("/list")
	public ModelAndView list(HttpSession s) {
		try {
			return new ModelAndView("/bondAccount/list")
					.addObject("bondAccounts", bas.queryAll());
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(Integer id) {
		try {
			return new ModelAndView("account/edit")
					.addObject("user", id == null ? new BondAccount() : bas.queryById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/update")
	public ModelAndView update(BondAccount bondAccount) {
		try {
			System.out.println("UserController.update " + bondAccount);
			bas.update(bondAccount);
			return new ModelAndView("redirect:/account/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/delete")
	public ModelAndView del(int id) {
		try {
			System.out.println("UserController.del " + id);
			bas.delete(id);
			return new ModelAndView("redirect:/account/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/*@RequestMapping("/modifyName")
	@ResponseBody
	public Boolean modifyName(Integer id, String oldName) {
		try {
			System.out.println("UserController.modifyName+:" + name + oldName);
			Account u = bas.queryById(id);
			return u == null || u.getName().equals(oldName);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}*/

}
