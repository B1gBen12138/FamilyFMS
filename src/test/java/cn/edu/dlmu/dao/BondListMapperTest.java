package cn.edu.dlmu.dao;

import cn.BaseTest;
import cn.edu.dlmu.pojo.BondIOList;
import cn.edu.dlmu.pojo.BondList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class BondListMapperTest extends BaseTest {

    @Autowired
    private BondListMapper bondListMapper;

    BondList bondList=new BondList(null,1,"asd");
    int bondAccountId;
    @Test
    public void addBondList() throws Exception {
    	bondAccountId = 1;
        int res=bondListMapper.add(bondList);
        System.out.println(res);
    }

    @Test
    public void deleteBondList() throws Exception {

        System.out.println(bondListMapper.queryById(bondAccountId));
        int res = bondListMapper.delete(bondAccountId);
        System.out.println(bondListMapper.queryById(bondAccountId));
    }

    @Test
    public void updateBondList() throws Exception {
	    System.out.println(bondListMapper.queryById(1));
	    bondListMapper.update(new BondList(1, 2, "asd"));
	    System.out.println(bondListMapper.queryById(2));
    }

    @Test
    public void queryById() throws Exception {
	    for (BondList bondList:bondListMapper.queryByBondAccountId(1)) {
		    System.out.println(bondList);
	    }

    }

    @Test
    public void queryAllBondList() throws Exception {
        for (BondList bil: bondListMapper.queryAll())
        {
            System.out.println(bil);
        }
    }
}