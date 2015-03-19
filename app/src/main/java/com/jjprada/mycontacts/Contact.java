package com.jjprada.mycontacts;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dr4ckO on 11/03/2015.
 */
public class Contact implements Serializable {

    private String mName;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> emails;

    public String getName() {
        return mName;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }
}
