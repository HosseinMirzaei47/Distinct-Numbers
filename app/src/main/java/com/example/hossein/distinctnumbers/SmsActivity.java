package com.example.hossein.distinctnumbers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {

    private EditText smsText;
    private Button applyChanges;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_text);
        findViews();

        smsText.setText(MyPreferenceManager.getInstance(this).getSms(this));

        applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyPreferenceManager.getInstance(SmsActivity.this).putSms(smsText.getText().toString());
                onBackPressed();

            }
        });

    }

    private void findViews() {

        smsText = findViewById(R.id.smsText);
        applyChanges = findViewById(R.id.smsTextConfirm);

    }

}
