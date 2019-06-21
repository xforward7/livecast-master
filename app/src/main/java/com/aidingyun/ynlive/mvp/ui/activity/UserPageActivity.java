package com.aidingyun.ynlive.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.mvp.ui.fragment.UserPageFragment;

public class UserPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        UserPageFragment fragment = new UserPageFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_fragment, fragment);
        transaction.commit();
    }
}
