package edu.good9016csumb.projectreview;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import edu.good9016csumb.projectreview.Helperobjects.Book;
import edu.good9016csumb.projectreview.Helperobjects.DatabaseHelper;
import edu.good9016csumb.projectreview.Helperobjects.Transactions;


public  class Place_Hold extends AppCompatActivity {
    static Calendar timepickup, datepickup;//pickup for the Time Picker for PickUP
    static String pickupHour;
    static String pickupMinute;
    static String returnHour;
    static String returnMinute;
    static String pickupMonth;
    static String pickupDay;
    static String pickupyear;
    static String returnMonth;
    static String returnDay;
    static String returnyear;
    static String pickup_ampm;
    static String return_ampm;
    static String userperson;
    private DatabaseHelper helper;
    ArrayList<Transactions> trans;



    DatePicker simpleDatePicker;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place__hold);
        pickupHour = "" ;
        pickupMinute ="" ;
        returnHour ="" ;
        returnMinute ="" ;
        pickupMonth ="" ;
        pickupDay ="";
        pickupyear ="";
        returnMonth ="";
        returnDay ="";
        return_ampm = "";
        returnyear ="";
        pickup_ampm = "";
        userperson = "";
        trans = null;
        Bundle extras = getIntent().getExtras();
        userperson = extras.getString("user");

    }

    public void reserveABook(View view)
    {
        pickupHour = ((EditText) findViewById(R.id.pick_hour)).getText().toString();
        pickupMinute = ((EditText) findViewById(R.id.pick_min)).getText().toString();
        returnHour = ((EditText) findViewById(R.id.return_hour)).getText().toString();
        returnMinute = ((EditText) findViewById(R.id.return_min)).getText().toString();
        pickup_ampm = ((EditText) findViewById(R.id.pick_am_pm)).getText().toString();
        return_ampm = ((EditText) findViewById(R.id.return_am_pm)).getText().toString();
        pickupMonth = ((EditText) findViewById(R.id.pick_month)).getText().toString();
        pickupDay = ((EditText) findViewById(R.id.pick_day)).getText().toString();
        pickupyear = ((EditText) findViewById(R.id.pick_year)).getText().toString();
        returnMonth = ((EditText) findViewById(R.id.return_month)).getText().toString();
        returnDay = ((EditText) findViewById(R.id.return_day)).getText().toString();
        returnyear = ((EditText) findViewById(R.id.return_year)).getText().toString();

        String pickupTime = "";
        String returnTime = "";
        String pickupDate = "";
        String returnDate = "";
        pickupTime = pickupHour + ":" + pickupMinute + " " + pickup_ampm;
        returnTime = returnHour + ":" + returnMinute + " " + return_ampm;
        pickupDate = pickupMonth + "/" + pickupDay + "/" + pickupyear;
        returnDate = returnMonth +"/" + returnDay + "/" + returnyear;
        Intent intent = new Intent(this, Choose_Book.class);//gives the next activity to it
        intent.putExtra("pt", pickupTime);
        intent.putExtra("rt", returnTime);
        intent.putExtra("pd", pickupDate);
        intent.putExtra("rd", returnDate);
        intent.putExtra("type", "place hold");
        startActivity(intent);
        }
    }



