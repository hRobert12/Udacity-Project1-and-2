package com.example.android.popluarmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {

    public static final String URL_BASE = "http://api.themoviedb.org/3/movie/";
    public static final String API_KEY = "";
    public static final String DETAIL_FRAGMENT_TAG = "detail_frag";
    public static boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.detail) == null) {
            twoPane = false;
        } else {
            twoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail, new MovieDetailFragment(), DETAIL_FRAGMENT_TAG)
                        .commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, Settings.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(long movie) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail, MovieDetailFragment.newInstance(movie), DETAIL_FRAGMENT_TAG)
                .commit();
    }
}
