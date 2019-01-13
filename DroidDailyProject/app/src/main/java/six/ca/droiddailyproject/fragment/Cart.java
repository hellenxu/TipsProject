package six.ca.droiddailyproject.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-28.
 */

public class Cart {
    private Activity activity;
    private Fragment fragment;

    public Cart(Context context){
        if(context instanceof Activity){
            activity = (Activity) context;
        }
    }

    public Cart(Fragment fragment){
        this.fragment = fragment;
    }

    public void jumpDialogActivity(){
        Intent intent = new Intent(activity, DialogActivity.class);
        activity.startActivityForResult(intent, 0);
    }

    public void jumpDialog(){
        Intent intent = new Intent(fragment.getActivity(), DialogActivity.class);
        fragment.startActivityForResult(intent, 0);
    }
}
