package com.dss.pepitolearning.ui.otroUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dss.pepitolearning.R;

public class OtroFragment extends Fragment {

    private OtroViewModel otroViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        otroViewModel =
                new ViewModelProvider(this).get(OtroViewModel.class);
        View root = inflater.inflate(R.layout.fragment_otro, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        otroViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}