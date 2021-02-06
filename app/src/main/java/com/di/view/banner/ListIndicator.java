package com.di.view.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * indicator是由列表组成的
 */
public abstract class ListIndicator<PV extends ViewGroup, CV extends View> implements Indicator<PV, CV> {

    private final Map<Integer, CV> indicatorMap = new HashMap<>();

    private PV parentView;

    @Override
    public void buildChild(Context context, int total, int index) {
        CV childView = createChildView(context);
        indicatorMap.put(index, childView);
        getParentView(context).addView(childView);
    }

    @Override
    public PV getParentView(Context context) {
        if (parentView == null) {
            parentView = createParentView(context);
        }
        return parentView;
    }

    public CV getChildView(int index) {
        return indicatorMap.get(index);
    }

    @Override
    public void clearStoredChildViews() {
        indicatorMap.clear();
    }
}
