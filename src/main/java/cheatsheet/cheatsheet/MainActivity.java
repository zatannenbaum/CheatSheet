package cheatsheet.cheatsheet;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

import cheatsheet.cheatsheet.view.SlidingTabLayout;

public class MainActivity extends Activity {

    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get tab related items from XML...
        tabLayout = (SlidingTabLayout) findViewById(R.id.slidingTabs);
        tabLayout.setSelectedIndicatorColors(Color.WHITE);
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        //add tabs to adapter
        ArrayList<Fragment> tabs = new ArrayList<>();
        tabs.add(new SongsFragment());  //songs tab on left
        tabs.add(new SetsFragment()); //sets tab in the left-middle
        tabs.add(new ToolsFragment()); //tools tab on the right-middle
        tabs.add(new AccountFragment()); //Account tab on the right

        ActionTabsViewPagerAdapter actionTabsViewPagerAdapter = new ActionTabsViewPagerAdapter(this.getFragmentManager(), tabs);

        //set adapter to viewPager and attach viewpager to tabLayout
        viewPager.setAdapter(actionTabsViewPagerAdapter);
        tabLayout.setDistributeEvenly(true);

        tabLayout.setViewPager(viewPager);

}

    public void onUserProfileClick(View view){
        startActivity(new Intent(this, UserProfileActivity.class));
    }

    public void onAchievementsClicked(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if (prefs.contains(getString(R.string.CowbellEasterEgg))){
            System.out.println("COWBELL EASTER EGG FOUND!");
        }
        startActivity(new Intent(this, AchievementsDisplayActivity.class));
    }

////Small Helper Class to adjust history_fragment when units change
//public class MyRunsViewPagerListener extends ViewPager.SimpleOnPageChangeListener {
//    @Override
//    public void onPageSelected(int position) {
//        if (position==HISTORY_FRAG){
//            historyFragment.onResume(); //if on the historyFragment, reset (update entries visable)
//        }
//    }
//}
}
