package com.di.view.banner;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.di.view.banner.adapter.ImageViewAdapter;
import com.di.view.banner.data.BannerImage;
import com.di.view.banner.indicator.ImagesIndicator;
import com.di.view.banner.style.BannerStyleRound;
import com.di.view.banner.util.DisplayTool;
import com.di.view.banner.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        BannerLayout bannerLayout = findViewById(R.id.banner_layout);
        bannerLayout.addIndicator(new ImagesIndicator.Builder(this).build());
        bannerLayout.setStyle(new BannerStyleRound(DisplayTool.dp2Px(this, 8)));
        bannerLayout.setBannerClickListener(position -> Log.e("test", "BannerClickListener，position=" + position));
        ImageViewAdapter imageViewAdapter = new ImageViewAdapter(this);
        imageViewAdapter.setImageLoader(new ImageLoader() {
            @Override
            public void load(ImageView imageView, BannerImage bannerImage) {
//                bannerImage.setImage(imageView);
            }
        });
        bannerLayout.setAdapter(imageViewAdapter);
        bannerLayout.setData(getBannerDataS());
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