package com.jjprada.mycontacts;

import java.io.Serializable;

/**
 * Created by Dr4ckO on 11/03/2015.
 */
public class Contact implements Serializable {

    private String mName;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
