package cn.edu.dlmu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IOList {
    public final static Map<Integer, List<String>> typeMap = new HashMap<>();

    static {
        typeMap.put(0, new LinkedList<String>());
        typeMap.get(0).add("税收");
        typeMap.get(0).add("工资");
        typeMap.put(1, new LinkedList<String>());
        typeMap.get(1).add("衣食住行");
        typeMap.get(1).add("股票");
        typeMap.put(2, new LinkedList<String>());
        typeMap.get(2).add("医疗");
        typeMap.get(2).add("分红");
        typeMap.put(3, new LinkedList<String>());
        typeMap.get(3).add("其他");
        typeMap.get(3).add("奖金");
    }

    private Integer id;
    private Integer accountId;
    private Integer familyId;
    private Boolean isOutput;
    private Float money;
    private Integer type; // TODO: use enum
    private String date;
    private String source;
    private String comment;

    public static String convertType(Integer type, Boolean isOutput) {

        try {
            if (isOutput)
                return IOList.typeMap.get(type).get(0);
            return IOList.typeMap.get(type).get(1);
        } catch (NullPointerException e) {
            return null;
        }

    }

    public String convertType() {
        return IOList.convertType(this.type, this.isOutput);
    }

}
