package cn.edu.dlmu.dao;

import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.pojo.Account;
import cn.edu.dlmu.pojo.FamilyAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FamilyAccountMapper extends BaseMapper<FamilyAccount> {
	//
    /*增加一家庭账户*/
    Integer add(FamilyAccount familyAccount);

    /*删除一个家庭账户*/
    Integer delete(@Param("id") int id);

    /*修改一个家庭账户*/
    Integer update(FamilyAccount familyAccount);

    /*根据id查询家庭账户*/
    FamilyAccount queryById(int id);

    /*根据家庭名查询家庭账户*/
    FamilyAccount queryByName(String name);
	//
    ///*查询所有家庭账户*/
    List<FamilyAccount> queryAll();
}
