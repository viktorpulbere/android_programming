package com.example.blockit.ui.blacklist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class BlacklistFragment extends Fragment {

    private BlacklistViewModel blacklistViewModel;
    private EditText mEdit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        blacklistViewModel =
                ViewModelProviders.of(this).get(BlacklistViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_blacklist, container, false);
        final Context context = getActivity();
        final Repository repo = new Repository(context);

        ImageButton add = root.findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdit = (EditText) root.findViewById(R.id.txtSearch);
                repo.insertException("number", mEdit.getText().toString());
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
        List<Exception> list = repo.getAll("number");

        //instantiate custom adapter
        MyCustomAdapter adpt = new MyCustomAdapter(list, getActivity());
        listView.setAdapter(adpt);
    }
}
