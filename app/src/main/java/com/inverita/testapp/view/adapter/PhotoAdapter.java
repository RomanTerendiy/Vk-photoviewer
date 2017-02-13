package com.inverita.testapp.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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
	private SharePhoto sharePhoto;

	public interface SharePhoto {
		void sharePhoto(int position);

		void displayToolbar();
	}

	public PhotoAdapter(Context context, List<Photo> photos, SharePhoto sharePhoto) {
		mContext = context;
		this.photos = photos;
		this.sharePhoto = sharePhoto;
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
	public Object instantiateItem(final ViewGroup container, int position) {
		View itemView = mLayoutInflater.inflate(R.layout.photo_layout, container, false);
		ImageView imageView = (ImageView) itemView.findViewById(R.id.photo_id);
		Glide.with(mContext)
				.load(photos.get(position).getSrc())
				.into(imageView);
		container.addView(itemView);
		sharePhoto.sharePhoto(position);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sharePhoto.displayToolbar();
			}
		});
		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((LinearLayout) object);
	}

}