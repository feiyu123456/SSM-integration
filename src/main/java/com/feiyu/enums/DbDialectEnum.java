package com.feiyu.enums;

public enum DbDialectEnum {
    DB2("db2", "db2数据库"),
    SQLSERVER("sqlserver", "sqlserver数据库"),
    ORACLE("oracle", "oracle数据库"),
    MYSQL("mysql", "mysql数据库"),
    POSTGRESQL("postgresql", "postgresql数据库");


    private String desc;
    private String code;
    DbDialectEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }


    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean equals(String code){
        return this.code.equals(code);
    }
}
