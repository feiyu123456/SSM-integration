package com.feiyu.base;

import com.feiyu.pojo.Person;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Repository("baseDao")
public class BaseDaoImpl implements IBaseDao{

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public <T, K> List<T> list(K queryParam, String statementId) {
        return sqlSessionTemplate.selectList(statementId, queryParam);
    }
}
