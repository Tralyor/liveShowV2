package org.liveshow.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dao.TclassMapper;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.TclassExample;
import org.liveshow.service.TClassService;
import org.red5.server.messaging.IFilter;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TClassServiceImpl implements TClassService {
    public static final String sqlUpdateTemplate = "update tclass set class_name='%s', class_intro='%s' where id='%s'";
    @Resource
    TclassMapper tclassMapper;

    @Override
    public Tclass queryTClassById(Integer id) {
        if ( id == null || !StringUtils.isNumeric(id.toString())) {
            return null;
        }
        TclassExample example = new TclassExample();
        TclassExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);

        List<Tclass> res = tclassMapper.selectByExample(example);

        if (CollectionUtils.isNotEmpty(res)) {
            return res.get(0);
        }

        return null;
    }

    @Override
    public List<Tclass> queryClassByIds(List<Integer> classIds) {
        TclassExample example = new TclassExample();
        TclassExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(classIds);

        return tclassMapper.selectByExample(example);
    }

    @Override
    public List<Tclass> queryClassByCreatorId(String userId) {
        if ( StringUtils.isBlank(userId)) {
            return null;
        }
        TclassExample example = new TclassExample();
        example.setOrderByClause("id DESC");
        TclassExample.Criteria criteria = example.createCriteria();
        criteria.andCreaterIdEqualTo(userId);

        return tclassMapper.selectByExample(example);
    }

    @Override
    public int addTclass(String userId, String className, String classInfo) {
        if ( StringUtils.isBlank(classInfo) || StringUtils.isBlank(classInfo) || StringUtils.isBlank(userId)) {
            return 0;
        }
        Tclass tclass = new Tclass();
        tclass.setClassName(className);
        tclass.setClassIntro(classInfo);
        tclass.setCreaterId(userId);
        tclass.setTeaching(false);

        return tclassMapper.insert(tclass);
    }

    @Override
    public boolean queryClassIsTeaching(Integer id) {
        Tclass res = queryTClassById(id);
        if ( res == null ) {
            return false;
        }

        if ( Boolean.TRUE.equals(res.getTeaching())) {
            return true;
        }

        return false;
    }

    @Override
    public void updateTeaching(Tclass tclass) {
        if ( tclass == null || tclass.getId() == null ) {
            return;
        }
        tclassMapper.updateByPrimaryKey(tclass);
    }

    @Override
    public void updateTeachInfo(Integer classId,String className, String classInfo) {
        if ( classId == null ) {
            return ;
        }
        String sql = String.format(sqlUpdateTemplate,className,classInfo,classId);
        tclassMapper.updateBySql(sql);
    }
}
