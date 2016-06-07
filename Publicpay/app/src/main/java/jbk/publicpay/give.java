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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class give extends AppCompatActivity {

    Firebase myFirebaseRef;

    String amount,login;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://publicpay.firebaseio.com/");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give);
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
        amount = sharedpreferences.getString("amount", "error");
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("the amount you are about to give is" + amount);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);


    }
    public void giveQR(View view) {new IntentIntegrator(this).initiateScan();}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (intentResult != null) {
                    String contents = intentResult.getContents();
                    //String format = intentResult.getFormatName();
                    //  TextView uno = (TextView) findViewById(R.id.textView1);
                    //  uno.setText(contents);
                    //Toast.makeText(this, "Numero: " + contents, Toast.LENGTH_LONG).show();
                    Log.d("joel", contents);

                    try {
                        XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
                        XmlPullParser myparser = xmlFactoryObject.newPullParser();
                        InputStream stream = new ByteArrayInputStream(contents.getBytes(StandardCharsets.UTF_8));
                        myparser.setInput(stream,null);
                        int event = myparser.getEventType();
                        while (event != XmlPullParser.END_DOCUMENT)
                        {
                            //String name =myparser.getName();
                            switch (event){
                                case XmlPullParser.START_TAG:
                                    break;

                                case XmlPullParser.END_TAG:
                                    String uid = myparser.getAttributeValue(null,"uid");
                                    name = myparser.getAttributeValue(null,"name");
                                    String yob = myparser.getAttributeValue(null,"yob");
                                    // String co = myparser.getAttributeValue(null,"co");
                                    String house = myparser.getAttributeValue(null,"house");
                                    // String street = myparser.getAttributeValue(null,"street");
                                    // String near = myparser.getAttributeValue(null,"1m");
                                    // String vtc = myparser.getAttributeValue(null,"vtc");
                                    String dist = myparser.getAttributeValue(null,"dist");
                                    String state = myparser.getAttributeValue(null,"state");
                                    String pc = myparser.getAttributeValue(null,"pc");

                                    Intent intent1;
                                    intent1 = new Intent(this, give_password.class);
                                    SharedPreferences sharedpreferences = getSharedPreferences("shit", getApplicationContext().MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString("nameot", name);
                                    editor.putString("uidot", uid);
                                    editor.putString("yobot", yob);
                                    editor.putString("houseot", house);
                                    editor.putString("distot", dist);
                                    editor.putString("stateot", state);
                                    editor.putString("pcot", pc);
                                    editor.commit();
Log.e("joel",name);
                                    Log.e("joel",uid);

                                    startActivity(intent1);
                                    //Toast.makeText(this, "uid: " + uid, Toast.LENGTH_LONG).show();
                                    //Toast.makeText(this, "name: " + name, Toast.LENGTH_LONG).show();


                                    //String uid_acc = ((EditText)findViewById(R.id.editText2)).getText().toString()
                                    //name_acc=((EditText)findViewById(R.id.editText3)).getText().toString();



                                    break;
                                   /*
                                    Intent intent;
                                    intent = new Intent(this, register1.class);
                                    startActivity(intent);
                                    Toast.makeText(this, "name: " + contents, Toast.LENGTH_LONG).show();*/

                            }

                            event = myparser.next();
                        }
                    } catch(Exception e){}





                } else {
                    Log.e("SEARCH_EAN", "IntentResult je NULL!");
                }
                // Do something with the contact here (bigger example below)
            }
        }
    }




}
