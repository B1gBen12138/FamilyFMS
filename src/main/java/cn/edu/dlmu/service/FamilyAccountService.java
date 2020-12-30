package cn.edu.dlmu.service;

import cn.edu.dlmu.dao.FamilyAccountMapper;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.FamilyAccount;

public interface FamilyAccountService extends IBaseService<FamilyAccount> {

	/*增加一个用户到家庭组*/
	public Boolean addFamilyMember(Account member, FamilyAccount familyAccount);

	/*删除一个家庭组成员*/
	public Boolean deleteFamilyMember(FamilyAccount familyAccount);

	/*删除批量家庭成员*/
	public Boolean deleteFamilyMemberBatch(FamilyAccount familyAccount);


}
