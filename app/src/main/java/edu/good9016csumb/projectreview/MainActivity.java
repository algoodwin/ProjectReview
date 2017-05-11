package edu.good9016csumb.projectreview;

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
        findViewById(R.id.place_hold).setOnClickListener(this);


    }

    public void onClick(View view) {
        Class nextActivity = null;
        //uses id
        String id="";
        switch (view.getId())
        {
            case R.id.create_account:
                nextActivity = CreateAccount.class;//goes to the next activit of the new one which one to go to
                id =null;
                break;

            case R.id.place_hold:
                nextActivity = Log_In.class;
                id = "place_hold";
                Log.d("IN PLACE HOLD", "ITS HERE BITCH");
                break;

            case R.id.cancel_hold:
                id = "cancel_hold";
                //Todo: put next activity to cancel  hold class
                break;

            case R.id.manage_syst:
                nextActivity = Log_In.class;
                id = "manage_syst";
                break;
        }
        // Intenet
        Intent intent = new Intent(this, nextActivity);//gives the next activity to it
        intent.putExtra("R.ID", id);
        //Starts the next activity
        startActivity(intent);



    }
}
