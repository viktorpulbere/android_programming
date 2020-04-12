package com.example.blockit.ui.sms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.blockit.R;

public class SmsFragment extends Fragment {

    private SmsViewModel smsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        smsViewModel =
                ViewModelProviders.of(this).get(SmsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sms, container, false);
        smsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("Hello");
            }
        });
        return root;
    }
}
