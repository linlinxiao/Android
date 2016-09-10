package http;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linlin.meishop.R;

import java.util.ArrayList;

import model.Category;

/**
 * Created by linlin on 8/17/16.
 */
public class ListViewAdapter extends BaseAdapter {

    private ArrayList<Category> categories;
    private LayoutInflater inflater;
    private View previousSelectedView;
    private View currentSelectedView;
    private int currentSelectedPosition;

    public ListViewAdapter(ArrayList<Category> categories, LayoutInflater inflater) {
        this.categories = categories;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        View lineView = convertView.findViewById(R.id.lineView);

        if (position == currentSelectedPosition) {
            convertView.setBackground(null);
            lineView.setVisibility(View.VISIBLE);
        }else {
            convertView.setBackgroundResource(R.drawable.white_button_background);
            lineView.setVisibility(View.GONE);
        }
        Holder holder = new Holder(convertView);
        holder.setCategory(categories.get(position));

        return convertView;
    }

    public void setCurrentSelectedPosition(int currentSelectedPosition) {
        this.currentSelectedPosition = currentSelectedPosition;
    }


//    public void setPreviousSelectedView(View previousSelectedView) {
//        this.previousSelectedView = previousSelectedView;
//        previousSelectedView.setBackgroundResource(R.drawable.white_button_background);
//        View lineView = previousSelectedView.findViewById(R.id.lineView);
//        lineView.setVisibility(View.GONE);
//    }
//
//    public void setCurrentSelectedView(View currentSelectedView) {
//        this.currentSelectedView = currentSelectedView;
//        currentSelectedView.setBackground(null);
//        lineView.setVisibility(View.VISIBLE);
//        ((CategoryActivity)inflater.getContext()).setPreviousItem(convertView);
//    }
//
//    private void refreshCurrentItem(View view) {
//        view.setBackground(null);
//        View lineView =  view.findViewById(R.id.lineView);
//        lineView.setVisibility(View.VISIBLE);
//    }
//    private void refreshPreviousItem(View view) {
//        if (view == null) return;
//        view.setBackgroundResource(R.drawable.white_button_background);
//        View lineView =  view.findViewById(R.id.lineView);
//        lineView.setVisibility(View.GONE);
//    }

    class Holder {
        private Category category;

        private TextView nameTV;

        private Holder(){}
        public Holder(View view) {
            nameTV = (TextView) view.findViewById(R.id.nameTV);
        }

        public void setCategory(Category category) {
            this.category = category;
            nameTV.setText(category.getName());
        }
    }
}