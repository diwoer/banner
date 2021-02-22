package com.di.view.banner.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.di.view.banner.data.BannerImage;
import com.di.view.banner.util.ImageLoader;

public class ImageViewAdapter extends BannerAdapter<BannerImage, ImageView> {

    private ImageLoader imageLoader;

    public ImageViewAdapter(Context context) {
        super(context);
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    public ImageView createView() {
        ImageView imageView = new ImageView(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    @Override
    public void setViews(ImageView contentView, BannerImage data, int position) {
        imageLoader.load(contentView, data);
    }

}
