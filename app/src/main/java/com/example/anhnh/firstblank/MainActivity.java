package com.example.anhnh.firstblank;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText numA, numB, numC;
    Button mButton;
    TextView mText;
    String text = "Result: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numA =(EditText) findViewById(R.id.txtNumA);
        numB =(EditText) findViewById(R.id.txtNumB);
        numC =(EditText) findViewById(R.id.txtNumC);
        mButton = (Button) findViewById(R.id.btnWelcome);
        mText = (TextView) findViewById(R.id.txtView);
        numA.requestFocus();


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check text box A
                if(numA.getText().toString().length()==0){
                    numA.setError("This field should't be empty");
                    numA.requestFocus();
                    return;
                }
                //Check text box B
                if(numB.getText().toString().length()==0){
                    numB.setError("This field should't be empty");
                    numB.requestFocus();
                    return;
                }
                //Check text box C
                if(numC.getText().toString().length()==0){
                    numC.setError("This field should't be empty");
                    numC.requestFocus();
                    return;
                }
                //if no error occur
                float a = Float.parseFloat(numA.getText().toString());
                float b = Float.parseFloat(numB.getText().toString());
                float c = Float.parseFloat(numC.getText().toString());


                if(a==0){
                    if(b==0&&c==0){
                        text = "Infinite solutions !!!";
                    }
                    if(b==0&&c!=0){
                        text="No solution !!!";
                    }
                }else{
                    calculate(a,b,c);
                }

                mText.setText(text);
                InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        };



        mButton.setOnClickListener(listener);

    }

    protected void calculate(float a, float b, float c){
        float delta = (float) Math.pow(b,2)-4*a*c;
        mText.requestFocus();
        if(delta<0){
            mText.setBackground(getResources().getDrawable(R.drawable.back));
            text="No solution !!!";
        }
        if(delta==0){
            mText.setBackground(getResources().getDrawable(R.drawable.back));
            text = "1 solution: "+ -b/(2*a);
        }
        if(delta>0){
            mText.setBackground(getResources().getDrawable(R.drawable.back));
            DecimalFormat format = new DecimalFormat("0.00");
            text = "Solution 1 = "+format.format((-b+Math.sqrt(delta))/2*a) +"\nSolution 2 = "+ format.format((-b-Math.sqrt(delta))/2*a);
        }
    }


}
