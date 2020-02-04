package com.example.hossein.distinctnumbers;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SentNumbersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NumbersAdapter adapter;
    private TextView noNumbers;

    private ArrayList<String> numbers = MyPreferenceManager.getInstance(this).getSelectedNumbers();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        findViews();

        if (numbers.size() <= 0)
            noNumbers.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NumbersAdapter(numbers, SentNumbersActivity.this);
        recyclerView.setAdapter(adapter);

    }

    private void findViews() {

        recyclerView = findViewById(R.id.recyclerView);

        noNumbers = findViewById(R.id.noEntries);

    }

}
