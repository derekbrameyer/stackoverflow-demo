package com.willowtreeapps.stackoverflowdemo.fragment;

import com.google.inject.Inject;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.willowtreeapps.stackoverflowdemo.R;
import com.willowtreeapps.stackoverflowdemo.StackOverflowApi;
import com.willowtreeapps.stackoverflowdemo.model.Question;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import oak.widget.CancelEditText;
import roboguice.inject.InjectView;

public class SearchFragment extends RoboSherlockListFragment {

    @InjectView(R.id.search_text) CancelEditText searchText;

    @Inject StackOverflowApi stackOverflowApi;

    private int mPageNumber = 1;
    private SearchListAdapter mSearchListAdapter;
    private GetQuestions mGetQuestions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGetQuestions = new GetQuestions("tree", 1);
        mGetQuestions.execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mGetQuestions != null) {
            mGetQuestions.cancel(true);
        }
    }

    private class SearchListAdapter extends BaseAdapter {

        private class ViewHolder {

            TextView title;
        }

        private Question.Response mResponse;
        private ViewHolder vh;

        public SearchListAdapter(Question.Response response) {
            mResponse = response;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = getSherlockActivity().getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
                vh = new ViewHolder();
                vh.title = (TextView) v.findViewById(android.R.id.text1);
                v.setTag(vh);
            } else {
                vh = (ViewHolder) v.getTag();
            }
            Question q = (Question) getItem(position);
            vh.title.setText(q.title);
            return v;
        }

        @Override
        public int getCount() {
            return mResponse.items.size();
        }

        @Override
        public Object getItem(int position) {
            return mResponse.items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

    private class GetQuestions extends AsyncTask<Void, Void, Void> {

        private Question.Response mResponse;
        private String mTitle;
        private int mPage;

        public GetQuestions(String title, int page) {
            mTitle = title;
            mPage = page;
        }

        @Override
        protected void onPreExecute() {
            // TODO Refresh indicator
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                mResponse = stackOverflowApi.getQuestions(mTitle, mPage);
            } catch (IOException e) {
                mResponse = null;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            if (mResponse != null) {
                mSearchListAdapter = new SearchListAdapter(mResponse);
                setListAdapter(mSearchListAdapter);
            } else {
                // TODO Handle error
            }
        }
    }

    @Override
    public void onListItemClick(ListView list, View v, int position, long id) {
        Toast.makeText(getSherlockActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT)
                .show();
    }
}
