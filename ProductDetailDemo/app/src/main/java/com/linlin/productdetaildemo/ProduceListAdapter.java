package com.linlin.productdetaildemo;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by linlin on 9/13/16.
 */
public class ProduceListAdapter extends BaseAdapter {
    private ArrayList<ProductListItemModel> dataList;
    private Context context;
    private Handler handler = new Handler();

    public ProduceListAdapter(ArrayList<ProductListItemModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, null);
            holder = new Holder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.priceTV = (TextView) convertView.findViewById(R.id.productPriceTV);
            holder.productNameTV = (TextView) convertView.findViewById(R.id.productNameTV);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }

        holder.setProductListItemModel(dataList.get(position));
        return convertView;
    }

    class Holder {
        ImageView imageView;
        TextView productNameTV;
        TextView priceTV;

        private ProductListItemModel productListItemModel;

        public void setProductListItemModel(ProductListItemModel productListItemModel) {
            this.productListItemModel = productListItemModel;

            new HttpImageThread(productListItemModel.getThumbnailUrl100(), handler, imageView).start();
            productNameTV.setText(productListItemModel.getProductName());
            priceTV.setText("￥"+productListItemModel.getSalePrice());
        }

        public ProductListItemModel getProductListItemModel() {
            return productListItemModel;
        }
    }
}
