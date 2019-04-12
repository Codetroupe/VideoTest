package com.example.test.blurdialogfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.adapter.ImageItem;
import com.example.test.adapter.VideoItem;
import com.example.test.adapter.ViewPageItemConstant;
import com.example.test.adapter.ViewPageModel;
import com.example.test.blurdialogfragment.lib.SupportBlurDialogFragment;

import java.util.ArrayList;
import java.util.List;

import kale.adapter.CommonPagerAdapter;
import kale.adapter.item.AdapterItem;


/**
 * Simple fragment with blur effect behind.
 */
public class SampleSupportDialogFragment extends SupportBlurDialogFragment {

    /**
     * Bundle key used to start the blur dialog with a given scale factor (float).
     */
    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";

    /**
     * Bundle key used to start the blur dialog with a given blur radius (int).
     */
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";

    /**
     * Bundle key used to start the blur dialog with a given dimming effect policy.
     */
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";

    /**
     * Bundle key used to start the blur dialog with a given debug policy.
     */
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";

    /**
     * Bundle key used for blur effect on action bar policy.
     */
    private static final String BUNDLE_KEY_BLURRED_ACTION_BAR = "bundle_key_blurred_action_bar";

    /**
     * Bundle key used for RenderScript
     */
    private static final String BUNDLE_KEY_USE_RENDERSCRIPT = "bundle_key_use_renderscript";

    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;
    private boolean mBlurredActionBar;
    private boolean mUseRenderScript;

    /**
     * Retrieve a new instance of the sample fragment.
     *
     * @param radius            blur radius.
     * @param downScaleFactor   down scale factor.
     * @param dimming           dimming effect.
     * @param debug             debug policy.
     * @param mBlurredActionBar blur affect on actionBar policy.
     * @param useRenderScript   use of RenderScript
     * @return well instantiated fragment.
     */
    public static SampleSupportDialogFragment newInstance(int radius,
                                                          float downScaleFactor,
                                                          boolean dimming,
                                                          boolean debug,
                                                          boolean mBlurredActionBar,
                                                          boolean useRenderScript) {
        SampleSupportDialogFragment fragment = new SampleSupportDialogFragment();
        Bundle args = new Bundle();
        args.putInt(
                BUNDLE_KEY_BLUR_RADIUS,
                radius
        );
        args.putFloat(
                BUNDLE_KEY_DOWN_SCALE_FACTOR,
                downScaleFactor
        );
        args.putBoolean(
                BUNDLE_KEY_DIMMING,
                dimming
        );
        args.putBoolean(
                BUNDLE_KEY_DEBUG,
                debug
        );
        args.putBoolean(
                BUNDLE_KEY_BLURRED_ACTION_BAR,
                mBlurredActionBar
        );
        args.putBoolean(
                BUNDLE_KEY_USE_RENDERSCRIPT,
                useRenderScript
        );

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Bundle args = getArguments();
        mRadius = args.getInt(BUNDLE_KEY_BLUR_RADIUS);
        mDownScaleFactor = args.getFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR);
        mDimming = args.getBoolean(BUNDLE_KEY_DIMMING);
        mDebug = args.getBoolean(BUNDLE_KEY_DEBUG);
        mBlurredActionBar = args.getBoolean(BUNDLE_KEY_BLURRED_ACTION_BAR);
        mUseRenderScript = args.getBoolean(BUNDLE_KEY_USE_RENDERSCRIPT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment, null);



//        TextView label = ((TextView) view.findViewById(R.id.textView));
//        label.setMovementMethod(LinkMovementMethod.getInstance());
//        Linkify.addLinks(label, Linkify.WEB_URLS);



        ViewPager viewPager = view.findViewById(R.id.goods_info_video_image);
        viewPager.setOffscreenPageLimit(5);
        final List<ViewPageModel> data = loadData();
        viewPager.setAdapter(updateAdapter(data));
        builder.setView(view);
        return builder.create();
    }

    @Override
    protected boolean isDebugEnable() {
        return mDebug;
    }

    @Override
    protected boolean isDimmingEnable() {
        return mDimming;
    }

    @Override
    protected boolean isActionBarBlurred() {
        return mBlurredActionBar;
    }

    @Override
    protected float getDownScaleFactor() {
        return mDownScaleFactor;
    }

    @Override
    protected int getBlurRadius() {
        return mRadius;
    }

    @Override
    protected boolean isRenderScriptEnable() {
        return mUseRenderScript;
    }





    //添加Adapter适配类型
    public static List<ViewPageModel> loadData( ) {

        List<ViewPageModel> list = new ArrayList<>();
        ViewPageModel videoModel = new ViewPageModel();
        videoModel.type = ViewPageItemConstant.VIDEO;
        videoModel.content = "https://mov.bn.netease.com/open-movie/nos/mp4/2017/05/31/SCKR8V6E9_hd.mp4";
        list.add(videoModel);


        ViewPageModel imageModel = new ViewPageModel();
        imageModel.type = ViewPageItemConstant.IMAGE;
        imageModel.content = "http://img17.3lian.com/d/file/201702/14/d89da51ef66b01809768ae0b3328bd89.jpg";
        list.add(imageModel);

        ViewPageModel imageMode2= new ViewPageModel();
        imageMode2.type = ViewPageItemConstant.IMAGE;
        imageMode2.content = "http://img17.3lian.com/d/file/201702/14/d89da51ef66b01809768ae0b3328bd89.jpg";
        list.add(imageMode2);
        return list;
    }


//给Adapter赋值
    /**
     * 正常加载
     */
    private CommonPagerAdapter<ViewPageModel> updateAdapter(List<ViewPageModel> data) {
        return new CommonPagerAdapter<ViewPageModel>(data) {
            @Override
            public Object getItemType(ViewPageModel demoModel) {
                return demoModel.type;
            }
            @NonNull
            @Override
            public AdapterItem createItem(Object type) {
                switch (((int) type)) {
                    case ViewPageItemConstant.VIDEO:
                        return new VideoItem(getContext());
                    case ViewPageItemConstant.IMAGE:
                        return new ImageItem();

                    default:
                        throw new IllegalArgumentException("type类型未定义");
                }
            }
        };
    }
}
