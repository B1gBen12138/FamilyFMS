package cn.edu.dlmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BondIOList {
	private Integer id;
	private Integer bondListId;
	private Boolean isBuyIn;
}
