package com.di.view.banner.indicator;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.di.view.banner.util.DisplayTool;
import com.di.view.banner.R;

import java.util.HashMap;
import java.util.Map;

public class ImagesIndicator extends Indicator {

    private final Map<Integer, ImageView> indicatorMap = new HashMap<>();
    private ViewGroup parentView;

    private int resId;
    private int checkedResId;
    private int margin;

    private ImagesIndicator(Context context) {
        super(context);
        resId = R.drawable.banner_indicator_circle_normal;
        checkedResId =R.drawable.banner_indicator_circle_checked;
        margin = DisplayTool.dp2Px(context, 8);
    }

    public ImageView getChildView(int index) {
        return indicatorMap.get(index);
    }

    @Override
    public void createView(Context context, int total) {
        clearStoredChildViews();
        for (int i = 0; i < total; i++) {
            ImageView childView = createChildView(context);
            indicatorMap.put(i, childView);
            getView(context).addView(childView);
        }
    }

    @Override
    public ViewGroup getView(Context context) {
        if (parentView == null) {
            parentView = createParentView(context);
        }
        return parentView;
    }

    @Override
    public void clearStoredChildViews() {
        indicatorMap.clear();
    }

    private LinearLayout createParentView(Context context) {
        LinearLayout llIndicator = new LinearLayout(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        layoutParams.setMargins(0, 0, 0, margin);
        llIndicator.setLayoutParams(layoutParams);
        return llIndicator;
    }

    private ImageView createChildView(Context context) {
        ImageView iv = new ImageView(context);
        iv.setImageResource(resId);
        LinearLayout.LayoutParams childLayoutParams = new LinearLayout.LayoutParams(margin, margin);
        int margin_half = margin / 2;
        childLayoutParams.setMargins(margin_half, margin_half, margin_half, margin_half);
        iv.setLayoutParams(childLayoutParams);
        return iv;
    }

    private void setNormalResId(int resId) {
        this.resId = resId;
    }

    private void setCheckedResId(int resId) {
        this.checkedResId = resId;
    }

    private void setIndicatorMargin(int margin) {
        this.margin = margin;
    }

    @Override
    public final void update(Indicator indicator, int totalNum, int currentPosition, int lastPosition) {
        if (checkedResId == -1 || resId == -1) throw new NullPointerException("无效的indicator图片资源");
        getChildView(lastPosition).setImageResource(resId);
        getChildView(currentPosition).setImageResource(checkedResId);
        super.update(indicator, totalNum, currentPosition, lastPosition);
    }

    public static class Builder {

        private final Context context;
        private int resId = -1;
        private int checkedResId = -1;
        private int margin = -1;
        private IndicatorChangeListener indicatorChangeListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setNormalResId(int resId) {
            this.resId = resId;
            return this;
        }

        public Builder setCheckedResId(int resId) {
            this.checkedResId = resId;
            return this;
        }

        public Builder setIndicatorMargin(int margin) {
            this.margin = margin;
            return this;
        }

        public Builder setChangeListener(IndicatorChangeListener listener) {
            indicatorChangeListener = listener;
            return this;
        }

        public ImagesIndicator build() {
            ImagesIndicator indicator = new ImagesIndicator(context);
            if (resId != -1) {
                indicator.setNormalResId(resId);
            }
            if (checkedResId != -1) {
                indicator.setCheckedResId(checkedResId);
            }
            if (margin != -1) {
                indicator.setIndicatorMargin(margin);
            }
            indicator.setIndicatorChangeListener(indicatorChangeListener);
            return indicator;
        }
    }
}
