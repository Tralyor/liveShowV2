package org.liveshow.service.resolver;

import org.liveshow.chat.WebScoket;

public interface ContentResolver {
    void resolve(String msgJson,WebScoket webSocket);
}
