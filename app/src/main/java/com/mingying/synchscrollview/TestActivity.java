package com.mingying.synchscrollview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Wsw
 * @description:
 * @date :2020/5/7 12:41
 */
public class TestActivity extends AppCompatActivity {

    @BindView(R.id.textView_productName)
    TextView textViewProductName;
    @BindView(R.id.horizontalScrollView)
    SyncHScrollView horizontalScrollView;
    @BindView(R.id.listView)
    GestureListView listView;
    @BindView(R.id.lin_title_content)
    LinearLayout linTitleContent;
    private HotsPotRelatedStockAdapter relatedStockAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspotrelatedstock);
        ButterKnife.bind(this);
        relatedStockAdapter = new HotsPotRelatedStockAdapter(this, listView, horizontalScrollView);
        listView.setAdapter(relatedStockAdapter);
        relatedStockAdapter.setTitleCount(10);
        ArrayList<String> a = new ArrayList<>();
        addTitleTag();
        for (int i = 0; i < 30; i++) {
            a.add(i+ "");
        }
        relatedStockAdapter.setDatas(a);
    }

    private void addTitleTag() {
        linTitleContent.removeAllViews();
        for (int i = 0; i <10; i++) {

                View view = LayoutInflater.from(this).inflate(R.layout.title_item_horizontalllist, null);
                TextView txtContent = view.findViewById(R.id.txt_content);
                txtContent.setText("内容");
                linTitleContent.addView(view);


        }
    }
}
