package edu.good9016csumb.projectreview;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.create_account).setOnClickListener(this);
        findViewById(R.id.manage_syst).setOnClickListener(this);


    }

    public void onClick(View view) {
        Log.d("Clicked", "yo");
        Class nextActivity = null;
        //uses id
        switch (view.getId())
        {
            case R.id.create_account:
                nextActivity = CreateAccount.class; //goes to the next activit of the new one which one to go to
                break;
            case R.id.place_hold:
                //Todo: put next activity to placeholder class

                break;
            case R.id.cancel_hold:
                //Todo: put next activity to cancel  hold class
                break;
            case R.id.manage_syst:
                Log.d("Clicked", "yo2");
                nextActivity = ManageSystem.class;
                break;
        }
        // Intenet
        Intent intent = new Intent(this, nextActivity); //gives the next activity to it
        //Starts the next activity
        startActivity(intent);



    }
}
