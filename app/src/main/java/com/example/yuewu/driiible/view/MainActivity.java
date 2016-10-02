package com.example.yuewu.driiible.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuewu.driiible.R;
import com.example.yuewu.driiible.view.shot_list.ShotListFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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

    }
}
