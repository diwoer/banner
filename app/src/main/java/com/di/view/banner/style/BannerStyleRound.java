package com.di.view.banner.style;

import android.os.Build;
import android.view.View;

import com.di.view.banner.BannerLayout;

/**
 * 圆角样式
 * */
public class BannerStyleRound extends BannerStyleWrapper{

    /**
     * 圆角弧度
     * */
    private final int radius;

    public BannerStyleRound(int radius) {
        this.radius = radius;
    }

    @Override
    public void setStyle(BannerLayout layout) {
        setRoundByViewpager(layout.getViewPager(), radius);
    }

    private void setRoundByViewpager(View view, int radius) {
        ViewStyleSetter viewStyleSetter = new ViewStyleSetter(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewStyleSetter.setRound(radius);//实现圆角效果
        }
    }
}
