package com.example.yoshi.funtimes.Model.DataSources;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.example.yoshi.funtimes.Model.Backend.IDataSource;
import com.example.yoshi.funtimes.Model.Backend.ManagerFactory;
import com.example.yoshi.funtimes.Model.Entitites.Action;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/*
* this class implements the IDataSource interface we created.
* this class act as implantation of remote DB of SQL.
* */

@SuppressWarnings("ALL")
public class RemoteDataSource implements IDataSource {

    private static JSONObject business;
    private static final String WEB_URL = "http://rubinj.vlab.jct.ac.il";
    //private java.util.Date currentBusinessTimestamp;
    private java.util.Date lastUpdatedTimestamp;

    public RemoteDataSource(){
        //currentBusinessTimestamp = new java.util.Date();
        try {
            JSONObject array = new JSONObject(GET(WEB_URL + "/getLastUpdateTime.php"));
            String ts = array.getString("last_update");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
            java.util.Date lastUpdatedTimestamp = dateFormat.parse(ts);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String GET(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            return response.toString();
        } else {
            return "";
        }
    }

    private static String POST(String url, Map<String, Object> params) throws
            IOException {
        //Convert Map<String,Object> into key=value&key=value pairs.
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
// For POST only - END
        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else return "";
    }

    @Override
    public Cursor getActionsDS() throws Exception {
        MatrixCursor actionCursor = new MatrixCursor(new String[]{"attraction",
                "country", "startdate", "enddate", "price", "description", "IDN", "user"});
        JSONArray array = new JSONObject(GET(WEB_URL +
                "/getAction.php")).getJSONArray("actions");
        for (int i = 0; i < array.length(); i++) {
            JSONObject action = array.getJSONObject(i);
            actionCursor.addRow(new Object[]{
                    action.getString("attraction"),
                    action.getString("country"),
                    action.getString("startdate"),
                    action.getString("enddate"),
                    action.getDouble("price"),
                    action.getString("description"),
                    action.getInt("idn"),
                    action.getString("user"),
            });
        }
        return actionCursor;
    }

    @Override
    public Cursor getBusinessDS() throws Exception {
        MatrixCursor businessCursor = new MatrixCursor(new String[]{"IDN",
                "name", "country", "city", "street", "housenum", "phoneNum", "email", "site", "user"});
        JSONArray array = new JSONObject(GET(WEB_URL +
                "/getBusiness.php")).getJSONArray("businesses");
        for (int i = 0; i < array.length(); i++) {
            JSONObject business = array.getJSONObject(i);
            businessCursor.addRow(new Object[]{
                    business.getInt("IDN"),
                    business.getString("name"),
                    business.getString("country"),
                    business.getString("city"),
                    business.getString("street"),
                    business.getInt("housenum"),
                    business.getString("phoneNum"),
                    business.getString("email"),
                    business.getString("site"),
                    business.getString("user")
            });
        }
        return businessCursor;
    }

    @Override
    public Cursor getUsersDS() throws Exception {
        MatrixCursor userCursor = new MatrixCursor(new String[]{"name", "password"});
        JSONArray array = new JSONObject(GET(WEB_URL +
                "/getUser.php")).getJSONArray("users");
        for (int i = 0; i < array.length(); i++) {
            JSONObject user = array.getJSONObject(i);
            userCursor.addRow(new Object[]{
                    user.getString("name"),
                    user.getString("password"),
            });
        }
        return userCursor;
    }

    @Override
    public Cursor getActionByString(String s) throws Exception {
        boolean flag = true;
        String myFormat = "dd/MM/yy"; //In which you need put here
        MatrixCursor actionCursor = new MatrixCursor(new String[]{"attraction",
                "country", "startdate", "enddate", "price", "description", "IDN", "user"});
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        JSONArray array = new JSONObject(GET(WEB_URL +
                "/getAction.php")).getJSONArray("actions");
        for (int i = 0; i < array.length(); i++) {
            JSONObject action = array.getJSONObject(i);
            if (Objects.equals(s, toString().valueOf(action.getInt("idn"))) || Objects.equals(action.getString("user") , s)){
                actionCursor.addRow(new Object[]{
                        action.getString("attraction"),
                        action.getString("country"),
                        sdf.parse(action.getString("startdate")),
                        sdf.parse(action.getString("enddate")),
                        action.getDouble("price"),
                        action.getString("description"),
                        action.getInt("IDN"),
                        action.getString("user"),
                });
                flag = false;//exists
            }
        }
        if (!flag){
            return actionCursor;
        }
        return null;
    }

    @Override
    public Cursor getBusinessByString(String s) throws Exception { //TODO send multiple businesses or other things
        MatrixCursor businessCursor = new MatrixCursor(new String[]{"IDN",
                "name", "country", "city", "street", "housenum", "phoneNum", "email", "site", "user"});
        boolean flag = true;
        JSONArray array = new JSONObject(GET(WEB_URL +
                "/getBusiness.php")).getJSONArray("businesses");
        for (int i = 0; i < array.length(); i++) {
            business = array.getJSONObject(i);
            if (Objects.equals(toString().valueOf(business.getInt("IDN")), s) || Objects.equals(business.getString("user"), s) || Objects.equals(business.getString("name"), s)) {
                    businessCursor.addRow(new Object[]{
                            business.getInt("IDN"),
                            business.getString("name"),
                            business.getString("country"),
                            business.getString("city"),
                            business.getString("street"),
                            business.getInt("housenum"),
                            business.getString("phoneNum"),
                            business.getString("email"),
                            business.getString("site"),
                            business.getString("user")
                    });
                    flag = false;//exists
                }
        }
        if(!flag){
            return businessCursor;
        }
        return null;
    }

    @Override
    public Cursor getUserByString(String s) throws Exception {
        JSONArray array = new JSONObject(GET(WEB_URL +
                "/getUser.php")).getJSONArray("users");
        boolean flag = true;
        MatrixCursor userCursor = new MatrixCursor(new String[]{"name", "password"});
        for (int i = 0; i < array.length(); i++) {
            JSONObject user = array.getJSONObject(i);
            if (Objects.equals(s, user.getString("name"))) {
                userCursor.addRow(new Object[]{
                        user.getString("name"),
                        user.getString("password"),
                });
                flag = false;//exists
            }
        }
        if(!flag){
            return userCursor;
        }
        return null;
    }

    @Override
    public void addUser(ContentValues user) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("name", user.getAsString("name"));
            params.put("password", user.getAsString("password"));
            String results = POST(WEB_URL + "/postUser.php", params);
            if (results.equals("")) {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        ManagerFactory.log += "New User " + user.toString() + "/n";
    }

    @Override
    public void addBusiness(ContentValues business) {
        try {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("IDN", business.getAsInteger("IDN"));
            params.put("name", business.getAsString("name"));
            params.put("country", business.getAsString("country"));
            params.put("city", business.getAsString("city"));
            params.put("street", business.getAsString("street"));
            params.put("housenum", business.getAsInteger("housenum"));
            params.put("phoneNum", business.getAsString("phoneNum"));
            params.put("email", business.getAsString("email"));
            params.put("site", business.getAsString("site"));
            params.put("user", business.getAsString("user"));
            String results = POST(WEB_URL + "/postBusiness.php", params);
            if (results.equals("")) {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        ManagerFactory.businessCount += 1;
        ManagerFactory.log += "New Business " + business.toString() + "/n";
    }

    @Override
    public void addAction(ContentValues action) throws ParseException {
        try {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("attraction", Action.Attraction.valueOf(action.getAsString("attraction")));
            params.put("country", action.getAsString("country"));
            params.put("startdate", action.getAsString("startdate"));
            params.put("enddate", action.getAsString("enddate"));
            params.put("price", action.getAsDouble("price"));
            params.put("description", action.getAsString("description"));
            params.put("IDN", action.getAsInteger("IDN"));
            params.put("user", action.getAsString("user"));
            String results = POST(WEB_URL + "/postAction.php", params);
            if (results.equals("")) {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        ManagerFactory.actionCount += 1;
        ManagerFactory.log += "New Action " + action.toString() + "/n";
    }

    @Override
    public boolean newBusiness() {
        JSONObject array = null;
        try {
            JSONObject array1 = new JSONObject(GET(WEB_URL + "/getLastUpdateTime.php"));
            String timeStamp = array1.getString("last_update");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
            java.util.Date lastUpdatedTimestamp = dateFormat.parse(timeStamp);
            array = new JSONObject(GET(WEB_URL + "/getTimeBusiness.php"));
            String ts = array.getString("last_update");
            java.util.Date d = dateFormat.parse(ts);
            if(d.after(lastUpdatedTimestamp)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean newAction() {
        //JSONObject array = null;
        try {
            JSONObject array1 = new JSONObject(GET(WEB_URL + "/getLastUpdateTime.php"));
            String timeStamp = array1.getString("last_update");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
            java.util.Date lastUpdatedTimestamp = dateFormat.parse(timeStamp);
            JSONObject array = new JSONObject(GET(WEB_URL + "/getTimeAttraction.php"));
            String ts = array.getString("last_update");
            java.util.Date d = dateFormat.parse(ts);
            if(d.after(lastUpdatedTimestamp)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

