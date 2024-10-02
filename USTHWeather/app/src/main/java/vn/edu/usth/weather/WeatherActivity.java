package vn.edu.usth.weather;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ViewPager pager = findViewById(R.id.pager);
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

        mediaPlayer = MediaPlayer.create(this, R.raw.a_test);
        if (mediaPlayer != null) {
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(mp -> {
                mp.release();
                mediaPlayer = null;
            });
        }

        // if (getSupportActionBar() != null) {
        //     getSupportActionBar().setTitle(R.string.app_name);
        // }
        // Log.i("Weather", "onCreate()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
            new RefreshWeatherTask().execute();
            return true;

        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PrefActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private class RefreshWeatherTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Weather data refreshed!";
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(WeatherActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Weather", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Weather", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Weather", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Weather", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Weather", "onDestroy()");
    }
}
