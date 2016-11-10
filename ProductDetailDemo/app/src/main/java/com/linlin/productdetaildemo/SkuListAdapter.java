package com.linlin.productdetaildemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by linlin on 9/13/16.
 */
public class SkuListAdapter extends BaseAdapter {
    private ArrayList<SkuItemModel> dataList;
    private Context context;
    private OnSkuItemSelectedListener onSkuItemSelectedListener;
    private HashMap<String,String> skuMap = new HashMap<>();

    public SkuListAdapter(ArrayList<SkuItemModel> dataList, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.sku_list_item, null);
            holder = new Holder();
            holder.titleTV = (TextView) convertView.findViewById(R.id.skuTitleTV);
            holder.flowLayout = (FlowLayout) convertView.findViewById(R.id.flowLayout);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }

        holder.setSkuItemModel(dataList.get(position));
        return convertView;
    }

    public void setOnSkuItemSelectedListener(OnSkuItemSelectedListener onSkuItemSelectedListener) {
        this.onSkuItemSelectedListener = onSkuItemSelectedListener;
    }

    class Holder implements CompoundButton.OnCheckedChangeListener {
        TextView titleTV;
        FlowLayout flowLayout;

        private SkuItemModel skuItemModel;

        public void setSkuItemModel(SkuItemModel skuItemModel) {
            this.skuItemModel = skuItemModel;
            titleTV.setText(skuItemModel.getAttributeName());
            for (int i=0; i<skuItemModel.getAttributeValue().size(); i++) {
                if (i==0){
                    flowLayout.removeAllViews();
                }
                AttributeModel attributeModel = skuItemModel.getAttributeValue().get(i);
                LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.sku_grid_item, null);
                view.setTag(skuItemModel);
                ToggleButton btn = (ToggleButton) view.findViewById(R.id.itemBtn);
                btn.setTag(attributeModel);
                btn.setOnCheckedChangeListener(this);
                btn.setTextOff(attributeModel.getValue());
                btn.setTextOn(attributeModel.getValue());
                btn.setChecked(getBtnChecked(attributeModel));
                flowLayout.addView(view);
            }
        }

        private boolean getBtnChecked(AttributeModel attributeModel){
            String value = skuMap.get(skuItemModel.getAttributeName());
            if (value == null) return false;
            return value.equals(attributeModel.getValue());
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            LinearLayout parent = (LinearLayout) buttonView.getParent();
            if (isChecked) {
                skuMap.put(skuItemModel.getAttributeName(), ((AttributeModel) buttonView.getTag()).getValue());
                buttonView.setTextColor(buttonView.getContext().getResources().getColor(R.color.holo_red_light));
                resetChild(parent, buttonView);
                if (onSkuItemSelectedListener != null){
                    onSkuItemSelectedListener.onSkuItemSelected((AttributeModel) buttonView.getTag(), skuItemModel);
                }
            }else {
                buttonView.setTextColor(buttonView.getContext().getResources().getColor(R.color.darker_gray));
            }
        }

        private void resetChild(LinearLayout parent, CompoundButton selectButton){
            FlowLayout rootParent = (FlowLayout) parent.getParent();
            if (rootParent == null) return;
            for (int i =0 ; i < rootParent.getChildCount(); i++){
                ViewGroup skuParent = (ViewGroup) rootParent.getChildAt(i);
                ToggleButton skuBtn = (ToggleButton) skuParent.getChildAt(0);
                if (skuBtn != selectButton) {
                    skuBtn.setChecked(false);
                }
            }
        }
    }

    interface OnSkuItemSelectedListener {
        public void onSkuItemSelected(AttributeModel attributeModel, SkuItemModel skuItemModel);
    }
}
