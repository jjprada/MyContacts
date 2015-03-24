package com.jjprada.mycontacts;

import java.util.ArrayList;

/**
 * Created by Dr4ckO on 24/03/2015.
 */
public class ContactList extends ArrayList<Contact>{
    private static ContactList sInstance = null;

    private ContactList(){};

    public static ContactList getInstance() {
        if(sInstance == null){
            sInstance = new ContactList();
        }
        return sInstance;
    }
}
