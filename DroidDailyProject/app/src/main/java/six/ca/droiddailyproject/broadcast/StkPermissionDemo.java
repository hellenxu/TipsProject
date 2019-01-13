package six.ca.droiddailyproject.broadcast;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import ca.six.util.IAfterDo;
import ca.six.util.Permission6;
import six.ca.droiddailyproject.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-10-20.
 */

public class StkPermissionDemo extends Activity implements IAfterDo{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_stk_permission);
    }

    public void requestStk(View view){
        Permission6.executeWithPermission(this, "android.permission.RECEIVE_STK_COMMANDS", this);
    }

    @Override
    public void doAfterPermission() {
        Log.d("xxl", "permission granted");
    }

    @Override
    public void userDenyPermission() {
        Permission6.startAppSettings(this, "six.ca.droiddailyproject");
        Log.d("xxl", "permission denied");
    }

    @Override
    protected void onDestroy() {
        Permission6.destory();
        super.onDestroy();
    }
}
