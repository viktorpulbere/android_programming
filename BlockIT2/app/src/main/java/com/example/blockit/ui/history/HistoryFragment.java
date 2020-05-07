package com.example.blockit.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.blockit.R;
import com.example.blockit.ui.MyCustomAdapter;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        final ListView listView = (ListView) root.findViewById(R.id.listView);

        //generate list
//        ArrayList<String> list = new ArrayList<String>();
//        list.add("0774462785");
//        list.add("068581429");
//        list.add("0774462785");
//        list.add("068581429");
//        list.add("0774462785");
//        list.add("068581429");
//        list.add("0774462785");
//        list.add("068581429");
//        list.add("0774462785");
//        list.add("068581429");
//        list.add("0774462785");
//        list.add("068581429");
//
//        //instantiate custom adapter
//        MyCustomAdapter adpt = new MyCustomAdapter(list, getActivity());
//        listView.setAdapter(adpt);


//        final TextView textView = root.findViewById(R.id.text_history);
//        historyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}
