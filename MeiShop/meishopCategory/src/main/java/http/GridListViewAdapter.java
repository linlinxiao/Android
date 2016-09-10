package http;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.linlin.meishop.R;

import java.util.ArrayList;

import model.Category;

/**
 * Created by linlin on 8/18/16.
 */
public class GridListViewAdapter extends BaseAdapter {
    private static final String TAG = "GridListViewAdapter";

    private ArrayList<Category> categories;
    private LayoutInflater inflater;

    public GridListViewAdapter(ArrayList<Category> categories, LayoutInflater inflater) {
        this.categories = categories;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return categories.size()*2;
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position%2);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category category =  categories.get(position/2);

        if (position%2 == 0) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        } else if (position%2 == 1) {
            convertView = inflater.inflate(R.layout.grid_list_item, null);

        }

        Log.i(TAG, "getView: " + category.getHasChildren());

        if (position%2 == 1 && category.getHasChildren().equals(Category.TRUE)) {
            Holder holder = new Holder(convertView);
            holder.setCategory(category);
        } else if (position%2 == 0){
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(category.getName());
        }


        return convertView;
    }

    class Holder {
        private Category category;

        private GridView gridView;

        private Holder() {
        }

        public Holder(View view) {
            Log.i(TAG, "Holder: " + view.toString());

            gridView = (GridView) view.findViewById(R.id.gridView);
        }

        public void setCategory(Category category) {
            this.category = category;
            Log.i(TAG, "setCategory: " + gridView.toString());

            gridView.setAdapter(new GridViewAdapter(category, inflater));
        }
    }
}


