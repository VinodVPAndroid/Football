package demo.com.footbal.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import demo.com.footbal.R;
import demo.com.footbal.network.WebServerCall;
import demo.com.footbal.utils.Constants;
import demo.com.footbal.utils.GameAdapter;
import demo.com.footbal.utils.Utils;

public class MainActivity extends AppCompatActivity {

    // Member variables
    private static ListView mLvGames = null;
    static GameAdapter mGameAdapter = null;
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Constants.mContext = this;
        mLvGames = (ListView) findViewById(R.id.lvGames);
        if (Constants.mHitWebServer) {
            WebServerCall web = new WebServerCall();
            web.execute(Constants.WEB_SERVER_URL);
        }
        loadListViewWithGame();
    }

    /**
     * This method is used to load the Listview with available Games.
     *
     * @return void.
     */
    public static void loadListViewWithGame() {
        try {
            if(null == mGameAdapter) {
                // Create The Adapter with passing ArrayList as 3rd parameter
                mGameAdapter =
                        new GameAdapter(mContext, R.layout.list_view_item, Utils.getGameList());
                // Set The Adapter
                mLvGames.setAdapter(mGameAdapter);
            } else {
                if(Constants.mHitWebServer) {
                    mGameAdapter.clear();
                    mGameAdapter.addAll(Utils.getGameList());
                    mGameAdapter.notifyDataSetChanged();
                    Constants.mHitWebServer = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    public void onBackPressed() {
        confirmExit();
    }

    /**
     * This method is used to promt a confirmation dialog for getting user response.
     *
     * @return void.     *
     */
    private void confirmExit() {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                MainActivity.this);
        alert.setTitle(R.string.app_name);
        alert.setIcon(android.R.drawable.ic_menu_info_details);
        alert.setMessage(getString(R.string.confirm_exit));
        alert.setPositiveButton(getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int arg1) {
                        dialog.cancel();
                    }
                });
        alert.setNegativeButton(getString(R.string.yes),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int arg1) {
                        dialog.cancel();
                        int pid = android.os.Process.myPid();
                        android.os.Process.killProcess(pid);
                    }
                });
        alert.create();
        alert.show();
    }
}
