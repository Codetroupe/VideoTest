package com.example.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

public class PagerFragment extends Fragment {
	private int index;


	public PagerFragment() {
		super();
	}

	public static PagerFragment getInstance(int index) {
		PagerFragment fragment = new PagerFragment();
		fragment.setIndex(index);
		return fragment;
	}

	private void setIndex(int index) {
		this.index = index;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View view = inflater.inflate(R.layout.fragment_page, null);

		RecyclerView  recyclerView =view.findViewById(R.id.recycle_view);




		GridLayoutManager manager = new GridLayoutManager(getContext(), 3);

		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				int type =2;//获得返回值
				//LogUtils.e("TAG","type:"+type);
				if (type == 1 || type == 2 || type == 3) {
					return 2;//占两格
				} else {
					return 1;
				}
			}
		});



//		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {


//			@Override
//			public int getSpanSize(int position) {
//				SearchResultBean bean = mDataList.get(position);
//				if (bean.getType() == 0) {
//					return 2;
//				} else if(bean.getType() == 1){
//					return 1;
//				}else{
//					return 1;
//				}
//			}
//		});
		recyclerView.setLayoutManager(manager);
//		recyclerView.setItemAnimator(new DefaultItemAnimator());
//		mAdapter = new HomeAdapter(this, mDataList);
//		recyclerView.setAdapter(mAdapter);
		return view;
	}



}
