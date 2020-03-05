package six.ca.droiddailyproject.widgets;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import six.ca.droiddailyproject.R;


import java.util.ArrayList;
import java.util.List;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-16.
 */

public class SampleViewPager extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_vp);
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SampleViewPager.this, "back", Toast.LENGTH_SHORT).show();
            }
        });

        List<Integer> data = new ArrayList<>();
        data.add(R.drawable.round_home);
        data.add(R.drawable.menu_bg);
        data.add(R.drawable.round_home);

        ((ViewPager)findViewById(R.id.vp_banner)).setAdapter(new ImageAdapter(this, data));

    }

    private final class ImageAdapter extends PagerAdapter{
        List<Integer> data = new ArrayList<>();
        Context ctx;

        ImageAdapter(Context ctx, List<Integer> data){
            this.ctx = ctx;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = LayoutInflater.from(ctx).inflate(R.layout.item_vp, container, false);
            ImageView content = (ImageView) itemView.findViewById(R.id.iv_content);
            content.setImageResource(data.get(position));
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            ViewPager viewPager = (ViewPager) container;
//            viewPager.removeView((View) object);
            container.removeView((View) object);
        }
    }
}
