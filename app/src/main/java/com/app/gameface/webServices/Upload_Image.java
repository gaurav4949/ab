package com.app.gameface.webServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.app.gameface.extra.Global;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class Upload_Image extends AsyncTask<String, Void, String>  {
    SharedPreferences sh;
    private URL connectURL;
    private String imagepath;

    byte[] dataToServer;
    private String fname, about, lname;
    Context c;
    int status;
    String response,group_image;
    private final ProgressDialog dialog;
    DataOutputStream dos = null;
    String jResponse, msg, product_id;

    String g_id;

    String message="";
    SharedPreferences sharedPreferences;

    String cat_id,subcat_id,sub_value,product_value;

    public Upload_Image(Context c, String imagepath) {

        this.c = c;
        this.imagepath = imagepath;
        sharedPreferences=c.getSharedPreferences("Preferences",Context.MODE_PRIVATE);


        dialog = new ProgressDialog(c);
        try {
            connectURL = new URL("https://www.thegamefaceapp.com/ios_game_faceapp/Api/change_group_image.json");
        } catch (Exception ex) {
            Log.i("URL FORMATION", "MALFORMATED URL");
        }
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Please Wait.....");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        FileInputStream fileInputStream = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String Tag = "3rd";
        String existingfilename = "";


        Log.e("FILE TYPE & FILE NAME", " " + imagepath);

        try {

            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();

            conn.setDoInput(true);

            conn.setDoOutput(true);

            conn.setUseCaches(false);

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Connection", "Keep-Alive");

            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            //name,phone,address,dob,gender,email
            Log.e("params",params[0]+params[1]+params[2]);
            dos.writeBytes("Content-Disposition: form-data; name=\"user_id\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes("" + params[0] + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"token\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes("" + params[1] + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            g_id=params[2];
            dos.writeBytes("Content-Disposition: form-data; name=\"group_id\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes("" + params[2] + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);


            /*dos.writeBytes("Content-Disposition: form-data; name=\"user_id\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes("" + sharedPreferences.getString("user_id","") + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);*/


            // --------------image--------------
            if (imagepath.equals("")||imagepath.contains("http")) {
                 dos.writeBytes("Content-Disposition: form-data; name=\"image\";filename=\"" + "" + "\"" + lineEnd);
            }
            // --------------image--------------
            /*if (imagepath.equals("")||||imagepath.contains("http")) {
                dos.writeBytes("Content-Disposition: form-data; name=\"image\";filename=\"" + "" + "\"" + lineEnd);
            } */else {
                File f = new File(imagepath);
                fileInputStream = new FileInputStream(f);
                existingfilename = f.getName();
                dos.writeBytes("Content-Disposition: form-data; name=\"image\";filename=\"" + existingfilename + "\""
                        + lineEnd);

                if (existingfilename.endsWith(".jpg")) {
                    dos.writeBytes("Content-type: image/jpg;" + lineEnd);
                }
                if (existingfilename.endsWith(".png")) {
                    dos.writeBytes("Content-type: image/png;" + lineEnd);
                }
                if (existingfilename.endsWith(".gif")) {
                    dos.writeBytes("Content-type: image/gif;" + lineEnd);
                }
                if (existingfilename.endsWith(".jpeg")) {
                    dos.writeBytes("Content-type: image/jpeg;" + lineEnd);
                }

                dos.writeBytes(lineEnd);
                // **********
                int bytesAvailable = fileInputStream.available();

                int maxBufferSize = 1024;

                int bufferSize = Math.min(bytesAvailable, maxBufferSize);
                byte[] buffer = new byte[bufferSize];

                int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                Log.e(Tag, "File is written");
                fileInputStream.close();

            }


            dos.flush();
            dos.close();
            InputStream is = conn.getInputStream();

            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
            jResponse = b.toString();

            response = "true";


            Log.e("Update Selfie Image API", jResponse);

            JSONObject jobj = new JSONObject(jResponse);
            status = jobj.getInt("status");

            Log.e("status", String.valueOf(status));
           // Log.e("errorfgfgfgf","errr");
            if (status == 0) {

                message= jobj.getString("message");
            } else {

                message= jobj.getJSONObject("result").getString("message");
                group_image= jobj.getJSONObject("result").getString("group_image");
            }

        } catch (MalformedURLException ex) {
            Log.e(Tag, "error: " + ex.getMessage(), ex);
        }

        catch (IOException ioe) {
            Log.e(Tag, "error: " + ioe.getMessage(), ioe);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String result) {

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        response=result;
        if (status == 0) {
          //  apiResponse.onError(result);

            Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        } else {
           // apiResponse.onSuccess(result);

            Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
            for (int i = 0; i< Global.Group_list.size(); i++) {
                if (Global.Group_list.get(i).get("group_id").equalsIgnoreCase(g_id)) {
                    String group_id = Global.Group_list.get(i).get("group_id");
                    String group_name=Global.Group_list.get(i).get("group_name");
                    String g_image = group_image;
                    String group_date = Global.Group_list.get(i).get("group_date");
                    String count = Global.Group_list.get(i).get("count");
                    String group_type = Global.Group_list.get(i).get("group_type");
                    String clipboard = Global.Group_list.get(i).get("clipboard");
                    String age_group = Global.Group_list.get(i).get("age_group");
                    String games = Global.Group_list.get(i).get("games");
                    String sport_name = Global.Group_list.get(i).get("sport_name");

                    Log.e("group_image",""+group_image);
                    Global.Group_list.remove(i);

                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("group_id", group_id);
                    hashMap.put("group_name", Global.GROUP_NAME);
                    hashMap.put("group_image", g_image);
                    hashMap.put("group_date", group_date);
                    hashMap.put("count", count);

                    hashMap.put("group_type", group_type);
                    hashMap.put("clipboard", clipboard);
                    hashMap.put("age_group", age_group);
                    hashMap.put("games", games);
                    hashMap.put("sport_name", sport_name);

                    Global.Group_list.add(i, hashMap);

                    break;


                }
            }

            dialog.dismiss();
           // ((Activity)c).finish();
        }
    }


}



