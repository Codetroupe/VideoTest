package com.example.test;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SlidingMenu mSlidingMenu;
    private LeftFragment mLeftFragment;
    private RightFragment mRightFragment;
    private CenterFragment mCenterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initView();
    }

    private void findView() {
        mSlidingMenu = (SlidingMenu) findViewById(R.id.sm);
    }

    private void initView() {
        View leftView = View.inflate(this, R.layout.frame_left, null);
        View rightView = View.inflate(this, R.layout.frame_right, null);
        View centerView = View.inflate(this, R.layout.frame_center, null);

        mSlidingMenu.setView(leftView, rightView, centerView, 250, 250);

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        mLeftFragment = new LeftFragment();
        transaction.replace(R.id.fl_left, mLeftFragment);
        mRightFragment = new RightFragment();
        transaction.replace(R.id.fl_right, mRightFragment);
        mCenterFragment = new CenterFragment();
        transaction.replace(R.id.fl_center, mCenterFragment);
        transaction.commit();

        mCenterFragment
                .setOnViewPagerChangeListener(new CenterFragment.OnViewPagerChangeListener() {

                    @Override
                    public void onPageChage(int position) {
                        if (mCenterFragment.isFirst()) {
                            mSlidingMenu.setWhichSideCanShow(true, false);
                        } else if (mCenterFragment.isLast()) {
                            mSlidingMenu.setWhichSideCanShow(false, true);
                        } else {
                            mSlidingMenu.setWhichSideCanShow(false, false);
                        }
                    }
                });
    }

    public void showLeftViewToogle() {
        mSlidingMenu.showLeftViewToogle();
    }

    public void showRightViewToogle() {
        mSlidingMenu.showRightViewToogle();
    }

}
