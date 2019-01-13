package six.ca.droiddailyproject.widgets;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import six.ca.droiddailyproject.R;


import java.util.ArrayList;
import java.util.List;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-07.
 */

public class MultiTypeDemo extends Activity {
    private ListView lvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_multi_type);
        lvProducts = (ListView) findViewById(R.id.lv_products);
        new GetData().execute();
    }

    private final class GetData extends AsyncTask<Void, Integer, Void>{
        private List<Product> products = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... params) {
            for(int i = 0; i < 10; i ++){
                Product product;
                if(i % 6 == 0){
                    product = new Product(true, "product" + i);
                }else {
                    product = new Product(false, "item " + i);
                }
                products.add(product);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            lvProducts.setAdapter(new MultiTypeAdapter(MultiTypeDemo.this, products));
            lvProducts.setAdapter(new MultiAdapter(MultiTypeDemo.this, products));
        }
    }
}
