package org.liveshow.controller;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.liveshow.entity.LearnRecord;
import org.liveshow.entity.Tclass;
import org.liveshow.entity.TeahRecor;
import org.liveshow.entity.Tuser;
import org.liveshow.face.FaceRecoUtil;
import org.liveshow.service.LearnRecordService;
import org.liveshow.service.TClassService;
import org.liveshow.service.TeachRecordService;
import org.liveshow.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.*;

@Controller
@RequestMapping("/face")
public class FaceRecoController {

    @Resource
    private TClassService tClassService;
    @Resource
    private LearnRecordService learnRecordService;
    @Resource
    private TeachRecordService teachRecordService;

    private static final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1, 60,TimeUnit.SECONDS,queue );

    @RequestMapping("/reco")
    public void doReco(HttpServletRequest request, String data, Integer roomId) {
        Base64 base64 = new Base64();
        byte[] k = base64.decode(data.substring("data:image/png;base64,"
                .length()));
        Tuser tuser = SessionUtil.getUserInSession(request);
        TeahRecor teahRecor = teachRecordService.getReMaxClassNum(roomId);
        if ( tuser == null || teahRecor == null || tuser.getImgAddress() == null) {
            return;
        }

        AsynTask asynTask = new AsynTask( tuser.getUserId(),teahRecor.getId(),k, learnRecordService, tuser.getImgAddress());
        threadPoolExecutor.execute(asynTask);
    }


}

class AsynTask  implements Runnable{

    private LearnRecordService learnRecordService;
    private String userPic;
    private LearnRecord learnRecord;
    private byte[] k;
    private String userId;
    private Integer techId;

    public AsynTask(String userId ,Integer techId,byte[] k, LearnRecordService learnRecordService, String userPic){
        this.k = k;
        this.learnRecordService = learnRecordService;
        this.userPic= userPic;
        this.userId = userId;
        this.techId=techId;

    }

    @Override
    public synchronized void run() {
        JSONObject jsonObject = new JSONObject(FaceRecoUtil.match(k, userPic));
        try {
            Thread.sleep(1000);
            String errMsg = jsonObject.getString("error_msg");
            LearnRecord learnRecord = learnRecordService.queryRecordByUidAndTidAndGmtOutNull(this.userId, this.techId);
            if ( learnRecord == null ) {
                return;
            }
             if ("SUCCESS".equals(errMsg) ) {
                JSONObject result = jsonObject.getJSONObject("result");
                if ( result == null || result.get("score") == null ) {
                    return ;
                } else {

                    double score = (Double) result.get("score");
                    //人脸相似度打与75认为同一个人
                    if ( score > 75f ) {
                        updateLearnRecordModel(learnRecord, learnRecord.getFaceRegoCount()+1, learnRecord.getFaceRegoSuccess()+1);
                    } else {
                        updateLearnRecordModel(learnRecord, learnRecord.getFaceRegoCount()+1, learnRecord.getFaceRegoSuccess());
                    }
                }
            } else {
                updateLearnRecordModel(learnRecord, learnRecord.getFaceRegoCount()+1, learnRecord.getFaceRegoSuccess());
            }
            learnRecordService.updateRecordById(learnRecord);
            System.out.println("count:"+learnRecord.getFaceRegoCount()+"success:"+learnRecord.getFaceRegoSuccess());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateLearnRecordModel(LearnRecord learnRecord , Integer count , Integer successNum) {
        learnRecord.setFaceRegoCount(count);
        learnRecord.setFaceRegoSuccess(successNum);
    }
}