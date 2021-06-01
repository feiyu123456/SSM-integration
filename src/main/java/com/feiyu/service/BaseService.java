package com.feiyu.service;

import com.feiyu.base.IBaseDao;

import javax.annotation.Resource;

public class BaseService {
    @Resource(name = "baseDao")
    protected IBaseDao dao;
}
