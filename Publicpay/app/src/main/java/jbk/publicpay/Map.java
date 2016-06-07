package jbk.publicpay;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity implements OnMapReadyCallback {
    Firebase myFirebaseRef;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://publicpay.firebaseio.com/");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(Map.this, "Permission not granted!!", Toast.LENGTH_SHORT).show();
            // Show rationale and request permission.
        }


        myFirebaseRef.child("location").child("bus").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //  System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                for (DataSnapshot buslocation : snapshot.getChildren()) {
                    Double lat = Double.parseDouble(buslocation.child("lat").getValue().toString());
                    Double longg = Double.parseDouble(buslocation.child("long").getValue().toString());
                    //  System.out.println(post.getAuthor() + " - " + post.getTitle());

                    LatLng sydney = new LatLng(lat, longg);
                    mMap.addMarker(new MarkerOptions().position(sydney).title(buslocation.getKey().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker2)));
                }

        
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });
        myFirebaseRef.child("location").child("toilet").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //  System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                for (DataSnapshot buslocation : snapshot.getChildren()) {
                    Double lat = Double.parseDouble(buslocation.child("lat").getValue().toString());
                    Double longg = Double.parseDouble(buslocation.child("long").getValue().toString());
                    //  System.out.println(post.getAuthor() + " - " + post.getTitle());

                    LatLng sydney = new LatLng(lat, longg);
                    mMap.addMarker(new MarkerOptions().position(sydney).title(buslocation.getKey().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.toiletmarker)));

                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });


        // Add a marker in Sydney and move the camera

    }
}
