package com.app.gameface.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.app.gameface.Gps.GPS_traker;
import com.app.gameface.R;

import com.app.gameface.extra.Global;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LocationFragment extends Fragment implements OnMapReadyCallback {

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    //private static final String API_KEY = "AIzaSyDExQ-8fw03rAeFoP81zuyPeYtUMlz_fAg";
    private static final String API_KEY = "AIzaSyA_YDyethfHAfSauWwXKNSvgEs2MIT6blk";
    private static final String LOG_TAG ="LOG" ;
    public static GoogleMap mMap;
    EditText selectLocation;
    AutoCompleteTextView autoCompView;
    Float lat,lng;
    EditText locationSearch;
    PlaceAutocompleteFragment autocompleteFragment;
    GPS_traker gps_traker;
    int GET_LOCATION_PERMISSION_REQUEST=1;
    SupportMapFragment mapFragment;
    String locationn="";

     ArrayList<String> place_id;
   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getPermissionToGetLocation();
        View view=inflater.inflate(R.layout.fragment_location, container, false);

        loadData(view);
        mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        autoCompView = (AutoCompleteTextView)view. findViewById(R.id.edt_location_reg);

        autoCompView.setAdapter(new  GooglePlacesAutocompleteAdapter(getActivity(), R.layout.list_item));
        autoCompView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String str = (String) parent.getItemAtPosition(position);

              new LongOperation().execute(place_id.get(position));
                //Toast.makeText(AddTrip.this, "Location"+str, Toast.LENGTH_LONG).show();
                locationn=str;

            }
        });
      /*  autocompleteFragment = (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete);
*/
        //onSearchView();

        mapFragment.getMapAsync(this);
        return view;
    }


    public void loadData(View view)
    {


    }

    public void onSearchView()
    {
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.e("Place",""+place.getName());

            }

            @Override
            public void onError(Status status) {

            }
        });




    }

    String add;
    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            add = obj.getAddressLine(0);// sector
            add = add + " " + obj.getLocality();

            add = add + " " + obj.getAdminArea();// state
            add = add + " " + obj.getCountryName();// country
         //  add = add + "\n" + obj.getCountryCode();// country code

           // add = add + "\n" + obj.getPostalCode();
          //  add = add + "\n" + obj.getSubAdminArea();

          //  add = add + "\n" + obj.getSubThoroughfare();

            Log.v("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
           // Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        gps_traker=new GPS_traker(getActivity());
        Log.e("canGetLocation",""+gps_traker.canGetLocation());

        if(gps_traker.canGetLocation()) {
            mMap = googleMap;
            // Add a marker in Sydney and move the camera
            LatLng place = new LatLng(gps_traker.getLatitude(), gps_traker.getLongitude());
            mMap.addMarker(new MarkerOptions().position(place));
          //  mMap.setPadding(10,10,10,10);
            // mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
            getAddress(gps_traker.getLatitude(), gps_traker.getLongitude());
            autoCompView.setText(add);
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(place, 15f)));
        }
        else
        {
            mMap = googleMap;
            gps_traker.showSettingsAlert();
            gps_traker.getLocation();
            LatLng place = new LatLng(gps_traker.getLatitude(), gps_traker.getLongitude());
            mMap.addMarker(new MarkerOptions().position(place));
            // mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
            //mMap.setPadding(10,10,10,10);
            getAddress(gps_traker.getLatitude(), gps_traker.getLongitude());
            autoCompView.setText(add);
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(place, 15f)));

        }



    }

    public void getPermissionToGetLocation() {

        boolean b= ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;

        Global.contacts_response=new ArrayList<>();
        if (b)
        {

            gps_traker=new GPS_traker(getActivity());



        }
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            requestPermissions(new String[]{ Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    GET_LOCATION_PERMISSION_REQUEST);
        }
    }

    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == GET_LOCATION_PERMISSION_REQUEST) {
            if (grantResults.length == 2 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Location permission granted", Toast.LENGTH_SHORT).show();



                gps_traker=new GPS_traker(getActivity());
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.attachment_frame,new LocationFragment());
                fragmentTransaction.commitAllowingStateLoss();


            } else {
                Toast.makeText(getActivity(), "Read Contacts permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }







    class GooglePlacesAutocompleteAdapter extends ArrayAdapter implements Filterable {
        public ArrayList resultList;

        public GooglePlacesAutocompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return resultList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            //Toast.makeText(AddTrip.this, "ghjhg", 2000).show();

            return resultList.get(position);

        }
        // @Override
        // public int getCount() {
        // return resultList.size();
        // }
        //
        // @Override
        // public String getItem(int index) {
        // return resultList.get(index);
        // }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        Log.d("constraints", constraint + "");
                        resultList = autocomplete(constraint.toString());
                        // Toast.makeText(Request.this, ""+resultList.size(),
                        // 2000).show();
                        // Assign the data to the FilterResults
                        filterResults.values = resultList;

                        filterResults.count = resultList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
        }
    }

    public  ArrayList autocomplete(String input) {
        ArrayList resultList = null;
        Log.d("input", input);
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);

				/*
				 * sb.append("&components=country:in");
				 */
            sb.append("&types=(cities)");

            sb.append("&input=" + URLEncoder.encode(input, "utf-8"));

            //
            // StringBuilder sb = new StringBuilder(PLACES_API_BASE
            // + TYPE_AUTOCOMPLETE + OUT_JSON);
            // // sb.append("&components=country:in&radius=5");
            // sb.append("&input=" + URLEncoder.encode(input, "utf8"));
            // //sb.append("&types=food");
            // //
            // sb.append("&types=establishment&location=30.7800,76.6900&radius=1000");
            // sb.append("&location=");
            // /*+ URLEncoder.encode(lat, "utf8") + ","
            // + URLEncoder.encode(lng, "utf8"));*/
            // sb.append("&radius=" + URLEncoder.encode("1000", "utf8"));
            // sb.append("&key=" + API_KEY);

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            Log.d("data", sb + "//"+jsonResults);
            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {

            Log.d("final block","1");
            if (conn != null) {
                Log.d("final block","2");
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList(predsJsonArray.length());
            place_id=new ArrayList<>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));

                place_id.add(predsJsonArray.getJSONObject(i).getString("place_id"));

                Log.d("result", resultList.get(i) + "");
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }






    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {


                ArrayList resultList = null;
                String link="https://maps.googleapis.com/maps/api/place/details/json?placeid="+params[0]+"&key=AIzaSyA_YDyethfHAfSauWwXKNSvgEs2MIT6blk";
                HttpURLConnection conn = null;
                StringBuilder jsonResults = new StringBuilder();
                try {

                    URL url = new URL(link);
                    conn = (HttpURLConnection) url.openConnection();
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    //   Log.d("data", sb + "//"+jsonResults);
                    // Load the results into a StringBuilder
                    int read;
                    char[] buff = new char[1024];
                    while ((read = in.read(buff)) != -1) {
                        jsonResults.append(buff, 0, read);
                    }
                } catch (MalformedURLException e) {
                    Log.e(LOG_TAG, "Error processing Places API URL", e);
                    ;
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error connecting to Places API", e);

                } finally {

                    Log.d("final block","1");
                    if (conn != null) {
                        Log.d("final block","2");
                        //  conn.disconnect();
                    }
                }

                try {
                    // Create a JSON object hierarchy from the results
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());
                    JSONObject result=jsonObj.getJSONObject("result");
                    JSONObject geometry=result.getJSONObject("geometry");
                    JSONObject location=geometry.getJSONObject("location");

                    String lat=location.getString("lat");
                    String lng=location.getString("lng");
                    Global.lat=Double.parseDouble(lat);
                    Global.lng=Double.parseDouble(lng);
                    Log.e("VALUE_LAT:",lat+"LNG"+lng);


                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Cannot process JSON results", e);
                }


            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            LatLng place = new LatLng(Global.lat, Global.lng);
            Log.e("Global.lat",""+Global.lat);
            Log.e("Global.lng",""+Global.lng);
            mMap.clear();
            MarkerOptions a = new MarkerOptions()
                    .position(place);
            Marker m = mMap.addMarker(a);
            m.setPosition(place);
          /*  mMap.addMarker(new MarkerOptions().position(place));*/
           // mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(place, 12f)));
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(place, 12f)));
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}

