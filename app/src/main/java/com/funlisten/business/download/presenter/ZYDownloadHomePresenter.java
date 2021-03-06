package com.funlisten.business.download.presenter;

import com.funlisten.base.mvp.ZYListDataPresenter;
import com.funlisten.business.download.contract.ZYDownloadHomeContract;
import com.funlisten.business.download.model.ZYDownloadModel;
import com.funlisten.business.download.model.bean.ZYDownloadEntity;
import com.funlisten.service.net.ZYNetSubscriber;
import com.funlisten.service.net.ZYNetSubscription;
import com.funlisten.utils.ZYLog;

import java.util.List;

/**
 * Created by ZY on 17/7/12.
 */

public class ZYDownloadHomePresenter extends ZYListDataPresenter<ZYDownloadHomeContract.IView, ZYDownloadModel, ZYDownloadEntity> implements ZYDownloadHomeContract.IPresenter {

    public ZYDownloadHomePresenter(ZYDownloadHomeContract.IView view) {
        super(view, new ZYDownloadModel());
    }

    @Override
    protected void loadData() {
        try {
            mDataList.clear();
            ZYDownloadEntity downloadingAudio = mModel.queryAudioByNotFinishedState();
            List<ZYDownloadEntity> audios = mModel.getAlbums();
            if (audios != null && audios.size() > 0) {
                mDataList.addAll(audios);
            } else {
                if (downloadingAudio != null) {
                    mDataList.add(new ZYDownloadEntity());
                }
            }
            if (mDataList.size() <= 0) {
                mView.showEmpty();
            } else {
                mView.refresh(downloadingAudio);
                mView.showList(false);
            }
        } catch (Exception e) {
            mView.showError();
            ZYLog.e(getClass().getSimpleName(), "error: " + e.getMessage());
        }
    }

    @Override
    public void setAsc(boolean asc) {

    }
}
