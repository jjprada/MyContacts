package com.jjprada.mycontacts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactViewActivity extends ActionBarActivity {

    private static final String TAG = "ContactViewActivity";
    public static final String EXTRA = "CVA_Contact";   // CVA = ContactViewActivity

    private int mColor;     // Almacena el color extraido con la Palette
    private Contact mContact;
    private int mPosition;
    private TextView mContactViewName;
    private ListView mListView;
    private FieldsAdapter mFieldsAdapter;

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
        headerSection.setLayoutParams(new LinearLayout.LayoutParams(width, (int) (width * (9.0 / 16.0))));

        mPosition = getIntent().getIntExtra(EXTRA, 0);
        mContact = ContactList.getInstance().get(mPosition);

        mContactViewName = (TextView)findViewById(R.id.contact_view_name);

        Toolbar toolbar = (Toolbar)findViewById(R.id.contact_view_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.contact_view_edit){
                    Intent i = new Intent(ContactViewActivity.this, ContactEditActivity.class);
                    i.putExtra(ContactEditActivity.EXTRA, mPosition);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu_contact_view);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sunset);
        Palette palette = Palette.generate(bitmap);
        mColor = palette.getDarkVibrantSwatch().getRgb();

        updateUI();
    }

    private void updateUI(){
        mContactViewName.setText(mContact.getName());

        //mFieldsAdapter.notifyDataSetChanged();    // Normalmente usariamos esto y las tres líneas de después irían fuera, pero así no funciona completamente bien. Nos lo explicará más adelante
        mListView = (ListView)findViewById(R.id.contact_view_fields);
        mFieldsAdapter = new FieldsAdapter(mContact.getPhoneNumbers(), mContact.getEmails());
        mListView.setAdapter(mFieldsAdapter);
    }

    private class FieldsAdapter extends BaseAdapter{
        ArrayList<String> phoneNumbers;
        ArrayList<String> emails;

        FieldsAdapter(ArrayList<String> phoneNumber, ArrayList<String> emails){
            this.phoneNumbers = phoneNumber;
            this.emails = emails;
        }

        @Override
        public int getCount() {
            return phoneNumbers.size() + emails.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = ContactViewActivity.this.getLayoutInflater().inflate(R.layout.contact_view_field_row, parent, false);
            }

            String value = (String)getItem(position);

            TextView contactValue = (TextView)convertView.findViewById(R.id.contact_view_row_value);
            contactValue.setText(value);

            ImageView contactIcon = (ImageView)convertView.findViewById(R.id.contact_view_row_icon);
            if (isFirst(position)){
                if (isEmail(position)){
                    contactIcon.setImageResource(R.drawable.ic_email);
                } else {
                    contactIcon.setImageResource(R.drawable.ic_call);
                }
            }

            contactIcon.setColorFilter(mColor);

            return convertView;
        }

        private boolean isFirst (int position){
            if (position == 0 || position == phoneNumbers.size()){
                return true;
            } else {
                return false;
            }
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (isEmail(position)){
                return emails.get(position - phoneNumbers.size());
            } else {
                return phoneNumbers.get(position);
            }
        }

        private boolean isEmail(int position){
            if (position > phoneNumbers.size()-1){
                return true;    // Es email
            } else {
                return false;   // Es phoneNumbers
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateUI();
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
