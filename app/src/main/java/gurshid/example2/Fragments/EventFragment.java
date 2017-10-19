package gurshid.example2.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import gurshid.example2.Events.Event;
import gurshid.example2.R;
import gurshid.example2.WebActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    private ListView mListViewEvent;
    private List<Event> mEventList;

    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragementView = inflater.inflate(R.layout.fragment_event, container, false);

        mListViewEvent = (ListView) fragementView.findViewById(R.id.MyListViewEvent);

        mEventList = new ArrayList<Event>();


        ParseQuery<ParseObject> query = ParseQuery.getQuery("MyEvents");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> concertsParseList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + concertsParseList.size() + " scores");

                    for (ParseObject parseObject : concertsParseList ){

                        String title = (String) parseObject.get("title");
                        String link = (String) parseObject.get("link");
                        String imageLink = (String) parseObject.get("imageLink");

                        Event event = new Event(title,link,imageLink);
                        mEventList.add(event);
                    }

                    mListViewEvent.setAdapter(new myAdapter());

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        mListViewEvent.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openBrowser(mEventList.get(position).getLink());
//                Intent webintent = new Intent(getContext(), WebActivity.class);
//                webintent.putExtra("URL",mEventList.get(position).getLink());
//                startActivity(webintent);
            }
        });


        return fragementView;
    }
    public void openBrowser(String link){

//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
//        startActivity(browserIntent);
        Intent webintent = new Intent(getContext(), WebActivity.class);
        webintent.putExtra("URL",link);
        Log.d("link", " "+link );
        startActivity(webintent);

    }

    private class myAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mEventList.size();
        }

        @Override
        public Object getItem(int position) {
            return mEventList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = getActivity().getLayoutInflater().inflate(R.layout.row,null);

            TextView textViewRow = (TextView) rowView.findViewById(R.id.textView);
            textViewRow.setText(mEventList.get(position).getTitle());

            ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewPicasso);
            Picasso.with(getActivity()).load(mEventList.get(position).getImageLink()).into(imageView);

            return rowView;
        }
    }
}
