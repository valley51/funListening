package com.funlisten.business.play.model.bean;

import com.funlisten.base.bean.ZYIBaseBean;
import com.funlisten.business.album.model.bean.ZYAlbumDetail;
import com.funlisten.business.play.ZYPlayService;

/**
 * Created by ZY on 17/7/10.
 */

public class ZYPlay implements ZYIBaseBean {

    public ZYAlbumDetail albumDetail;

    public ZYAudio audio;

    public ZYPlay(ZYAlbumDetail albumDetail, ZYAudio audio) {
        this.albumDetail = albumDetail;
        this.audio = audio;
    }
}
