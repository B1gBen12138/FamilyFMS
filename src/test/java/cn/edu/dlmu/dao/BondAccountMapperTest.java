package cn.edu.dlmu.dao;

import cn.BaseTest;
import cn.edu.dlmu.pojo.BondAccount;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class BondAccountMapperTest extends BaseTest {
    @Autowired
    private BondAccountMapper bondAccountMapper;

    @Test
    public void addBondAccount() throws Exception {
        BondAccount bondAccount = new BondAccount( null, 1,"股票");
        System.out.println(bondAccountMapper.add(bondAccount));
    }

    @Test
    public void deleteBondAccount() throws Exception {
	    System.out.println(bondAccountMapper.delete(1));
    }

    @Test
    public void updateBondAccountById() throws Exception {
    	bondAccountMapper.update(new BondAccount(1, 1,"ksdjfkajl"));
	    System.out.println(bondAccountMapper.queryById(1));
    }

    @Test
    public void queryBondAccountById() throws Exception {
        System.out.println(bondAccountMapper.queryById(1));

    }

    @Test
    public void queryAllBondAccount() throws Exception {
        System.out.println(bondAccountMapper.queryAll());
    }
}