package cn.edu.dlmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BondAccount {
	private Integer id;
	private Integer accountId;
	private String name;

}
