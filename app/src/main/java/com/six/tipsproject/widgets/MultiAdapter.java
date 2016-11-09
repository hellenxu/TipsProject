package com.six.tipsproject.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.six.tipsproject.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-07.
 */

public class MultiAdapter extends BaseAdapter {
    private static final int COUNT_TYPE = 2;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Product> data;
    private List<Integer> map;
    private Context ctx;

    private int line = 0;
    private int maxColumn = 2;
    private int sameLineCursor = 0;

    public MultiAdapter(Context context, List<Product> data) {
        ctx = context;
        this.data = data;

        organizeData();
    }

    private void organizeData() {
        List<Integer> tmp = new ArrayList<>();

        for (Product pro : data) {
            if (pro.isHeader) {
                if (sameLineCursor != 0) {
                    sameLineCursor = 0;
                    line++;
                }
                tmp.add(line);
                line++;
            } else {
                tmp.add(line);
                sameLineCursor++;
                if (sameLineCursor >= maxColumn) {
                    line++;
                    sameLineCursor = 0;
                }
            }
        }

        // optimize
        map = new ArrayList<>();
        int lastItem = -1;
        for (int i = 0; i < tmp.size(); i++) {
            int position = tmp.get(i);
            if (position != lastItem) {
                map.add(i);
                lastItem = position;
            }
        }
    }


    @Override
    public int getCount() {
        return line;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        int index = getIndexFromPosition(position);
        if (data.get(index).isHeader) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    private int getIndexFromPosition(int position) {
        return map.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return COUNT_TYPE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int index = getIndexFromPosition(position);
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                ProductHeaderViewHolder headerHolder;
                if (convertView == null) {
                    headerHolder = new ProductHeaderViewHolder();
                    convertView = LayoutInflater.from(ctx).inflate(R.layout.item_header, parent, false);
                    headerHolder.header = (TextView) convertView.findViewById(R.id.tv_header);
                    convertView.setTag(headerHolder);
                } else {
                    headerHolder = (ProductHeaderViewHolder) convertView.getTag();
                }
                headerHolder.header.setText(data.get(index).productName);

                break;
            case TYPE_ITEM:
                ProductItemViewHolder itemViewHolder;
                if (convertView == null) {
                    itemViewHolder = new ProductItemViewHolder();
                    convertView = LayoutInflater.from(ctx).inflate(R.layout.item_product, parent, false);
                    itemViewHolder.productLogoLeft = (ImageView) convertView.findViewById(R.id.iv_avatar_left);
                    itemViewHolder.productLogoRight = (ImageView) convertView.findViewById(R.id.iv_avatar_right);
                    itemViewHolder.productNameLeft = (TextView) convertView.findViewById(R.id.tv_product_name_left);
                    itemViewHolder.productNameRight = (TextView) convertView.findViewById(R.id.tv_product_name_right);
                    convertView.setTag(itemViewHolder);
                } else {
                    itemViewHolder = (ProductItemViewHolder) convertView.getTag();
                }

                itemViewHolder.productNameLeft.setText(data.get(index).productName);
                Product next = data.get(index + 1);
                if (next.isHeader) {
                    itemViewHolder.productLogoRight.setVisibility(View.INVISIBLE);
                    itemViewHolder.productNameRight.setText("");
                } else {
                    itemViewHolder.productLogoRight.setVisibility(View.VISIBLE);
                    itemViewHolder.productNameRight.setText(data.get(index + 1).productName);
                }

                break;
        }

        return convertView;
    }

    private final class ProductHeaderViewHolder {
        TextView header;
    }

    private final class ProductItemViewHolder {
        TextView productNameLeft, productNameRight;
        ImageView productLogoLeft, productLogoRight;
    }
}

