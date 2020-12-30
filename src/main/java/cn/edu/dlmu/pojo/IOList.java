package cn.edu.dlmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IOList {
	private Integer id;
	private Integer accountId;
	private Integer familyId;
	private Boolean isOutput;
	private Float money;
	private Integer type;
	private Date date;
	private String source;
	private String comment;
}
