package org.liveshow.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.liveshow.dao.TclassMapper;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.TclassExample;
import org.liveshow.service.TClassService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TClassServiceImpl implements TClassService {
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
}
