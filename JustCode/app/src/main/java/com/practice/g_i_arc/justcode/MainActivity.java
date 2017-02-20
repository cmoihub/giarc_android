package com.practice.g_i_arc.justcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int weekday = 5;
        int weekend = 9;
        int optimalHours = 7 * 8;
        int actualHours = weekday*3  + weekend * 2;
        int solution = optimalHours - actualHours;
        displayInt(solution);

        display("This is Box 1",1);
        display("& this is Box 2", 2);
        display("& look! Box 3!!!", 3);
    }

    public void display(String text, int i){
        int viewID;
        switch (i){
            case 1: viewID = R.id.display_text_view;
                break;
            case 2: viewID = R.id.display_text_view_2;
                break;
            case 3: viewID = R.id.display_text_view_3;
                break;
            default: viewID = R.id.display_text_view;
                break;
        }
        TextView t = (TextView) findViewById(viewID);
        t.setText(text);
    }

    public void displayInt(int i){
        TextView t = (TextView) findViewById(R.id.display_text_view_4);
        t.setText(""+i);
    }
}