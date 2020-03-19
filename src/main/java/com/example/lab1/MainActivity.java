package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static String INFO = "INFO";
    private final static String DESCRIPTION = "DESCRIPTION";

    private TextView description;

    private Product[] products = {
        new Product("Brown eggs", "Raw organic brown eggs in a basket", 28.10),
        new Product("Sweet fresh stawberry", "Sweet fresh stawberry on the wooden table", 29.45),
        new Product("Asparagus", "Asparagus with ham on the wooden table", 18.95),
        new Product("Pesto with basil", "Italian traditional pesto with basil, chesse and oil", 17.68)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.listView);
        final ArrayList<String> list = new ArrayList<String>();

        description = (TextView) findViewById(R.id.textView);

        for (int i = 0; i < products.length; ++i) {
            list.add(products[i].getName());
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                description.setText(products[position].toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.open:
                Toast.makeText(this, "Open", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(INFO, "onResume() call");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(INFO, "onPause() call");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(INFO, "onStart() call");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(INFO, "onStop() call");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(INFO, "onDestroy() call");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(DESCRIPTION, description.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        description.setText(savedInstanceState.getString(DESCRIPTION));
    }
}