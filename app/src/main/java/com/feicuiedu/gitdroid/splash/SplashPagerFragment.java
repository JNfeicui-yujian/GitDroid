package com.feicuiedu.gitdroid.splash;


import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.feicuiedu.gitdroid.R;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class SplashPagerFragment extends Fragment {

    @Bind(R.id.content) FrameLayout frameLayout; // 当前页面布局,用于显示背景色的渐变
    @Bind(R.id.viewPager) ViewPager viewPager;
    @Bind(R.id.indicator) CircleIndicator indicator; // 指示器（下方的小圆点）

    private SplashPagerAdapter adapter;

    @BindColor(R.color.colorGreen) int colorGreen;   // ViewPager页面对应背景色
    @BindColor(R.color.colorRed) int colorRed;  // ViewPager页面对应背景色
    @BindColor(R.color.colorYellow) int colorYellow;    // ViewPager页面对应背景色

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        // 添加ViewPager监听
        viewPager.addOnPageChangeListener(pageChangeListener);
        indicator.setViewPager(viewPager);
    }

    // 此监听器主要负责背景颜色的渐变，和最后一个页面视图动画的显示。
    private final ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        // ARGB插值器
        final ArgbEvaluator evaluator = new ArgbEvaluator();

        @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // 第一个页面到第二个页面之间
            if (position == 0) {
                // 颜色取绿色到红色的中间值，偏移量positionOffset是[0, 1]
                int color = (int) evaluator.evaluate(positionOffset, colorGreen, colorRed);
                frameLayout.setBackgroundColor(color);
                return;
            }
            // 第二个页面到第三个页面之间
            if (position == 1) {
                int color = (int) evaluator.evaluate(positionOffset, colorRed, colorYellow);
                frameLayout.setBackgroundColor(color);
                return;
            }
        }

        @Override public void onPageSelected(int position) {
        }

        @Override public void onPageScrollStateChanged(int state) {
        }
    };
}
