package com.funlisten.business.play;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.funlisten.base.player.FZAudioPlayer;
import com.funlisten.base.player.FZIPlayer;
import com.funlisten.business.play.model.FZAudionPlayEvent;
import com.funlisten.business.play.model.ZYPlayManager;
import com.funlisten.business.play.model.bean.ZYAudio;
import com.funlisten.business.play.model.bean.ZYPlayHistory;
import com.funlisten.utils.ZYLog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by ZY on 17/7/16.
 */

public class ZYPlayService extends Service implements FZIPlayer.PlayerCallBack {

    //顺序播放
    public static final int PLAY_LOOP_TYPE = 1;

    //随机
    public static final int PLAY_RANDOM_TYPE = 2;

    int playType = PLAY_LOOP_TYPE;

    //正在播放音频
    ZYAudio mCurrentPlayAudio;

    //缓存的音频数据列表
    List<ZYAudio> mAudios = new ArrayList<ZYAudio>();

    //音频播放器
    FZAudioPlayer audioPlayer;

    Handler handler = new Handler();

    UpdateProgressRunnable updateProgress;

    CompositeSubscription mSubscription;

    @Override
    public void onCreate() {
        super.onCreate();
        updateProgress = new UpdateProgressRunnable();
        ZYLog.e(getClass().getSimpleName(), "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new AudioBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ZYLog.e(getClass().getSimpleName(), "onStartCommand");
        return START_STICKY;
    }

    public void play(ZYAudio currenPlayAudio, List<ZYAudio> audios) {
        setAudios(audios);
        mCurrentPlayAudio = currenPlayAudio;
        play();
    }

    public void setAudios(List<ZYAudio> audios) {
        mAudios.clear();
        mAudios.addAll(audios);
    }


    private void play() {
        if (mCurrentPlayAudio.isFree() || mCurrentPlayAudio.isAudition() || mCurrentPlayAudio.isBuy()) {
            ZYPlayHistory.saveByAudio(mCurrentPlayAudio, 0);
            if (audioPlayer == null) {
                audioPlayer = new FZAudioPlayer(getApplicationContext(), "ZYPlayService");
                audioPlayer.setPlayerCallBack(this);
            }
            audioPlayer.stop();
            stopProgressUpdate();
            sendCallBack(ZYPlayManager.STATE_PREPARING, "播放器初使化中");
            audioPlayer.open(mCurrentPlayAudio.fileUrl, 0);
            reportAudioPlay();
        } else {
            audioPlayer.stop();
            sendCallBack(ZYPlayManager.STATE_NEED_BUY_PAUSED, "暂停播放,收费视频");
        }
    }

    public void startOrPuase() {
        if (audioPlayer.isPlaying()) {
            puase();
        } else {
            start();
        }
    }

    public void start() {
        if (!audioPlayer.isPlaying()) {
            startProgressUpdate(0);
            audioPlayer.start(true);
            sendCallBack(ZYPlayManager.STATE_PLAYING, "正在播放");
        }
    }

    public void puase() {
        if (audioPlayer.isPlaying()) {
            stopProgressUpdate();
            audioPlayer.pause();
            sendCallBack(ZYPlayManager.STATE_PAUSED, "暂停播放");
        }
    }

    public void seekTo(int currentProgress, int totalProgress) {
        float progress = (float) currentProgress / (float) totalProgress;
        audioPlayer.seekTo((int) (progress * audioPlayer.getDuration()));
    }

    @Override
    public boolean onCallBack(String tag, int what, String msg, FZIPlayer player) {
        ZYLog.e(getClass().getSimpleName(), "FZAudioPlaysevice-onCallBack: " + what + ":" + msg);

        switch (what) {
            case FZIPlayer.PLAYER_PREPARED:
                startProgressUpdate(0);
                sendCallBack(ZYPlayManager.STATE_PREPARED, "准备播放");
                break;
            case FZIPlayer.PLAYER_BUFFERING_END:
                startProgressUpdate(0);
                sendCallBack(ZYPlayManager.STATE_BUFFERING_END, "缓冲结束-开始播放");
                break;
            case FZIPlayer.PLAYER_BUFFERING_START:
                stopProgressUpdate();
                sendCallBack(ZYPlayManager.STATE_BUFFERING_START, "缓冲开始-等待播放");
                break;
            case FZIPlayer.PLAYER_COMPLETIIONED:
                stopProgressUpdate();
                playNext();
                break;
            case FZIPlayer.PLAYER_ERROR_SYSTEM:
            case FZIPlayer.PLAYER_ERROR_UNKNOWN:
            case FZIPlayer.PLAYER_ERROR_NET:
                stopProgressUpdate();
                sendCallBack(ZYPlayManager.STATE_ERROR, "播放出错");
                break;
        }
        return true;
    }

    private void playNext() {
        int curPosition = mAudios.indexOf(mCurrentPlayAudio);
        int position = 0;
        if (playType == PLAY_LOOP_TYPE) {
            position = curPosition++;
        } else {
            position = new Random(mAudios.size()).nextInt();
        }
        if (position < mAudios.size() - 1) {
            sendCallBack(ZYPlayManager.STATE_PREPARING_NEXT, "准备播放下一集");
            //缓存中还有
            mCurrentPlayAudio = mAudios.get(position);
            if (mCurrentPlayAudio.isFree() || mCurrentPlayAudio.isAudition() || mCurrentPlayAudio.isBuy()) {
                play();
            } else {
                audioPlayer.stop();
                sendCallBack(ZYPlayManager.STATE_NEED_BUY_PAUSED, "暂停播放,收费视频");
            }
        } else {
            sendCallBack(ZYPlayManager.STATE_COMPLETED, "列表播放完成");
        }
    }

    public boolean isPlaying() {
        return audioPlayer.isPlaying();
    }

    /**
     * 开始进度改变
     *
     * @param delayedTime 启动延迟时间
     */
    private void startProgressUpdate(int delayedTime) {
        if (updateProgress == null) {
            return;
        }

        stopProgressUpdate();
        if (delayedTime > 0) {
            handler.postDelayed(updateProgress, delayedTime);
        } else {
            handler.post(updateProgress);
        }
    }

    /**
     * 停止进度改变
     */
    private void stopProgressUpdate() {
        if (updateProgress != null) {
            handler.removeCallbacks(updateProgress);
        }
    }

    private void reportAudioPlay() {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("audio_id", mCurrentPlayAudio.getAudioId());
//        getSubscription().add(FZNetBaseSubscription.subscription(FZNetManager.shareInstance().getApi().reportAudioPlay(params), new FZNetBaseSubscriber() {
//
//        }));
    }

    public int getPlayType() {
        return playType;
    }

    public void setPlayType(int playType) {
        this.playType = playType;
    }

    private void sendCallBack(int state, String msg) {
        FZAudionPlayEvent playEvent = new FZAudionPlayEvent(mCurrentPlayAudio, state, msg, audioPlayer.getCurrentPosition(), audioPlayer.getDuration());
        EventBus.getDefault().post(playEvent);
    }

    private CompositeSubscription getSubscription() {
        if (mSubscription == null) {
            mSubscription = new CompositeSubscription();
        }
        return mSubscription;
    }

    private void unsubscribe() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }

    class UpdateProgressRunnable implements Runnable {
        @Override
        public void run() {
            if (audioPlayer != null) {
                sendCallBack(ZYPlayManager.STATE_PLAYING, "正在播放");
                handler.postDelayed(updateProgress, 500);
            }
        }
    }

    public class AudioBinder extends Binder {
        //返回Service对象
        public ZYPlayService getService() {
            return ZYPlayService.this;
        }
    }
}
