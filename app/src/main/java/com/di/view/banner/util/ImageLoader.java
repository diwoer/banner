package com.di.view.banner.util;

import android.widget.ImageView;

import com.di.view.banner.data.BannerImage;

public abstract class ImageLoader {

    public abstract void load(ImageView imageView, BannerImage bannerImage);
}
