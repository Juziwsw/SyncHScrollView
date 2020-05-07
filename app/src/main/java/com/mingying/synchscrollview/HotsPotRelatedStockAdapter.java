package com.mingying.synchscrollview;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;



import butterknife.BindView;

/**
 * @author wsw
 */
public class HotsPotRelatedStockAdapter extends BaseAdapter<String> {
    private int tagCount;

    public HotsPotRelatedStockAdapter(Context context, GestureListView listView, SyncHScrollView mHeadStickyHSView) {
        super(context, listView, mHeadStickyHSView);
    }

    public void setTitleCount(int tagCount) {
        this.tagCount = tagCount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hotspotrelatedstock, null);
            holder = new ViewHolder(convertView, tagCount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bindView(getItem(position), position);
        return convertView;
    }

    class ViewHolder extends BaseViewHolderList {
        @BindView(R.id.textView_productName)
        TextView textViewProductName;
        @BindView(R.id.txt_productCode)
        TextView txtProductCode;
        @BindView(R.id.lin_content_tag)
        LinearLayout linContentTag;

        public ViewHolder(View view, int tagCount) {
            super(view);
            addTagContent(linContentTag, tagCount);
        }

        public void bindView(String bean, int position) {
            /*JSONArray array = JSONArray.parseArray(bean);
            textViewProductName.setText((String) array.get(0));
            txtProductCode.setText((String) array.get(1));
            for (int i = 0; i < linContentTag.getChildCount(); i++) {
                View view = linContentTag.getChildAt(i);
                TextView textView = view.findViewById(R.id.txt_content);
                if (array.size() > i + 2) {
                    textView.setText(Html.fromHtml((String) array.get(i + 2)));
                }
            }*/
        }

        private void addTagContent(LinearLayout linContentTag, int tagCount) {
            linContentTag.removeAllViews();
            for (int i = 0; i < 10; i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.title_item_horizontalllist, null);
                linContentTag.addView(view);
            }
        }
    }
}
