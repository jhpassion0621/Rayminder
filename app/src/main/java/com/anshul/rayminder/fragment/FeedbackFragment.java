package com.anshul.rayminder.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anshul.rayminder.MainActivity;
import com.anshul.rayminder.R;

public class FeedbackFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View rootView;

    public static FeedbackFragment newInstance(String param1, String param2) {
        FeedbackFragment fragment = new FeedbackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FeedbackFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        ImageView nav = (ImageView) rootView.findViewById(R.id.imgNav);
        nav.setOnClickListener(this);
        rootView.findViewById(R.id.lblSave).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgNav:
                NavigationDrawerFragment.mDrawerLayout.openDrawer(NavigationDrawerFragment.mFragmentContainerView);
                break;
            case R.id.lblSave:
                ((MainActivity) getActivity()).replaceFragment(ReminderFragment.newInstance("", ""));
                break;
        }
    }
}
