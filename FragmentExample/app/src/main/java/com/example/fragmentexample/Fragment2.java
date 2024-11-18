package com.example.fragmentexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        textView = view.findViewById(R.id.textview_first);  // Assuming this is the correct ID of the TextView
        return view;
    }

    // Method to update the TextView with data
    public void updateData(String data) {
        if (textView != null) {
            textView.setText(data);
        }
    }
}
