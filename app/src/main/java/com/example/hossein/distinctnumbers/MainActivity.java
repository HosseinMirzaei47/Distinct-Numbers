package com.example.hossein.distinctnumbers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button addNumber;
    private Button clearData;
    private Button numbersList;
    private EditText numberET;

    private ArrayList<String> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        checkForBackupData();

        addToList();

        showList();

        clearData();

    }

    private void checkForBackupData() {
        numbers = MyPreferenceManager.getInstance(this).getNumbers();
    }

    private void showList() {
        numbersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyPreferenceManager.getInstance(MainActivity.this).putNumbers(numbers);
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("NumbersList", numbers);
                MainActivity.this.startActivity(intent);

            }
        });
    }

    private void addToList() {

        addNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!numbers.contains(numberET.getText().toString()) && !numberET.getText().toString().equals("")) {
                    numbers.add(numbers.size(), numberET.getText().toString());
                }
                Log.i("jalil", String.valueOf(numbers.size()));
            }
        });

    }

    private void clearData() {

        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers.clear();
                MyPreferenceManager.getInstance(MainActivity.this).putNumbers(numbers);
            }
        });

    }

    @Override
    protected void onResume() {
        numberET.setText("");
        numbers.clear();
        numbers = MyPreferenceManager.getInstance(this).getNumbers();
        super.onResume();
    }

    private void findViews() {

        addNumber = findViewById(R.id.addNumber);
        numbersList = findViewById(R.id.numbersList);
        clearData = findViewById(R.id.clearData);
        numberET = findViewById(R.id.editText);

    }

}
