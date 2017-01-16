package com.example.daniloikeda.zuptestemobile;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.daniloikeda.zuptestemobile.DatabaseORMLite.DatabaseHelper;
import com.example.daniloikeda.zuptestemobile.DatabaseORMLite.Movies;
import com.example.daniloikeda.zuptestemobile.Fragment.CadastroFragment;
import com.example.daniloikeda.zuptestemobile.Fragment.FilmesFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    SampleFragmentPagerAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        RuntimeExceptionDao<Movies, Integer> moviesDao = dbHelper.getMovieRuntimeExceptionDao();

        pageAdapter = new SampleFragmentPagerAdapter(getSupportFragmentManager());
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        pageAdapter.addFragment(new FilmesFragment(), "Filmes");
        pageAdapter.addFragment(new CadastroFragment(), "Cadastrar");

        viewPager.setAdapter(pageAdapter);
        // Give the TabLayout the ViewPager
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.WHITE, Color.rgb(92,163,155));

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
