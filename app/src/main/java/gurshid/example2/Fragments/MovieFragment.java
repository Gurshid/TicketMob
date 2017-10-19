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
import gurshid.example2.Events.Movie;
import gurshid.example2.R;
import gurshid.example2.WebActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ListView mListViewMovie;
    private List<Movie> mMovieList;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragementView = inflater.inflate(R.layout.fragment_movie, container, false);

        mListViewMovie = (ListView) fragementView.findViewById(R.id.MyListViewMovie);

        mMovieList = new ArrayList<Movie>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("MyMovies");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> concertsParseList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + concertsParseList.size() + " scores");

                    for (ParseObject parseObject : concertsParseList ){

                        String title = (String) parseObject.get("title");
                        String link = (String) parseObject.get("link");
                        String imageLink = (String) parseObject.get("imageLink");

                        Movie movie = new Movie(title,link,imageLink);
                        mMovieList.add(movie);
                    }

                    mListViewMovie.setAdapter(new myAdapter());

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        mListViewMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openBrowser(mMovieList.get(position).getLink());
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
            return mMovieList.size();
        }

        @Override
        public Object getItem(int position) {
            return mMovieList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = getActivity().getLayoutInflater().inflate(R.layout.row,null);

            TextView textViewRow = (TextView) rowView.findViewById(R.id.textView);
            textViewRow.setText(mMovieList.get(position).getTitle());

            ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewPicasso);
            Picasso.with(getActivity()).load(mMovieList.get(position).getImageLink()).into(imageView);

            return rowView;
        }
    }
}
