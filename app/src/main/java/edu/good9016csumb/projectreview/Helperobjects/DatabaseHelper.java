package edu.good9016csumb.projectreview.Helperobjects;

import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;


/**
 * Created by alyssiagoodwin on 5/2/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


        private static final String DATABASE_NAME = "library"; // gives it the name of hte database
        private static final int DATABASE_VERSION = 1; //gives it the version (going to have multiple types from the database, like "refreshes" the database

        private static final String TABLE_BOOKZ = "books"; //name of the table

        //columns in the table
        private static final String KEY_ID = "id";
        private static final String KEY_TITLE = "title";
        private static final String KEY_AUTHOR = "author";
        private static final String KEY_PRICE = "price";


    //bundles columns into the an array
        private static final String[] COLUMNS = {KEY_ID, KEY_TITLE, KEY_AUTHOR, KEY_PRICE};

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        /////////////create tables and default data in the onclick/////////
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String CREATE_BOOK_TABLE = "CREATE TABLE books ( id TEXT, title TEXT, author TEXT, price TEXT)";

            sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);
            sqLiteDatabase.execSQL(UserTable.CREATE_USER_TABLE); // links it from the UserTbale class
            //Todo: insert the admin stuff here
        }

        @Override
                ///////Drop statments to make sure the table exits/////
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS books");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");

            this.onCreate(sqLiteDatabase);

        }
                   /////ADDS THE USER TO THE THINGY//////////
        public void addUser(User user) throws SQLException {

            Log.d("addUser", user.toString());
            SQLiteDatabase db = this.getWritableDatabase(); //get database instance
            ContentValues values = new ContentValues(); // create object of content values that temp stores what values are for each col before inserting
            values.put(UserTable.KEY_USERNAME, user.getUsername()); //links the data of user
            values.put(UserTable.KEY_PASSWORD, user.getPassword());//links the data of password
            values.put(UserTable.KEY_ADMIN, user.isAdmin());//links the data of admin
            db.insertOrThrow(UserTable.TABLE_USERS, null, values);
            db.close();
        }

        public void addBook(Book book) {
            Log.d("addBook", book.toString());
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_TITLE, book.getTitle());
            values.put(KEY_AUTHOR, book.getAuthor());
            values.put(KEY_PRICE, book.getPrice());
            values.put(KEY_ID, book.getId());

            db.insert(TABLE_BOOKZ, null, values);
            db.close();
        }

        public Book getBook(int id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_BOOKZ,
                    COLUMNS,
                    " id = ?",
                    new String[] { String.valueOf(id) },
                    null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            Book book = new Book();
            book.setId((cursor.getString(0)));
            book.setTitle(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setPrice(cursor.getString(3));
            Log.d("getBook(" + id + ")", book.toString());

            return book;

        }
        ////////GET ALL BOOKS /////
        public ArrayList<Book> getAllBooks() {
            ArrayList<Book> books = new ArrayList<>();
            String query = "SELECT * FROM " + TABLE_BOOKZ;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(query,null);

            Book book = null;

            if(cursor.moveToFirst()) {
                do {
                    book = new Book();
                    book.setId((cursor.getString(0)));
                    book.setTitle(cursor.getString(1));
                    book.setAuthor(cursor.getString(2));
                    book.setPrice(cursor.getString(3));

                    books.add(book);
                } while (cursor.moveToNext());
            }

            Log.d("getAllBooks()", books.toString());

            return books;

        }
        //////////////////////////GET ALL USERS//////////////////////////
        public ArrayList<User> getAllUsers() {
            ArrayList<User> users = new ArrayList<>();
            String query = "SELECT * FROM " + UserTable.TABLE_USERS;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(query,null);

            User user = null;

            if(cursor.moveToFirst()) {
                do {
                    user = new User();
                    user.setAdmin(Boolean.parseBoolean(cursor.getString(0)));
                    user.setUsername(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    users.add(user);
                } while (cursor.moveToNext());
            }

            Log.d("getAllUsers()", users.toString());

            return users;

        }

//Changes the "row" use it for if the book is checkout or nah |||||| check transaction log to see if it checked out or nah

        public int updateBook (Book book) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("title", book.getTitle());
            values.put("author", book.getAuthor());
            values.put("id", book.getId());
            values.put("price", book.getPrice());
            int i = db.update(TABLE_BOOKZ,
                    values,
                    KEY_ID + " = ? ",
                    new String[] { String.valueOf(book.getId())});

            db.close();
            return i;
        }


        public void deleteBook(Book book) {

            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_BOOKZ,
                    KEY_ID + " = ?",
                    new String[] { String.valueOf(book.getId())});

            db.close();

            Log.d("deleteBook", book.toString());
        }
    }

