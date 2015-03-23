package com.jjprada.mycontacts;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ContactEditActivity extends ActionBarActivity {

    public static final String EXTRA = "CEA_Contact";   // CEA = ContactEditActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

        Contact contact = (Contact)getIntent().getSerializableExtra(EXTRA);

        EditText editName = (EditText)findViewById(R.id.contact_edit_name);
        editName.setText(contact.getName());

        addToSection(R.id.phone_number_section, contact.getPhoneNumbers());
        addToSection(R.id.email_section, contact.getEmails());
   }

    private void addToSection(int sectionID, ArrayList<String> values){
        LinearLayout section = (LinearLayout)findViewById(sectionID);
        for (int i = 0; i < values.size(); i++) {
            EditText et = new EditText(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(lp);
            et.setText(values.get(i));
            section.addView(et);
        }

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
