package com.family.financial.management.emun;

/**
 * Created by zhangyiping on 2017/11/19.
 */
public enum  AccountTypeEnum {
    type1(1,"日常用品"),
    type2(2,"服装鞋帽"),
    type3(3,"通讯费"),
    type4(4,"医疗保健"),
    type5(5,"休闲娱乐"),
    type6(6,"物管"),
    type7(7,"人情支出"),
    type8(8,"美容健身"),
    type9(9,"父母赡养"),
    type10(10,"教育培训"),
    type11(11,"维修保养"),
    type12(12,"生儿育女"),
    type13(13,"慈善捐助"),
    type14(14,"博彩支出"),
    type15(15,"意外损失"),
    type16(16,"其它损失"),
    type17(17,"食物"),
    type18(18,"交通"),
    type19(19,"餐费"),
    type20(20,"职业工薪"),
    type21(21,"业余收入"),
    type22(22,"人情收入"),
    type23(23,"博彩收入"),
    type24(24,"意外所得"),
    type25(25,"租金收入"),
    type26(26,"分红"),
    type27(27,"其他收入"),
    type28(28,"未分类");
    private long type;
    private String typeName;

    AccountTypeEnum(long type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static String getType(long id){
        return AccountTypeEnum.valueOf("type"+String.valueOf(id)).getTypeName();
    }
}
