package cn.edu.dlmu.controller;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.pojo.BondList;
import cn.edu.dlmu.service.AccountService;
import cn.edu.dlmu.service.BondAccountService;
import cn.edu.dlmu.service.BondListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bondList")
public class BondListController {

	@Autowired
	@Qualifier("bondListServiceImpl")
	BondListService bls;

	@Autowired
	@Qualifier("bondAccountServiceImpl")
	BondAccountService bas;

	@Autowired
	@Qualifier("accountServiceImpl")
	AccountService ac;

	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService accountService;

	public static final Integer HASH_MAP_SIZE = 100;

	public static final String MAP_ACCOUNT_BOND_ACCOUNT = "mapAccountBondAccount";

	private final static Logger logger = LogManager.getLogger(IOListController.class);

	/**
	 * 获取证券账户的证券信息
	 * */
	@RequestMapping("/list")
	public ModelAndView list(Integer bondAccountId, HttpSession s) {
		try {
			Account account = (Account) s.getAttribute(AccountController.LOGIN_ACCOUNT);
			List<BondList> list = new LinkedList<>();
			List<Account> aList = new LinkedList<>();
			Map<String, Object> mapAccountBondAccount = new HashMap<>(HASH_MAP_SIZE);
			ModelAndView modelAndView = new ModelAndView("/bondList/list");
			logger.debug("list:" +bondAccountId );
			if(bondAccountId == null){
				/** 如果是管理员：查询所有的Account的所有的BondAccount的BondList*/
				if(account.getIsAdmin()){
					/*查询所有account*/
					aList = accountService.queryAll();
					for(Account a : aList){
						/*查询所有account的bondAccount*/
						List<BondAccount> bList = bas.queryByAccountId(a.getId());
						Map<String,Object> tmp = new HashMap<>();
						for (BondAccount ba: bList) {
							tmp.put(ba.getName(), bls.queryByBondAccountId(ba.getId()));
						}
						mapAccountBondAccount.put(a.getLoginName(), tmp);
					}
					//logger.debug("queryALl: " + mapAccountBondAccount);
					modelAndView.addObject("bondList", mapAccountBondAccount);
				}else{
					/**不是管理员，则返回当前account的bondAccount的bondList*/
					List<BondAccount> bList = bas.queryByAccountId(account.getId());
					Map<String,Object> tmp = new HashMap<>();
					for (BondAccount ba: bList) {
						tmp.put(ba.getName(), bls.queryByBondAccountId(ba.getId()));
					}
					mapAccountBondAccount.put(account.getLoginName(), tmp);
					modelAndView.addObject("bondList", mapAccountBondAccount);
				}
				s.setAttribute(MAP_ACCOUNT_BOND_ACCOUNT, mapAccountBondAccount);
				logger.debug(String.format("query Account: %d %s", bondAccountId, mapAccountBondAccount));
			}
			else{
				/*不为空，则说明是查看某个bondAccount的证券*/
				//TODO：某个bondAccount的bondList
			}
			return modelAndView.addObject("account",(Account) s.getAttribute(AccountController.LOGIN_ACCOUNT));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("500")
					.addObject("ex", e);
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpSession s)
	{
		/*返回当前用户的证券账户，并为证券账户添加证券*/
		ModelAndView modelAndView = new ModelAndView("/bondList/edit");
		Account account = (Account)s.getAttribute(AccountController.LOGIN_ACCOUNT);
		HashMap<String, Object> map = (HashMap) s.getAttribute(MAP_ACCOUNT_BOND_ACCOUNT);
		return modelAndView
				.addObject("account", account )
				.addObject("mapBondAccount", map.get(account.getLoginName()));
	}

	@RequestMapping("/add")
	public ModelAndView add(String user, String bondAccount, String bondName, HttpSession s)
	{
		/*返回当前用户的证券账户，并为证券账户添加证券*/
		try {
			logger.debug(String.format("Add: %s %s %s",user, bondAccount, bondName));
			Account account = (Account)s.getAttribute(AccountController.LOGIN_ACCOUNT);
			HashMap<String, Object> map = (HashMap) s.getAttribute(MAP_ACCOUNT_BOND_ACCOUNT);
			List<BondAccount> list = bas.queryByAccountId(account.getId());
			for (BondAccount ba: list) {
			    if(ba.getName().equals(bondAccount)){
				    BondList newBondList = new BondList(null, ba.getId(), bondName);
				    bls.add(newBondList);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/bondList/list");
	}

	@ResponseBody
	@RequestMapping("/check")
	public boolean check(String bondAccount, String bondName, HttpSession s){
		try {
			Account account = (Account)s.getAttribute(AccountController.LOGIN_ACCOUNT);
			HashMap<String, Object> map = (HashMap) s.getAttribute(MAP_ACCOUNT_BOND_ACCOUNT);
			Map<String ,Object> bondMap = (HashMap) map.get(account.getLoginName());

			List<BondList> bondLists = (List<BondList>) bondMap.get(bondAccount);
			for (BondList bl: bondLists) {
			    if(bl.getName().equals(bondName)){
				    return false;
			    }
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
