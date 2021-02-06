package com.di.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图
 * 实现效果说明：
 * 包含2个部分：banner，indicator
 * 支持设置banner，indicator样式
 */
public class BannerLayout extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private BannerAdapter imageViewAdapter;
    private Indicator indicator;

    private final List<BannerImage> dataList = new ArrayList<>();

    private int lastPosition;

    private static final long DELAY_TIME = 3000;

    private final WeakRunnable weakRunnable;

    public BannerLayout(@NonNull Context context) {
        this(context, null);
    }

    public BannerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        weakRunnable = new WeakRunnable(this);
        buildViewPager();
    }

    private void buildViewPager() {
        viewPager = new ViewPager(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        addView(viewPager, layoutParams);
        viewPager.addOnPageChangeListener(this);
    }

    public void addIndicator(Indicator indicator) {
        this.indicator = indicator;
        addView(indicator.getParentView(getContext()));
    }

    public <T, V extends View> void setAdapter(BannerAdapter<T, V> adapter) {
        imageViewAdapter = adapter;
        viewPager.setAdapter(imageViewAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        indicator.update(dataList.size(), position % dataList.size(), lastPosition % dataList.size());
        lastPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setData(List<BannerImage> list) {
        dataList.clear();
        dataList.addAll(list);

        //设置indicator
        indicator.clearStoredChildViews();
        for (int i = 0; i < dataList.size(); i++) {
            indicator.buildChild(getContext(), dataList.size(), i);
        }

        imageViewAdapter.setData(dataList);
        viewPager.setCurrentItem(dataList.size() * 1000, false);
        startAutoPlay();
    }

    private int dp2px(int dpValue) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dpValue + 0.5f);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopAutoPlay();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                startAutoPlay();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == View.VISIBLE) {
            startAutoPlay();
        } else {
            stopAutoPlay();
        }
    }

    public void nextPage() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        startAutoPlay();
    }

    public void startAutoPlay() {
        if (weakRunnable != null) {
            removeCallbacks(weakRunnable);
            postDelayed(weakRunnable, DELAY_TIME);
        }
    }

    public void stopAutoPlay() {
        if (weakRunnable != null) {
            removeCallbacks(weakRunnable);
        }
    }

    /**
     * 计时工具
     */
    static class WeakRunnable implements Runnable {

        private final WeakReference<BannerLayout> weakReference;

        public WeakRunnable(BannerLayout bannerLayout) {
            weakReference = new WeakReference<>(bannerLayout);
        }

        @Override
        public void run() {
            BannerLayout bannerLayout = weakReference.get();
            if (bannerLayout != null) {
                bannerLayout.nextPage();
            }
        }
    }
}
