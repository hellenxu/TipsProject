package six.ca.droiddailyproject.fonts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-06.
 */
public class CSpinnerAdapter extends CustomSpinnerAdapter {
    private Typeface ytkTypeface;
    private LayoutInflater mLayoutInflater;

    public CSpinnerAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        ytkTypeface = Typeface.createFromAsset(context.getAssets(), "FZYTK.TTF");
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTypeface(ytkTypeface);
        view.setTextColor(Color.BLUE);
        Log.d("xxl", "view: " + view);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTypeface(ytkTypeface);
        view.setTextColor(Color.BLUE);
        Log.d("xxl", "Drop view: " + view + "; pos: " + position);
        return view;
//        SpinnerViewHolder holder;
//        if (convertView == null) {
//            convertView = mLayoutInflater.inflate(R.layout.spinner_item, parent, false);
//            holder = new SpinnerViewHolder();
//            holder.mTextViewItem = (TextView) convertView.findViewById(android.R.id.text1);
//            convertView.setTag(holder);
//        } else {
//            holder = (SpinnerViewHolder) convertView.getTag();
//        }
//
//        holder.mTextViewItem.setText(getItem(position));
//        Log.d("xxl", "Drop convertView: " + convertView + "; pos: " + position);
//        return convertView;
    }

    private static class SpinnerViewHolder {
        TextView mTextViewItem;
    }
}
