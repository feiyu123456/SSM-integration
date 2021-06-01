package com.feiyu.base;

import java.util.List;

public interface IBaseDao {

    <T, K> List<T> list(K queryParam, String statementId);

}
