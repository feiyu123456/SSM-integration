package com.feiyu.utils;

import com.feiyu.enums.DbDialectEnum;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class DBContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    private static Map<String,String> dialetMap = new HashMap<String, String>();

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
        //TODO: databaseProviderId
        //TODO: PageHelper dialect
    }

    /**
     * 閲嶇疆涓洪粯璁ゆ暟鎹簮
     */
    public static void setDefaultSource(){
        contextHolder.set(getDataSourceDefaultId());
    }

    /**
     * 鑾峰彇褰撳墠鏁版嵁婧�
     * @return
     */
    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
    /**
     * 鑾峰彇榛樿鏁版嵁婧恑d
     * @return
     */
    public static String getDataSourceDefaultId() {
        String dataSourceDefaultId = "";
        DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringContextHelper
                .getBean("multiDataSource");
        Map<Object, Object> dynamicTargetDataSources = dynamicDataSource
                .getDynamicTargetDataSources();
        Iterator it = dynamicTargetDataSources.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == dynamicDataSource.getDynamicDefaultTargetDataSource()) {
                dataSourceDefaultId = String.valueOf(key);
            }
        }
        return dataSourceDefaultId;
    }

    /**
     * 鑾峰彇鎵�鏈夋暟鎹簮id鐨刲ist闆嗗悎
     * @return
     */
    public static List<String> getDataSourceIdList() {
        List<String> dataSourceIdList = new ArrayList<String>();
        DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringContextHelper
                .getBean("multiDataSource");
        Map<Object, Object> dynamicTargetDataSources = dynamicDataSource
                .getDynamicTargetDataSources();
        Iterator it = dynamicTargetDataSources.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            dataSourceIdList.add(String.valueOf(key));
        }
        return dataSourceIdList;
    }

    /**
     * 鑾峰彇褰撳墠鏁版嵁搴撴柟瑷�
     * @return 鏁版嵁搴撴柟瑷� oracle db2 mysql sqlserver
     */
    public static String getDialet(){
        Connection conn = null;
        String value="";
        String dialetString;

        String datasourceName = DBContextHolder.getDataSource();
        if(StringUtils.isEmpty(datasourceName)){
            datasourceName = "default";
        }
        //鍒ゆ柇map缂撳瓨涓槸鍚﹀瓨鍦紝瀛樺湪鐩存帴杩斿洖
        if(dialetMap.get(datasourceName) != null){
            return dialetMap.get(datasourceName);
        }
        try {
            //璇诲彇褰撳墠鏁版嵁婧愮殑dialet
            DynamicDataSource dynamicDataSource=(DynamicDataSource)SpringContextHelper.getBean("multiDataSource");
            Map<Object, Object> dynamicTargetDataSources = dynamicDataSource.getDynamicTargetDataSources();
            DataSource datasource = null;
            if("default".equals(datasourceName)){
                datasource = (DataSource)dynamicDataSource.getDynamicDefaultTargetDataSource();
            }else if(dynamicTargetDataSources.containsKey(datasourceName)){
                datasource = (DataSource)dynamicTargetDataSources.get(datasourceName);
            }
            conn = DataSourceUtils.getConnection(datasource);
            //鏍规嵁鏁版嵁搴撹繛鎺ヨ幏鍙栨柟瑷�
            value = getDialect(conn);
            //灏哾ialet鏀惧叆map缂撳瓨
            dialetMap.put(datasourceName, value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(conn!=null&&!conn.isClosed()){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return value;
    }


    /**
     * 鏍规嵁鏁版嵁搴撹繛鎺ヨ幏鍙栨柟瑷�
     * @param conn
     * @return
     */
    public static String getDialect(Connection conn) {
        String dialetString = null;
        String value = null;
        try {
            dialetString = conn.getMetaData().getDatabaseProductName();
            if(dialetString.contains("DB2")){
                value= DbDialectEnum.DB2.getCode();
            }else if(dialetString.contains("SQL Server")){
                value=DbDialectEnum.SQLSERVER.getCode();
            }else if(dialetString.contains("Oracle")){
                value=DbDialectEnum.ORACLE.getCode();
            }else if(dialetString.contains("MySQL")){
                value=DbDialectEnum.MYSQL.getCode();
            }else if(dialetString.contains("DM DBMS")){
                value=DbDialectEnum.ORACLE.getCode();
            }else if(dialetString.contains("Zenith")){
                value=DbDialectEnum.ORACLE.getCode();
            }else if(dialetString.contains("PostgreSQL")){
                value=DbDialectEnum.POSTGRESQL.getCode();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }
}
