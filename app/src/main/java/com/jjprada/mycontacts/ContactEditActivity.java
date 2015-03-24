package com.jjprada.mycontacts;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactEditActivity extends ActionBarActivity {

    private static final String TAG = "ContactEditActivity";
    public static final String EXTRA = "CEA_Contact";   // CEA = ContactEditActivity

    private Contact mContact;
    private EditText mEditName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

        int position = getIntent().getIntExtra(EXTRA, 0);
        mContact = ContactList.getInstance().get(position);

        mEditName = (EditText) findViewById(R.id.contact_edit_name);
        mEditName.setText(mContact.getName());

        addToSection(R.id.phone_number_section, mContact.getPhoneNumbers());
        addToSection(R.id.email_section, mContact.getEmails());

        TextView addNewPhoneNumber = (TextView) findViewById(R.id.add_new_phone_number);
        addNewPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToSection(R.id.phone_number_section);
            }
        });

        TextView addNewEmail = (TextView) findViewById(R.id.add_new_email);
        addNewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToSection(R.id.email_section);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.contact_edit_toolbar);
        toolbar.setTitle(R.string.toolbar_name);
        toolbar.setNavigationIcon(R.drawable.ic_done);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContact.setName(mEditName.getText().toString());
                mContact.setPhoneNumbers(updateContact(R.id.phone_number_section));
                mContact.setEmails(updateContact(R.id.email_section));

                Toast.makeText(ContactEditActivity.this, R.string.toast, Toast.LENGTH_LONG)
                        .show();

                finish();
            }
        });
    }

    private ArrayList<String> updateContact(int sectionID) {
        ArrayList<String> newData = new ArrayList<String>();

        LinearLayout section = (LinearLayout) findViewById(sectionID);
        for (int i = 0; i < section.getChildCount(); i++) {
            EditText et = (EditText) section.getChildAt(i);
            newData.add(et.getText().toString());
        }
        return newData;
    }

    private void addToSection(int sectionID, ArrayList<String> values) {
        LinearLayout section = (LinearLayout) findViewById(sectionID);
        for (int i = 0; i < values.size(); i++) {
            EditText et = new EditText(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(lp);
            et.setText(values.get(i));
            section.addView(et);
        }
    }

    private void addToSection(int sectionID) {
        LinearLayout section = (LinearLayout) findViewById(sectionID);
        EditText et = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(lp);
        section.addView(et);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_edit, menu);
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
