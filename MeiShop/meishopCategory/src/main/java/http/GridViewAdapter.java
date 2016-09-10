package http;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linlin.meishop.R;

import model.Category;

/**
 * Created by linlin on 8/17/16.
 */
public class GridViewAdapter extends BaseAdapter {
    private static final String TAG = "GridViewAdapter";

    private Category category;
    private LayoutInflater inflater;

    public GridViewAdapter(Category category, LayoutInflater inflater) {
        this.category = category;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return category.getSubs().size();
    }

    @Override
    public Object getItem(int position) {
        return category.getSubs().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        Holder holder = new Holder(convertView);
        holder.setCategory(category.getSubs().get(position));

        return convertView;
    }

    class Holder {
        private Category category;
        private Handler handler = new Handler();

        private TextView nameTV;
        private ImageView iconImageView;

        private Holder(){}
        public Holder(View view) {
            iconImageView = (ImageView) view.findViewById(R.id.iconImageView);
            nameTV = (TextView) view.findViewById(R.id.textTV);
        }

        public void setCategory(Category category) {
            this.category = category;
            nameTV.setText(category.getName());
            Log.i(TAG, "setCategory: "+category.getName());
            if (category.getIcon().length() == 0)return;
//            new HttpImageThread(category.getIcon(), handler, iconImageView).start();
        }
    }
}
