package com.martin.dividerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MarginDividerAct extends AppCompatActivity {
    DividerLinearLayout dividerLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_margin_divider);
        dividerLinearLayout = (DividerLinearLayout) findViewById(R.id.group);
//        dividerLinearLayout.setBackgroundColor(Color.WHITE);
    }

}
