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
import edu.good9016csumb.projectreview.Helperobjects.User;

public class ManageSystem extends AppCompatActivity {

    private DatabaseHelper helper;
    private ListView taskListView;
    private ArrayAdapter<User> adapterUsers;
    private ArrayAdapter<Book> adapterBooks;// // TODO: 5/5/17 CHANGE TO LOG
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        helper = new DatabaseHelper(this);
       helper.getAllUsers();
      //  helper.getAllBooks();
        taskListView = (ListView) findViewById(R.id.task_title);
        udpateUI();

    }

    private void udpateUI() {
        ArrayList<User> taskListUser = helper.getAllUsers();
        ArrayList<Book> taskListBook = helper.getAllBooks();
        if(adapterUsers == null)
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
                    R.id.books_log_test_out, taskListBook);
            taskListView.setAdapter(adapterBooks);
            Log.d("BOOKZ", taskListBook.toString());
        }
        else{
            adapterBooks.clear();
            adapterBooks.addAll(taskListBook);
            adapterBooks.notifyDataSetChanged();
        }


    }
    EditText author, title, num, price;

    public void enterInfo(final View view)
    {

        final DatabaseHelper db = new DatabaseHelper(this);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        final LayoutInflater inflater = this.getLayoutInflater();

       dialog.setView(R.layout.add_a_book);

        dialog.setPositiveButton("Add", new Dialog.OnClickListener() {

            @Override
                    public void onClick(DialogInterface diag, int which) {

                         author = (EditText) findViewById(R.id.book_author);
                         title = (EditText)findViewById(R. id.book_title);
                         num = (EditText) view.findViewById(R.id.book_id);
                         price = (EditText) view.findViewById(R.id.book_price);
                        String a = author.getText().toString();
                        db.addBook(new Book(a, "00", "X3", "10"));
                        udpateUI();
                    }
                });

        dialog.show();




    }




}
