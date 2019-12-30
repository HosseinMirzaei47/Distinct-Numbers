package com.example.hossein.distinctnumbers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;


public class MyPreferenceManager {

    private static MyPreferenceManager instance = null;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    private MyPreferenceManager(Context context) {

        sharedPreferences = context.getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public static MyPreferenceManager getInstance(Context context) {

        if (instance == null)
            instance = new MyPreferenceManager(context);

        return instance;

    }

    public void putNumbers(ArrayList<String> numbers) {

        Gson gson = new Gson();
        String strNumbers = gson.toJson(numbers, IntegerArrayModel.class);
        editor.putString("Numbers", strNumbers);
        editor.apply();

    }

    public ArrayList<String> getNumbers() {

        Gson gson = new Gson();
        String numbers = sharedPreferences.getString("Numbers", null);

        if (numbers == null)
            return new IntegerArrayModel();

        return gson.fromJson(numbers, IntegerArrayModel.class);

    }

}