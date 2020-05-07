package com.example.blockit.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.blockit.R;
import com.example.blockit.storage.Exception;
import com.example.blockit.storage.Repository;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private List<Exception> list = new ArrayList<>();
    private Context context;

    public MyCustomAdapter(List<Exception> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return list.indexOf(pos);
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_layout, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        TextView date = (TextView) view.findViewById(R.id.list_item_date);

        listItemText.setText(list.get(position).value);
        date.setText(list.get(position).timestamp);

        final Repository repo = new Repository(this.context);

        //Handle buttons and add onClickListeners
        //Handle buttons and add onClickListeners
        ImageButton deleteBtn = (ImageButton)view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                repo.deleteOne(list.get(position));
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}