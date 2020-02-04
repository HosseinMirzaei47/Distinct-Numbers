package com.example.hossein.distinctnumbers;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button addNumber;
    private Button clearData;
    private Button numbersList;
    private Button sentNumbersList;
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

        sentNumbersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SentNumbersActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });

    }

    private void addToList() {

        addNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNumber = numberET.getText().toString();

                if (!numbers.contains(phoneNumber) && !phoneNumber.equals("")) {

                    if (isIrancell(phoneNumber)) {
                        numbers.add(numbers.size(), phoneNumber);
                        Log.i("jalil", "if");
                    } else {
                        Log.i("jalil", "else");
                        numbers.add(0, phoneNumber);
                    }

                }

                MyPreferenceManager.getInstance(MainActivity.this).putNumbers(numbers);

                for (String elm :
                        numbers) {
                    Log.i("jalil", elm);
                }
                Log.e("jalil", "__________________________________________");

                numberET.setText("");

            }
        });

    }

    private boolean isIrancell(String string) {

        if (string.startsWith("090") || string.startsWith("093"))
            return true;
        else
            return false;

    }

    private void clearData() {

        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDatabaseClearance();
            }
        });

    }

    private void confirmDatabaseClearance() {

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/sans_normal.ttf");
        TextView textView1 = null;

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Attention")
                .setMessage("تمامی شماره ها رو پاک کنم؟")
                .setPositiveButton("آره", null)
                .setNegativeButton("نه، بیخیال", null)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPreferenceManager.getInstance(MainActivity.this).clearNumbers();
                numbers.clear();
                Toast.makeText(MainActivity.this, "همه شماره ها پاک شد!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        textView1 = (TextView) dialog.findViewById(android.R.id.message);
        textView1.setTypeface(typeface);

        TextView textView2 = (TextView) dialog.findViewById(android.R.id.message);
        textView2.setTypeface(typeface);

        Button btn1 = (Button) dialog.findViewById(android.R.id.button1);
        btn1.setTypeface(typeface);

        Button btn2 = (Button) dialog.findViewById(android.R.id.button2);
        btn2.setTypeface(typeface);

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
        sentNumbersList = findViewById(R.id.sentList);
        clearData = findViewById(R.id.clearData);
        numberET = findViewById(R.id.editText);

    }

}
