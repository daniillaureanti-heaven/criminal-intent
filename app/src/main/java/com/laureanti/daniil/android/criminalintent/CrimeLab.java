package com.laureanti.daniil.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.laureanti.daniil.android.criminalintent.database.CrimeBaseHelper;
import com.laureanti.daniil.android.criminalintent.database.CrimeCursorWrapper;
import com.laureanti.daniil.android.criminalintent.database.CrimeDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public void addCrime(Crime crime) {
        ContentValues values = getContentValues(crime);
//        String title = values.getAsString(crime.getTitle());
//        if (title.equals("")){
//
//        } else {
//            mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, values);
//
//        }
        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, values);
    }

    public void removeCrime(Crime crime) {
//        mCrimes.remove(crime);
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.delete(CrimeDbSchema.CrimeTable.NAME,
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.update(CrimeDbSchema.CrimeTable.NAME, values,
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null, // columns - с null выбираются все столбцы
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime) {
            ContentValues values = new ContentValues();
            values.put(CrimeDbSchema.CrimeTable.Cols.UUID,
                    crime.getId().toString());
            values.put(CrimeDbSchema.CrimeTable.Cols.TITLE, crime.getTitle());
            values.put(CrimeDbSchema.CrimeTable.Cols.DATE, crime.getDate().getTime());
            values.put(CrimeDbSchema.CrimeTable.Cols.SOLVED, crime.getSolved() ? 1 : 0);
            return values;
    }

}
