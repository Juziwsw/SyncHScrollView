package com.mingying.synchscrollview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;

/**
 * @author wsw
 * 基金界面股票型右边Recycler适配器
 */
public class FoundationRightListAdapter extends BaseAdapter<TestBean> {


    public FoundationRightListAdapter(Context context, GestureListView listView, SyncHScrollView mHeadStickyHSView) {
        super(context, listView, mHeadStickyHSView);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foundation_common, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bindView(getItem(position), position);
        return convertView;
    }

    class ViewHolder extends BaseViewHolderList {
        @BindView(R.id.txt_net_worth)
        TextView txtNetWorth;
        @BindView(R.id.txt_daily_gain)
        TextView txtDailyGain;
        @BindView(R.id.txt_nearly_one_month)
        TextView txtNearlyOneMonth;
        @BindView(R.id.txt_nearly_three_month)
        TextView txtNearlyThreeMonth;
        @BindView(R.id.txt_nearly_six_month)
        TextView txtNearlySixMonth;
        @BindView(R.id.txt_nearly_one_year)
        TextView txtNearlyOneYear;
        @BindView(R.id.txt_nearly_three_year)
        TextView txtNearlyThreeYear;
        @BindView(R.id.txt_Since_founded)
        TextView txtSinceFounded;
        @BindView(R.id.textView_productName)
        TextView textViewProductName;
        @BindView(R.id.txt_productCode)
        TextView txtProductCode;

        public ViewHolder(View view) {
            super(view);
        }

        public void bindView(TestBean bean, int position) {

        }


    }

}
