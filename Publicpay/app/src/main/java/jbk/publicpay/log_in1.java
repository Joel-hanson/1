package jbk.publicpay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class log_in1 extends AppCompatActivity {

    public final static String NAME = "com.mycompany.myfirstapp.NANE";
    public final static String UID = "com.mycompany.myfirstapp.UID";
    public final static String YOB = "com.mycompany.myfirstapp.YOB";
    public final static String HOUSE = "com.mycompany.myfirstapp.HOUSE";
    public final static String DIST = "com.mycompany.myfirstapp.DIST";
    public final static String STATE= "com.mycompany.myfirstapp.STATE";
    public final static String PC = "com.mycompany.myfirstapp.PC";

    int a;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().hide();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });






        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in1, menu);
        return true;
    }

    public void register(View v) {
        new IntentIntegrator(this).initiateScan();
        a =1;
    }

    public void signin(View v) {
        new IntentIntegrator(this).initiateScan();

a=0;
    }

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
                                   String name = myparser.getAttributeValue(null,"name");
                                    String yob = myparser.getAttributeValue(null,"yob");
                                   // String co = myparser.getAttributeValue(null,"co");
                                    String house = myparser.getAttributeValue(null,"house");
                                   // String street = myparser.getAttributeValue(null,"street");
                                   // String near = myparser.getAttributeValue(null,"1m");
                                   // String vtc = myparser.getAttributeValue(null,"vtc");
                                    String dist = myparser.getAttributeValue(null,"dist");
                                    String state = myparser.getAttributeValue(null,"state");
                                    String pc = myparser.getAttributeValue(null,"pc");
                                    if(a==1){
                                        Intent intent;
                                        intent = new Intent(this, register1.class);
                                        intent.putExtra(NAME,name);
                                        intent.putExtra(UID,uid);
                                        intent.putExtra(YOB,yob);
                                        intent.putExtra(HOUSE,house);
                                        intent.putExtra(DIST,dist);
                                        intent.putExtra(STATE,state);
                                        intent.putExtra(PC,pc);
                                        startActivity(intent);
                                        //Toast.makeText(this, "uid: " + uid, Toast.LENGTH_LONG).show();
                                        //Toast.makeText(this, "name: " + name, Toast.LENGTH_LONG).show();


                                        //String uid_acc = ((EditText)findViewById(R.id.editText2)).getText().toString()
                                        //name_acc=((EditText)findViewById(R.id.editText3)).getText().toString();

                                    }
                                    else
                                    {
                                        Intent intent;
                                        intent = new Intent(this, sign_in.class);
                                        SharedPreferences sharedpreferences = getSharedPreferences("shit", getApplicationContext().MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putString("uid", uid);
                                        editor.putString("name", name);
                                        editor.commit();
                                        startActivity(intent);
                                       // Toast.makeText(this, "name: " + contents, Toast.LENGTH_LONG).show();
                                    }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "log_in1 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://jbk.publicpay/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "log_in1 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://jbk.publicpay/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
