package edu.good9016csumb.projectreview;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.R.layout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.good9016csumb.projectreview.Helperobjects.Book;
import edu.good9016csumb.projectreview.Helperobjects.DatabaseHelper;
import edu.good9016csumb.projectreview.Helperobjects.Transactions;
import edu.good9016csumb.projectreview.Helperobjects.User;

public class ManageSystem extends AppCompatActivity {

    private DatabaseHelper helper;
    private ListView taskListView;
    private ArrayList<Book> book;
    private ArrayAdapter<User> adapterUsers;
    private ArrayAdapter<Book> adapterBooks;
    private ArrayAdapter<Transactions> adapterTrans;// // TODO: 5/5/17 CHANGE TO LOG
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        book =null;
        helper = new DatabaseHelper(this);
       helper.getAllUsers();
      //  helper.getAllBooks();
        taskListView = (ListView) findViewById(R.id.task_title);
        udpateUI();

    }

    private void udpateUI() {
        ArrayList<User> taskListUser = helper.getAllUsers();
        ArrayList<Book> taskListBook = helper.getAllBooks();
        ArrayList<Transactions> taskListTrans = helper.getAllTrans();
       /* if(adapterUsers == null)
        {
            adapterUsers = new ArrayAdapter<>(this, R.layout.display,
                    R.id.test_vieww, taskListUser);
            taskListView.setAdapter(adapterUsers);
            Log.d("TESTER", taskListUser.toString());

        }
        else{
            adapterUsers.clear();
            adapterUsers.addAll(taskListUser);
            adapterUsers.notifyDataSetChanged();
        }

        if(adapterBooks == null)
        {
            adapterBooks = new ArrayAdapter<>(this, R.layout.display,
                    R.id.checkout_bookView, taskListBook);
            taskListView.setAdapter(adapterBooks);
            Log.d("BOOKZ", taskListBook.toString());
        }
        else{
            adapterBooks.clear();
            adapterBooks.addAll(taskListBook);
            adapterBooks.notifyDataSetChanged();
        }*/

        if(adapterTrans == null)
        {
            adapterTrans = new ArrayAdapter<Transactions>(this, R.layout.activity_place__hold,
                    R.id.start_it_up, taskListTrans);
            taskListView.setAdapter(adapterTrans);
            Log.d("TRANZZZ", taskListTrans.toString());
        }
        else{
            adapterTrans = new ArrayAdapter<Transactions>(this, R.layout.check_out_book) ;
            adapterTrans.addAll(taskListTrans);
            adapterTrans.notifyDataSetChanged();
        }


    }
    EditText author, title, num, price;

    public void enterInfo(final View view)
    {

        final DatabaseHelper db = new DatabaseHelper(this);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_a_book, null);
       dialog.setView(dialogView);

        dialog.setPositiveButton("Add", new Dialog.OnClickListener() {

            @Override
                    public void onClick(DialogInterface diag, int which) {

                         author = (EditText) dialogView.findViewById(R.id.book_author);
                         title = (EditText)dialogView.findViewById(R. id.book_title);
                         num = (EditText) dialogView.findViewById(R.id.book_id);
                         price = (EditText) dialogView.findViewById(R.id.book_price);
                        String a = author.getText().toString();
                        if(a.matches(""))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ManageSystem.this);
                            builder.setMessage("Author not inputed")
                                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            finish();                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }
                        String t = title.getText().toString();
                        if(t.matches(""))
                         {
                             AlertDialog.Builder builder = new AlertDialog.Builder(ManageSystem.this);
                             builder.setMessage("Title not inputed")
                                     .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                         public void onClick(DialogInterface dialog, int id) {
                                             finish();                                        }
                                     });
                             AlertDialog alert = builder.create();
                             alert.show();

                          }
                        String n = num.getText().toString();
                        if(n.matches(""))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ManageSystem.this);
                            builder.setMessage("Id not inputed")
                                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            finish();                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }

                        String p = price.getText().toString();
                        if(p.matches(""))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ManageSystem.this);
                            builder.setMessage("Price not inputed")
                                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            finish();                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                        book = helper.getAllBooks();
                        boolean same = false;

                        for(int i=0; i < book.size(); i++)
                        {
                            if(book.get(i).getAuthor().equals(a) && book.get(i).getTitle().equals(t) && book.get(i).getId().equals(n) && book.get(i).getPrice().equals(p)) {
                                same = true;
                            }

                        }

                        if(!a.matches("") && !t.matches("") && !n.matches("") && !p.matches("") && same == false)
                        {
                                    db.addBook(new Book(a, t, n, p));
                            db.addTrans(new Transactions("null", "user", "DATE", "null", "sad", "1")); //Toda
                            AlertDialog.Builder builder = new AlertDialog.Builder(ManageSystem.this);
                            builder.setMessage("Author: " + a+ "\nTitle: " + t +
                                    "\nISBN: " + n +
                                    "\nHourly Fee: $" + Double.parseDouble(p))
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                         Intent intent = new Intent(ManageSystem.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                                    udpateUI();
                        }
                        if(same == true)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ManageSystem.this);
                            builder.setMessage("That book alreadys exists")
                                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(ManageSystem.this, MainActivity.class);
                                            startActivity(intent); }                                   });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    }
                });

        dialog.show();




    }




}
