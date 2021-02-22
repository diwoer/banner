package com.di.view.banner.indicator;

/**
 * indicator切换监听
 * */
public interface IndicatorChangeListener {

    /**
     * @param indicator 父对象
     * @param totalNum indicator的总个数
     * @param currentPosition 目前显示的索引值
     * @param lastPosition 上一个显示的索引值
     * */
    void update(Indicator indicator, int totalNum, int currentPosition, int lastPosition);
}
