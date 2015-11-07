package demo.com.footbal.network;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import demo.com.footbal.BaseApplication;
import demo.com.footbal.ui.MainActivity;
import demo.com.footbal.utils.Constants;
import demo.com.footbal.utils.Utils;

/**
 * This class communicate with the Web server.
 *
 */
public class WebServerCall extends AsyncTask<String, String, String> {

    // Member variables
    ProgressDialog progressBar = null;
    private String TAG = WebServerCall.class.getName();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // prepare for a progress bar dialog
        progressBar = new ProgressDialog(Constants.mContext);
        progressBar.setCancelable(false);
        progressBar.setMessage("Fetching Match details...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        Integer result = 0;
        HttpURLConnection urlConnection = null;
        StringBuilder response = null;
        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(String res) {
        super.onPostExecute(res);
        try {
            JSONObject response = new JSONObject(res);
            JSONArray matches = response.getJSONArray("fixtures");
            if(Utils.generateGameList(matches.toString())) {
                BaseApplication.writeToSharedPreference(matches.toString());
            }
            MainActivity.loadListViewWithGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
        progressBar.dismiss();
    }
}
