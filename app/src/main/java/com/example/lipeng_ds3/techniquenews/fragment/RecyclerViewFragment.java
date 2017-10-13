package com.example.lipeng_ds3.techniquenews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lipeng_ds3.techniquenews.R;
import com.example.lipeng_ds3.techniquenews.model.Crime;
import com.example.lipeng_ds3.techniquenews.model.CrimeLab;


import java.util.List;

/**
 * Created by lipeng-ds3 on 2017/10/12.
 */

public class RecyclerViewFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        Toast.makeText(getActivity(), "RecyclerView is opened", Toast.LENGTH_SHORT).show();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> mCrimes = crimeLab.getCrimes();
        mAdapter = new CrimeAdapter(mCrimes);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.mTitleTextView.setText(crime.getTitle());
            holder.mTitleTextView.setGravity(Gravity.CENTER);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder{
        public TextView mTitleTextView;

        public CrimeHolder(View view){
            super(view);
            mTitleTextView = (TextView)view;
        }
    }
}
