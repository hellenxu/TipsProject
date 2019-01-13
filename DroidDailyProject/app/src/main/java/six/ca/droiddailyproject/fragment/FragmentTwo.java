package six.ca.droiddailyproject.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import six.ca.droiddailyproject.R;


import java.util.ArrayList;
import java.util.List;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-28.
 */

public class FragmentTwo extends Fragment {
    private List<String> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_one, container, false);
        TextView label = (TextView) view.findViewById(R.id.tv_label);
        label.setText(R.string.label_fragment_two);
//        view.findViewById(R.id.btn_load).setVisibility(View.GONE);
        for(int i = 0; i < 10; i ++){
            data.add("item: " + i);
        }
        view.findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                ListView options = new ListView(getActivity());
                options.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(data.get(position))
                                .setMessage("You click " + data.get(position))
                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .create()
                                .show();
                    }
                });
                options.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data));
                builder.setTitle("Option List")
                        .setView(options)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("xxl-onDestroyView-Two");
    }
}
