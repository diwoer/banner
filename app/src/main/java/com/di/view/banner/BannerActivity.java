package com.di.view.banner;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        BannerLayout bannerLayout = findViewById(R.id.banner_layout);
        bannerLayout.addIndicator(new ListIndicator<LinearLayout, ImageView>() {

            private final int defaultIndicatorMargin = dp2px(8);

            @Override
            public LinearLayout createParentView(Context context) {
                LinearLayout llIndicator = new LinearLayout(context);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
                layoutParams.setMargins(0, 0, 0, defaultIndicatorMargin);
                llIndicator.setLayoutParams(layoutParams);
                return llIndicator;
            }

            @Override
            public ImageView createChildView(Context context) {
                ImageView iv = new ImageView(context);
                iv.setImageResource(R.drawable.banner_indicator_circle_normal);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(defaultIndicatorMargin, defaultIndicatorMargin);
                int margin = defaultIndicatorMargin / 2;
                layoutParams.setMargins(margin, margin, margin, margin);
                iv.setLayoutParams(layoutParams);
                return iv;
            }

            @Override
            public void update(int totalNum, int currentPosition, int lastPosition) {
                getChildView(lastPosition).setImageResource(R.drawable.banner_indicator_circle_normal);
                getChildView(currentPosition).setImageResource(R.drawable.banner_indicator_circle_checked);
            }
        });
        bannerLayout.setAdapter(new ImageViewAdapter(this));
        bannerLayout.setData(getBannerDataS());

        BannerLayout bannerLayout1 = findViewById(R.id.banner_layout_1);
        bannerLayout1.addIndicator(new ListIndicator<FrameLayout, TextView>() {

            private final int defaultIndicatorMargin = dp2px(8);

            @Override
            public FrameLayout createParentView(Context context) {
                FrameLayout llIndicator = new FrameLayout(context);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM | Gravity.END);
                layoutParams.setMargins(0, 0, defaultIndicatorMargin * 2, defaultIndicatorMargin);
                llIndicator.setLayoutParams(layoutParams);
                return llIndicator;
            }

            @Override
            public TextView createChildView(Context context) {
                TextView tv = new TextView(context);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv.setTextColor(Color.WHITE);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tv.setLayoutParams(layoutParams);
                return tv;
            }

            @Override
            public void update(int totalNum, int currentPosition, int lastPosition) {
                getChildView(lastPosition).setText(String.format(Locale.getDefault(), "%d/%d", currentPosition, totalNum));
            }
        });
        bannerLayout1.setAdapter(new ImageViewAdapter(this));
        bannerLayout1.setData(getBannerDataS());

    }

    private int dp2px(int dpValue) {
        return (int) (getResources().getDisplayMetrics().density * dpValue + 0.5f);
    }

    private List<BannerImage> getBannerDataS() {
        final int[] colors = new int[]{
                Color.parseColor("#FF8800"),
                Color.parseColor("#FF0088"),
                Color.parseColor("#FF88FF"),
        };
        List<BannerImage> list = new ArrayList<>();
        for (int color : colors) {
            BannerImage bannerImage = new BannerImage();
            bannerImage.type = BannerImage.TYPE_COLOR;
            bannerImage.color = color;
            list.add(bannerImage);
        }
        return list;
    }
}