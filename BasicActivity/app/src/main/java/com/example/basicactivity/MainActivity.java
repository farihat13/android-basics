package com.example.basicactivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textView = findViewById(R.id.textView);
        textView.setText("Start");
//        textView.setText(Integer.toString(0));

        Button randomRangeButton = findViewById(R.id.random_in_range_button);
        randomRangeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");
                Intent randActivity = new Intent(getApplicationContext(), RandomInRangeActivity.class);

                String count = textView.getText().toString();
                int range = 0;
                try {
                    range = Integer.parseInt(count);
                } catch (NumberFormatException e) {
                }
                Bundle bundle = new Bundle();
                bundle.putInt("range", range);
                randActivity.putExtras(bundle);
                startActivity(randActivity);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countMe(View view) {
        if (textView != null) {
            String count = textView.getText().toString();
            try {
                int c = Integer.parseInt(count);
                textView.setText(Integer.toString(c + 1));
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(this, R.string.error_msg, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    public void showRandom(View view) {
        int c = new Random().nextInt(100);
        if (textView != null) textView.setText(Integer.toString(c));

    }
}
