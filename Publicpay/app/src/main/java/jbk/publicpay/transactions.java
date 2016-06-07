package jbk.publicpay;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class transactions extends AppCompatActivity {
    Firebase myFirebaseRef;
    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://publicpay.firebaseio.com/trans/");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
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
        login = sharedpreferences.getString("uid","error");
        Log.d("jo",login);

        myFirebaseRef.child(login).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> ar = new ArrayList<String>();
                if (dataSnapshot.getValue() != null) {


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ar.add(snapshot.getValue().toString() + "\n" + snapshot.getKey().toString());

                    }


                }
                else {
                    Toast.makeText(getApplicationContext(), "no transation", Toast.LENGTH_LONG).show();
                }

                String[] a = ar.toArray(new String[0]);

                ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listitem, a);

                ListView listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
