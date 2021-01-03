package com.dss.pepitolearning.ui.otroUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dss.pepitolearning.R;
import com.dss.pepitolearning.api.APIProductGet;

public class OtroFragment extends Fragment {

    private OtroViewModel otroViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        otroViewModel =
                new ViewModelProvider(this).get(OtroViewModel.class);
        View root = inflater.inflate(R.layout.fragment_otro, container, false);






        return root;
    }
}