package com.laureanti.daniil.android.criminalintent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
