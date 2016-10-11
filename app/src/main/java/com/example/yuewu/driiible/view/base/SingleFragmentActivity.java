package com.example.yuewu.driiible.view.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.yuewu.driiible.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YueWu on 10/10/16.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity{

    @BindView(R.id.my_toolbar) Toolbar toolbar;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_single_fragment);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (isBackEnabled()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setTitle(getActivityTitle());

        if (savedInstance == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, newFragment())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isBackEnabled() && item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected boolean isBackEnabled() {
        return true;
    }

    @NonNull
    protected String getActivityTitle() { return "";}

    @NonNull
    protected abstract Fragment newFragment();
}




