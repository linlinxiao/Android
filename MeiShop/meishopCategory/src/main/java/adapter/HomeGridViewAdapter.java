package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linlin.meishop.HomeActivity;
import com.linlin.meishop.R;

import java.util.List;

import http.HttpImageThread;
import model.Goods;

/**
 * Created by linlin on 8/23/16.
 */
public class HomeGridViewAdapter extends BaseAdapter {
    private List<Goods> goodList;
    private Context context;

    public HomeGridViewAdapter(List<Goods> goodList, Context context) {
        this.goodList = goodList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return goodList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.home_grid_item, null);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            holder.priceTextView = (TextView) convertView.findViewById(R.id.priceTextView);
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.setGoods(goodList.get(position));
        return convertView;
    }

    public class ViewHolder {
        TextView titleTextView;
        ImageView iconImageView;
        TextView priceTextView;

        private Goods goods;

        public void setGoods(Goods goods) {
            this.goods = goods;
            titleTextView.setText(goods.getTitle());
            priceTextView.setText("￥"+goods.getPrice());

            new HttpImageThread(goods.getPic(), ((HomeActivity)context).getHandler(), iconImageView).start();
        }
    }
}
