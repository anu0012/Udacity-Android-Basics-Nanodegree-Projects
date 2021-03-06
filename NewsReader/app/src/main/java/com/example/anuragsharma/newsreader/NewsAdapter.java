package com.example.anuragsharma.newsreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anuragsharma on 23/08/16.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context,List<News> news){
        super(context,0,news);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        News news = getItem(position);
        TextView titleView = (TextView) listItemView.findViewById(R.id.news_title);
        titleView.setText(news.getTitle());
        TextView sectionView = (TextView) listItemView.findViewById(R.id.news_section);
        sectionView.setText("Category: "+news.getSection());
        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        authorView.setText("Contributors: "+news.getAuthor());

        return listItemView;
    }
}
