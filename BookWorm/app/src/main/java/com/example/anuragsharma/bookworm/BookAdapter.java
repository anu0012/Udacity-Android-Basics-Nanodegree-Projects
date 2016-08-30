package com.example.anuragsharma.bookworm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anuragsharma on 20/08/16.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context,ArrayList<Book> bookList){
        super(context,0,bookList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Book book = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(book.getTitle());

        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText(book.getAuthor());

        return listItemView;
    }
}
