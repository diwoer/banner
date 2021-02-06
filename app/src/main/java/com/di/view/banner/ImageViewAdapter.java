package com.di.view.banner;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageViewAdapter extends BannerAdapter<BannerImage, ImageView> {

    public ImageViewAdapter(Context context) {
        super(context);
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
        data.setImage(contentView);
    }

}
