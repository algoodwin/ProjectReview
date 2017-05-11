package edu.good9016csumb.projectreview;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import edu.good9016csumb.projectreview.Helperobjects.DatabaseHelper;
import edu.good9016csumb.projectreview.Helperobjects.User;

public class Log_In extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<User> userArray;
    ArrayList<User> data;
    private SQLiteDatabase readableDatabase;
    Intent next = getIntent();
    String user;
    String pass;
    String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);
        Button log = (Button) findViewById(R.id.login_button);
        data = new ArrayList<>();
        mHelper = new DatabaseHelper(this);
        mHelper.getAllUsers();
        user = "";
        pass = "";
        id="";

    }

    public void login(View view) {

        user = ((EditText) findViewById(R.id.username_login)).getText().toString();
        pass = ((EditText) findViewById(R.id.password_login)).getText().toString();
        Log.d("USERNAME", user);
        Log.d("PASSWORD", pass);
        Bundle extras = getIntent().getExtras();



        id = extras.getString("R.ID");
        // Make a datatbase
        DatabaseHelper db = new DatabaseHelper(this);
        if (id.equals("manage_syst")) {
            Log.d("IN ID THINGY", "YES HERE");
            if (user.equals("!admin2") && pass.equals("!admin2")) {
                Intent intent = new Intent(this, ManageSystem.class);//gives the next activity to it
                startActivity(intent);

            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                        .setTitle("That Username and Password Do Not Exist")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();

                return;
            }


        } else { ////Todo: Check format of User input (user and password are accoridign to thier requirments) with Regex
            if (!checkUserInput(user, pass)) {
                AlertDialog alertDialogBuilder = new AlertDialog.Builder(this)
                        .setTitle("That Username and Password Do Not Exist")
                        .setNegativeButton("Reinput Information", null)
                        .create();
                alertDialogBuilder.show();
                return;
            }

            if (checkUserInput(user, pass)) {

                if (id == null) {
                    Log.d("NOTHING", "NOTHING HOMESKILLET");
                }
                    Log.d("WHAT'S UP", id.toString());
                if (id.equals("place_hold")) {//TODO

                    Intent intent = new Intent(this, Place_Hold.class);//gives the next activity to it
                    intent.putExtra("user", user);
                    //Starts the next activity
                    startActivity(intent);
                }
                if (id.equals("cancel_hold")) {//TODO

                    //TODO:   Intent intent = new Intent(this, Cancel_Hold.class);//gives the next activity to it
                    //Starts the next activity
                    //startActivity(intent);
                }
            }

        }
    }
    private boolean checkUserInput(String usern, String pa) {
        Log.d("CHECKING", "HERE");
        data = mHelper.getAllUsers();
        boolean u = false;
        boolean p = false;

        for(int i=0; i < data.size(); i++)
        {
            Log.d("ADAS", data.get(i).username);
            if(data.get(i).getUsername().equals(usern)) {
                Log.d("USER", "TRUE");
                u = true;
            }
            if(data.get(i).getPassword().equals(pa)) {
                p = true;
                Log.d("PASS", "TRUE");
            }
            Log.d("CHECKING", "YES HERE");

        }

        Log.d("WHATS IN THE LIST", Arrays.toString(data.toArray()));



        if(p == true && u == true) {
            Log.d("YESSS", "TRUE");
            return true;
        }
        Log.d("NOOO", "FALSE");
        return false;
    }

}




