package cn.edu.dlmu.controller;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.service.AccountService;
import cn.edu.dlmu.service.BondAccountService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.jvm.hotspot.oops.BooleanField;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/bondAccount")
@Controller
public class BondAccountController {

	@Autowired
	@Qualifier("bondAccountServiceImpl")
	BondAccountService bas;
	@Autowired
	@Qualifier("accountServiceImpl")
	AccountService ac;

	private Boolean message;

	public Boolean getMessage() {
		return message;
	}

	public void setMessage(Boolean message) {
		this.message = message;
	}

	@RequestMapping("/list")
	public ModelAndView list(HttpSession s) {
		try {
			Account account = (Account) s.getAttribute("loginUser");
			List<BondAccount> list = bas.queryByAccountId(account.getId());
			s.setAttribute("BondList", list);
			for (BondAccount ba : list) {
				System.out.println(ba);
			}
			return new ModelAndView("/bondAccount/list")
					.addObject("bondAccounts", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(Integer id) {
		try {
			System.out.println("edit"+ id);
			BondAccount ret = id == null ? new BondAccount() : bas.queryById(id);
			System.out.println(ret);
			return new ModelAndView("/bondAccount/edit")
					.addObject("bondAccount", ret);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/update")
	public ModelAndView update(BondAccount bondAccount) {
		try {
			System.out.println("BondAccount.update " + bondAccount);
			bas.update(bondAccount);
			return new ModelAndView("redirect:/bondAccount/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/delete")
	public ModelAndView del(int id) {
		try {
			System.out.println("BondAccount.del " + id);
			bas.delete(id);
			return new ModelAndView("redirect:/account/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/modifyName")
	public @ResponseBody boolean modifyName(String newName, String oldName, HttpSession s) {
		try {
			Account account = (Account)s.getAttribute("loginUser");
			System.out.println("BondAccount" + account.getLoginName() +" modifyName:" + oldName +" to " + newName);
			@SuppressWarnings("unchecked")
			List<BondAccount> list = (List<BondAccount>) s.getAttribute("BondList");
			if(list != null){
				for (BondAccount ba: list){
					System.out.println(ba.getName());
					if(ba.getName().equals(newName)) {
						return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
