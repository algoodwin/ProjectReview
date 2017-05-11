package edu.good9016csumb.projectreview;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.good9016csumb.projectreview.Helperobjects.DatabaseHelper;
import edu.good9016csumb.projectreview.Helperobjects.User;

public class CreateAccount extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }
    int tries =0;
    public void createUser(View view){
        SimpleDateFormat fmt = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss Z");
        Date date = Calendar.getInstance().getTime();
        // Grab username and password
        String user = ((EditText)findViewById(R.id.username)).getText().toString();
        String pass = ((EditText)findViewById(R.id.password)).getText().toString();

        // Make a datatbase
        DatabaseHelper db = new DatabaseHelper(this);
        //Todo: Check format of User input (user and password are accoridign to thier requirments) with Regex
        if(!checkUserInput(user, pass))
        {
            AlertDialog alertDialogBuilder = new AlertDialog.Builder(this)
                    .setTitle("Standards Not Met")
                    .setNegativeButton("Reinput Information", null )
                    .create();
            alertDialogBuilder.show();
            return;
        }
        //Add it to the Database

        try {
            db.addUser(new User(user, pass, false));

        }
        catch(Exception e)
        {
            /// if fail-Toast
            tries++;

            if(tries == 2 ) {
                Toast.makeText(this, "User already exists", Toast.LENGTH_LONG).show();
                finish();
            }
            if (tries ==1) {
                AlertDialog alertDialogBuilder = new AlertDialog.Builder(this)
                        .setTitle("That user alreadys exists")
                        .setNegativeButton("Reinput Information", null)
                        .create();
                alertDialogBuilder.show();
            }
            return;

        }
        Toast.makeText(this, "user created", Toast.LENGTH_LONG).show();

        finish(); //Gets back to the main

    }

    private boolean checkUserInput(String username, String password)
    {
        //// TODO: 5/2/17 check things
      /* final String STANDARDS ="((?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\\\S+$).{5,}$)";
        // Create a Pattern object with a regex expression
        Pattern myPattern = Pattern.compile("(?=.*[0-9])(?=.*[!@#$])(?=\\S+$)(?=.*[A-Za-z]{3,})");
        // Create a matcher object to check.
        Matcher userisValid, passIsValid;
        // To check a string:
        userisValid = myPattern.matcher(username);
        passIsValid = myPattern.matcher(password);
        // Check if valid or not.
        boolean checkUser = userisValid.lookingAt();
        boolean checkPass = passIsValid.lookingAt();

        if(checkUser == true && checkPass == true)
        {
            return true;
        }

        else
            return false;*/
      return true;

    }

}
