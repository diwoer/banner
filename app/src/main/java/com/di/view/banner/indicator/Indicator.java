package com.di.view.banner.indicator;

import android.content.Context;
import android.view.ViewGroup;

public abstract class Indicator implements IndicatorChangeListener {

    private IndicatorChangeListener indicatorChangeListener;

    public Indicator(Context context) {}

    /**
     * 生成View
     * */
    public abstract void createView(Context context, int total);

    /**
     * 获取View
     * */
    public abstract ViewGroup getView(Context context);

    /**
     * 移除View
     * */
    public void clearStoredChildViews() {

    }

    @Override
    public void update(Indicator indicator, int totalNum, int currentPosition, int lastPosition) {
        if (indicatorChangeListener != null) {
            indicatorChangeListener.update(indicator, totalNum, currentPosition, lastPosition);
        }
    }

    /**
     * 设置indicator切换监听
     */
    public void setIndicatorChangeListener(IndicatorChangeListener listener) {
        this.indicatorChangeListener = listener;
    }
}
