package org.liveshow.service;

import org.liveshow.dto.AttendanceVO;
import org.liveshow.dto.AttendanceDTO;

import java.util.List;

public interface AttendanceService {
    List<AttendanceDTO> queryInfoById(Integer classId, String userId);
    AttendanceVO queryInfoByIdWrapped(Integer classId, String userId);
    AttendanceDTO queryInfoByRidAndUis(Integer recordId, String userId);
}
