package cheatsheet.cheatsheet;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserProfileActivity extends Activity {

    private Uri newPicUri;
    public static final int REQUEST_TAKE_PHOTO=1; //Request code to take a photo
    public static final int REQUEST_CHOOSE_FROM_GALLERY = 2; //request code to access gallery

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        //load text and radiobutton shared preferences as well as load any saved pro pic
        loadProfile();
        loadProfileIcon();

        Spinner instrumentSpinner = (Spinner) findViewById(R.id.instrument);
        instrumentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(UserProfileActivity.this);

                if (!prefs.contains(getString(R.string.CowbellEasterEgg)) && position==Integer.valueOf(getString(R.string.CowbellPositionEasterEgg))){
                    SharedPreferences.Editor prefsEditor = prefs.edit();
                    prefsEditor.putBoolean(getString(R.string.CowbellEasterEgg), true);
                    prefsEditor.commit();
                    Toast.makeText(UserProfileActivity.this, "Dr. Love Achievement Found! \n 'I got a fever! And the only prescription is more cowbell!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
//        instrumentSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

    //Load saved profile picture if one is available or saved.
    private void loadProfileIcon() {
        try {
            FileInputStream saved_photo = openFileInput(getResources().getString(R.string.profile_icon));
            Bitmap decoded_image = BitmapFactory.decodeStream(saved_photo);

            ((ImageView) findViewById(R.id.UserIcon)).setImageBitmap(decoded_image);

        } catch (FileNotFoundException exception){
            //Image not set or cannot be found. Use default image.
        }
    }

    //load shared preferences to populate saved profile fields.
    private void loadProfile() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs != null) {
            //Set each TextView to correct text by looking at shared "prefs" using string keys.
            ((TextView) findViewById(R.id.Name)).setText(prefs.getString(getResources().getString(R.string.Name), ""));
            ((TextView) findViewById(R.id.Age)).setText(prefs.getString(getResources().getString(R.string.Age), ""));
            ((TextView) findViewById(R.id.Phone)).setText(prefs.getString(getResources().getString(R.string.Phone), ""));
            ((TextView) findViewById(R.id.Email)).setText(prefs.getString(getResources().getString(R.string.Email), ""));
            ((TextView) findViewById(R.id.Bio)).setText(prefs.getString(getResources().getString(R.string.Bio), ""));
            ((Spinner) findViewById(R.id.instrument)).setSelection(prefs.getInt(getString(R.string.instrumentPosition), 0));
//            //if male, set button to checked. likewise for female. May not have either checked.
//            //set clicked button to turqoise color
//            if (prefs.getBoolean("male", false)){
//                ((RadioButton) findViewById(R.id.MaleID)).toggle();
//                ((RadioButton) findViewById(R.id.MaleID)).setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.turqoise)));
//            }
//            else if (prefs.getBoolean("female", false)){
//                ((RadioButton) findViewById(R.id.FemaleID)).toggle();
//                ((RadioButton) findViewById(R.id.FemaleID)).setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.turqoise)));
//            }
        }
    }

    //Save profile when save button clicked
    public void onSaveClick(View v){
        saveProfile();
        Toast.makeText(getApplicationContext(), getString(R.string.User_Profile_SavedMessage), Toast.LENGTH_SHORT).show();
        finish();
    }

    //Save Profile's shared preferences so when reloading app data, this data is already saved.
    private void saveProfile() {

        SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(this).edit();

        String name = ((TextView) findViewById(R.id.Name)).getText().toString();
        String age = ((TextView) findViewById(R.id.Age)).getText().toString();
        String phone = ((TextView) findViewById(R.id.Phone)).getText().toString();
        String email = ((TextView) findViewById(R.id.Email)).getText().toString();
        String bio = ((TextView) findViewById(R.id.Bio)).getText().toString();
        Integer instrumentPos = ((Spinner) findViewById(R.id.instrument)).getSelectedItemPosition();

        savePicToMemory(); //handles saving the new profile picture. No need to save in preferences as checked in onCreate

        prefs.putString(getResources().getString(R.string.Name), name);
        prefs.putString(getResources().getString(R.string.Age), age);
        prefs.putString(getResources().getString(R.string.Phone), phone);
        prefs.putString(getResources().getString(R.string.Email), email);
        prefs.putString(getResources().getString(R.string.Bio), bio);
        prefs.putInt(getResources().getString(R.string.instrumentPosition), instrumentPos);
        prefs.commit();
    }

    //Save the new profile picture to memory.
    private void savePicToMemory() {

        findViewById(R.id.UserIcon).buildDrawingCache();
        Bitmap pic = findViewById(R.id.UserIcon).getDrawingCache();

        try {   //try saving photo as bitmap with name = String.pro_pic_file
            FileOutputStream file_stream = openFileOutput(
                    getString(R.string.User_Profile_Picture), MODE_PRIVATE);
            pic.compress(Bitmap.CompressFormat.PNG, 100, file_stream);
            file_stream.flush();
            file_stream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    public void onCancel(View v){
        finish(); //don't save anything. just quit app
    }



}
