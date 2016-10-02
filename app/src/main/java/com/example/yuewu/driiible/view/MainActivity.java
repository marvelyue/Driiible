package com.example.yuewu.driiible.view;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yuewu.driiible.R;
import com.example.yuewu.driiible.view.bucket_list.BucketListFragment;
import com.example.yuewu.driiible.view.shot_list.ShotListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, ShotListFragment.newInstance())
                    .commit();
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.isChecked()) {
                    drawerLayout.closeDrawers();
                    return true;
                }
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.drawer_item_home:
                        Toast.makeText(MainActivity.this, "home clicked", Toast.LENGTH_LONG).show();
                        fragment = ShotListFragment.newInstance();
                        setTitle(R.string.title_home);
                        break;
                    case R.id.drawer_item_likes:
                        Toast.makeText(MainActivity.this, "likes clicked", Toast.LENGTH_LONG).show();
                        fragment = ShotListFragment.newInstance();
                        setTitle(R.string.title_likes);
                        break;
                    case R.id.drawer_item_buckets:
                        Toast.makeText(MainActivity.this, "buckets clicked", Toast.LENGTH_LONG).show();
                        fragment = BucketListFragment.newInstance();
                        setTitle(R.string.title_buckets);
                        break;
                }

                drawerLayout.closeDrawers();


//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, fragment)
//                        .commit();
//                return true;  // this is also true, but there may be other cases (others may change the logic of the code which may set the fragment into null)
                // So the following code is better in real project

                if (fragment != null) {
                    getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
                }
                return false;
            }
        });

    }
}
