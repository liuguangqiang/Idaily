package com.liuguangqiang.idaily.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ItemWebviewBinding;
import com.liuguangqiang.idaily.domain.entity.Story;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 详情页adapter
 * Created by Eric on 15/6/6.
 */
public class WebViewAdapter extends BaseQuickAdapter<Story, BaseViewHolder> {

    public WebViewAdapter(@Nullable List<Story> data) {
        super(R.layout.item_webview, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Story item) {
        ItemWebviewBinding binding = ItemWebviewBinding.bind(helper.itemView);
        binding.webView.loadData(getBody(item), "text/html; charset=UTF-8", null);
    }

    public String getBody(Story story) {
        if (story == null) return "";
        return loadDataWithCSS(story.getBody(), story.getCss().get(0));
    }

    private String loadDataWithCSS(String loadData, String cssPath) {
        String header = "<html><head><link href=\"%s\" type=\"text/css\" rel=\"stylesheet\"/></head><body>";
        String footer = "</body></html>";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(header, cssPath));
        sb.append(loadData);
        sb.append(footer);
        return sb.toString();
    }
}