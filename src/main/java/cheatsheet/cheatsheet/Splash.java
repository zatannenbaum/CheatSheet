package cheatsheet.cheatsheet;

import android.app.Activity;
import android.content.Intent;
import android.media.session.MediaController;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import pl.droidsonroids.gif.GifTextView;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (savedInstanceState==null){
            loadApp();
        }
    }

    private void loadApp() {

        new Timer().schedule(new TimerTask(){
            public void run() {
                Splash.this.runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(Splash.this, MainActivity.class));
                        finish();
                    }
                });
            }
        }, 4500);


//
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        }

    }


}
