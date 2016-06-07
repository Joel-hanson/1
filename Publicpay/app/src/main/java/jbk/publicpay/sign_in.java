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

public class sign_in extends AppCompatActivity {
    Firebase myFirebaseRef;
    String uid,name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://publicpay.firebaseio.com/");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
             findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                SharedPreferences sharedpreferences = getSharedPreferences("shit", Context.MODE_PRIVATE);
                uid=sharedpreferences.getString("uid","error");
                name=sharedpreferences.getString("name","error");

                    myFirebaseRef.child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.getValue() != null) {
                                UserClass user = snapshot.getValue(UserClass.class);
                                String passcheck = ((EditText) findViewById(R.id.editText)).getText().toString();

                                if (user.getPassword().compareTo(passcheck) == 0) {
                                    Toast.makeText(getApplicationContext(), "Welcome " + user.getName(), Toast.LENGTH_SHORT).show();

                                    Intent inte = new Intent(getApplicationContext(), main_activity.class);
                                    startActivity(inte);

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
        });
    }

}
