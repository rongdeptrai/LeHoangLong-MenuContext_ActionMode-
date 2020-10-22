package com.example.activiti_customlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    ArrayList<LS> lsArrayList;
    ListView listView;

    final Context context = this;
    String m_Text = "";
    CustomAdapter customAdapter;
    List<LS> arrayList= new ArrayList<LS>();

    int idd=-1;
    int iddd=1;
    protected Object mActionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lsArrayList= new ArrayList<LS>();
        lsArrayList.add(new LS("Le Hoang Long","1811505310322",R.drawable.avata));
        lsArrayList.add(new LS("Le Hoang Long1","1811505310322",R.drawable.avata));
        lsArrayList.add(new LS("Le Hoang Long2","1811505310322",R.drawable.avata));
        lsArrayList.add(new LS("Le Hoang Long4","1811505310322",R.drawable.avata));
        lsArrayList.add(new LS("Le Hoang Long3","1811505310322",R.drawable.avata));

        customAdapter=new CustomAdapter(this,R.layout.row,lsArrayList);

        listView=findViewById(R.id.listView);
        listView.setAdapter(customAdapter);

        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                idd=position;
                return false;
            }

        });

    }
    AbsListView.MultiChoiceModeListener modeListener= new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            if(arrayList.contains(lsArrayList.get(position))){
               arrayList.remove(lsArrayList.get(position));
            }else {
                arrayList.add(lsArrayList.get(position));
            }
            mode.setTitle(arrayList.size()+" item selected");

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater= mode.getMenuInflater();
            inflater.inflate(R.menu.menu,menu);

            return true;

        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.dele_all:
                    customAdapter.removeItem(arrayList);
                    customAdapter.notifyDataSetChanged();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            arrayList.clear();
            mode=null;
            registerForContextMenu(listView);
        }
    };
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Select The Action");
    }
    @Override
    public boolean onContextItemSelected(final MenuItem item){
        if(item.getItemId()==R.id.them) {
            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.activity_edit, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText tit = (EditText) promptsView
                    .findViewById(R.id.tt);
            final EditText des = (EditText) promptsView
                    .findViewById(R.id.ds);
            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // get user input and set it to result
                                    // edit text
                                    LS ls= new LS(tit.getText().toString(),des.getText().toString(),R.drawable.ddt);
                                    lsArrayList.add(ls);
                                    customAdapter.notifyDataSetChanged();
                                    Toast.makeText(SecondActivity.this,"Add Item success",Toast.LENGTH_LONG).show();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
        else if(item.getItemId()==R.id.sua){
// get prompts.xml view
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.activity_edit, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText tit = (EditText) promptsView
                    .findViewById(R.id.tt);
            final EditText des = (EditText) promptsView
                    .findViewById(R.id.ds);
            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // get user input and set it to result
                                    // edit text
                                    LS sua= new LS(tit.getText().toString(),des.getText().toString(),R.drawable.ddt);
                                    lsArrayList.set(idd,sua);
                                    idd=0;
                                    customAdapter.notifyDataSetChanged();
                                    Toast.makeText(SecondActivity.this,"Changed success",Toast.LENGTH_LONG).show();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
        else if(item.getItemId()==R.id.xoa){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);
            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Delete",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // get user input and set it to result
                                    // edit text
                                    lsArrayList.remove(idd);
                                    idd=-1;
                                    customAdapter.notifyDataSetChanged();
                                    Toast.makeText(SecondActivity.this,"Delete success",Toast.LENGTH_LONG).show();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
        else if(item.getItemId()==R.id.more){
            listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setMultiChoiceModeListener(modeListener);
        }
        else{
            return false;
        }
        return true;
    }

}