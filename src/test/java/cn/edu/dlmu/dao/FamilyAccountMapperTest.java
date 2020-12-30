package cn.edu.dlmu.dao;

import cn.BaseTest;
import cn.edu.dlmu.pojo.FamilyAccount;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class FamilyAccountMapperTest extends BaseTest {
	@Autowired
	FamilyAccountMapper familyAccountMapper;

	@Test
	public void addFamilyAccount() throws Exception {
		FamilyAccount familyAccount = new FamilyAccount(null, "461");
		System.out.println(familyAccountMapper.add(familyAccount));
	}

	@Test
	public void deleteFamilyAccountById() throws Exception {
		System.out.println(familyAccountMapper.delete(1));
		System.out.println(familyAccountMapper.queryById(1));
	}

	@Test
	public void updateFamilyAccountById() throws Exception {
		System.out.println(familyAccountMapper.queryById(1));
		System.out.println(familyAccountMapper.update(new FamilyAccount(1, "sdfjalkdj")));
		System.out.println(familyAccountMapper.queryById(1));
	}

	@Test
	public void queryFamilyAccountById() throws Exception {
		System.out.println(familyAccountMapper.queryById(1));
	}

	@Test
	public void queryFamilyAccountByName() throws Exception {
		System.out.println(familyAccountMapper.queryByName("Hello"));
	}

	@Test
	public void queryAllFamilyAccount() throws Exception {
		for (FamilyAccount f : familyAccountMapper.queryAll()) {
			System.out.println(f);
		}
	}
}