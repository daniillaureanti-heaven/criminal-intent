package com.laureanti.daniil.android.criminalintent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private Boolean mSolved = false;

    private String mSuspect;


    public Crime(){
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public Boolean getSolved() {
        return mSolved;
    }

    public void setSolved(Boolean mSolved) {
        this.mSolved = mSolved;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect; }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

    public boolean isSolved(){
        return true;
    }

    public String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, yyyy");
        Date currentData = mDate;
        return formatter.format(currentData);
    }
}
