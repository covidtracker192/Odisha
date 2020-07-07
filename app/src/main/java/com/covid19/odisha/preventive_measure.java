package com.covid19.odisha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class preventive_measure extends AppCompatActivity {
private static int currentpage=0;
private static int numpage=0;
int[] img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preventive_measure);
        img=new int[] {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.one};
        final ViewPager viewPager = findViewById(R.id.pager);
        ImageAdapter adapter = new ImageAdapter(preventive_measure.this,img);
        viewPager.setAdapter(adapter);

        CircleIndicator indicator=findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

       viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
                currentpage=position;
           }

           @Override
           public void onPageScrollStateChanged(int state) {
               if(state==ViewPager.SCROLL_STATE_IDLE){
                   int pagecount=img.length;
                   if(currentpage==0)
                   {
                       viewPager.setCurrentItem(pagecount-1,false);
                   }
                   else if(currentpage==pagecount-1)
                   {
                       viewPager.setCurrentItem(0,false);
                   }

               }

           }
       });

    }
    public void onBackPressed(){
        Intent intent = new Intent(preventive_measure.this, home.class);
        startActivity(intent);
        finish();
    }
}
