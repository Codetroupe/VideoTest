package com.example.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.play.DataInter;
import com.example.test.play.ReceiverGroupManager;
import com.kk.taurus.playerbase.entity.DataSource;
import com.kk.taurus.playerbase.receiver.ReceiverGroup;
import com.kk.taurus.playerbase.render.AspectRatio;
import com.kk.taurus.playerbase.render.IRender;
import com.kk.taurus.playerbase.widget.BaseVideoView;


import kale.adapter.item.AdapterItem;


/**
 * @author Jack Tony
 * @date 2015/5/15
 */
public class VideoItem implements AdapterItem<ViewPageModel> {

    private int mOldImageUrl = 0;

    private BaseVideoView mIv;
//    private VideoView mIv;
    private ImageItemCallback mCallback;


    private Context mContext;

    public VideoItem(Context context) {
        this.mContext = context;
    }

    /**
     * @param callback 一般的点击事件可以在内部处理，如果需要通知到外部的事件。可以通过构造函数传入监听器，
     *                 或者是通过public方法set监听器。
     */
    public VideoItem(ImageItemCallback callback) {
        mCallback = callback;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.adapter_item_view_video;
    }

    @Override
    public void bindViews(@NonNull View root) {
        mIv =  root.findViewById(R.id.baseVideoView);
    }

    @Override
    public void setViews() {
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "is clicked", Toast.LENGTH_SHORT).show();
                if (mCallback != null) {
                    mCallback.onImageClick(view);
                }
            }
        });
    }

    @Override
    public void handleData(ViewPageModel model, int position) {



        //网络视频
//        String videoUrl = model.content ;
//        Uri uri = Uri.parse( videoUrl);
//        //设置视频控制器
//        mIv.setMediaController(new MediaController(mContext));
//        //播放完成回调
////      mIv.setOnCompletionListener( new MyPlayerOnCompletionListener());
//        //设置视频路径
//        mIv.setVideoURI(uri);
//        //开始播放视频
//        mIv.start();




        ReceiverGroup mReceiverGroup = ReceiverGroupManager.get().getReceiverGroup(mContext);
        mReceiverGroup.getGroupValue().putBoolean(DataInter.Key.KEY_NETWORK_RESOURCE, true);
        mIv.setReceiverGroup(mReceiverGroup);


        mIv.setAspectRatio(AspectRatio.AspectRatio_FIT_PARENT);
        mIv.setRenderType(IRender.RENDER_TYPE_TEXTURE_VIEW);

        DataSource dataSource = new DataSource(model.content);
        mIv.stop();
        mIv.setDataSource(dataSource);
        mIv.start();

    }

    /**
     * 作为item的回调可以放一个内部类在这里，然后从外部传入callback的实现。
     *
     * 为什么做空实现，而不是一个接口呢？如果item被多个页面复用了，而且多个页面接收到的回调是不同的，
     * 那么别的页面可以针对性的实现某方法，如onImageClick()，不用全部实现。
     */
    public static class ImageItemCallback {

        /**
         * 这个名字一定要取得和item中具体的事务有关，不要叫的太抽象。比如onClick就不好了。
         */
        public void onImageClick(View view) {
            // do nothing
        }

        public void onViewChanged() {
            // do nothing
        }

        public void otherListener() {
            // do noting
        }
    }










}
