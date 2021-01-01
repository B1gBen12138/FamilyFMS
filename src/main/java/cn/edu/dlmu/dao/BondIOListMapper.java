package cn.edu.dlmu.dao;


import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.pojo.BondIOList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BondIOListMapper extends BaseMapper<BondIOList> {

	/*买入卖出证券*/
	Integer add(BondIOList bondIOList) throws Exception;

	/*删除一个证券流水*/
	Integer delete(Integer id) throws Exception;

	/*修改一个证券流水*/
	Integer update(BondIOList bondIOList) throws Exception;


	/*管理员方法：查询所有流水*/
	List<BondIOList> queryAll() throws Exception;
	/*根据证券id查询所有流水*/
	List<BondIOList> queryByBondListId(Integer bondListId)throws Exception;
}
