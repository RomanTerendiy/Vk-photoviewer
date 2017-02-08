package com.inverita.testapp.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.inverita.testapp.R;
import com.inverita.testapp.model.Photo;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {

	Context mContext;
	List<Photo> photos;
	LayoutInflater mLayoutInflater;

		public PhotoAdapter(Context context, List<Photo> photos) {
			mContext = context;
			this.photos = photos;
			mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return photos.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((LinearLayout) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			View itemView = mLayoutInflater.inflate(R.layout.photo_layout, container, false);
			ImageView imageView = (ImageView) itemView.findViewById(R.id.photo_id);
			Log.d("Log", "image Glie = " + photos.get(position).getSrc());
			Log.d("ggg", "image Glie = " + position);
			Glide.with(mContext)
					.load(photos.get(position).getSrc())
					.into(imageView);

			container.addView(itemView);

			return itemView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((LinearLayout) object);
		}
}
