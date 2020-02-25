package com.example.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SelectItems extends AppCompatActivity {
    private static final String LOG_TAG = SelectItems.class.getSimpleName();
    public static final String ITEM = "ITEM";
    private LinearLayout linearLayout;
    private String[] itemList = {"Apple", "Orange", "Pen", "Pencil", "Veg", "Meat", "Notebook", "Dress"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_items);

        linearLayout = findViewById(R.id.items_list);
        for (String item : itemList)
            createButton(item);
    }

    private void createButton(String text) {
        final Button button = new Button(this);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(20, 20, 20, 20);
        button.setLayoutParams(lparams);
        button.setLineSpacing(1, 2);
        button.setText(text);
        button.setTextColor(0xffffffff);
        button.setBackgroundColor(0xff00574B); // hex color 0xAARRGGBB
        button.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = button.getText().toString();
                Intent selectedItem = new Intent(getApplicationContext(), MainActivity.class);
                selectedItem.putExtra(ITEM, item);
                setResult(RESULT_OK, selectedItem);
                Log.d(LOG_TAG, "End SelectItem");
                finish();
            }
        });

        linearLayout.addView(button);
    }

}
