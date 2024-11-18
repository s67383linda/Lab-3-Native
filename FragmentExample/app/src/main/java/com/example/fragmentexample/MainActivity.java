package com.example.fragmentexample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Fragment2 dynamically (if not already in the layout)
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment2 fragment2 = new Fragment2();
            transaction.replace(R.id.fragment2, fragment2, "Fragment2");
            transaction.commit();
        }
    }

    // Method to send data to Fragment2
    public void sendDataToFragment2(String data) {
        Fragment2 fragment2 = (Fragment2) getSupportFragmentManager().findFragmentByTag("Fragment2");
        if (fragment2 != null) {
            fragment2.updateData(data);
        }
    }
}
