package com.uodelana.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dataList = new ArrayList<>(
                Arrays.asList("Edmonton", "Vancouver", "Moscow", "Sydney", "Lagos", "Abuja"));

        // An array adapter generates a layout for each item in the given list
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        // Get list view in `activity_main`
        ListView cityList = findViewById(R.id.city_list);
        cityList.setAdapter(cityAdapter);
        // Handle clicks on list item
        cityList.setOnItemClickListener((adapterView, view, i, l) -> onCityClicked(i));

        Button addCity = findViewById(R.id.new_city_btn);
        addCity.setOnClickListener(view -> onNewCityClicked());
    }

    void onCityClicked(int index) {
        String item = dataList.get(index);
        cityAdapter.remove(item);

        cityAdapter.notifyDataSetChanged();
    }

    void onNewCityClicked() {
        EditText newCityField = findViewById(R.id.new_city_field);
        String newCity = newCityField.getText().toString();

        cityAdapter.add(newCity);
        cityAdapter.notifyDataSetChanged();

        newCityField.setText("");

        Toast.makeText(this, "Added '" + newCity + "'", Toast.LENGTH_SHORT).show();
    }
}