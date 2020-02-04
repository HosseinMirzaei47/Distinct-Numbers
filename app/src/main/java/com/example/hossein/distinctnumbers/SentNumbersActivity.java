package com.example.hossein.distinctnumbers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class SentNumbersActivity extends AppCompatActivity implements NumbersAdapter.ItemClickListener {

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
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    private void findViews() {

        recyclerView = findViewById(R.id.recyclerView);

        noNumbers = findViewById(R.id.noEntries);

    }

    @Override
    public void onItemClick(View view, int position) {

        String phoneNumber = ((TextView) Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(position)).itemView.findViewById(R.id.item)).getText().toString();
        sendMessage(phoneNumber);

    }

    private void sendMessage(String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        String sms = MyPreferenceManager.getInstance(this).getSms(this);
        intent.putExtra("sms_body", sms);
        startActivity(intent);

    }

}
