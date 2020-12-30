package cn.edu.dlmu.pojo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Account")

public class Account{

	private Integer id;
	private Integer familyId;
	private String loginName;
	private String name;
	private String password;
	private Boolean isAdmin;
	private Boolean isSuperAccount;

}
