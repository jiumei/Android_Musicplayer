package com.example.zzuli.myapplication_week3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class StartActivity extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<View> viewList = new ArrayList<View>();
    private MyPagerAdapter myAdapter;

    private ImageView[] indicationPoint;//指示点控件
    private int[] points = {R.id.point1,R.id.point2,R.id.point3};

    private Button btnStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start_layout);
        initData();
        initViews();
    }

    private void initData() {
        LayoutInflater inflater = LayoutInflater.from(this);
        viewList.add(inflater.inflate(R.layout.guid_view1,null));
        viewList.add(inflater.inflate(R.layout.guid_view2,null));
        viewList.add(inflater.inflate(R.layout.guid_view3,null));

        indicationPoint = new ImageView[viewList.size()];
        //实例化每个指示点控件
        for (int i=0; i<viewList.size(); i++) {
            indicationPoint[i] = (ImageView) findViewById(points[i]);
        }
    }

    private void initViews() {
        myAdapter = new MyPagerAdapter(viewList);
        mViewPager = (ViewPager) this.findViewById(R.id.mViewPager);
        mViewPager.setAdapter(myAdapter);
        //mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setOffscreenPageLimit(viewList.size());// 加载缓存的页面个数
        mViewPager.setOnPageChangeListener(this);

        btnStart = (Button) viewList.get(viewList.size()-1).findViewById(R.id.start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i=0; i<points.length; i++){
            if (position == i) {
                indicationPoint[i].setImageResource(R.drawable.point_red);
            } else {
                indicationPoint[i].setImageResource(R.drawable.point_normal);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}