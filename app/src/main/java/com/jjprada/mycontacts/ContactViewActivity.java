package com.jjprada.mycontacts;

import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactViewActivity extends ActionBarActivity {

    private static final String TAG = "ContactViewActivity";
    public static final String EXTRA = "CVA_Contact";   // CVA = ContactViewActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        int width = point.x;
        int height = point.y;

        RelativeLayout headerSection = (RelativeLayout)findViewById(R.id.contact_view_header);
        headerSection.setLayoutParams(new RelativeLayout.LayoutParams(width, (int) (width * (9.0 / 16.0))));

        Contact contact = (Contact)getIntent().getSerializableExtra(EXTRA);
        TextView contactViewName = (TextView)findViewById(R.id.contact_view_name);
        contactViewName.setText(contact.getName());

        Toolbar toolbar = (Toolbar)findViewById(R.id.contact_view_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.contact_view_edit){
                    Log.d(TAG, "Edit message");
                    return true;
                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu_contact_view);

        ListView listView = (ListView)findViewById(R.id.contact_view_fields);


    }

    private class FieldsAdapter extends BaseAdapter{
        ArrayList<String> emails;
        ArrayList<String> phoneNumber;

        FieldsAdapter(ArrayList<String> emails, ArrayList<String> phoneNumber){
            this.emails = emails;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public int getCount() {
            return emails.size() + phoneNumber.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_view, menu);
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
