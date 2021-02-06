package com.di.view.banner;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BannerAdapter<T, V extends View> extends PagerAdapter {

    private final ViewCreator<V> viewCreator;
    private final List<T> dataList;

    private final Context context;

    public BannerAdapter(Context context) {
        this.context = context;
        viewCreator = new ViewCreator<>();
        dataList = new ArrayList<>();
    }

    public abstract V createView();

    public abstract void setViews(V contentView, T data, int position);

    public void setData(List<T> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public final Object instantiateItem(@NonNull ViewGroup container, int position) {
        V contentView = viewCreator.get(position, createView());
        container.addView(contentView);
        position %= dataList.size();
        setViews(contentView, dataList.get(position), position);
        return contentView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        viewCreator.remove(position);
    }

    /**
     * 因为ViewPager默认最多会显示3个对象
     * 这个类是为了防止重复创建View对象
     */
    static class ViewCreator<V extends View> {

        private final Map<Integer, V> usingMap;
        private final List<V> cachedList;

        public ViewCreator() {
            usingMap = new HashMap<>();
            cachedList = new ArrayList<>();
        }

        public V get(int position, V defaultView) {
            V view;
            if (cachedList.size() > 0) {
                view = cachedList.get(0);
                cachedList.remove(0);
            } else {
                view = defaultView;
            }
            usingMap.put(position, view);
            return view;
        }

        public void remove(int position) {
            if (usingMap.containsKey(position)) {
                V view = usingMap.get(position);
                if (view != null) {
                    cachedList.add(view);
                }
                usingMap.remove(position);
            }
        }

        private void log(String tag) {
            Log.e(tag, String.format("[ViewCreator] usingSize=%d, cachedSize=%d", usingMap.size(), cachedList.size()));
        }
    }


}
