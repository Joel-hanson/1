package jbk.publicpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class register1 extends AppCompatActivity {
    public final static String NAME = "com.mycompany.myfirstapp.NANE";
    public final static String UID = "com.mycompany.myfirstapp.UID";
    public final static String YOB = "com.mycompany.myfirstapp.YOB";
    public final static String HOUSE = "com.mycompany.myfirstapp.HOUSE";
    public final static String DIST = "com.mycompany.myfirstapp.DIST";
    public final static String STATE= "com.mycompany.myfirstapp.STATE";
    public final static String PC = "com.mycompany.myfirstapp.PC";
    String name;
    String uid;
    String yob;
    String house;
    String dist;
    String state;
    String pc;

    public void register1(View view)
{
    Intent intent=new Intent(this,register2.class);
    intent.putExtra(NAME,name);
    intent.putExtra(UID,uid);
    intent.putExtra(YOB,yob);
    intent.putExtra(HOUSE,house);
    intent.putExtra(DIST,dist);
    intent.putExtra(STATE,state);
    intent.putExtra(PC,pc);


    startActivity(intent);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
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



        Intent intent = getIntent();
        name = intent.getStringExtra(log_in1.NAME);
        uid = intent.getStringExtra(log_in1.UID);
         yob = intent.getStringExtra(log_in1.YOB);
         house = intent.getStringExtra(log_in1.HOUSE);
         dist = intent.getStringExtra(log_in1.DIST);
         state = intent.getStringExtra(log_in1.STATE);
         pc = intent.getStringExtra(log_in1.PC);
        TextView obj1 = (TextView) findViewById(R.id.textview);
        TextView obj2 = (TextView) findViewById(R.id.textView2);
        TextView obj3 = (TextView) findViewById(R.id.textView3);
        TextView obj4 = (TextView) findViewById(R.id.textView4);

       obj1.setText("name :" + name);
        obj2.setText("adhar number:" + uid);
        obj3.setText("year of birth:" + yob);
        obj4.setText("The adress is : \n house: " + house + "\n district :" + dist + "\n state :" + state + "\n pin :" + pc);





    }


}
