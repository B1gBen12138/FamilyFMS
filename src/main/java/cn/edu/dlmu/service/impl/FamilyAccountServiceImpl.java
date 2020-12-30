package cn.edu.dlmu.service.impl;

import cn.edu.dlmu.base.AssertUtil;
import cn.edu.dlmu.dao.FamilyAccountMapper;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.FamilyAccount;
import cn.edu.dlmu.service.FamilyAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("familyAccountServiceImpl")
public class FamilyAccountServiceImpl implements FamilyAccountService {

	FamilyAccountMapper familyAccountMapper;

	public void setFamilyAccountMapper(FamilyAccountMapper familyAccountMapper) {
		this.familyAccountMapper = familyAccountMapper;
	}

	public Integer add(FamilyAccount t) throws Exception {
		AssertUtil.isNull(t, "实体为空");
		return familyAccountMapper.add(t);
	}

	/*批量添加记录*/
	public Integer add(List<FamilyAccount> tList) throws Exception {
		int cot = 0;
		for (FamilyAccount t : tList) {
			familyAccountMapper.add(t);
			cot++;
		}
		return cot;
	}

	/*删除记录*/
	public Integer delete(Integer id) throws Exception {
		// 判断 空
		AssertUtil.isNull(id, "列表为空");
		AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
		return familyAccountMapper.delete(id);
	}

	/*删除记录*/
	public Integer delete(List<Integer> tList) throws Exception {
		// 判断 空
		AssertUtil.isNull(tList, "列表为空");
		int cot = 0;
		for (Integer id : tList) {
			AssertUtil.isNull(queryById(id), "待删除的记录不存在!");
			cot += familyAccountMapper.delete(id);
		}
		return cot;
	}
	/*修改记录*/
	public Integer update(FamilyAccount entity) throws Exception {
		return familyAccountMapper.update(entity);
	}

	public Integer update(List<FamilyAccount> tList) throws Exception {
		AssertUtil.isNull(tList, "List is Null");
		int cot = 0;
		for (FamilyAccount t : tList) {
			familyAccountMapper.update(t);
			cot++;
		}
		return cot;
	}

	/**
	 * 根据参数统计记录数
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryCountByParams(Map map) throws Exception {
		return familyAccountMapper.queryCountByParams(map);
	}

	/*根据id查询记录*/
	public FamilyAccount queryById(Integer id) throws Exception {
		AssertUtil.isNull(id, "记录id非空!");
		return familyAccountMapper.queryById(id);
	}

	public List<FamilyAccount> queryAll() throws Exception{
		return familyAccountMapper.queryAll();
	}
	public Boolean addFamilyMember(Account member, FamilyAccount familyAccount) {
		return null;
	}

	public Boolean deleteFamilyMember(FamilyAccount familyAccount) {
		return null;
	}

	public Boolean deleteFamilyMemberBatch(FamilyAccount familyAccount) {
		return null;
	}
}
