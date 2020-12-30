package cn.edu.dlmu.dao;

import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.BondAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BondAccountMapper extends BaseMapper<BondAccount> {

    ///*增加一证券账户*/
    //Integer add(BondAccount bondAccount);
	//
    ///*删除一个证券账户*/
    //Integer delete(Integer bondAccountId);
	//
    ///*修改一个证券账户*/
    //Integer update(BondAccount bondAccount);
	//
    ///*根据证券账户id查询证券账户信息*/
    //BondAccount queryById(Integer bondAccountId);
	//
    ///*查询所有家庭账户*/
    //List<BondAccount> queryAll();
	List<BondAccount> queryByAccountId(Integer id);

}
