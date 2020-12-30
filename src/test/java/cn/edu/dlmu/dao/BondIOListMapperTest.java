package cn.edu.dlmu.dao;

import cn.BaseTest;
import cn.edu.dlmu.base.BaseMapper;
import cn.edu.dlmu.pojo.BondIOList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class BondIOListMapperTest extends BaseTest {

    @Autowired
    private BondIOListMapper bondIOListMapper;

    BondIOList bondIOList=new BondIOList(null,3,true);;
    int bondListID=1;
    @Test
    public void addBondIOList() throws Exception {
        bondIOList = new BondIOList(null,3,true);
        int res = bondIOListMapper.add(bondIOList);
        System.out.println(res);

    }

    @Test
    public void deleteBondIOList() throws Exception {
        bondIOListMapper.add(bondIOList);
        System.out.println(bondIOListMapper.queryById(1));
        int res =bondIOListMapper.delete(bondListID);
        System.out.println(bondIOListMapper.queryById(1));
    }

    @Test
    public void updateBondIOList() throws Exception {
        //bondIOListMapper.addBondIOList(bondIOList);
        List<BondIOList> bondIOLists = bondIOListMapper.queryByBondAccountID(1);
	    for (BondIOList bondIOList:bondIOLists) {
		    System.out.println(bondIOList);
		    bondIOListMapper.update(new BondIOList(bondIOList.getId(), bondIOList.getBondListId(), bondIOList.getIsBuyIn()==Boolean.TRUE?Boolean.FALSE:Boolean.TRUE));
	    }

    }

    @Test
    public void queryBondIOListByBondListID() throws Exception {
    	
        System.out.println(bondIOListMapper.queryById(2));
    }

    @Test
    public void queryAllBondIOList() throws Exception {
        for (BondIOList bil: bondIOListMapper.queryAll())
        {
            System.out.println(bil);
        }
    }
}