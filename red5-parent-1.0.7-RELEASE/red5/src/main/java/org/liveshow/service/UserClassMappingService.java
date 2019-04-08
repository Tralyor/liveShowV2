package org.liveshow.service;

import org.liveshow.entity.UserClassMapping;

import java.util.List;

public interface UserClassMappingService {
    boolean isMapping(String userId, Integer classId);
    List<UserClassMapping> getClassUser(Integer classId);
    List<UserClassMapping> getUserClass(String userId);
}
