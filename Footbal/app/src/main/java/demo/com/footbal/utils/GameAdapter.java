package demo.com.footbal.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import demo.com.footbal.R;
import demo.com.footbal.modal.Game;

/**
 * Adapter implementation for the Match list view.
 *
 */
public class GameAdapter extends ArrayAdapter<Game> {

    private Context mContext;
    private int layoutResourceId;
    private List<Game> mGameList = null;

    /**
     * Default constructor for Game adapter class.
     *
     * @param mContext         Application handle.
     * @param layoutResourceId Layout id.
     * @param data             Data for inflating views.
     */
    public GameAdapter(Context mContext, int layoutResourceId, List<Game> data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGameList = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder = null;

        if (convertView == null) {
            try {
                // inflate the layout
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(layoutResourceId, parent, false);
                // well set up the ViewHolder
                viewHolder = new ViewHolderItem();
                viewHolder.mHomeTeamName = (TextView) convertView.findViewById(R.id.tvHomeTeamName);
                viewHolder.mAwayTeamName = (TextView) convertView.findViewById(R.id.tvAwayTeamName);
                viewHolder.mGoal = (TextView) convertView.findViewById(R.id.tvScoreValue);
                viewHolder.mGameDate = (TextView) convertView.findViewById(R.id.tvGameDate);
                viewHolder.mGameStatus = (TextView) convertView.findViewById(R.id.tvMatchStatus);
                // store the holder with the view.
                convertView.setTag(viewHolder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        // object item based on the position
        Game objectItem = mGameList.get(position);
        // assign values if the object is not null
        if (objectItem != null) {
            // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
            viewHolder.mHomeTeamName.setText(objectItem.getHomeTeamName());
            viewHolder.mAwayTeamName.setText(objectItem.getAwayTeamName());
            viewHolder.mGameStatus.setText(objectItem.getStatus());
            viewHolder.mGameDate.setText(objectItem.getDate());
            viewHolder.mGoal.setText(objectItem.getResult().getGoalHomeTeam() + " - " + objectItem.getResult().getGoalAwayTeam());
        }
        return convertView;
    }

    /**
     * View holder class for Game adapter to increase the performance.
     */
    private static class ViewHolderItem {
        TextView mHomeTeamName, mAwayTeamName, mGameStatus, mGoal, mGameDate;
    }
}
