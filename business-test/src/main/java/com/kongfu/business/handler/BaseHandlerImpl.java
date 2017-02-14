package com.kongfu.business.handler;

import javax.annotation.PostConstruct;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BaseHandlerImpl<T> extends SqlSessionDaoSupport implements BaseHandler<T> {

    private String saveMethod;

    @PostConstruct
    public void initData() {
        // 根据反射得到泛型的实现类
        this.saveMethod = "com.kongfu.dao.channel.mapper.ChannelDetailMapper" + ".insertSelective";
    }

    @Override
    public int saveObject(T t) {
        // TODO Auto-generated method stub
        return getSqlSession().insert(saveMethod);
    }

}
