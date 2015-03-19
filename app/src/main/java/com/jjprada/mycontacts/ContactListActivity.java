package com.jjprada.mycontacts;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactListActivity extends ActionBarActivity {

    private ArrayList<Contact> mContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mContacts = new ArrayList<>();
       /* Contact contact1 = new Contact();
        contact1.setName("José Prada");
        contacts.add(contact1);*/

        for (int i = 0; i < 30; i++) {                  // Crear datos por defecto para ver como es la app
            ArrayList<String> phoneNumbers = new ArrayList<>();
            phoneNumbers.add("916765334");
            phoneNumbers.add("646724165");

            ArrayList<String> emails = new ArrayList<>();
            emails.add("jjprada@gmail.com");
            emails.add("dracko46@gmail.com");

            Contact contactDefault = new Contact();             // Crear contacto por defecto
            contactDefault.setName("José Prada " + i);          // Definir el nombre
            contactDefault.setPhoneNumbers(phoneNumbers);       // Definir los telefonos
            contactDefault.setEmails(emails);                   // Definir los emails
            mContacts.add(contactDefault);                      // Cargar contacto en la app
        }


        ListView listView = (ListView)findViewById(R.id.contact_list_view);
        listView.setAdapter(new ContactAdapter(mContacts));

        // LISTNER PARA EL SCROLL: OCULAR O MOSTRAR LA ACTION BAR
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int previousFirstItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > previousFirstItem){
                    getSupportActionBar().hide();
                } else if (firstVisibleItem < previousFirstItem) {
                    getSupportActionBar().show();
                }
                previousFirstItem = firstVisibleItem;
            }
        });

        // LISTNER PARA EL ITEM DEL LISTVIEW: CUANDO PINCHEMOS EN UN CONTACTO QUE NOS LLEVE A LA PAGINA DEL CONTACTO
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = mContacts.get(position);

                Intent i = new Intent(ContactListActivity.this, ContactViewActivity.class);
                i.putExtra(ContactViewActivity.EXTRA, contact);
                startActivity(i);
            }
        });

    }

    private class ContactAdapter extends ArrayAdapter<Contact>{
        ContactAdapter(ArrayList<Contact> contacts){
            super(ContactListActivity.this, R.layout.contact_list_row, R.id.contact_row_name, contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);

            Contact contact = getItem(position);        // Obtenemos el contacto

            TextView textViewName = (TextView)convertView.findViewById(R.id.contact_row_name);      // Buscamos el elemento al cual queremos modificarle el nombre
            textViewName.setText(contact.getName());            // Hacemos que el texto de la vista muestre el nombre del contacto

            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
