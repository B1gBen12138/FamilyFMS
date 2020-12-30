package cn.edu.dlmu.dao;



import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.pojo.BondList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BondListMapper extends BaseMapper<BondList> {
    ///*增加一个持股明细*/
    //Integer add(BondList bondList) throws Exception;
	//
    ///*删除一个持股明细*/
    //Integer delete(Integer id) throws Exception;
	//
    ///*修改一个持股明细*/
    //Integer update(BondList bondList) throws Exception;


    /*根据证券id查询流水*/
    List<BondList> queryByBondAccountId(Integer bondAccountId) throws Exception;
	//
    ///*查询所有明细*/
    //List<BondList> queryAll() throws Exception;

}
