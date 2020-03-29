package com.example.onlineshop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static String INFO = "INFO";
    private final static String DESCRIPTION = "DESCRIPTION";
    public final static String EXTRA_MESSAGE = "Extra message";

    private FusedLocationProviderClient fusedLocationClient;
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
        final Button sendText = findViewById(R.id.send_text);
        final Button signUp = findViewById(R.id.sign_up);

        description = (TextView) findViewById(R.id.textView);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1
                );
        }

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

        sendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Open.class);
                String message = description.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SIgnUp dialog = new SIgnUp();
                dialog.show(getSupportFragmentManager(), "Sign Up");
            }
        });

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Toast.makeText(
                                    MainActivity.this, "Latitude: " + location.getLatitude() + "\nLatitude: " + location.getLongitude(), Toast.LENGTH_SHORT
                            ).show();
                            Log.i("LOCATION", location.toString());
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("LOCATION", e.getMessage());
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            case R.id.open:
                Toast.makeText(this, "Open", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sensor:
                Toast.makeText(this, "Sensor", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SensorActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

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