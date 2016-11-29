package com.six.tipsproject.fragment;

import android.app.Fragment;
import android.content.Intent;
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

public class FragmentOne extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_one, container, false);
        TextView label = (TextView) view.findViewById(R.id.tv_label);
        label.setText(R.string.label_fragment_one);
        final Cart cart = new Cart(this);

        view.findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cart.jumpDialogActivity();
                cart.jumpDialog();
//                Intent intent = new Intent(getActivity(), DialogActivity.class);
//                startActivityForResult(intent, 0);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("xxl-frag");
        //do something...
    }
}
