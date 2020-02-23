package com.example.basicactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class RandomInRangeActivity extends AppCompatActivity {
    private static final String LOG_TAG = RandomInRangeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random);
        Log.i(LOG_TAG, "in random in range");

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        int range = bundle.getInt("range");
        range = Math.max(1, range);
        TextView randomRangeTextView = findViewById(R.id.random_range_textview);
        Log.i(LOG_TAG, "range " + range);
        randomRangeTextView.setText(String.format(Locale.getDefault(), "Here is a number between 0 and %d", range));
        int c = new Random().nextInt(range);
        ((TextView) findViewById(R.id.random_number_textview)).setText(String.format(Locale.getDefault(), "%d", c));


        Button previous = findViewById(R.id.previous_button);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }
}
