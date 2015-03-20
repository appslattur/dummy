package appslattur.appslatturdemo.Gluggar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import java.io.IOException;
import java.io.InputStream;

import appslattur.appslatturdemo.MainActivity;
import appslattur.appslatturdemo.R;

public class Splash extends Activity {
    public double alfa;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AssetManager assetManager = this.getAssets();
        Bitmap dickbutt;
        InputStream jizz = null;

        try{
            jizz = assetManager.open("appslattur.png");
            dickbutt = BitmapFactory.decodeStream(jizz);

            findViewById(R.id.frame).setBackground(new BitmapDrawable(dickbutt));

        } catch (IOException e) {
            e.printStackTrace();
        }
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(3000);
        anim.setRepeatCount(10);
        anim.setRepeatMode(Animation.REVERSE);
        findViewById(R.id.frame).startAnimation(anim);

        CountDownTimer waitforboom = new CountDownTimer(2000, 10) {
            @Override
            public void onTick(long l) {


            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(intent);
                Splash.this.finish();
            }
        }.start();





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
