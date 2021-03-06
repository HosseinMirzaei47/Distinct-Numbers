package com.example.hossein.distinctnumbers;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ListActivity extends AppCompatActivity implements NumbersAdapter.ItemClickListener {

    private ArrayList<String> numbers = MyPreferenceManager.getInstance(this).getNumbers();
    private ArrayList<String> selectedNumbers = MyPreferenceManager.getInstance(this).getSelectedNumbers();

    private RecyclerView recycler;
    private NumbersAdapter adapter;
    private TextView noNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        findViews();
        getExtras();

        if (numbers.size() <= 0)
            noNumbers.setVisibility(View.VISIBLE);

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

        /*((TextView) Objects.requireNonNull(recycler.findViewHolderForAdapterPosition(position)).itemView.findViewById(R.id.item)).setTextColor(Color.parseColor("#008080"));
        Objects.requireNonNull(recycler.findViewHolderForAdapterPosition(position)).itemView.findViewById(R.id.checkIcon).setVisibility(View.VISIBLE);*/

        String phoneNumber = ((TextView) Objects.requireNonNull(recycler.findViewHolderForAdapterPosition(position)).itemView.findViewById(R.id.item)).getText().toString();
        sendMessage(phoneNumber);

        selectedNumbers.add(phoneNumber);
        MyPreferenceManager.getInstance(this).putSelectedNumbers(selectedNumbers);

        numbers.remove(position);
        adapter.notifyDataSetChanged();

        /*Collections.swap(numbers, position, numbers.size() - 1);
        adapter.notifyItemMoved(position, numbers.size() - 1);*/

        MyPreferenceManager.getInstance(this).putNumbers(numbers);

    }

    private void sendMessage(String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        String sms = MyPreferenceManager.getInstance(this).getSms(this);
        intent.putExtra("sms_body", sms);
        startActivity(intent);

    }

    private void findViews() {
        recycler = findViewById(R.id.recyclerView);
        noNumbers = findViewById(R.id.noEntries);
    }

}
