package six.ca.droiddailyproject.mockito;

import android.content.Context;

/**
 * Created by Xiaolin on 2016-07-06.
 */
public class GetResources {
    private Context context;

    public GetResources(Context context){
        this.context = context;
    }

    public String getString(int resId){
        return context.getString(resId);
    }
}
