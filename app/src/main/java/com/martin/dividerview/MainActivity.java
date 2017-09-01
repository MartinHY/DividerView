package com.martin.dividerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected Button margin;
    protected Button space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.margin) {
            intent = new Intent(this,MarginDividerAct.class);
        } else if (view.getId() == R.id.space) {
            intent = new Intent(this,SpaceDividerAct.class);
        }
        this.startActivity(intent);
    }

    private void initView() {
        margin = (Button) findViewById(R.id.margin);
        margin.setOnClickListener(MainActivity.this);
        space = (Button) findViewById(R.id.space);
        space.setOnClickListener(MainActivity.this);
    }
}
