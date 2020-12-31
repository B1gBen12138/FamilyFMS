package cn.edu.dlmu.controller;

import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.service.AccountService;
import cn.edu.dlmu.service.BondAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BondListController {

	@Autowired
	@Qualifier("bondListServiceImpl")
	BondAccountService bls;
	@Autowired
	@Qualifier("accountServiceImpl")
	AccountService ac;

	public ModelAndView list(HttpSession s) {
		try {
			List<BondAccount> list = bls.queryAll();
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
}
