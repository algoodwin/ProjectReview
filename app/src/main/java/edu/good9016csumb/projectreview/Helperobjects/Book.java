package edu.good9016csumb.projectreview.Helperobjects;

/**
 * Created by alyssiagoodwin on 5/2/17.
 */

public class Book {

    private String mId;
    private String mTitle;
    private String mAuthor;
    private String mprice;
    public Book() {
        mTitle = "";
        mAuthor = "";
    }

    public Book(String title, String author, String id, String price) {
        mTitle = title;
        mAuthor = author;
        mId = id;
        mprice = price;

    }


    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }


    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    public String getPrice() {
        return mprice;
    }

    public void setPrice(String price) {
        mprice = price;
    }
    @Override
    public String toString() {
        return "Book [id = " + mId + ", title" + mTitle + ", author = " + mAuthor +
                ", price = " + mprice + "]";
    }
}

