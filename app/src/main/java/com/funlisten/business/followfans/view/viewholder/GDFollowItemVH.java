package com.funlisten.business.followfans.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.funlisten.R;
import com.funlisten.base.viewHolder.ZYBaseViewHolder;
import com.funlisten.business.followfans.contract.ZYFollowOrUnFollowContract;
import com.funlisten.business.user.model.ZYUserList;
import com.funlisten.thirdParty.image.ZYGlideImageLoader;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by gd on 2017/7/13.
 */

public class GDFollowItemVH extends ZYBaseViewHolder<ZYUserList> {
    @Bind(R.id.head_image)
    ImageView headImage;

    @Bind(R.id.nick_name)
    TextView nickName;

    @Bind(R.id.album_count)
    TextView albumCount;

    @Bind(R.id.fans)
    TextView fans;

    @Bind(R.id.follow_line)
    LinearLayout followLine;

    @Bind(R.id.icon_attention)
    ImageView iconAttention;

    @Bind(R.id.follow_tv)
    TextView followTv;

    public  static ZYFollowOrUnFollowContract followOrUnFollowContract;
    @Override
    public void updateView(ZYUserList data, int position) {
        new ZYGlideImageLoader().loadCircleImage(mContext,headImage,data.user.getAvatarUrl());
        nickName.setText(data.user.getNickname()+"");
        albumCount.setText("专辑："+data.user.getAlbumCount());
        fans.setText("粉丝："+data.user.getFans());
        followLine.setTag(data);
        changeColor(data.followStatus);
    }

    @OnClick({R.id.follow_line})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.follow_line:
                ZYUserList userList = (ZYUserList) view.getTag();
                if("be_follow".equals(userList.followStatus)){
                    userList.followStatus ="no_follow";
                    if(followOrUnFollowContract != null)followOrUnFollowContract.onUnFollow(userList);
                }else {
                    userList.followStatus ="be_follow";
                    if(followOrUnFollowContract != null)followOrUnFollowContract.onFollow(userList);
                }
                changeColor(userList.followStatus);
                break;
        }
    }

    private void changeColor(String followStatus){
        if("be_follow".equals(followStatus)){
            iconAttention.setImageResource(R.drawable.icon_attention_pre_n);
            followTv.setTextColor(mContext.getResources().getColor(R.color.c1));
            followTv.setText("已关注");
        }else{
            iconAttention.setImageResource(R.drawable.icon_attention_n);
            followTv.setTextColor(mContext.getResources().getColor(R.color.c5));
            followTv.setText("加关注");
        }

    }

    @Override
    public int getLayoutResId() {
        return R.layout.gd_follow_item;
    }
}
