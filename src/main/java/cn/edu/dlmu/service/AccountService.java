package cn.edu.dlmu.service;

import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import cn.edu.dlmu.pojo.FamilyAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountServiceImpl")
public interface AccountService extends IBaseService<Account> {

	/*校验账户*/
	Boolean checkUser(Account account) throws Exception;

	/*获取账户的家庭组信息*/
	//FamilyAccount queryFamily(Integer id) throws Exception;

	Account queryByLoginName(String loginName) throws Exception;

	/*获取账户的证券账户*/
	List<BondAccount> queryBondAccount(Integer id) throws Exception;

}
