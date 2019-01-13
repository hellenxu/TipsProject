package six.ca.droiddailyproject.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import six.ca.droiddailyproject.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-11-07.
 */

public class MultiTypeAdapter extends BaseAdapter {
    private List<Product> dataSource = new ArrayList<>();
    private Context context;
    private int listRows = 0;
    private HashMap<Integer, int[]> linesTypesIndex = new LinkedHashMap<>();

    private static final int COUNT_TYPE = 2;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int INVALID_ITEM_INDEX = -1;

    public MultiTypeAdapter(Context context, List<Product> data) {
        this.context = context;
        dataSource = data;
        dataParser();
    }

    public void setDataSource(List<Product> data) {
        dataSource = data;
        dataParser();
        notifyDataSetChanged();
    }

    private void dataParser() {
        if (dataSource == null) {
            return;
        }

        boolean isFirstItem = true;
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).isHeader) {
                linesTypesIndex.put(listRows, new int[]{TYPE_HEADER, i, INVALID_ITEM_INDEX});
//                System.out.println("put(" + listRows + ", [" + TYPE_HEADER + ", " + i + ", " + INVALID_ITEM_INDEX + "])");
                listRows++;
                isFirstItem = true;
            } else {
                if (isFirstItem) {
                    if(i + 1 < dataSource.size()){
                        if(!dataSource.get(i + 1).isHeader){
                            linesTypesIndex.put(listRows, new int[]{TYPE_ITEM, i, i + 1});
//                            System.out.println("put(" + listRows + ", [" + TYPE_ITEM + ", " + i + ", " + (i + 1) + "])");
                        }else {
                            linesTypesIndex.put(listRows, new int[]{TYPE_ITEM, i, INVALID_ITEM_INDEX});
//                            System.out.println("put(" + listRows + ", [" + TYPE_ITEM + ", " + i + ", " + INVALID_ITEM_INDEX + "])");
                            listRows ++;
                        }
                    }else {
                        linesTypesIndex.put(listRows, new int[]{TYPE_ITEM, i, INVALID_ITEM_INDEX});
//                        System.out.println("put(" + listRows + ", [" + TYPE_ITEM + ", " + i + ", " + INVALID_ITEM_INDEX + "])");
                        listRows ++;
                    }
                    isFirstItem = false;
                } else {
                    listRows++;
                    isFirstItem = true;
                }
            }
        }

        System.out.println("lines: " + listRows);

    }

    @Override
    public int getCount() {
        return listRows;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return linesTypesIndex.get(position)[0];
    }

    @Override
    public int getViewTypeCount() {
        return COUNT_TYPE;
    }

    private int[] posToIndex(int pos){
        return linesTypesIndex.get(pos);
    }

    private static final int FIRST_ITEM_INDEX = 1;
    private static final int SECOND_ITEM_INDEX = 2;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int[] dataInfo = posToIndex(position);

        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                ProductHeaderViewHolder headerHolder;
                if (convertView == null) {
                    headerHolder = new ProductHeaderViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
                    headerHolder.header = (TextView) convertView.findViewById(R.id.tv_header);
                    convertView.setTag(headerHolder);
                } else {
                    headerHolder = (ProductHeaderViewHolder) convertView.getTag();
                }
                headerHolder.header.setText(dataSource.get(dataInfo[FIRST_ITEM_INDEX]).productName);

                break;
            case TYPE_ITEM:
                ProductItemViewHolder itemViewHolder;
                if (convertView == null) {
                    itemViewHolder = new ProductItemViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
                    itemViewHolder.productLogoLeft = (ImageView) convertView.findViewById(R.id.iv_avatar_left);
                    itemViewHolder.productLogoRight = (ImageView) convertView.findViewById(R.id.iv_avatar_right);
                    itemViewHolder.productNameLeft = (TextView) convertView.findViewById(R.id.tv_product_name_left);
                    itemViewHolder.productNameRight = (TextView) convertView.findViewById(R.id.tv_product_name_right);
                    convertView.setTag(itemViewHolder);
                } else {
                    itemViewHolder = (ProductItemViewHolder) convertView.getTag();
                }
                itemViewHolder.productNameLeft.setText(dataSource.get(dataInfo[FIRST_ITEM_INDEX]).productName);

                if(dataInfo[SECOND_ITEM_INDEX] == INVALID_ITEM_INDEX){
                    itemViewHolder.productNameRight.setVisibility(View.GONE);
                    itemViewHolder.productLogoRight.setVisibility(View.GONE);
                }else {
                    itemViewHolder.productNameRight.setVisibility(View.VISIBLE);
                    itemViewHolder.productLogoRight.setVisibility(View.VISIBLE);
                    itemViewHolder.productNameRight.setText(dataSource.get(dataInfo[SECOND_ITEM_INDEX]).productName);
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
