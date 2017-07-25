package com.funlisten.business.album.view.viewHolder;

import android.view.View;

import com.funlisten.R;
import com.funlisten.base.viewHolder.ZYBaseViewHolder;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ZY on 17/7/5.
 */

public class ZYAlbumAudiosHeaderVH extends ZYBaseViewHolder<Object> {

    AlbumAudiosHeaderListener listener;

    public ZYAlbumAudiosHeaderVH(AlbumAudiosHeaderListener listener) {
        this.listener = listener;
    }

    @Override
    public void updateView(Object data, int position) {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.zy_view_album_audios_header;
    }

    @OnClick({R.id.textSort, R.id.textEpisode, R.id.textChoice, R.id.textDown})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textDown:
                listener.onDownloadClick();
                break;
            case R.id.textEpisode:
                break;
            case R.id.textChoice:
                listener.onChoiceClick();
                break;
            case R.id.textSort:
                listener.onSortClick();
                break;
        }
    }

    public interface AlbumAudiosHeaderListener {
        void onSortClick();

        void onChoiceClick();

        void onDownloadClick();
    }
}
