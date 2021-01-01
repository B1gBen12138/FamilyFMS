package cn.edu.dlmu.controller;


import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.pojo.BondIOList;
import cn.edu.dlmu.pojo.BondList;
import cn.edu.dlmu.service.BondAccountService;
import cn.edu.dlmu.service.BondIOListService;
import cn.edu.dlmu.service.BondListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bondIOList")
public class BondIOListController {


	@Autowired
	@Qualifier("bondIOListServiceImpl")
	private BondIOListService bondIOListService;
	@Autowired
	@Qualifier("bondAccountServiceImpl")
	private BondAccountService bondAccountService;
	@Autowired
	@Qualifier("bondListServiceImpl")
	private BondListService bondListService;


	@RequestMapping("/list")
	public ModelAndView list(HttpSession s) {

		try {
			Account account = (Account) s.getAttribute("loginAccount");
			ModelAndView modelAndView = new ModelAndView("/bondIOList/list");
			List<BondAccount> bondAccount = bondAccountService.queryByAccountId(account.getId());
			Map<String, Map<String, Object>> bondAccountMap = new HashMap<>();
			for (BondAccount ba : bondAccount) {
				Map<String, Object> tmp = new HashMap<>();
				List<BondList> bondList = bondListService.queryByBondAccountId(ba.getId());

				for (BondList bl : bondList) {

					List<BondIOList> bondIOLists = bondIOListService.queryByBondListId(bl.getId());
					AssertUtil.isNull(bondIOLists, "账户证券为空");

					tmp.put(bl.getName(), bondIOLists);
				}
				bondAccountMap.put(ba.getName(), tmp);
			}
			System.out.println(bondAccountMap);
			return modelAndView.addObject("bondIOLists", bondAccountMap);

		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/*删除*/
	@RequestMapping("/delete")
	public ModelAndView del(int id) {
		try {
			System.out.println("UserController.del " + id);
			bondIOListService.delete(id);
			return new ModelAndView("redirect:/bondIOList/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/*新增*/
	@RequestMapping("/add")
	public ModelAndView add(String id) {
		try {
			return new ModelAndView("/bondIOList/add");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	/*修改*/
	@RequestMapping("/edit")
	public ModelAndView edit(String id) {
		try {

			return new ModelAndView("bondIOList/update").addObject("bondIOList", bondIOListService.queryById(Integer.parseInt(id)));

		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/update")
	public ModelAndView update(BondIOList bondIOList) {
		try {
			System.out.println("UserController.update " + bondIOList);
			bondIOListService.update(bondIOList);
			return new ModelAndView("redirect:/bondIOList/list");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@ResponseBody
	@RequestMapping("/check")
	public boolean check(String bondAccontname, HttpSession session) {
		try {
			System.out.println("Check bondAccontname: " + bondAccontname);
			Account account = (Account) session.getAttribute("loginAccount");
			List<BondAccount> bondAccount = bondAccountService.queryByAccountId(account.getId());
			for (BondAccount ba : bondAccount) {
				if (bondAccontname.equals(ba.getName()))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@ResponseBody
	@RequestMapping("/add/addBondIOList")
	public boolean addBondIOList(String bondAccontname, String bondListname, Boolean isBuyIn, HttpSession session) {
		try {

			System.out.println(String.format("add a Account %s  %s %b ", bondAccontname, bondListname, isBuyIn));
			Integer accountid = null, listid = null;

			Account account = (Account) session.getAttribute("loginAccount");
			List<BondAccount> bondAccount = bondAccountService.queryByAccountId(account.getId());
			for (BondAccount ba : bondAccount) {
				if (bondAccontname.equals(ba.getName())) {
					accountid = ba.getId();
				}

			}
			BondList bondList = new BondList(listid, accountid, bondListname);
			System.out.println(bondList);
			Integer ret1 = bondListService.add(bondList);
			if (listid == null) {
				for (BondAccount ba : bondAccount) {
					if (bondAccontname.equals(ba.getName())) {
						accountid = ba.getId();
					}
					List<BondList> bondList2 = bondListService.queryByBondAccountId(ba.getId());
					for (BondList bl : bondList2) {
						List<BondIOList> bondIOLists = bondIOListService.queryByBondListId(bl.getId());
						AssertUtil.isNull(bondIOLists, "账户证券为空");
						if (bondListname.equals(bl.getName()))
							listid = bl.getId();
					}
				}
			}
			BondIOList bondIOList = new BondIOList(null, listid, isBuyIn);
			Integer ret2 = bondIOListService.add(bondIOList);

			if (ret1 == 1 && ret2 == 1) {
				return true;
			} else return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

