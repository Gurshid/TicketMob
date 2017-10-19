package gurshid.example2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gurshid.example2.Fragments.ConcertFragment;
import gurshid.example2.Fragments.EventFragment;
import gurshid.example2.Fragments.MovieFragment;
import gurshid.example2.Fragments.SportFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

    }


    public class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0)
                return new MovieFragment();
            else if(position == 1)
                return new ConcertFragment();
            else if(position == 2)
                return new SportFragment();
            else return new EventFragment();
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0)
                return "Movies";
            else if(position == 1)
                return "Events";
            else if(position == 2)
                return "Sports & Fitness";
            else return "Travel And Adventure";
        }
    }

}


