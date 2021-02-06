package com.di.view.banner;

import android.widget.ImageView;

public class BannerImage {

    public static final int TYPE_COLOR = 0;
    public static final int TYPE_URL = 1;

    /**
     * 数据使用哪个作为图片的内容
     * <p>
     * color 颜色值
     * url 图片链接
     */
    public int type;
    public int color;
    public String url;

    public void setImage(ImageView iv) {
        switch (type) {
            case TYPE_COLOR:
                iv.setBackgroundColor(color);
                break;
            case TYPE_URL:
                //加载图片
                break;
        }
    }
}
