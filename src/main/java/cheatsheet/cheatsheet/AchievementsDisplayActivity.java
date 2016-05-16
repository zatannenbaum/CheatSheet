package cheatsheet.cheatsheet;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AchievementsDisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements_display);

        loadAchievements();
    }

    public void loadAchievements(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<ArrayList<String>> achievements = new ArrayList<>();

//        for achievement in all achievements
        //fix later on!!
        for (int i=0; i<10; i++) {
            if (prefs.contains(getString(R.string.CowbellEasterEgg))) {
                ArrayList<String> achievement = new ArrayList<>();
                achievement.add(0, "Dr. Love Achievement: ");
                achievement.add(1, "For setting your instrument as Cowbell");
                achievement.add(2, "'I've got a fever, and the only perscription is more cowbell.' -Christopher Walken, SNL 2000");
                achievement.add(3, "cowbell_easter_egg");

                achievements.add(achievement);
            }
        }

        listView.setAdapter(new AchievementsAdapter(this, achievements));

    }

    //ListAdapter used to display exercise activities
    public class AchievementsAdapter extends ArrayAdapter<ArrayList<String>> {
        public AchievementsAdapter(Context context, ArrayList<ArrayList<String>> achievements) {
            super(context, 0, achievements);
        }
        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ArrayList<String> achievement = getItem(position);

            if (view == null) view = LayoutInflater.from(getContext()).inflate(R.layout.achievement, viewGroup, false);
            TextView achievementTitle = (TextView) view.findViewById(R.id.achievement);
            achievementTitle.setText(achievement.get(0));

            TextView gag = (TextView) view.findViewById(R.id.gag);
            gag.setText(achievement.get(1));

            TextView description = (TextView) view.findViewById(R.id.description);
            description.setText(achievement.get(2));

            ImageView achievementImage = (ImageView) view.findViewById(R.id.imageView);
            achievementImage.setImageDrawable(getDrawable(R.drawable.cowbell_easter_egg));

            return view;
        }

    }
}
