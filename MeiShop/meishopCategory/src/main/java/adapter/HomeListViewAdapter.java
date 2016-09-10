package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.linlin.meishop.R;

import java.util.List;

import model.Product;

/**
 * Created by linlin on 8/23/16.
 */
public class HomeListViewAdapter extends BaseAdapter {

    private List<Product> products;
    private Context context;

    public HomeListViewAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.home_list_item, null);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            holder.contentGridView = (GridView) convertView.findViewById(R.id.contentGridView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.setProduct(products.get(position));
        return convertView;
    }

    public class ViewHolder {
        TextView titleTextView;
        GridView contentGridView;

        private Product product;

        public void setProduct(Product product) {
            this.product = product;
            titleTextView.setText(product.getTitle());
            contentGridView.setAdapter(new HomeGridViewAdapter(product.getGoodsList(), context));
        }
    }
}
