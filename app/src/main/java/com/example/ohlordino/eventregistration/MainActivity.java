package com.example.ohlordino.eventregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //tag = 2 => not registered. 1=> registered 0=> already participated 4=>paid
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> events = Arrays.asList("Paper Presentation", "Poster Presentation", "Challengica"
        , "BeyCode", "Anwesha", "Deal or No Deal", "Al 'Patch' ino", "TechTrix", "Aeroplane Chess",
                "Cryptothon", "Knock Off Tournament", "Criminal Case", "A Walk in the Dark");

        int id = 0;
        final int[] status = new int[1];
        status[0] = 1;
        GridLayout grid = (GridLayout) findViewById(R.id.grid);

        for(String event : events)
        {
            final TextView currEvent = new TextView(this);
            currEvent.setText(event);
            currEvent.setId(++id);
            currEvent.setTag(2);
            currEvent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            currEvent.setBackgroundResource(R.drawable.normalstate);
            //demonstrating disabled state.
            if(id % 5 == 0) {
                currEvent.setBackgroundResource(R.drawable.disabled);
                currEvent.setTag(0);
            }
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(
                    GridLayout.UNDEFINED,GridLayout.FILL,1f),
                    GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f));

            currEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    String viewId = getResources().getResourceName(currEvent.getId());
//                    Toast.makeText(MainActivity.this, viewId, Toast.LENGTH_SHORT).show();
                    if(currEvent.getTag() == (Object)2)
                    {
                        currEvent.setTag(1);
                        currEvent.setBackgroundResource(R.drawable.selected);
                    }

                    else if (currEvent.getTag() == (Object)1)
                    {
                            currEvent.setTag(2);
                            currEvent.setBackgroundResource(R.drawable.normalstate);


                    }
                }
            });
            grid.addView(currEvent, params);
        }

        Button submit = new Button(this);
        submit.setText("Submit");
        submit.setTag(3);
        final int size = id;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                for (int j = 1; j <= size; j++)
                {
                    String findId = "R.id." + j;
                    TextView text = (TextView) findViewById(j);
                    if(text.getTag() == (Object)1 && text != null)
                    {
                        text.setBackgroundResource(R.drawable.paid);
                        text.setTag(4);
                    }
                }
            }
        });
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f),
                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f));
        grid.addView(submit, params);

    }
}
