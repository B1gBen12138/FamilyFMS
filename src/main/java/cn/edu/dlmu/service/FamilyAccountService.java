package cn.edu.dlmu.service;

import cn.edu.dlmu.dao.FamilyAccountMapper;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.FamilyAccount;

import java.util.List;
import java.util.Map;

public interface FamilyAccountService{

	public Integer add(FamilyAccount entity) throws Exception;

	/**
	 * 添加记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer add(List<FamilyAccount> entity) throws Exception;

	/**
	 * 删除记录
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer delete(Integer id) throws Exception;

	/**
	 * 删除记录
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer delete(List<Integer> id) throws Exception;

	/*修改记录*/
	public Integer update(FamilyAccount t) throws Exception;

	/**
	 * 修改记录
	 *
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer update(List<FamilyAccount> entity) throws Exception;

	/**
	 * 查询记录通过id
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FamilyAccount queryById(Integer id) throws Exception;

	List<FamilyAccount> queryAll() throws Exception;

	/**
	 * 根据参数统计记录数
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryCountByParams(Map map) throws Exception;
	
	/*增加一个用户到家庭组*/
	public Boolean addFamilyMember(Account member, FamilyAccount familyAccount);

	/*删除一个家庭组成员*/
	public Boolean deleteFamilyMember(FamilyAccount familyAccount);

	/*删除批量家庭成员*/
	public Boolean deleteFamilyMemberBatch(FamilyAccount familyAccount);


}
