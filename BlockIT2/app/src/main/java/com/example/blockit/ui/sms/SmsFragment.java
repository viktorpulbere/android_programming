package com.example.blockit.ui.sms;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.blockit.R;
import com.example.blockit.storage.Exception;
import com.example.blockit.storage.Repository;
import com.example.blockit.ui.MyCustomAdapter;

import java.util.List;

public class SmsFragment extends Fragment {
    private SmsViewModel smsViewModel;
    private EditText mEdit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        smsViewModel =
                ViewModelProviders.of(this).get(SmsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_sms, container, false);
        final Context context = getActivity();
        final Repository repo = new Repository(context);

        ImageButton add = root.findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdit = (EditText) root.findViewById(R.id.txtSearch);
                repo.insertException("sms", mEdit.getText().toString());
                mEdit.setText("");
                populateList(root, repo);

                Toast.makeText(
                        getActivity(), "Inserted successfully", Toast.LENGTH_SHORT
                ).show();
            }
        });

        populateList(root, repo);

        return root;
    }

    private void populateList(View root, Repository repo) {
        final ListView listView = (ListView) root.findViewById(R.id.listView);

        //generate list
        List<Exception> list = repo.getAll("sms");

        //instantiate custom adapter
        MyCustomAdapter adpt = new MyCustomAdapter(list, getActivity());
        listView.setAdapter(adpt);
    }
}
