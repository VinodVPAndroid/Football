package demo.com.footbal.utils;

import android.content.Context;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import demo.com.footbal.modal.Game;

/**
 * Common utility functions contained in this function.
 *
 */
public class Utils {

    // Member variables
    private static List<Game> mGameList = new ArrayList<Game>();
    private static GsonBuilder mGsonBuilder = null;
    private static Gson mGson = null;
    private static Context mContext;

    /**
     * This method is used to initialize the Utils variables.
     *
     * @return void.
     */
    public static void initialize(Context context) {
        mGsonBuilder = new GsonBuilder();
        mGsonBuilder.setDateFormat("M/d/yy hh:mm a");
        mGson = mGsonBuilder.create();
        mContext = context;
    }

    /**
     * This method is used to generate the Game list.
     *
     * @param gameList Response from the web server.
     * @return True - Successfully generated GSON; else False.
     */
    public static boolean generateGameList(String gameList) {
        if (null != gameList) {
            try {
                mGameList.clear();
                mGameList = Arrays.asList(mGson.fromJson(gameList, Game[].class));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Utils.showToast("JSON response is empty");
        }

        return false;
    }

    /**
     * This method is used to pop an message to the end user.
     *
     * @param msg Message to be toasted.
     * @return void.
     */
    private static void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * This method is used to generate the Game list.
     *
     * @param content InputStream object.
     * @return void
     */
    public static void generateGameList(InputStream content) {
        try {
            //Read the server response and attempt to parse it as JSON
            Reader reader = new InputStreamReader(content);
            mGameList.clear();
            mGameList = Arrays.asList(mGson.fromJson(reader, Game[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method return the List of Games.
     *
     * @return List of games.
     */
    public static List<Game> getGameList() {
        return mGameList;
    }
}
