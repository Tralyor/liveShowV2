package org.liveshow.service;

import org.liveshow.entity.Tuser;

public interface  UserService {
    Tuser queryHasUser(String username, String password);
    Tuser queryUserByUserId(String uid);
    int updateUser(Tuser tuser);
}
