package com.di.view.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface Indicator<PV extends ViewGroup, CV extends View> {

    PV createParentView(Context context);

    CV createChildView(Context context);

    void update(int totalNum, int currentPosition, int lastPosition);

    void buildChild(Context context, int total, int index);

    void clearStoredChildViews();

    ViewGroup getParentView(Context context);
}
