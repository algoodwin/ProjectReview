package edu.good9016csumb.projectreview.Helperobjects;

/**
 * Created by alyssiagoodwin on 5/9/17.
 */

public class Transactions_Table {
    public static final String TABLE_TRANS = "trans"; //name of the table

    //columns in the table
    public static final String KEY_TYPE = "type";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PICKUP = "pickup";
    public static final String KEY_RETURN = "return";
    public static final String KEY_RESERVATION = "reservation";


    //bundles columns into the an array
    public static final String[] COLUMNS = {KEY_TYPE, KEY_USERNAME, KEY_TITLE,KEY_PICKUP,KEY_RETURN, KEY_RESERVATION };
    public static final String CREATE_TRANS_TABLE = "CREATE TABLE " + TABLE_TRANS + " ( " +
            KEY_TYPE + " TEXT, " +
            KEY_USERNAME+ " TEXT , " +
            KEY_TITLE +" TEXT NOT NULL, "+
            KEY_PICKUP +" TEXT , "+
            KEY_RETURN + " TEXT, " +
            KEY_RESERVATION + " INTEGER PRIMARY KEY AUTOINCREMENT)";
}
