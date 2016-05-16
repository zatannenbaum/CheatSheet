package cheatsheet.cheatsheet;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pl.droidsonroids.gif.GifTextView;

/**
 * Created by matthewginsberg on 5/12/16.
 */
public class AccountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.account_fragment, container, false);

//        GifTextView music_gif = (GifTextView) view.findViewById(R.id.music_gif);
//        music_gif.setBottom(SCREEN_BO).setScaleType(ScaleType.FIT_END);
        return view;
    }

}
