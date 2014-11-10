package com.example.tabactivity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TestActivity extends ActivityGroup implements OnItemClickListener {
	/**
	 * Tab标签
	 */
	private GridView mGridView;
	/**
	 * 普通图片id数组
	 */
	private int [] mImageIds;
	/**
	 * 高亮图片id数组
	 */
	private int [] mImageLightIds;
	/**
	 * GridView 适配器
	 */
	private ImageAdapter mImageAdapter;
	/**
	 * 装载Activity的容器
	 */
	private LinearLayout mLinearLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		mImageIds = new int[]{R.drawable.home, R.drawable.task, R.drawable.addrbook, R.drawable.longnormal};
		mImageLightIds = new int[]{R.drawable.home1, R.drawable.taskhl, R.drawable.addrbook1, R.drawable.longhightlight};
		
		mLinearLayout = (LinearLayout) findViewById(R.id.activity_group);
		mGridView = (GridView) findViewById(R.id.gridView1);
		//因为我们显示一行，列数等于数组的长度
		mGridView.setNumColumns(mImageIds.length);
		mImageAdapter = new ImageAdapter();
		mGridView.setAdapter(mImageAdapter);
		mGridView.setOnItemClickListener(this);
		
		startActivity(0);
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//点击设置高亮显示
		mImageAdapter.setImageLight(position);
		startActivity(position);
	}
	
	/**
	 * 根据position跳转到不同的Activity
	 * @param id
	 */
	private void startActivity(int position){
		//先清除容器里面的View
		mLinearLayout.removeAllViews();
		Intent intent = null;
		if(position == 0){
			intent = new Intent(TestActivity.this, Activity_01.class);
		}else if(position == 1){
			intent = new Intent(TestActivity.this, Activity_02.class);
		}else if(position == 2){
			intent = new Intent(TestActivity.this, Activity_03.class);
		}else if(position == 3){
			intent = new Intent(TestActivity.this, Activity_04.class);
		}
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		//将Activity转换成View
		View view = getLocalActivityManager().startActivity("intent", intent).getDecorView();
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,    
                LayoutParams.FILL_PARENT));
		
		//将Activity转换成的View添加到容器
		mLinearLayout.addView(view, layoutParams);
	}
	
	/**
	 * 图片适配器,没什么特别的，里面有一个设置高亮的方法比较重要
	 * @author mining
	 *
	 */
	private class ImageAdapter extends BaseAdapter{
		private int currentItem;
		
		/**
		 * 设置高亮显示
		 * @param currentItem
		 */
		public void setImageLight(int selectItem){
			this.currentItem = selectItem;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mImageIds.length;
		}

		@Override
		public Object getItem(int position) {
			return mImageIds[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = new ImageView(TestActivity.this);
			}
			if(position == currentItem){
				convertView.setBackgroundResource(mImageLightIds[position]);
			}else{
				convertView.setBackgroundResource(mImageIds[position]);
			}
			
			return convertView;
		}
		
		
	}



}
