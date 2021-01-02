package cn.edu.dlmu.controller;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.service.AccountService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import cn.edu.dlmu.pojo.Account;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {
	/*Session中存放的当前登录账户*/
	public static final String LOGIN_ACCOUNT = "loginAccount";

	private final static Logger logger = LogManager.getLogger(IOListController.class);

	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService accountService;


	@RequestMapping("/list")
	public ModelAndView list(HttpSession s) {
		try {
			Account account = (Account) s.getAttribute(LOGIN_ACCOUNT);
			ModelAndView modelAndView = new ModelAndView("/account/list");
			Map map = new HashMap<String, Object>(1);
			map.put("familyId", account.getFamilyId());
			/*如果是管理员，获取所有的用户*/
			if (account.getIsAdmin() == Boolean.TRUE) {
				return modelAndView.addObject("accounts", accountService.queryAll())
						.addObject("loginAccount", (Account) s.getAttribute(LOGIN_ACCOUNT));

			} else {
				/*普通用户*/
				return modelAndView.addObject("loginAccount", (Account) s.getAttribute(LOGIN_ACCOUNT));

			}
			//return new ModelAndView("account/list")
			//		.addObject("users", accountService.queryAll());
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/*新增用户*/
	@RequestMapping("/add")
	public ModelAndView add(String id) {
		try {
			return new ModelAndView("/account/add");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/*修改用户*/
	@RequestMapping("/edit")
	public ModelAndView edit(String id) {
		try {
			return new ModelAndView("account/edit").addObject("account", accountService.queryById(Integer.parseInt(id)));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/*页面修改用户，修改完毕后跳转回list*/
	@RequestMapping("/update")
	public ModelAndView update(Account account) {
		try {
			Account a = accountService.queryByLoginName(account.getLoginName());
			account.setId(a.getId());
			logger.debug("UserController.update " + account);
			accountService.update(account);
			return new ModelAndView("redirect:/account/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/*页面删除用户，删除后跳转回list，刷新页面*/
	@RequestMapping("/delete")
	public ModelAndView del(int id, HttpSession session) {
		try {
			logger.debug("UserController.del " + id);
			Account account = (Account) session.getAttribute(LOGIN_ACCOUNT);

			accountService.delete(id);
			if(account.getId() == id)
				session.invalidate();
			return new ModelAndView("redirect:/account/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/modifyName")
	@ResponseBody
	public boolean modifyName(@RequestBody String name, @RequestBody String oldName) {
		try {
			logger.debug("UserController.modifyName+:" + name + oldName);
			Account u = accountService.queryByLoginName(name);
			return u == null || u.getName().equals(oldName);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@ResponseBody
	@RequestMapping("/check")
	public boolean check(String loginName) {
		try {
			logger.debug("Check LoginName: " + loginName);
			Account account = accountService.queryByLoginName(loginName);
			logger.debug("Check Result:" + account);
			/*如果查询的账户为空，则可以添加用户*/
			/*不为空，则说明有相同登录名的用户*/
			return account == null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*ajax: 增加一个用户*/
	@ResponseBody
	@RequestMapping("/add/addAccount")
	public boolean addAccount(String loginName, String name, String password, Boolean isAdmin, HttpSession session) {
		try {
			//Account la = (Account) session.getAttribute(loginAccount);
			logger.debug(String.format("add a Account %s %s %s %b ", loginName, name, password, isAdmin));
			Account account = new Account(null, null, loginName, name, password, isAdmin, null);
			logger.debug(String.format("add a Account %s", account));
			Integer ret = accountService.add(account);
			if (ret == 1) {
				return true;
			}
			logger.debug("add return:  " + ret);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}
