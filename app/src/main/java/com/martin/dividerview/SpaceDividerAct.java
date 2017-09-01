package com.martin.dividerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SpaceDividerAct extends AppCompatActivity {

    DividerSpaceLayout dividerLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_space_divider);
        dividerLinearLayout = (DividerSpaceLayout) findViewById(R.id.group);
//        dividerLinearLayout.setBackgroundColor(Color.WHITE);
    }

}
