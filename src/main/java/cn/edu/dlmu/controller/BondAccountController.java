package cn.edu.dlmu.controller;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.service.AccountService;
import cn.edu.dlmu.service.BondAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/bondAccount")
@Controller
public class BondAccountController {

	public static final String NEW_BOND_ACCOUNT = "NewBondAccount";

	public static final String BOND_LIST = "BondList";

	@Autowired
	@Qualifier("bondAccountServiceImpl")
	BondAccountService bas;
	@Autowired
	@Qualifier("accountServiceImpl")
	AccountService ac;

	@RequestMapping("/list")
	public ModelAndView list(HttpSession s) {
		try {
			/*清除session*/
			s.removeAttribute(NEW_BOND_ACCOUNT);
			Account account = (Account) s.getAttribute(AccountController.LOGIN_ACCOUNT);
			List<BondAccount> list = bas.queryByAccountId(account.getId());
			s.setAttribute(BOND_LIST, list);
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

	/*Ajax 新增证券账户*/
	@RequestMapping("/add")
	public ModelAndView add(Integer id) {
		try {

			return new ModelAndView("/bondAccount/add");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/**
	 * @Description 修改或新增BondAccount
	 * 如果是修改，会提供需要修改的id，否则为新增
	 * @Params [id, session]
	 * @Return org.springframework.web.servlet.ModelAndView
	 * @Author Mr.Xie
	 * @Date 2021/1/1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(Integer id, HttpSession session) {
		try {
			/** 根据是否有id，选择新增BondAccount或修改*/
			BondAccount ba = id == null ? new BondAccount() : bas.queryById(id);
			;
			System.out.println("edit " + ba);
			session.setAttribute(BondAccountController.NEW_BOND_ACCOUNT, ba);
			return new ModelAndView("/bondAccount/edit")
					.addObject("bondAccount", ba);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/*提交证券账户信息，修改证券账户*/
	@RequestMapping("/update")
	public ModelAndView update(String bondName, HttpSession session) {
		try {
			Account account = (Account) session.getAttribute(AccountController.LOGIN_ACCOUNT);
			BondAccount bondAccount = (BondAccount) session.getAttribute(NEW_BOND_ACCOUNT);
			bondAccount.setAccountId(account.getId());
			bondAccount.setName(bondName);
			int ret = 0;
			if (bondAccount.getId() == null) {
				System.out.println("BondAccount.add " + bondAccount);
				ret = bas.add(bondAccount);
			} else {
				System.out.println("BondAccount.update " + bondAccount);
				ret = bas.update(bondAccount);
			}
			System.out.println("update result: " + ret);
			return new ModelAndView("redirect:/bondAccount/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/**/
	@RequestMapping("/delete")
	public ModelAndView del(int id) {
		try {
			System.out.println("BondAccount.del " + id);
			bas.delete(id);
			return new ModelAndView("redirect:/bondAccount/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@ResponseBody
	@RequestMapping("/modifyName")
	public boolean modifyName(String bondName, HttpSession session) {
		try {
			/**从session中获取Account的BondAccount的list，
			 * 从session中取出需要更新（新增）的BondAccount
			 * 如果BondAccound*/
			List<BondAccount> lists = (List<BondAccount>) session.getAttribute(BOND_LIST);
			BondAccount ba = (BondAccount) session.getAttribute(NEW_BOND_ACCOUNT);
			System.out.println(String.format("modifyName: %s newName: %s lists :%s", ba, bondName, lists));
			Account account = (Account) session.getAttribute(AccountController.LOGIN_ACCOUNT);
			if (ba.getId() == null) {
				/** 创建新账户,*/
				for (BondAccount b : lists) {
					/**需要对所有的现有的账户名进行比较*/
					if (b.getName().equals(bondName)) {
						return false;
					}
				}
				return true;
			}

			/*修改老账户*/
			for (BondAccount b : lists) {
				/**不与同id的对比*/
				if (!ba.getId().equals(b.getId()) && ba.getName().equals(bondName)) {
					return false;
					/**如果提交的名字没有修改，也会返回true，
					 * 如果出现除自己外其他的名字，说明发生重名，返回false*/
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
