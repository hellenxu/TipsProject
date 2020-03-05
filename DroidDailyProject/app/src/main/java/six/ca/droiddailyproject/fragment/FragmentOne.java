package six.ca.droiddailyproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import six.ca.droiddailyproject.R;


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

    @Override
    public void onDestroy() {
        System.out.println("xxl-FragmentOne-onDestroy");
        super.onDestroy();
    }
}
