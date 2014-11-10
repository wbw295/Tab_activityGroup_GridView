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
	 * Tab��ǩ
	 */
	private GridView mGridView;
	/**
	 * ��ͨͼƬid����
	 */
	private int [] mImageIds;
	/**
	 * ����ͼƬid����
	 */
	private int [] mImageLightIds;
	/**
	 * GridView ������
	 */
	private ImageAdapter mImageAdapter;
	/**
	 * װ��Activity������
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
		//��Ϊ������ʾһ�У�������������ĳ���
		mGridView.setNumColumns(mImageIds.length);
		mImageAdapter = new ImageAdapter();
		mGridView.setAdapter(mImageAdapter);
		mGridView.setOnItemClickListener(this);
		
		startActivity(0);
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//������ø�����ʾ
		mImageAdapter.setImageLight(position);
		startActivity(position);
	}
	
	/**
	 * ����position��ת����ͬ��Activity
	 * @param id
	 */
	private void startActivity(int position){
		//��������������View
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
		
		//��Activityת����View
		View view = getLocalActivityManager().startActivity("intent", intent).getDecorView();
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,    
                LayoutParams.FILL_PARENT));
		
		//��Activityת���ɵ�View��ӵ�����
		mLinearLayout.addView(view, layoutParams);
	}
	
	/**
	 * ͼƬ������,ûʲô�ر�ģ�������һ�����ø����ķ����Ƚ���Ҫ
	 * @author mining
	 *
	 */
	private class ImageAdapter extends BaseAdapter{
		private int currentItem;
		
		/**
		 * ���ø�����ʾ
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
