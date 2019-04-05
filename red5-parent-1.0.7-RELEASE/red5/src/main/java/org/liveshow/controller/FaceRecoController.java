package org.liveshow.controller;

import org.apache.commons.codec.binary.Base64;
import org.liveshow.face.FaceRecoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/face")
public class FaceRecoController {

    @RequestMapping("/reco")
    public void doReco(HttpServletRequest request, String data) {
        Base64 base64 = new Base64();
        byte[] k = base64.decode(data.substring("data:image/png;base64,"
                .length()));
        System.out.println(FaceRecoUtil.match(k));
    }
}
