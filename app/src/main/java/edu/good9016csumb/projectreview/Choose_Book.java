package edu.good9016csumb.projectreview;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.R.*;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import edu.good9016csumb.projectreview.Helperobjects.Book;
import edu.good9016csumb.projectreview.Helperobjects.DatabaseHelper;
import edu.good9016csumb.projectreview.Helperobjects.Transactions;
import edu.good9016csumb.projectreview.Helperobjects.User;


public class Choose_Book extends AppCompatActivity {

    private String rtime, rdate, ptime, pdate, ttype;


    private DatabaseHelper helper;
    private ArrayAdapter<Transactions> adapterTrans;
    private ListView taskListView;

    private Button yesbtn;
    ArrayList<Transactions> t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place__hold);
        helper = new DatabaseHelper(this);
        t = null;
        taskListView = (ListView) findViewById(R.id.book_system);

        rtime ="";
        rdate = "";
        pdate = "";
        ptime="";
        ttype="";
        udpateUI();

    }

    private void udpateUI() {

        ArrayList<Transactions> taskListTrans = helper.getAllTrans();

       if (adapterTrans == null) {
            adapterTrans = new ArrayAdapter<Transactions>(this, R.layout.activity_place__hold, R.id.addhere);
           adapterTrans.addAll(taskListTrans);
           Log.d("ADAPTER THINGY", adapterTrans.toString());
           Log.d("TRANZZZ", taskListTrans.toString());
            taskListView.setAdapter(adapterTrans);
            //Log.d("TRANZZZ", taskListTrans.toString());
        } else {
            adapterTrans = new ArrayAdapter<Transactions>(this, R.layout.activity_place__hold);
            adapterTrans.addAll(taskListTrans);
            adapterTrans.notifyDataSetChanged();
        }
    }

    public void chooseBooks(View view){
        Bundle extras = getIntent().getExtras();
        ptime = extras.getString("pt");
        pdate = extras.getString("pd");
        rtime = extras.getString("rt");
        rdate = extras.getString("rd");
        ttype =extras.getString("type");
        Log.d("PTIME", ptime);
        Log.d("PDATE", pdate);
        Log.d("RTIME", rtime);
        Log.d("RDATE", rdate);
        Log.d("TTYPE", ttype);

        DatabaseHelper db = new DatabaseHelper(this);

        if(!ptime.matches("") && !pdate.matches("") && !rdate.matches("") && !rtime.matches(""))
        {
            Log.d("HERE IN THE THINGY", "YESSS");
          /*  db.addTrans(new Transactions(, t, n, p));
            AlertDialog.Builder builder = new AlertDialog.Builder(ManageSystem.this);
            builder.setMessage("Author: " + a+ "\nTitle: " + t +
                    "\nISBN: " + n +
                    "\nHourly Fee: $" + Double.parseDouble(p))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(ManageSystem.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            udpateUI();*/
        }
    }


}



