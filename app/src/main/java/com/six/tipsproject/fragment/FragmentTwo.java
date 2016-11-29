package com.six.tipsproject.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.six.tipsproject.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-28.
 */

public class FragmentTwo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_one, container, false);
        TextView label = (TextView) view.findViewById(R.id.tv_label);
        label.setText(R.string.label_fragment_two);
        view.findViewById(R.id.btn_load).setVisibility(View.GONE);
        return view;
    }
}
