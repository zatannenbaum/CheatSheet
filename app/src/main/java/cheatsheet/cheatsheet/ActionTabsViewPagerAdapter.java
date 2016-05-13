package cheatsheet.cheatsheet;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by matthewginsberg on 4/7/16.
 *
 * ActionTabsViewPager is the adapter used to implement Tab Layout for the Main Activity.
 */
public class ActionTabsViewPagerAdapter extends FragmentPagerAdapter{

    //NOTE: The values of START, HISTORY, AND SETTINGS are used with respective text tab labels
    public static final int SONGS = 0;
    public static final int SET_LISTS = 1;
    public static final int TOOLS = 2;
    public static final int ACCOUNT = 3;


    //Text labels for tabs
    public static final String UI_TAB_SONGS = "SONGS";
    public static final String UI_TAB_SETLISTS = "SET LISTS";
    public static final String UI_TAB_TOOLS = "TOOLS";
    public static final String UI_TAB_ACCOUNT = "ACCOUNT";


    private ArrayList<Fragment> fragTabs; //list of fragments ("tabs")

    //Constructor
    public ActionTabsViewPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragsToUse){
        super(fragmentManager);
        fragTabs = fragsToUse;
    }

    //Display the correct text label for teach tab
    public CharSequence getPageTitle(int tabPosition){
        switch (tabPosition){
            case SONGS:
                return UI_TAB_SONGS;
            case SET_LISTS:
                return UI_TAB_SETLISTS;
            case TOOLS:
                return UI_TAB_TOOLS;
            case ACCOUNT:
                return UI_TAB_ACCOUNT;
        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        return fragTabs.get(position);
    }

    @Override
    public int getCount() {
        return fragTabs.size();
    }
}
