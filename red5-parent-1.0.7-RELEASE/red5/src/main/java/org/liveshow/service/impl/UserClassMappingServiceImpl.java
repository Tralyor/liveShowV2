package org.liveshow.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dao.UserClassMappingMapper;
import org.liveshow.entity.UserClassMapping;
import org.liveshow.entity.UserClassMappingExample;
import org.liveshow.service.UserClassMappingService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserClassMappingServiceImpl implements UserClassMappingService {
    @Resource
    private UserClassMappingMapper ucMapper;

    @Override
    public boolean isMapping(String userId, Integer classId) {
        if (StringUtils.isBlank(userId) || classId == null || !StringUtils.isNumeric(classId.toString())){
            return false;
        }

        org.liveshow.entity.UserClassMappingExample example = new UserClassMappingExample();
        UserClassMappingExample.Criteria criteria = example.createCriteria();

        criteria.andUserIdEqualTo(userId);
        criteria.andClassIdEqualTo(classId);

        List<org.liveshow.entity.UserClassMapping>  res =  ucMapper.selectByExample(example);

        if ( CollectionUtils.isNotEmpty(res) ) {
            return true;
        }
        return false;
    }

    @Override
    public List<UserClassMapping> getClassUser(Integer classId) {
        if ( classId == null ) {
            return null;
        }
        UserClassMappingExample example = new UserClassMappingExample();
        UserClassMappingExample.Criteria criteria = example.createCriteria();
        criteria.andClassIdEqualTo(classId);

        return ucMapper.selectByExample(example);
    }
}
