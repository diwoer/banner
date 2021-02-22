package com.di.view.banner.util;

import android.content.Context;

public class DisplayTool {

    public static int dp2Px(Context context, int dpValue) {
        return (int) (context.getResources().getDisplayMetrics().density * dpValue + 0.5f);
    }
}
