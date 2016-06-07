package jbk.publicpay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class payment extends AppCompatActivity {
    Firebase myFirebaseRef;
String login_id,amount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://publicpay.firebaseio.com/");


        setContentView(R.layout.activity_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        SharedPreferences sharedpreferences = getSharedPreferences("shit", Context.MODE_PRIVATE);
        login_id = sharedpreferences.getString("uid", "error");
        Toast.makeText(getApplicationContext(), login_id, Toast.LENGTH_SHORT).show();
    }

            public void get(View v) {
                amount = ((EditText) findViewById(R.id.editText4)).getText().toString();
                SharedPreferences sharedpreferences = getSharedPreferences("shit", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("amount", amount);
                editor.commit();
                myFirebaseRef.child(login_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.getValue() != null) {
                            UserClass user = snapshot.getValue(UserClass.class);
                            String passcheck = ((EditText) findViewById(R.id.editText5)).getText().toString();


                            if (user.getPassword().compareTo(passcheck) == 0) {
                                Toast.makeText(getApplicationContext(), "Welcome " + user.getName(), Toast.LENGTH_SHORT).show();
                                Intent inte = new Intent(getApplicationContext(), get.class);
                                startActivity(inte);





                            } else {
                                Toast.makeText(getApplicationContext(), "Pass wrng", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "User not founf", Toast.LENGTH_LONG).show();
                        }


                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        //System.out.println("The read failed: " + firebaseError.getMessage());
                    }

                });

            }


            public void give(View v) {
                amount = ((EditText) findViewById(R.id.editText4)).getText().toString();
                SharedPreferences sharedpreferences = getSharedPreferences("shit", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("amount", amount);
                editor.commit();
                myFirebaseRef.child(login_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.getValue() != null) {
                            UserClass user = snapshot.getValue(UserClass.class);
                            String passcheck = ((EditText) findViewById(R.id.editText5)).getText().toString();
                            if (user.getPassword().compareTo(passcheck) == 0) {
                                Toast.makeText(getApplicationContext(), "Welcome " + user.getName(), Toast.LENGTH_SHORT).show();

                                Intent inten = new Intent(getApplicationContext(), give.class);
                                startActivity(inten);


                            } else {
                                Toast.makeText(getApplicationContext(), "Pass wrng", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "User not founf", Toast.LENGTH_LONG).show();
                        }


                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        //System.out.println("The read failed: " + firebaseError.getMessage());
                    }

                });

            }


    public void transactions(View view)
    {
        Intent intent = new Intent(this,transactions.class);
        startActivity(intent);
    }

}
