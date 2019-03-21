package com.example.zzuli.myapplication_week3;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TableLayout mytab;
    ArrayList<String> mtitle;
    ArrayList mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initViewPager();
    }
    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("CloudMusic");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemId = item.getItemId();
        if (menuItemId == R.id.action_search){
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        }else if (menuItemId == R.id.action_notification){
            Toast.makeText(this, "通知", Toast.LENGTH_SHORT).show();
        }
        else if (menuItemId == R.id.action_item_one){
            Toast.makeText(this, "测试一", Toast.LENGTH_SHORT).show();
        }else if (menuItemId == R.id.action_item_two){
            Toast.makeText(this, "测试二", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    private void initViewPager(){
        ViewPager mvp = findViewById(R.id.viewpager);
        mtitle = new ArrayList<>();
        mtitle.add("本地歌曲");
        mtitle.add("网络曲库");
        mtitle.add("我的");
        //mtitle.add("选项4");

        mFragment = new ArrayList<>();
        mFragment.add(MyFragment.newInstance(mtitle.get(0)));
        mFragment.add(MyFragment2.newInstance(mtitle.get(1)));
        mFragment.add(MyFragment3.newInstance(mtitle.get(2)));
        //mFragment.add(MyFragment4.newInstance(mtitle.get(3)));

        TabLayout mytab = findViewById(R.id.mytab);
        mytab.setupWithViewPager(mvp);
        mvp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return (Fragment) mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mtitle.get(position);
            }
        });

    }
}
