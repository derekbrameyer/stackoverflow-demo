package com.willowtreeapps.stackoverflowdemo.fragment;

import com.google.inject.Inject;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.willowtreeapps.stackoverflowdemo.R;
import com.willowtreeapps.stackoverflowdemo.StackOverflowApi;
import com.willowtreeapps.stackoverflowdemo.model.Question;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import oak.widget.CancelEditText;
import roboguice.inject.InjectView;

public class SearchFragment extends RoboSherlockListFragment {

    @InjectView(R.id.search_text) CancelEditText searchText;
    @InjectView(android.R.id.empty) TextView emptyText;

    @Inject StackOverflowApi stackOverflowApi;

    private int mPageNumber = 1;
    private SearchListAdapter mSearchListAdapter;
    private GetQuestions mGetQuestions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchListAdapter = new SearchListAdapter(null);
        setListAdapter(mSearchListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setEmptyView(emptyText);
        emptyText.setText(R.string.loading);

        mGetQuestions = new GetQuestions("tree", 1);
        mGetQuestions.execute();

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) getSherlockActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                    mGetQuestions = new GetQuestions(searchText.getText().toString(), 1);
                    mGetQuestions.execute();
                    return true;
                }
                return false;
            }
        });
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

        public void setData(Question.Response qr) {
            mResponse = qr;
            notifyDataSetChanged();
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
            return mResponse == null ? 0 : mResponse.items.size();
        }

        @Override
        public Object getItem(int position) {
            return mResponse == null ? null : mResponse.items.get(position);
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
            emptyText.setText(R.string.loading);
            getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
            mSearchListAdapter.setData(null);
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
            emptyText.setText(R.string.no_questions_found);
            getSherlockActivity().setSupportProgressBarIndeterminateVisibility(false);
            if (mResponse != null) {
                mSearchListAdapter.setData(mResponse);
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
