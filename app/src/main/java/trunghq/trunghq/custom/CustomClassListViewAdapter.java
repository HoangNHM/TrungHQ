package trunghq.trunghq.custom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import trunghq.trunghq.ClassTab;
import trunghq.trunghq.listItem.ClassListItem;
import trunghq.trunghq.R;

/**
 * Created by vantuegia on 10/1/2015.
 */
public class CustomClassListViewAdapter extends ArrayAdapter<ClassListItem> {

    private OnFriendsItemInteractionListener itemInteractionListener;
    private ArrayList<ClassListItem> mClassListItems;
    private Activity mActivity;

    public CustomClassListViewAdapter(Activity activity, ClassTab classTab, ArrayList<ClassListItem> classListItems) {
        super(activity, R.layout.class_list_item_layout, classListItems);
        this.mActivity = activity;
        this.mClassListItems = classListItems;
        this.itemInteractionListener = (OnFriendsItemInteractionListener) classTab;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            // first time creation
            LayoutInflater inflater = this.mActivity.getLayoutInflater();
            view = inflater.inflate(R.layout.class_list_item_layout, null);
        } // else next time will recover

        // chart
        ImageView mImageViewClassListItem = (ImageView) view.findViewById(R.id.imageViewClassListItem);
        mImageViewClassListItem.setImageBitmap(mClassListItems.get(position).getBitmap());

        // class 1, class 2, ...
        TextView mTextViewClassNumber = (TextView) view.findViewById(R.id.textViewClassNumber);
        mTextViewClassNumber.setText("Class " + mClassListItems.get(position).getClassNumber());

        // btn Study
        ImageButton mBtnStudyClassItem = (ImageButton) view.findViewById(R.id.btnStudyClassItem);
        mBtnStudyClassItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemInteractionListener.onFriendsItemInteractionListener(v.getId(), position);
            }
        });

        // btn View More
        ImageButton mBtnViewMoreClassItem = (ImageButton) view.findViewById(R.id.btnViewMoreClassItem);
        mBtnViewMoreClassItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemInteractionListener.onFriendsItemInteractionListener(v.getId(), position);
            }
        });

        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFriendsItemInteractionListener {
        // TODO: Update argument type and name
        public void onFriendsItemInteractionListener(int btnId, int position);
    }
}
