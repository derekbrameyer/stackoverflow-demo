package com.willowtreeapps.stackoverflowdemo.fragment;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.willowtreeapps.stackoverflowdemo.R;
import com.willowtreeapps.stackoverflowdemo.model.Question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import oak.widget.CancelEditText;
import roboguice.inject.InjectView;

public class SearchFragment extends RoboSherlockListFragment {

    @InjectView(R.id.search_text) CancelEditText searchText;

    private int mPageNumber = 1;
    private SearchListAdapter mSearchListAdapter;

    private String[] dataArray = new String[]{"This", "Is", "An", "Android", "App!"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                dataArray);
        setListAdapter(listAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private class SearchListAdapter extends BaseAdapter {

        private Question.Response mResponse;

        public SearchListAdapter(Question.Response response) {
            mResponse = response;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
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

    @Override
    public void onListItemClick(ListView list, View v, int position, long id) {
        Toast.makeText(getSherlockActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT)
                .show();
    }
}
