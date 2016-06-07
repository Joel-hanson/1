package jbk.publicpay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class give_password extends AppCompatActivity {
    Firebase myFirebaseRef1,myFirebaseRef;
    String uid,login;
    Integer coin,amount,coin1;
    String time;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z", Locale.ENGLISH);





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://publicpay.firebaseio.com/");
myFirebaseRef1=new Firebase("https://publicpay.firebaseio.com/trans");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_password);
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


    }

    public void givepay(View view) {
        SharedPreferences sharedpreferences = getSharedPreferences("shit", Context.MODE_PRIVATE);
              amount =Integer.parseInt(sharedpreferences.getString("amount", "error"));
            login = sharedpreferences.getString("uid", "error");
              uid = sharedpreferences.getString("uidot", "error");
              time = sdf.format(c.getTime());
              myFirebaseRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("joel","1");
                               if (snapshot.getValue() != null) {
                                   UserClass user = snapshot.getValue(UserClass.class);
                    String passcheck = ((EditText) findViewById(R.id.editText7)).getText().toString();
                                   if (user.getPassword().compareTo(passcheck) == 0) {
                        coin = user.getCoin();
                        if (!(coin == 0)) {
                       if (amount > coin) {
                            Toast.makeText(getApplicationContext(), "the amount is greater", Toast.LENGTH_SHORT).show();
                            } else {
                                coin = coin + amount;
                                Toast.makeText(getApplicationContext(), "the after balance " + coin + user.getName(), Toast.LENGTH_SHORT).show();
                                myFirebaseRef.child(uid).child("coin").setValue(coin);
                           myFirebaseRef1.child(uid).child(time).setValue(time+"\n give amount "+amount+"balance is "+coin);
                                myFirebaseRef.child(login).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        if (snapshot.getValue() != null) {
                                            UserClass user1 = snapshot.getValue(UserClass.class);
                                            coin1 = user1.getCoin();
                                            coin1 = coin1 - amount;
                                            myFirebaseRef.child(login).child("coin").setValue(coin1);
                                            myFirebaseRef1.child(login).child(time).setValue(time+"\n give amount "+amount+" balance is "+coin1);

                                            Intent inten = new Intent(getApplicationContext(), main_activity.class);
                                            startActivity(inten);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                                        }

                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                        //System.out.println("The read failed: " + firebaseError.getMessage());
                                    }

                                });

                            }

                        } else

                        {
                            Toast.makeText(getApplicationContext(), "in sufficient balance", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Pass wrng", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });

    }


}
