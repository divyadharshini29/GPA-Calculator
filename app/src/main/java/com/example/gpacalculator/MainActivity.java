package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.TotalCaptureResult;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnTouchListener;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Spinner c1gpa;
    private Spinner c2gpa;
    private Spinner c3gpa;
    private Spinner c4gpa;
    private Spinner c5gpa;
    private EditText c1;
    private EditText c2;
    private EditText c3;
    private EditText c4;
    private EditText c5;
    private Spinner c1credit;
    private Spinner c2credit;
    private Spinner c3credit;
    private Spinner c4credit;
    private Spinner c5credit;
    private Button compute;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private static String Tag ="MainActivity" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compute=(Button) findViewById(R.id.compute);
        c1gpa=(Spinner) findViewById(R.id.cr1);
        c2gpa=(Spinner) findViewById(R.id.cr2);
        c3gpa=(Spinner) findViewById(R.id.cr3);
        c4gpa=(Spinner) findViewById(R.id.cr4);
        c5gpa=(Spinner) findViewById(R.id.cr5);
        c1credit=(Spinner) findViewById(R.id.hr1);
        c2credit=(Spinner) findViewById(R.id.hr2);
        c3credit=(Spinner) findViewById(R.id.hr3);
        c4credit=(Spinner) findViewById(R.id.hr4);
        c5credit=(Spinner) findViewById(R.id.hr5);
        //R.array.grade (string array created in Strings.xml)
        //Dropdown list
        ArrayAdapter<String> gradeArray=new ArrayAdapter<String>
                (MainActivity.this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.grade));
        gradeArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //assign layout to display the spinner
        Integer[] items = new Integer[]{1,2,3,4};
        ArrayAdapter<Integer> credit=new ArrayAdapter<Integer>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,items);
        c1gpa.setAdapter(gradeArray); //finally assign the design spinner to the array adapter
        c2gpa.setAdapter(gradeArray);
        c3gpa.setAdapter(gradeArray);
        c4gpa.setAdapter(gradeArray);
        c5gpa.setAdapter(gradeArray);
        c1credit.setAdapter(credit);
        c2credit.setAdapter(credit);
        c3credit.setAdapter(credit);
        c4credit.setAdapter(credit);
        c5credit.setAdapter(credit);
        c1=(EditText) findViewById(R.id.c1);
        c2=(EditText) findViewById(R.id.c2);
        c3=(EditText) findViewById(R.id.c3);
        c4=(EditText) findViewById(R.id.c4);
        c5=(EditText) findViewById(R.id.c5);

        Log.i(Tag,"Oncreate");
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(Tag,"pause");
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i(Tag,"stop");
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(Tag,"resume");
    }
    //Calculate GPA
    public void calculate(View v)
    {
        float total=0.0f;
        if(c1.getText().toString().isEmpty() || c2.getText().toString().isEmpty() || c3.getText().toString().isEmpty()
                || c4.getText().toString().isEmpty()|| c5.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Please Enter Subject Name", Toast.LENGTH_SHORT).show();
        }
        else{
            //Get selected item
            String c1selected = c1gpa.getSelectedItem().toString();
            float c1Point = convertGradeToPoint(c1selected);
            String c2selected = c2gpa.getSelectedItem().toString();
            float c2Point = convertGradeToPoint(c2selected);
            String c3selected = c3gpa.getSelectedItem().toString();
            float c3Point = convertGradeToPoint(c3selected);
            String c4selected = c4gpa.getSelectedItem().toString();
            float c4Point = convertGradeToPoint(c4selected);
            String c5selected = c5gpa.getSelectedItem().toString();
            float c5Point = convertGradeToPoint(c5selected);
            int c1hr = (int) c1credit.getSelectedItem();
            int c2hr = (int) c2credit.getSelectedItem();
            int c3hr = (int) c3credit.getSelectedItem();
            int c4hr = (int) c4credit.getSelectedItem();
            int c5hr = (int) c5credit.getSelectedItem();
            int totalcredit = c1hr + c2hr + c3hr + c4hr + c5hr;
            total = ((c1Point * c1hr) + (c2Point * c2hr) + (c3Point * c3hr) + (c4Point * c4hr) + (c5Point * c5hr)) / totalcredit;
            total=Math.round(total);
            TextView result = (TextView) findViewById(R.id.totalLbl);
            result.setText(String.valueOf(total));
            c1credit.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    compute.setEnabled(true);
                    c1credit.setEnabled(true);
                    return false;//to enable hr scroll(Must)
                }
            });
            setBackgorund(total);
        }

    }
    public float convertGradeToPoint(String grade)
    {
        float point=0.0f;
        switch(grade)
        {
            case "A":
                point=4.0f;
                break;
            case "A-":
                point=3.7f;
                break;
            case "B+":
                point=3.3f;
                break;
            case "B":
                point=3.0f;
                break;
            case "B-":
                point=2.7f;
                break;
            case "C+":
                point=2.3f;
                break;
            case "C":
                point=2.0f;
                break;
            case "C-":
                point=1.7f;
                break;
            case "D+":
                point=1.3f;
                break;
            case "D":
                point=1.0f;
                break;
            case "F":
                point=0f;
                break;



        }
        return point;
    }
    public void setBackgorund(float total)
    {
        if(total>3.5)
        {
            findViewById(R.id.layout).setBackgroundColor(getResources().getColor(R.color.Pink));
        }
        else if(total>2.5)
        {
            findViewById(R.id.layout).setBackgroundColor(getResources().getColor(R.color.Orange));
        }
        else
        {
            findViewById(R.id.layout).setBackgroundColor(getResources().getColor(R.color.Yellow));
        }

    }

}
