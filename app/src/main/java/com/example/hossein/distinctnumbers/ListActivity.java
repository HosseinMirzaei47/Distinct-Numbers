package com.example.hossein.distinctnumbers;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class ListActivity extends AppCompatActivity implements NumbersAdapter.ItemClickListener {

    private static final String MESSAGE = "با سلام خد مت شما.";
    private ArrayList<String> numbers = new ArrayList<>();
    private ArrayList<Integer> selectedNumbers = MyPreferenceManager.getInstance(this).getSelectedNumbers();

    private RecyclerView recycler;
    private NumbersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        findViews();
        getExtras();

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NumbersAdapter(numbers, this);
        adapter.setClickListener(this);
        recycler.setAdapter(adapter);

    }

    private void getExtras() {
        numbers = getIntent().getStringArrayListExtra("NumbersList");
    }

    @Override
    public void onItemClick(View view, int position) {

        openSMSandChangeColor(position);

    }

    private void openSMSandChangeColor(int position) {
        String phoneNumber = ((TextView) Objects.requireNonNull(recycler.findViewHolderForAdapterPosition(position)).itemView.findViewById(R.id.item)).getText().toString();
        selectedNumbers.add(position);
        ((TextView) Objects.requireNonNull(recycler.findViewHolderForAdapterPosition(position)).itemView.findViewById(R.id.item)).setTextColor(Color.parseColor("#008080"));
        MyPreferenceManager.getInstance(this).putSelectedNumbers(selectedNumbers);
        sendMessage(phoneNumber);
    }

    private void sendMessage(String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", MESSAGE);
        startActivity(intent);

    }

    private void findViews() {
        recycler = findViewById(R.id.recyclerView);
    }

}
