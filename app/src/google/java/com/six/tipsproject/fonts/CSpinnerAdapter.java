package com.six.tipsproject.fonts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-06.
 */
public class CSpinnerAdapter extends CustomSpinnerAdapter {
    private Typeface stkTypeface;

    public CSpinnerAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        stkTypeface = Typeface.createFromAsset(context.getAssets(), "FZSTK.TTF");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTypeface(stkTypeface);
        view.setTextColor(Color.BLUE);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTypeface(stkTypeface);
        return view;
    }
}
