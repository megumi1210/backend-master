package org.example.model;

/**
 * 性别的数据转换对象
 * @author chenj
 */
public enum SexEnum {
    //男性
    MALE(1,"男"),
    //女性
    FEMALE(0,"女");


    /**
     *  代表存入数据库的数据0或1
     */
    private int id;
    /**
     *  代表读出来的数据男或女
     */
    private String name;


    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public static SexEnum getSexById(int id){
        for(SexEnum sex : SexEnum.values()){
            if(sex.getId() == id){
                return sex;
            }
        }
        return  null;
    }
}
