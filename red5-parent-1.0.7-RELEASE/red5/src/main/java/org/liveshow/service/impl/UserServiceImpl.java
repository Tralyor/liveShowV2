package org.liveshow.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dao.TuserMapper;
import org.liveshow.entity.Tuser;
import org.liveshow.entity.TuserExample;
import org.liveshow.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserServiceImpl implements UserService{

    @Resource
    TuserMapper tuserMapper;

    @Override
    public Tuser queryHasUser(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return null;
        }
        TuserExample tuserExample = new TuserExample();
        TuserExample.Criteria criteria = tuserExample.createCriteria();
        criteria.andUserIdEqualTo(username);
        criteria.andPasswordEqualTo(password);

        List<Tuser> res = tuserMapper.selectByExample(tuserExample);

        if (CollectionUtils.isNotEmpty(res)) {
            return res.get(0);
        }
        return null;
    }

    @Override
    public Tuser queryUserByUserId(String uid) {
        if (StringUtils.isBlank(uid) ) {
            return null;
        }
        TuserExample tuserExample = new TuserExample();
        TuserExample.Criteria criteria = tuserExample.createCriteria();
        criteria.andUserIdEqualTo(uid);
        List<Tuser> res = tuserMapper.selectByExample(tuserExample);

        if (CollectionUtils.isNotEmpty(res)) {
            return res.get(0);
        }
        return null;
    }

    @Override
    public int updateUser(Tuser tuser) {
        return tuserMapper.updateByPrimaryKey(tuser);
    }
}
