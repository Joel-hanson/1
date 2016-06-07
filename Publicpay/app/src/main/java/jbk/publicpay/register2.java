package jbk.publicpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class register2 extends AppCompatActivity {
    Firebase myFirebaseRef;
    EditText password,repassword;

    String uid; Intent intent;
    @Override
        protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://publicpay.firebaseio.com/");


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


         intent = getIntent()  ;
         password = (EditText)findViewById(R.id.editText2);
         repassword=(EditText)findViewById(R.id.editText3);
         uid = intent.getStringExtra(log_in1.UID);



    }


    public void register2(View view)
    {

        UserClass scanned_user = new UserClass(intent.getStringExtra(log_in1.UID),password.getText().toString(),intent.getStringExtra(log_in1.NAME),intent.getStringExtra(log_in1.YOB),intent.getStringExtra(log_in1.HOUSE),intent.getStringExtra(log_in1.STATE),intent.getStringExtra(log_in1.DIST),intent.getStringExtra(log_in1.PC),0);


        if(password.getText().length() < 6 )
    {
        password.setError("Minimum 6 chara");
    }
    else if( password.getText().length() > 8 )
    {
        password.setError("Max 8 chara");
    }
    else {

        if (password.getText().toString().compareTo(repassword.getText().toString()) == 0) {

            myFirebaseRef.child(uid).setValue(scanned_user);
            Toast.makeText(this, "welcome to public pay family", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, log_in1.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, " Passwod missmatch", Toast.LENGTH_SHORT).show();
        }
    }
}

}
