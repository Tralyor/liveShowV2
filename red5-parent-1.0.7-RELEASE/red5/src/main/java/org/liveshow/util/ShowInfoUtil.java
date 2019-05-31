package org.liveshow.util;

import org.liveshow.dto.Show;

public class ShowInfoUtil {
    public static void buildShowInfo(Show show , boolean success, String msg, Integer state, Object data) {
        show.setSuccess(success);
        show.setMessage(msg);
        show.setData(data);
        show.setState(state);
    }
}
