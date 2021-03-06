package com.funlisten.business.myorder.view.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.funlisten.R;
import com.funlisten.base.viewHolder.ZYBaseViewHolder;
import com.funlisten.business.favorite.ZYFavorite;
import com.funlisten.business.order.ZYOrder;
import com.funlisten.thirdParty.image.ZYImageLoadHelper;
import com.funlisten.utils.ZYDateUtils;

import butterknife.Bind;

/**
 * Created by gd on 2017/7/22.
 */

public class ZYMyOrderVH extends ZYBaseViewHolder<ZYFavorite> {

    @Bind(R.id.coverUrl)
    ImageView coverUrl;

    @Bind(R.id.pay_icon)
    ImageView payIcon;

    @Bind(R.id.pay_tv)
    TextView payTv;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.textSubTitle)
    TextView textSubTitle;

    @Bind(R.id.play_count)
    TextView playCount;

    @Bind(R.id.audioCount)
    TextView audioCount;

    @Bind(R.id.text_time)
    TextView textTime;

    @Override
    public void updateView(ZYFavorite data, int position) {
        ZYImageLoadHelper.getImageLoader().loadImage(mContext, coverUrl, data.album.coverUrl);
        showPay("paid".equals(data.album.costType));
        title.setText(TextUtils.isEmpty(data.album.name) ? "" : data.album.name);
        textSubTitle.setText(TextUtils.isEmpty(data.album.title) ? "" : data.album.title);
        playCount.setText(data.album.playCount + "");
        audioCount.setText("更新至" + data.album.audioCount + "集");
        textTime.setText(ZYDateUtils.getTimeString(data.gmtCreate, ZYDateUtils.YYMMDDHHMM24, ZYDateUtils.HHMM24));
    }

    private void showPay(boolean isShow) {
        if (isShow) {
            payIcon.setVisibility(View.VISIBLE);
            payTv.setVisibility(View.VISIBLE);
        } else {
            payIcon.setVisibility(View.GONE);
            payTv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.gd_order_item;
    }
}
