package com.example.anuragsharma.bookworm;

/**
 * Created by anuragsharma on 20/08/16.
 */
public class Book {

    private String mTitle;
    private String mAuthor;

    public Book(String title,String author){
        mTitle = title;
        mAuthor = author;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getAuthor(){
        return mAuthor;
    }
}
