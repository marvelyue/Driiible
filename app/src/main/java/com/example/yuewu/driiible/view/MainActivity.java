package com.example.yuewu.driiible.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yuewu.driiible.R;
import com.example.yuewu.driiible.dribbble.Dribbble;
import com.example.yuewu.driiible.utils.ImageUtils;
import com.example.yuewu.driiible.view.bucket_list.BucketListFragment;
import com.example.yuewu.driiible.view.shot_list.ShotListFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.my_toolbar) Toolbar toolBar; // using toolBar

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, ShotListFragment.newInstance(ShotListFragment.LIST_TYPE_POPULAR))
                    .commit();
        }

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setupDrawer();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawer() {
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,          /* DrawerLayout object */
                R.string.open_drawer,         /* "open drawer" description */
                R.string.close_drawer         /* "close drawer" description */
        );

        drawerLayout.setDrawerListener(drawerToggle);

        View headerView = navigationView.getHeaderView(0);

        ((TextView) headerView.findViewById(R.id.nav_header_user_name)).setText(Dribbble.getCurrentUser().name);

        headerView.findViewById(R.id.nav_header_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dribbble.logout(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
                        //Toast.makeText(MainActivity.this, "home clicked", Toast.LENGTH_LONG).show();
                        fragment = ShotListFragment.newInstance(ShotListFragment.LIST_TYPE_POPULAR);
                        setTitle(R.string.title_home);
                        break;
                    case R.id.drawer_item_likes:
                        //Toast.makeText(MainActivity.this, "likes clicked", Toast.LENGTH_LONG).show();
                        fragment = ShotListFragment.newInstance(ShotListFragment.LIST_TYPE_LIKED);
                        setTitle(R.string.title_likes);
                        break;
                    case R.id.drawer_item_buckets:
                        //Toast.makeText(MainActivity.this, "buckets clicked", Toast.LENGTH_LONG).show();
                        fragment = BucketListFragment.newInstance(null, false, null);
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

        setupNavHeader();
    }

    private void setupNavHeader() {
        View headerView = navigationView.getHeaderView(0);

        ((TextView) headerView.findViewById(R.id.nav_header_user_name)).setText(Dribbble.getCurrentUser().name);

        ((SimpleDraweeView) headerView.findViewById(R.id.nav_header_user_picture))
                .setImageURI(Uri.parse(Dribbble.getCurrentUser().avatar_url));

        headerView.findViewById(R.id.nav_header_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dribbble.logout(MainActivity.this);

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}

//public class MainActivity extends AppCompatActivity {
//
//    @BindView(R.id.my_toolbar) Toolbar toolbar;
//    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
//    @BindView(R.id.drawer) NavigationView navigationView;
//
//    private ActionBarDrawerToggle drawerToggle;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawerToggle = new ActionBarDrawerToggle(
//                this,                  /* host Activity */
//                drawerLayout,          /* DrawerLayout object */
//                R.string.open_drawer,         /* "open drawer" description */
//                R.string.close_drawer         /* "close drawer" description */
//        );
//        drawerLayout.setDrawerListener(drawerToggle);
//
//        setupDrawer(drawerLayout);
//
//        if (savedInstanceState == null) {
//            ShotListFragment shotListFragment = ShotListFragment.newInstance(
//                    ShotListFragment.LIST_TYPE_POPULAR);
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.fragment_container, shotListFragment)
//                    .commit();
//        }
//    }
//
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        drawerToggle.onConfigurationChanged(newConfig);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (drawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void setupDrawer(final DrawerLayout drawerLayout) {
//        // dynamically set header, the header is not specified in main_activity.xml layout
//        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
//
//        ((TextView) headerView.findViewById(R.id.nav_header_user_name)).setText(
//                Dribbble.getCurrentUser().name);
//
//        headerView.findViewById(R.id.nav_header_logout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dribbble.logout(MainActivity.this);
//
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        ImageView userPicture = (ImageView) headerView.findViewById(R.id.nav_header_user_picture);
//        ImageUtils.loadUserPicture(this, userPicture, Dribbble.getCurrentUser().avatar_url);
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                if (item.isChecked()) {
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//
//                Fragment fragment = null;
//                switch (item.getItemId()) {
//                    case R.id.drawer_item_home:
//                        fragment = ShotListFragment.newInstance(ShotListFragment.LIST_TYPE_POPULAR);
//                        setTitle(R.string.title_home);
//                        break;
//                    case R.id.drawer_item_likes:
//                        fragment = ShotListFragment.newInstance(ShotListFragment.LIST_TYPE_LIKED);
//                        setTitle(R.string.title_likes);
//                        break;
//                    case R.id.drawer_item_buckets:
//                        fragment = BucketListFragment.newInstance(null, false, null);
//                        setTitle(R.string.title_buckets);
//                        break;
//                }
//
//                drawerLayout.closeDrawers();
//
//                if (fragment != null) {
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.fragment_container, fragment)
//                            .commit();
//                    return true;
//                }
//
//                return false;
//            }
//        });
//    }
//}
