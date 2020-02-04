package com.example.hossein.distinctnumbers;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.ViewHolder> {

    private ArrayList<String> numbers;
    private ItemClickListener listener;
    private Context context;

    private ArrayList<String> selectedNumbers = new ArrayList<>(MyPreferenceManager.getInstance(context).getSelectedNumbers());

    public NumbersAdapter(ArrayList<String> numbers, Context context) {
        this.numbers = numbers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_number_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder parent, int position) {
        parent.numberTextView.setText(numbers.get(position));

        /*for (Integer element :
                selectedNumbers) {
            if (position == element) {
                parent.checkIcon.setVisibility(View.VISIBLE);
                parent.numberTextView.setTextColor(Color.parseColor("#58b747"));
            }
        }*/

    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView numberTextView;
        public ImageView checkIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numberTextView = itemView.findViewById(R.id.item);
            checkIcon = itemView.findViewById(R.id.checkIcon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (listener != null)
                listener.onItemClick(view, getAdapterPosition());
        }
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
