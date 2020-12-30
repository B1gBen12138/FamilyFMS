package cn.edu.dlmu.service;

import cn.edu.dlmu.dao.IOListMapper;
import cn.edu.dlmu.pojo.FamilyAccount;
import cn.edu.dlmu.pojo.IOList;

import java.util.List;

public interface IOListService extends IBaseService<IOList> {

	/*批量删除流水*/
	public Boolean deleteBatch(List<IOList> ioLists);

	/*根据家庭统计流水*/
	public List<IOList> queryByFamily(FamilyAccount familyAccount);

}
