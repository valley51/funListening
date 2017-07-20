package com.funlisten.service.downNet.down;

import com.funlisten.base.event.ZYEventDowloadUpdate;
import com.funlisten.utils.ZYLog;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.SoftReference;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by ZY on 17/3/17.
 */

public class ZYDownloadSubscriber<T> extends Subscriber<T> implements ZYDownloadProgressListener {

    private ZYIDownBase downEntity;

    public ZYDownloadSubscriber(ZYIDownBase downEntity) {
        this.downEntity = downEntity;
    }

    @Override
    public void onStart() {
        ZYLog.e(getClass().getSimpleName(), "onStart: " + downEntity.getUrl());
        downEntity.setState(ZYDownState.START);
        EventBus.getDefault().post(new ZYEventDowloadUpdate(downEntity));
    }

    @Override
    public void onCompleted() {
        ZYLog.e(getClass().getSimpleName(), "onCompleted: " + downEntity.getCurrent() + ":" + downEntity.getTotal());
        ZYDownloadManager.getInstance().removeTask(downEntity.getId());
        downEntity.setState(ZYDownState.FINISH);
        downEntity.setCurrent(downEntity.getTotal());
        downEntity.update();
        EventBus.getDefault().post(new ZYEventDowloadUpdate(downEntity));
    }

    @Override
    public void onError(Throwable e) {
        ZYLog.e(getClass().getSimpleName(), "onError: " + e.getMessage());
        ZYDownloadManager.getInstance().removeTask(downEntity.getId());
        downEntity.setState(ZYDownState.ERROR);
        downEntity.update();
        EventBus.getDefault().post(new ZYEventDowloadUpdate(downEntity));
    }

    @Override
    public void onNext(T t) {
    }

    @Override
    public void update(long current, long total, boolean done) {
        downEntity.setTotal(total);
        if (done) {
            ZYLog.e(getClass().getSimpleName(), "update: " + current + ":" + total + ":" + done);
        } else {
            if (current % 100 * 1024 == 0) {
                ZYLog.e(getClass().getSimpleName(), "update: " + current + ":" + total + ":" + done);
            }
        }
        if (done) {
            downEntity.setCurrent(total);
            downEntity.setState(ZYDownState.FINISH);
        } else {
            downEntity.setCurrent(current);
            downEntity.setState(ZYDownState.DOWNING);
        }
        downEntity.update();
        EventBus.getDefault().post(new ZYEventDowloadUpdate(downEntity));
    }
}
