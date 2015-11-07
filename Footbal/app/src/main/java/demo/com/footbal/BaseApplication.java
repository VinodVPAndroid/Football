package demo.com.footbal;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import demo.com.footbal.utils.Constants;
import demo.com.footbal.utils.Utils;

/**
 * This class implementation override the default application.
 */
public class BaseApplication extends Application {

    // Member variables
    private static SharedPreferences mPreference = null;
    public static String mJSONString = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.initialize(this);
        initSharedPrefernce();
    }

    /**
     * This method is used to initialize the shared prefernce; also checking for json string availability.
     *
     * @return void.
     */
    private void initSharedPrefernce() {
        mPreference = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        mJSONString = mPreference.getString(Constants.SHARED_PREFERENCE_JSON_STRING, Constants.EMPTY_STRING);
        if(mJSONString.equals(Constants.EMPTY_STRING)) {
            Constants.mHitWebServer = true;
        } else {
            Constants.mHitWebServer = false;
            Utils.generateGameList(mJSONString);
        }
    }

    /**
     * This method is used to save the retrieved json response from the server.
     *
     * @param jsonString Retrieved response from server.
     * @return True - If saved; else False.
     */
    public static boolean writeToSharedPreference(String jsonString) {
        try {
            SharedPreferences.Editor editor = mPreference.edit();
            editor.putString(Constants.SHARED_PREFERENCE_JSON_STRING, jsonString);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

