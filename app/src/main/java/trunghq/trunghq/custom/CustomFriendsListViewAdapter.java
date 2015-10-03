package trunghq.trunghq.custom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import trunghq.trunghq.listItem.FriendsItem;
import trunghq.trunghq.R;

/**
 * Created by vantuegia on 10/1/2015.
 */
public class CustomFriendsListViewAdapter extends ArrayAdapter<FriendsItem> {

    private ArrayList<FriendsItem> mFriendsListItems;
    private Activity mActivity;

    public CustomFriendsListViewAdapter(Activity activity, ArrayList<FriendsItem> friendsListItems) {
        super(activity, R.layout.friend_list_item_layout, friendsListItems);
        this.mActivity = activity;
        this.mFriendsListItems = friendsListItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            // first time creation
            LayoutInflater inflater = this.mActivity.getLayoutInflater();
            view = inflater.inflate(R.layout.friend_list_item_layout, null);
        } // else next time will recover

        // avatar
        ImageView mImageViewClassListItem = (ImageView) view.findViewById(R.id.imageViewFriendsListItem);
        mImageViewClassListItem.setImageBitmap(mFriendsListItems.get(position).getBitmap());

        // name
        TextView mTextViewFriendName = (TextView) view.findViewById(R.id.textViewFriendName);
        mTextViewFriendName.setText(mFriendsListItems.get(position).getName());

        // percent
        TextView mTextViewFriendPercent = (TextView) view.findViewById(R.id.textViewFriendPercent);
        mTextViewFriendPercent.setText(mFriendsListItems.get(position).getPercent() + "%");


        return view;
    }

}
