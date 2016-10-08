package com.oxionaz.kursovaya_rabota_sqlite.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.oxionaz.kursovaya_rabota_sqlite.model.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static SQLiteDatabase db;
    public static Cursor userCursor;

    //----------------------------Military district query------------------------------------

    public static List<String> getDataMD(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_MILITARY_DISTRICT, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowMD(int position){
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2)};
    }

    public static void addDataMD(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_MILITARY_DISTRICT, null, cv);
        } catch (SQLiteException ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataMD(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_MILITARY_DISTRICT, cv, DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataMD(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_MILITARY_DISTRICT, DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + "=?", new String[]{id});
    }

    public static List<String> getListMD(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "+ DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID +" from " + DatabaseHelper.TABLE_MILITARY_DISTRICT, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    //----------------------------Dislocation place query------------------------------------

    public static List<String> getDataDP(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_DISLOCATION_PLACE, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowDP(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_DISLOCATION_PLACE, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2)};
    }

    public static void addDataDP(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_DISLOCATION_PLACE, null, cv);
        } catch (SQLiteException ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataDP(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_DISLOCATION_PLACE, cv, DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataDP(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_DISLOCATION_PLACE, DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID + "=?", new String[]{id});
    }

    public static List<String> getListDP(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID + ","
                + DatabaseHelper.COLUMN_NAME
                + " from "
                + DatabaseHelper.TABLE_DISLOCATION_PLACE
                + " order by "
                + DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0) + " - " + userCursor.getString(1)+ " - " + userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowListDP(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID
                + " from "
                + DatabaseHelper.TABLE_DISLOCATION_PLACE
                + " order by "
                + DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1)};
    }

    //----------------------------Rank query------------------------------------

    public static List<String> getDataRank(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_RANK, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowRank(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_RANK, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2)};
    }

    public static void addDataRank(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_RANK, null, cv);
        } catch (SQLiteException ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataRank(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_RANK, cv, DatabaseHelper.COLUMN_RANK_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataRank(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_RANK, DatabaseHelper.COLUMN_RANK_ID + "=?", new String[]{id});
    }

    public static List<String> getListRank(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "+ DatabaseHelper.COLUMN_RANK_ID +" from " + DatabaseHelper.TABLE_RANK, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    //----------------------------Military query------------------------------------

    public static List<String> getDataMilitary(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_MILITARY, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowMilitary(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_MILITARY, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2),
                userCursor.getString(3), userCursor.getString(4)};
    }

    public static void addDataMilitary(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_MILITARY, null, cv);
        } catch (SQLiteException ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataMilitary(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_MILITARY, cv, DatabaseHelper.COLUMN_MILITARY_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataMilitary(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_MILITARY, DatabaseHelper.COLUMN_MILITARY_ID + "=?", new String[]{id});
    }

    public static String[] getFreeMilitaryRowOF(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT military._id_mlt, military.fio " +
                "FROM rank INNER JOIN military " +
                "ON rank._id_rnk = military._id_rnk " +
                "WHERE rank.type = 'Офицерский состав' " +
                "AND NOT EXISTS(SELECT army._id_mlt FROM army WHERE army._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT community._id_mlt FROM community WHERE community._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT military_unit._id_mlt FROM military_unit WHERE military_unit._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT company._id_mlt FROM company WHERE company._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT platoon._id_mlt FROM platoon WHERE platoon._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT department._id_mlt FROM department WHERE department._id_mlt = military._id_mlt) " +
                "ORDER BY military._id_mlt", null); //получаем данные
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1)};
    }

    public static List<String> getListFreeMilitaryOF(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT military._id_md, military.fio, military._id_rnk " +
                "FROM rank INNER JOIN military " +
                "ON rank._id_rnk = military._id_rnk " +
                "WHERE rank.type = 'Офицерский состав' " +
                "AND NOT EXISTS(SELECT army._id_mlt FROM army WHERE army._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT community._id_mlt FROM community WHERE community._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT military_unit._id_mlt FROM military_unit WHERE military_unit._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT company._id_mlt FROM company WHERE company._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT platoon._id_mlt FROM platoon WHERE platoon._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT department._id_mlt FROM department WHERE department._id_mlt = military._id_mlt) " +
                "ORDER BY military._id_mlt", null); //получаем данные
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0)+" - "+userCursor.getString(1)+" - "+userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getFreeMilitaryRow(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT military._id_mlt, military.fio " +
                "FROM military " +
                "WHERE NOT EXISTS(SELECT army._id_mlt FROM army WHERE army._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT community._id_mlt FROM community WHERE community._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT military_unit._id_mlt FROM military_unit WHERE military_unit._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT company._id_mlt FROM company WHERE company._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT platoon._id_mlt FROM platoon WHERE platoon._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT department._id_mlt FROM department WHERE department._id_mlt = military._id_mlt) " +
                "ORDER BY military._id_mlt", null); //получаем данные
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1)};
    }

    public static List<String> getListFreeMilitary(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT military._id_md, military.fio, military._id_rnk " +
                "FROM military " +
                "WHERE NOT EXISTS(SELECT army._id_mlt FROM army WHERE army._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT community._id_mlt FROM community WHERE community._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT military_unit._id_mlt FROM military_unit WHERE military_unit._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT company._id_mlt FROM company WHERE company._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT platoon._id_mlt FROM platoon WHERE platoon._id_mlt = military._id_mlt) " +
                "AND NOT EXISTS(SELECT department._id_mlt FROM department WHERE department._id_mlt = military._id_mlt) " +
                "ORDER BY military._id_mlt", null); //получаем данные
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0)+" - "+userCursor.getString(1)+" - "+userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getListMilitaryRow(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT military._id_md, military._id_mlt, military.fio " +
                "FROM military ORDER BY military._id_mlt", null); //получаем данные
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2)};
    }

    public static List<String> getListMilitary(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT military._id_md, military.fio, military._id_rnk " +
                "FROM military ORDER BY military._id_mlt", null); //получаем данные
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0)+" - "+userCursor.getString(1)+" - "+userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    //----------------------------Army query------------------------------------

    public static List<String> getDataArmy(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_ARMY, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4)
                        +"\n"+userCursor.getColumnName(5).toUpperCase()+":  "+userCursor.getString(5));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowArmy(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_ARMY, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(3), userCursor.getString(4)};
    }

    public static void addDataArmy(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_ARMY, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataArmy(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_ARMY, cv, DatabaseHelper.COLUMN_ARMY_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataArmy(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_ARMY, DatabaseHelper.COLUMN_ARMY_ID + "=?", new String[]{id});
    }

    public static List<String> getListArmy(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_ARMY_ID + ","
                + DatabaseHelper.COLUMN_NAME
                + " from "
                + DatabaseHelper.TABLE_ARMY
                + " order by "
                + DatabaseHelper.COLUMN_ARMY_ID, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0) + " - " + userCursor.getString(1)+ " - " + userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowListArmy(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_ARMY_ID
                + " from "
                + DatabaseHelper.TABLE_ARMY
                + " order by "
                + DatabaseHelper.COLUMN_ARMY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1)};
    }

    //----------------------------Community query------------------------------------

    public static List<String> getDataComm(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_COMMUNITY, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4)
                        +"\n"+userCursor.getColumnName(5).toUpperCase()+":  "+userCursor.getString(5)
                        +"\n"+userCursor.getColumnName(6).toUpperCase()+":  "+userCursor.getString(6)
                        +"\n"+userCursor.getColumnName(7).toUpperCase()+":  "+userCursor.getString(7));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowComm(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT "
                + DatabaseHelper.TABLE_COMMUNITY + "." + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.TABLE_COMMUNITY + "." + DatabaseHelper.COLUMN_ARMY_ID + ","
                + DatabaseHelper.TABLE_ARMY + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_COMMUNITY + "." + DatabaseHelper.COLUMN_TYPE + ","
                + DatabaseHelper.TABLE_COMMUNITY + "." + DatabaseHelper.COLUMN_COMMUNITY_ID + ","
                + DatabaseHelper.TABLE_COMMUNITY + "." + DatabaseHelper.COLUMN_NAME
                + " FROM "
                + DatabaseHelper.TABLE_COMMUNITY
                + " LEFT JOIN "
                + DatabaseHelper.TABLE_ARMY
                + " ON "
                + DatabaseHelper.TABLE_ARMY + "." + DatabaseHelper.COLUMN_ARMY_ID
                + "="
                + DatabaseHelper.TABLE_COMMUNITY + "." + DatabaseHelper.COLUMN_ARMY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2),
                userCursor.getString(3), userCursor.getString(4), userCursor.getString(5)};
    }

    public static void addDataComm(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_COMMUNITY, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataComm(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_COMMUNITY, cv, DatabaseHelper.COLUMN_COMMUNITY_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataComm(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_COMMUNITY, DatabaseHelper.COLUMN_COMMUNITY_ID + "=?", new String[]{id});
    }

    public static List<String> getListComm(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_COMMUNITY_ID + ","
                + DatabaseHelper.COLUMN_NAME
                + " from "
                + DatabaseHelper.TABLE_COMMUNITY
                + " order by "
                + DatabaseHelper.COLUMN_COMMUNITY_ID, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0) + " - " + userCursor.getString(1)+ " - " + userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String getRowListComm(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_COMMUNITY_ID
                + " from "
                + DatabaseHelper.TABLE_COMMUNITY
                + " order by "
                + DatabaseHelper.COLUMN_COMMUNITY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return userCursor.getString(0);
    }

    //----------------------------Military unit query------------------------------------

    public static List<String> getDataMU(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_MILITARY_UNIT, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4)
                        +"\n"+userCursor.getColumnName(5).toUpperCase()+":  "+userCursor.getString(5)
                        +"\n"+userCursor.getColumnName(6).toUpperCase()+":  "+userCursor.getString(6)
                        +"\n"+userCursor.getColumnName(7).toUpperCase()+":  "+userCursor.getString(7));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowMU(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT "
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID + ","
                + DatabaseHelper.TABLE_DISLOCATION_PLACE + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_COMMUNITY_ID + ","
                + DatabaseHelper.TABLE_COMMUNITY + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_MILITARY_UNIT_ID + ","
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_NAME
                + " FROM "
                + DatabaseHelper.TABLE_DISLOCATION_PLACE
                + " INNER JOIN "
                + DatabaseHelper.TABLE_MILITARY_UNIT
                + " ON "
                + DatabaseHelper.TABLE_DISLOCATION_PLACE + "." + DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID
                + "="
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID
                + " LEFT JOIN "
                + DatabaseHelper.TABLE_COMMUNITY
                + " ON "
                + DatabaseHelper.TABLE_COMMUNITY + "." + DatabaseHelper.COLUMN_COMMUNITY_ID
                + "="
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_COMMUNITY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2),
                userCursor.getString(3), userCursor.getString(4), userCursor.getString(5), userCursor.getString(6)};
    }

    public static void addDataMU(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_MILITARY_UNIT, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataMU(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_MILITARY_UNIT, cv, DatabaseHelper.COLUMN_MILITARY_UNIT_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataMU(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_MILITARY_UNIT, DatabaseHelper.COLUMN_MILITARY_UNIT_ID + "=?", new String[]{id});
    }

    public static List<String> getListMU(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_MILITARY_UNIT_ID + ","
                + DatabaseHelper.COLUMN_NAME
                + " from "
                + DatabaseHelper.TABLE_MILITARY_UNIT
                + " order by "
                + DatabaseHelper.COLUMN_MILITARY_UNIT_ID, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0) + " - " + userCursor.getString(1)+ " - " + userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowListMU(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID + ","
                + DatabaseHelper.COLUMN_MILITARY_UNIT_ID
                + " from "
                + DatabaseHelper.TABLE_MILITARY_UNIT
                + " order by "
                + DatabaseHelper.COLUMN_MILITARY_UNIT_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2)};
    }

    //----------------------------Company query------------------------------------

    public static List<String> getDataCmp(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_COMPANY, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4)
                        +"\n"+userCursor.getColumnName(5).toUpperCase()+":  "+userCursor.getString(5)
                        +"\n"+userCursor.getColumnName(6).toUpperCase()+":  "+userCursor.getString(6)
                        +"\n"+userCursor.getColumnName(7).toUpperCase()+":  "+userCursor.getString(7));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowCmp(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT "
                + DatabaseHelper.TABLE_COMPANY + "." + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.TABLE_COMPANY + "." + DatabaseHelper.COLUMN_MILITARY_UNIT_ID + ","
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_COMPANY + "." + DatabaseHelper.COLUMN_COMPANY_ID + ","
                + DatabaseHelper.TABLE_COMPANY + "." + DatabaseHelper.COLUMN_NAME
                + " FROM "
                + DatabaseHelper.TABLE_MILITARY_UNIT
                + " INNER JOIN "
                + DatabaseHelper.TABLE_COMPANY
                + " ON "
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_MILITARY_UNIT_ID
                + "="
                + DatabaseHelper.TABLE_COMPANY + "." + DatabaseHelper.COLUMN_MILITARY_UNIT_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2),
                userCursor.getString(3), userCursor.getString(4)};
    }

    public static void addDataCmp(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_COMPANY, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataCmp(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_COMPANY, cv, DatabaseHelper.COLUMN_COMPANY_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataCmp(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_COMPANY, DatabaseHelper.COLUMN_COMPANY_ID + "=?", new String[]{id});
    }

    public static List<String> getListCmp(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_COMPANY_ID + ","
                + DatabaseHelper.COLUMN_NAME
                + " from "
                + DatabaseHelper.TABLE_COMPANY
                + " order by "
                + DatabaseHelper.COLUMN_COMPANY_ID, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0) + " - " + userCursor.getString(1)+ " - " + userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowListCmp(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID + ","
                + DatabaseHelper.COLUMN_MILITARY_UNIT_ID + ","
                + DatabaseHelper.COLUMN_COMPANY_ID
                + " from "
                + DatabaseHelper.TABLE_COMPANY
                + " order by "
                + DatabaseHelper.COLUMN_COMPANY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2), userCursor.getString(3)};
    }

    //----------------------------Platoon query------------------------------------

    public static List<String> getDataPln(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_PLATOON, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4)
                        +"\n"+userCursor.getColumnName(5).toUpperCase()+":  "+userCursor.getString(5)
                        +"\n"+userCursor.getColumnName(6).toUpperCase()+":  "+userCursor.getString(6)
                        +"\n"+userCursor.getColumnName(7).toUpperCase()+":  "+userCursor.getString(7)
                        +"\n"+userCursor.getColumnName(8).toUpperCase()+":  "+userCursor.getString(8));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowPln(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT "
                + DatabaseHelper.TABLE_PLATOON + "." + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.TABLE_PLATOON + "." + DatabaseHelper.COLUMN_COMPANY_ID + ","
                + DatabaseHelper.TABLE_COMPANY + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_PLATOON + "." + DatabaseHelper.COLUMN_PLATOON_ID + ","
                + DatabaseHelper.TABLE_PLATOON + "." + DatabaseHelper.COLUMN_NAME
                + " FROM "
                + DatabaseHelper.TABLE_COMPANY
                + " INNER JOIN "
                + DatabaseHelper.TABLE_PLATOON
                + " ON "
                + DatabaseHelper.TABLE_COMPANY + "." + DatabaseHelper.COLUMN_COMPANY_ID
                + "="
                + DatabaseHelper.TABLE_PLATOON + "." + DatabaseHelper.COLUMN_COMPANY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2),
                userCursor.getString(3), userCursor.getString(4)};
    }

    public static void addDataPln(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_PLATOON, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataPln(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_PLATOON, cv, DatabaseHelper.COLUMN_PLATOON_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataPln(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_PLATOON, DatabaseHelper.COLUMN_PLATOON_ID + "=?", new String[]{id});
    }

    public static List<String> getListPln(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_PLATOON_ID + ","
                + DatabaseHelper.COLUMN_NAME
                + " from "
                + DatabaseHelper.TABLE_PLATOON
                + " order by "
                + DatabaseHelper.COLUMN_PLATOON_ID, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0) + " - " + userCursor.getString(1)+ " - " + userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowListPln(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID + ","
                + DatabaseHelper.COLUMN_MILITARY_UNIT_ID + ","
                + DatabaseHelper.COLUMN_COMPANY_ID + ","
                + DatabaseHelper.COLUMN_PLATOON_ID
                + " from "
                + DatabaseHelper.TABLE_PLATOON
                + " order by "
                + DatabaseHelper.COLUMN_PLATOON_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2)
                , userCursor.getString(3), userCursor.getString(4)};
    }

    //----------------------------Department query------------------------------------

    public static List<String> getDataDpm(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_DEPARTMENT, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4)
                        +"\n"+userCursor.getColumnName(5).toUpperCase()+":  "+userCursor.getString(5)
                        +"\n"+userCursor.getColumnName(6).toUpperCase()+":  "+userCursor.getString(6)
                        +"\n"+userCursor.getColumnName(7).toUpperCase()+":  "+userCursor.getString(7)
                        +"\n"+userCursor.getColumnName(8).toUpperCase()+":  "+userCursor.getString(8)
                        +"\n"+userCursor.getColumnName(9).toUpperCase()+":  "+userCursor.getString(9));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowDpm(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT "
                + DatabaseHelper.TABLE_DEPARTMENT + "." + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.TABLE_DEPARTMENT + "." + DatabaseHelper.COLUMN_PLATOON_ID + ","
                + DatabaseHelper.TABLE_PLATOON + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_DEPARTMENT + "." + DatabaseHelper.COLUMN_DEPARTMENT_ID + ","
                + DatabaseHelper.TABLE_DEPARTMENT + "." + DatabaseHelper.COLUMN_NAME
                + " FROM "
                + DatabaseHelper.TABLE_PLATOON
                + " INNER JOIN "
                + DatabaseHelper.TABLE_DEPARTMENT
                + " ON "
                + DatabaseHelper.TABLE_PLATOON + "." + DatabaseHelper.COLUMN_PLATOON_ID
                + "="
                + DatabaseHelper.TABLE_DEPARTMENT + "." + DatabaseHelper.COLUMN_PLATOON_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2),
                userCursor.getString(3), userCursor.getString(4)};
    }

    public static void addDataDpm(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_DEPARTMENT, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataDpm(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_DEPARTMENT, cv, DatabaseHelper.COLUMN_DEPARTMENT_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataDpm(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_DEPARTMENT, DatabaseHelper.COLUMN_DEPARTMENT_ID + "=?", new String[]{id});
    }

    //----------------------------Speciality query------------------------------------

    public static List<String> getDataSpc(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_SPECIALITY, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowSpc(int position){
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2)};
    }

    public static void addDataSpc(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_SPECIALITY, null, cv);
        } catch (SQLiteException ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataSpc(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_SPECIALITY, cv, DatabaseHelper.COLUMN_SPECIALITY_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataSpc(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_SPECIALITY, DatabaseHelper.COLUMN_SPECIALITY_ID + "=?", new String[]{id});
    }

    public static List<String> getListSpc(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "+ DatabaseHelper.COLUMN_SPECIALITY_ID +" from " + DatabaseHelper.TABLE_SPECIALITY, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    //----------------------------Equipment category query------------------------------------

    public static List<String> getDataEquC(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_EQUIPMENT_CATEGORY, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowEquC(int position){
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1)};
    }

    public static void addDataEquC(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_EQUIPMENT_CATEGORY, null, cv);
        } catch (SQLiteException ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataEquC(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_EQUIPMENT_CATEGORY, cv, DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataEquC(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_EQUIPMENT_CATEGORY, DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID + "=?", new String[]{id});
    }

    public static List<String> getListEquC(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from "
                + DatabaseHelper.TABLE_EQUIPMENT_CATEGORY
                + " order by "
                + DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0) + " - " + userCursor.getString(1));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String getRowListEquC(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID
                + " from "
                + DatabaseHelper.TABLE_EQUIPMENT_CATEGORY
                + " order by "
                + DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return userCursor.getString(0);
    }

    //----------------------------Weaponry category query------------------------------------

    public static List<String> getDataWpnC(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_WEAPONRY_CATEGORY, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowWpnC(int position){
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1)};
    }

    public static void addDataWpnC(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_WEAPONRY_CATEGORY, null, cv);
        } catch (SQLiteException ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataWpnC(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_WEAPONRY_CATEGORY, cv, DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataWpnC(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_WEAPONRY_CATEGORY, DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID + "=?", new String[]{id});
    }

    public static List<String> getListWpnC(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from "
                + DatabaseHelper.TABLE_WEAPONRY_CATEGORY
                + " order by "
                + DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0) + " - " + userCursor.getString(1));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String getRowListWpnC(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID
                + " from "
                + DatabaseHelper.TABLE_WEAPONRY_CATEGORY
                + " order by "
                + DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return userCursor.getString(0);
    }

    //----------------------------Equipment query------------------------------------

    public static List<String> getDataEqu(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_EQUIPMENT, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowEqu(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT "
                + DatabaseHelper.TABLE_EQUIPMENT + "." + DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID + ","
                + DatabaseHelper.TABLE_EQUIPMENT_CATEGORY + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_EQUIPMENT + "." + DatabaseHelper.COLUMN_TYPE + ","
                + DatabaseHelper.TABLE_EQUIPMENT + "." + DatabaseHelper.COLUMN_EQUIPMENT_ID + ","
                + DatabaseHelper.TABLE_EQUIPMENT + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_EQUIPMENT + "." + DatabaseHelper.COLUMN_ATT
                + " FROM "
                + DatabaseHelper.TABLE_EQUIPMENT
                + " LEFT JOIN "
                + DatabaseHelper.TABLE_EQUIPMENT_CATEGORY
                + " ON "
                + DatabaseHelper.TABLE_EQUIPMENT_CATEGORY + "." + DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID
                + "="
                + DatabaseHelper.TABLE_EQUIPMENT + "." + DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2),
                userCursor.getString(3), userCursor.getString(4), userCursor.getString(5)};
    }

    public static void addDataEqu(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_EQUIPMENT, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataEqu(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_EQUIPMENT, cv, DatabaseHelper.COLUMN_EQUIPMENT_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataEqu(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_EQUIPMENT, DatabaseHelper.COLUMN_EQUIPMENT_ID + "=?", new String[]{id});
    }

    public static List<String> getListEqu(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_EQUIPMENT_ID + ","
                + DatabaseHelper.COLUMN_NAME
                + " from "
                + DatabaseHelper.TABLE_EQUIPMENT
                + " order by "
                + DatabaseHelper.COLUMN_EQUIPMENT_ID, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0) + " - " + userCursor.getString(1));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String getRowListEqu(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_EQUIPMENT_ID
                + " from "
                + DatabaseHelper.TABLE_EQUIPMENT
                + " order by "
                + DatabaseHelper.COLUMN_EQUIPMENT_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return userCursor.getString(0);
    }

    //----------------------------Weaponry query------------------------------------

    public static List<String> getDataWpn(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_WEAPONRY, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowWpn(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT "
                + DatabaseHelper.TABLE_WEAPONRY + "." + DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID + ","
                + DatabaseHelper.TABLE_WEAPONRY_CATEGORY + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_WEAPONRY + "." + DatabaseHelper.COLUMN_WEAPONRY_ID + ","
                + DatabaseHelper.TABLE_WEAPONRY + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_WEAPONRY + "." + DatabaseHelper.COLUMN_ATT
                + " FROM "
                + DatabaseHelper.TABLE_WEAPONRY
                + " LEFT JOIN "
                + DatabaseHelper.TABLE_WEAPONRY_CATEGORY
                + " ON "
                + DatabaseHelper.TABLE_WEAPONRY_CATEGORY + "." + DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID
                + "="
                + DatabaseHelper.TABLE_WEAPONRY + "." + DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2),
                userCursor.getString(3), userCursor.getString(4)};
    }

    public static void addDataWpn(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_WEAPONRY, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataWpn(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_WEAPONRY, cv, DatabaseHelper.COLUMN_WEAPONRY_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataWpn(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_WEAPONRY, DatabaseHelper.COLUMN_WEAPONRY_ID + "=?", new String[]{id});
    }

    public static List<String> getListWpn(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_WEAPONRY_ID + ","
                + DatabaseHelper.COLUMN_NAME
                + " from "
                + DatabaseHelper.TABLE_WEAPONRY
                + " order by "
                + DatabaseHelper.COLUMN_WEAPONRY_ID, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0) + " - " + userCursor.getString(1));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String getRowListWpn(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "
                + DatabaseHelper.COLUMN_WEAPONRY_ID
                + " from "
                + DatabaseHelper.TABLE_WEAPONRY
                + " order by "
                + DatabaseHelper.COLUMN_WEAPONRY_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return userCursor.getString(0);
    }

    //----------------------------Building query------------------------------------

    public static List<String> getDataBlg(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_BUILDING, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4)
                        +"\n"+userCursor.getColumnName(5).toUpperCase()+":  "+userCursor.getString(5));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowBlg(Context context, int position){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT "
                + DatabaseHelper.TABLE_BUILDING + "." + DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID + ","
                + DatabaseHelper.TABLE_BUILDING + "." + DatabaseHelper.COLUMN_MILITARY_UNIT_ID + ","
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_BUILDING + "." + DatabaseHelper.COLUMN_BUILDING_ID + ","
                + DatabaseHelper.TABLE_BUILDING + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_BUILDING + "." + DatabaseHelper.COLUMN_ATT
                + " FROM "
                + DatabaseHelper.TABLE_MILITARY_UNIT
                + " INNER JOIN "
                + DatabaseHelper.TABLE_BUILDING
                + " ON "
                + DatabaseHelper.TABLE_MILITARY_UNIT + "." + DatabaseHelper.COLUMN_MILITARY_UNIT_ID
                + "="
                + DatabaseHelper.TABLE_BUILDING + "." + DatabaseHelper.COLUMN_MILITARY_UNIT_ID, null); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2),
                userCursor.getString(3), userCursor.getString(4), userCursor.getString(5)};
    }

    public static void addDataBlg(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_BUILDING, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataBlg(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_BUILDING, cv, DatabaseHelper.COLUMN_BUILDING_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataBlg(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_BUILDING, DatabaseHelper.COLUMN_BUILDING_ID + "=?", new String[]{id});
    }

    public static List<String> getListBlg(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select "+ DatabaseHelper.COLUMN_BUILDING_ID +" from " + DatabaseHelper.TABLE_BUILDING, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getString(0));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    //----------------------------Subdivision dislocation query------------------------------------

    public static List<String> getDataSubDis(Context context){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_SUBDIVISION_DISLOCATION, null); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowSubDis(int position){
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1)};
    }

    public static void addDataSubDis(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_SUBDIVISION_DISLOCATION, null, cv);
        } catch (SQLiteException ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataSubDis(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_SUBDIVISION_DISLOCATION, cv, DatabaseHelper.COLUMN_SUBDIVISION_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataSubDis(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_SUBDIVISION_DISLOCATION, DatabaseHelper.COLUMN_SUBDIVISION_ID + "=?", new String[]{id});
    }

    //----------------------------Speciality accounting query------------------------------------

    public static List<String> getDataSpcA(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_SPECIALITY_ACCOUNTING +
                " where _id_mlt=? order by _id_spc", new String[]{id}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowSpcA(Context context, int position, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select _id_accounting,_id_spc from " + DatabaseHelper.TABLE_SPECIALITY_ACCOUNTING +
                " where _id_mlt=? order by _id_spc", new String[]{id}); //получаем данные из бд

        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1)};
    }

    public static void addDataSpcA(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_SPECIALITY_ACCOUNTING, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataSpcA(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_SPECIALITY_ACCOUNTING, cv, DatabaseHelper.COLUMN_ACCOUNTING_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataSpcA(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_SPECIALITY_ACCOUNTING, DatabaseHelper.COLUMN_ACCOUNTING_ID + "=?", new String[]{id});
    }

    //----------------------------Equipment accounting query------------------------------------

    public static List<String> getDataEquA(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING +
                " where _id_mu=? order by _id_equ", new String[]{id}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4)
                        +"\n"+userCursor.getColumnName(5).toUpperCase()+":  "+userCursor.getString(5));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowEquA(Context context, int position, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT "
                + DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING + "." + DatabaseHelper.COLUMN_ACCOUNTING_ID + ","
                + DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING + "." + DatabaseHelper.COLUMN_EQUIPMENT_ID + ","
                + DatabaseHelper.TABLE_EQUIPMENT + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING + "." + DatabaseHelper.COLUMN_QUANTITY
                + " FROM " + DatabaseHelper.TABLE_EQUIPMENT
                + " INNER JOIN " + DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING
                + " ON " + DatabaseHelper.TABLE_EQUIPMENT + "." + DatabaseHelper.COLUMN_EQUIPMENT_ID
                + " = " + DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING + "." + DatabaseHelper.COLUMN_EQUIPMENT_ID
                + " WHERE " + DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING + "." + DatabaseHelper.COLUMN_MILITARY_UNIT_ID
                + " = ? ORDER BY " + DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING + "." + DatabaseHelper.COLUMN_EQUIPMENT_ID, new String[]{id}); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2), userCursor.getString(3)};
    }

    public static void addDataEquA(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataEquA(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING, cv, DatabaseHelper.COLUMN_ACCOUNTING_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataEquA(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_EQUIPMENT_ACCOUNTING, DatabaseHelper.COLUMN_ACCOUNTING_ID + "=?", new String[]{id});
    }

    //----------------------------Weaponry accounting query------------------------------------

    public static List<String> getDataWpnA(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING +
                " where _id_mu=? order by _id_wpn", new String[]{id}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4)
                        +"\n"+userCursor.getColumnName(5).toUpperCase()+":  "+userCursor.getString(5));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static String[] getRowWpnA(Context context, int position, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT "
                + DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING + "." + DatabaseHelper.COLUMN_ACCOUNTING_ID + ","
                + DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING + "." + DatabaseHelper.COLUMN_WEAPONRY_ID + ","
                + DatabaseHelper.TABLE_WEAPONRY + "." + DatabaseHelper.COLUMN_NAME + ","
                + DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING + "." + DatabaseHelper.COLUMN_QUANTITY
                + " FROM " + DatabaseHelper.TABLE_WEAPONRY
                + " INNER JOIN " + DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING
                + " ON " + DatabaseHelper.TABLE_WEAPONRY + "." + DatabaseHelper.COLUMN_WEAPONRY_ID
                + " = " + DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING + "." + DatabaseHelper.COLUMN_WEAPONRY_ID
                + " WHERE " + DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING + "." + DatabaseHelper.COLUMN_MILITARY_UNIT_ID
                + " = ? ORDER BY " + DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING + "." + DatabaseHelper.COLUMN_WEAPONRY_ID, new String[]{id}); //получаем данные из бд
        userCursor.moveToPosition(position);
        return new String[]{userCursor.getString(0), userCursor.getString(1), userCursor.getString(2), userCursor.getString(3)};
    }

    public static void addDataWpnA(Context context, ContentValues cv){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.insert(DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING, null, cv);
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void changeDataWpnA(Context context, ContentValues cv, String id){
        try {
            DatabaseHelper sqlHelper = new DatabaseHelper(context);
            db = sqlHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING, cv, DatabaseHelper.COLUMN_ACCOUNTING_ID + "=?", new String[]{id});
        } catch (SQLiteConstraintException ex){
            Toast.makeText(context, "Нарушение целостности", Toast.LENGTH_SHORT).show();
        }
    }

    public static void deleteDataWpnA(Context context, String id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_WEAPONRY_ACCOUNTING, DatabaseHelper.COLUMN_ACCOUNTING_ID + "=?", new String[]{id});
    }

    //----------------------------Tasks query------------------------------------

    public static List<String> getDataT1(Context context, String data_1, String data_2, String data_3){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT military_unit._id_mu, military_unit.name, military_unit.fio "
                + "FROM military_unit "
                + "LEFT JOIN community ON community._id_comm = military_unit._id_comm "
                + "LEFT JOIN army ON army._id_army = community._id_army "
                + "WHERE military_unit._id_md = ? AND army._id_army LIKE ? AND community._id_comm LIKE ?"
                , new String[]{data_1,'%'+data_2+'%','%'+data_3+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT2(Context context, String... id){
        Log.e("DataManager: ", "Data: "+ id[0]+ id[1]+ id[2]+ id[3]+ id[4]);
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        if (id[1].isEmpty() && id[2].isEmpty() && id[3].isEmpty())
        userCursor = db.rawQuery("SELECT rank.name, military.fio "
                + "FROM rank "
                + "INNER JOIN military ON rank._id_rnk = military._id_rnk "
                + "WHERE rank.type = 'Офицерский состав' "
                + "AND military._id_md = ? "
                + "AND military._id_rnk LIKE ? "
                , new String[]{id[0],'%'+id[4]+'%'}); //получаем данные из бд
        else
            userCursor = db.rawQuery("SELECT rank.name, military.fio "
                            + "FROM rank "
                            + "INNER JOIN military ON rank._id_rnk = military._id_rnk "
                            + "WHERE rank.type = 'Офицерский состав' "
                            + "AND military._id_md = ? "
                            + "AND military._id_rnk LIKE ? "
                            + "AND ("
                            + "EXISTS(SELECT army._id_mlt FROM army WHERE army._id_mlt = military._id_mlt AND army._id_army = ?) "
                            + "OR NOT EXISTS(SELECT military_unit._id_mu FROM military_unit WHERE military_unit._id_mu = ?) AND EXISTS(SELECT community._id_mlt FROM community WHERE community._id_mlt = military._id_mlt AND community._id_army LIKE ? AND community._id_comm LIKE ?) "
                            + "OR EXISTS(SELECT military_unit._id_mlt FROM military_unit WHERE military_unit._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = military_unit._id_comm AND community._id_army LIKE ?) AND military_unit._id_comm LIKE ? AND military_unit._id_mu LIKE ?) "
                            + "OR EXISTS(SELECT company._id_mlt FROM company WHERE company._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = company._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = company._id_mu AND military_unit._id_comm LIKE ?) AND company._id_mu LIKE ?) "
                            + "OR EXISTS(SELECT platoon._id_mlt FROM platoon WHERE platoon._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = platoon._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = platoon._id_mu AND military_unit._id_comm LIKE ?) AND platoon._id_mu LIKE ?) "
                            + "OR EXISTS(SELECT department._id_mlt FROM department WHERE department._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = department._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = department._id_mu AND military_unit._id_comm LIKE ?) AND department._id_mu LIKE ?)) "
                    , new String[]{id[0],'%'+id[4]+'%',id[1],id[3],'%'+id[1]+'%','%'+id[2]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT3(Context context, String... id){
        Log.e("DataManager: ", "Data: "+ id[0]+ id[1]+ id[2]+ id[3]+ id[4]);
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        if (id[1].isEmpty() && id[2].isEmpty() && id[3].isEmpty())
            userCursor = db.rawQuery("SELECT rank.name, military.fio "
                            + "FROM rank "
                            + "INNER JOIN military ON rank._id_rnk = military._id_rnk "
                            + "WHERE rank.type != 'Офицерский состав' "
                            + "AND military._id_md = ? "
                            + "AND military._id_rnk LIKE ? "
                    , new String[]{id[0],'%'+id[4]+'%'}); //получаем данные из бд
        else
            userCursor = db.rawQuery("SELECT rank.name, military.fio "
                            + "FROM rank "
                            + "INNER JOIN military ON rank._id_rnk = military._id_rnk "
                            + "WHERE rank.type != 'Офицерский состав' "
                            + "AND military._id_md = ? "
                            + "AND military._id_rnk LIKE ? "
                            + "AND ("
                            + "EXISTS(SELECT army._id_mlt FROM army WHERE army._id_mlt = military._id_mlt AND army._id_army = ?) "
                            + "OR NOT EXISTS(SELECT military_unit._id_mu FROM military_unit WHERE military_unit._id_mu = ?) AND EXISTS(SELECT community._id_mlt FROM community WHERE community._id_mlt = military._id_mlt AND community._id_army LIKE ? AND community._id_comm LIKE ?) "
                            + "OR EXISTS(SELECT military_unit._id_mlt FROM military_unit WHERE military_unit._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = military_unit._id_comm AND community._id_army LIKE ?) AND military_unit._id_comm LIKE ? AND military_unit._id_mu LIKE ?) "
                            + "OR EXISTS(SELECT company._id_mlt FROM company WHERE company._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = company._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = company._id_mu AND military_unit._id_comm LIKE ?) AND company._id_mu LIKE ?) "
                            + "OR EXISTS(SELECT platoon._id_mlt FROM platoon WHERE platoon._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = platoon._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = platoon._id_mu AND military_unit._id_comm LIKE ?) AND platoon._id_mu LIKE ?) "
                            + "OR EXISTS(SELECT department._id_mlt FROM department WHERE department._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = department._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = department._id_mu AND military_unit._id_comm LIKE ?) AND department._id_mu LIKE ?)) "
                    , new String[]{id[0],'%'+id[4]+'%',id[1],id[3],'%'+id[1]+'%','%'+id[2]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT4(Context context, String... id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
            userCursor = db.rawQuery("SELECT rank.name, military.fio " +
                    "FROM rank INNER JOIN military ON rank._id_rnk = military._id_rnk " +
                    "INNER JOIN military_district ON military_district._id_md = military._id_md " +
                    "INNER JOIN army ON military_district._id_md = army._id_md " +
                    "INNER JOIN community ON army._id_army = community._id_army " +
                    "INNER JOIN military_unit ON community._id_comm = military_unit._id_comm " +
                    "INNER JOIN company ON military_unit._id_mu = company._id_mu " +
                    "INNER JOIN platoon ON company._id_cmp = platoon._id_cmp " +
                    "INNER JOIN department ON platoon._id_pln = department._id_pln " +
                    "WHERE army._id_mlt = military._id_mlt "
                    , new String[]{}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1));
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT5(Context context, String... id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
            userCursor = db.rawQuery("SELECT dislocation_place._id_md, dislocation_place._id_dp, dislocation_place.name, military_unit.name "
                            + "FROM dislocation_place "
                            + "INNER JOIN military_unit ON dislocation_place._id_dp = military_unit._id_dp "
                            + "LEFT JOIN community ON community._id_comm = military_unit._id_comm "
                            + "LEFT JOIN army ON army._id_army = community._id_army "
                            + "WHERE military_unit._id_md = ? AND army._id_army LIKE ? AND community._id_comm LIKE ? AND military_unit._id_mu LIKE ?"
                    , new String[]{id[0],'%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                );
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT6(Context context, String... id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT equipment.name, equipment.type, equipment_accounting.quantity, military_unit.name "
                        + "FROM equipment "
                        + "INNER JOIN equipment_accounting ON equipment._id_equ = equipment_accounting._id_equ "
                        + "INNER JOIN military_unit ON military_unit._id_mu = equipment_accounting._id_mu "
                        + "LEFT JOIN community ON community._id_comm = military_unit._id_comm "
                        + "LEFT JOIN army ON army._id_army = community._id_army "
                        + "WHERE military_unit._id_md = ? AND army._id_army LIKE ? AND community._id_comm LIKE ? AND military_unit._id_mu LIKE ? AND equipment._id_equ_c LIKE ? AND equipment.type LIKE ?"
                , new String[]{id[0],'%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[4]+'%','%'+id[5]+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                );
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT7(Context context, String... id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        if (id[2].isEmpty())
        userCursor = db.rawQuery("SELECT building._id_blg, building.name, military_unit.name, subdivision_dislocation._id_subdivision "
                        + "FROM military_unit "
                        + "INNER JOIN building ON military_unit._id_mu = building._id_mu "
                        + "LEFT JOIN subdivision_dislocation ON building._id_blg = subdivision_dislocation._id_blg "
                        + "WHERE military_unit._id_md = ? AND military_unit._id_mu LIKE ? "
                        + "GROUP BY building._id_blg "
                , new String[]{id[0],'%'+id[1]+'%'}); //получаем данные из бд
        else
        userCursor = db.rawQuery("SELECT building._id_blg, building.name, military_unit.name, COUNT(subdivision_dislocation._id_subdivision) AS subdivision "
                        + "FROM military_unit "
                        + "INNER JOIN building ON military_unit._id_mu = building._id_mu "
                        + "LEFT JOIN subdivision_dislocation ON building._id_blg = subdivision_dislocation._id_blg "
                        + "WHERE military_unit._id_md = ? AND military_unit._id_mu LIKE ? "
                        + "GROUP BY building._id_blg " + id[2]
                , new String[]{id[0],'%'+id[1]+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                );
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT8(Context context, String... id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
            userCursor = db.rawQuery("SELECT military_unit.name, equipment.name, equipment._id_equ_c, equipment.type, equipment_accounting.quantity "
                            + "FROM equipment "
                            + "INNER JOIN equipment_accounting ON equipment._id_equ = equipment_accounting._id_equ "
                            + "INNER JOIN military_unit ON military_unit._id_mu = equipment_accounting._id_mu "
                            + "WHERE equipment._id_equ_c LIKE ? AND equipment_accounting.quantity > 5 "
                    , new String[]{'%'+id[0]+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                        +"\n"+userCursor.getColumnName(4).toUpperCase()+":  "+userCursor.getString(4)
                );
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT9(Context context, String... id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT weaponry.name, weaponry._id_wpn_c, weaponry_accounting.quantity, military_unit.name "
                        + "FROM weaponry "
                        + "INNER JOIN weaponry_accounting ON weaponry._id_wpn = weaponry_accounting._id_wpn "
                        + "INNER JOIN military_unit ON military_unit._id_mu = weaponry_accounting._id_mu "
                        + "LEFT JOIN community ON community._id_comm = military_unit._id_comm "
                        + "LEFT JOIN army ON army._id_army = community._id_army "
                        + "WHERE military_unit._id_md = ? AND army._id_army LIKE ? AND community._id_comm LIKE ? AND military_unit._id_mu LIKE ? AND weaponry._id_wpn_c LIKE ? "
                , new String[]{id[0],'%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[4]+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                );
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT10(Context context, String... id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        if (id[1].isEmpty() && id[2].isEmpty() && id[3].isEmpty())
            userCursor = db.rawQuery("SELECT speciality._id_spc, speciality.name, COUNT(military._id_mlt) AS military "
                            + "FROM speciality "
                            + "INNER JOIN speciality_accounting ON speciality._id_spc = speciality_accounting._id_spc "
                            + "LEFT JOIN military ON military._id_mlt = speciality_accounting._id_mlt "
                            + "WHERE military._id_md = ? "
                            + "GROUP BY speciality._id_spc " + id[4]
                    , new String[]{id[0]}); //получаем данные из бд
        else
        userCursor = db.rawQuery("SELECT speciality._id_spc, speciality.name, COUNT(military._id_mlt) AS military "
                        + "FROM speciality "
                        + "INNER JOIN speciality_accounting ON speciality._id_spc = speciality_accounting._id_spc "
                        + "LEFT JOIN military ON military._id_mlt = speciality_accounting._id_mlt "
                        + "WHERE military._id_md = ? "
                        + "AND ("
                        + "EXISTS(SELECT army._id_mlt FROM army WHERE army._id_mlt = military._id_mlt AND army._id_army = ?) "
                        + "OR NOT EXISTS(SELECT military_unit._id_mu FROM military_unit WHERE military_unit._id_mu = ?) AND EXISTS(SELECT community._id_mlt FROM community WHERE community._id_mlt = military._id_mlt AND community._id_army LIKE ? AND community._id_comm LIKE ?) "
                        + "OR EXISTS(SELECT military_unit._id_mlt FROM military_unit WHERE military_unit._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = military_unit._id_comm AND community._id_army LIKE ?) AND military_unit._id_comm LIKE ? AND military_unit._id_mu LIKE ?) "
                        + "OR EXISTS(SELECT company._id_mlt FROM company WHERE company._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = company._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = company._id_mu AND military_unit._id_comm LIKE ?) AND company._id_mu LIKE ?) "
                        + "OR EXISTS(SELECT platoon._id_mlt FROM platoon WHERE platoon._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = platoon._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = platoon._id_mu AND military_unit._id_comm LIKE ?) AND platoon._id_mu LIKE ?) "
                        + "OR EXISTS(SELECT department._id_mlt FROM department WHERE department._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = department._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = department._id_mu AND military_unit._id_comm LIKE ?) AND department._id_mu LIKE ?)) "
                        + "GROUP BY speciality._id_spc " + id[4]
                , new String[]{id[0],id[1],id[3],'%'+id[1]+'%','%'+id[2]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                );
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT11(Context context, String... id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        if (id[1].isEmpty() && id[2].isEmpty() && id[3].isEmpty())
            userCursor = db.rawQuery("SELECT speciality._id_spc, speciality.name, military._id_mlt, military.fio "
                            + "FROM speciality "
                            + "INNER JOIN speciality_accounting ON speciality._id_spc = speciality_accounting._id_spc "
                            + "LEFT JOIN military ON military._id_mlt = speciality_accounting._id_mlt "
                            + "WHERE military._id_md = ? AND speciality._id_spc LIKE ? "
                    , new String[]{id[0],'%'+id[4]+'%'}); //получаем данные из бд
        else
            userCursor = db.rawQuery("SELECT speciality._id_spc, speciality.name, military._id_mlt, military.fio "
                            + "FROM speciality "
                            + "INNER JOIN speciality_accounting ON speciality._id_spc = speciality_accounting._id_spc "
                            + "LEFT JOIN military ON military._id_mlt = speciality_accounting._id_mlt "
                            + "WHERE military._id_md = ? AND speciality._id_spc LIKE ? "
                            + "AND ("
                            + "EXISTS(SELECT army._id_mlt FROM army WHERE army._id_mlt = military._id_mlt AND army._id_army = ?) "
                            + "OR NOT EXISTS(SELECT military_unit._id_mu FROM military_unit WHERE military_unit._id_mu = ?) AND EXISTS(SELECT community._id_mlt FROM community WHERE community._id_mlt = military._id_mlt AND community._id_army LIKE ? AND community._id_comm LIKE ?) "
                            + "OR EXISTS(SELECT military_unit._id_mlt FROM military_unit WHERE military_unit._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = military_unit._id_comm AND community._id_army LIKE ?) AND military_unit._id_comm LIKE ? AND military_unit._id_mu LIKE ?) "
                            + "OR EXISTS(SELECT company._id_mlt FROM company WHERE company._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = company._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = company._id_mu AND military_unit._id_comm LIKE ?) AND company._id_mu LIKE ?) "
                            + "OR EXISTS(SELECT platoon._id_mlt FROM platoon WHERE platoon._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = platoon._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = platoon._id_mu AND military_unit._id_comm LIKE ?) AND platoon._id_mu LIKE ?) "
                            + "OR EXISTS(SELECT department._id_mlt FROM department WHERE department._id_mlt = military._id_mlt AND EXISTS(SELECT community._id_comm FROM community WHERE community._id_comm = (SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = department._id_mu) AND community._id_army LIKE ?) AND EXISTS(SELECT military_unit._id_comm FROM military_unit WHERE military_unit._id_mu = department._id_mu AND military_unit._id_comm LIKE ?) AND department._id_mu LIKE ?)) "
                    , new String[]{id[0],'%'+id[4]+'%',id[1],id[3],'%'+id[1]+'%','%'+id[2]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%','%'+id[1]+'%','%'+id[2]+'%','%'+id[3]+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                );
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT12(Context context, String... id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        userCursor = db.rawQuery("SELECT military_unit.name, weaponry.name, weaponry._id_wpn_c, weaponry_accounting.quantity "
                        + "FROM weaponry "
                        + "INNER JOIN weaponry_accounting ON weaponry._id_wpn = weaponry_accounting._id_wpn "
                        + "INNER JOIN military_unit ON military_unit._id_mu = weaponry_accounting._id_mu "
                        + "WHERE weaponry._id_wpn_c LIKE ? AND weaponry_accounting.quantity > 10 "
                , new String[]{'%'+id[0]+'%'}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                        +"\n"+userCursor.getColumnName(3).toUpperCase()+":  "+userCursor.getString(3)
                );
                userCursor.moveToNext();
            }
        }
        return data;
    }

    public static List<String> getDataT13(Context context, String... id){
        DatabaseHelper sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getReadableDatabase();
        if (id[0].equals("Армия"))
        userCursor = db.rawQuery("SELECT army._id_army, army.name, COUNT(military_unit.name) AS military_unit "
                        + "FROM military_unit "
                        + "INNER JOIN community ON community._id_comm = military_unit._id_comm "
                        + "INNER JOIN army ON army._id_army = community._id_army "
                        + "GROUP BY army._id_army "
                        + "ORDER BY military_unit " + id[1]
                , new String[]{}); //получаем данные из бд
        else
        userCursor = db.rawQuery("SELECT community._id_comm, community.name, COUNT(military_unit.name) AS military_unit "
                        + "FROM military_unit "
                        + "INNER JOIN community ON community._id_comm = military_unit._id_comm "
                        + "INNER JOIN army ON army._id_army = community._id_army "
                        + "GROUP BY community._id_comm "
                        + "ORDER BY military_unit " + id[1]
                , new String[]{}); //получаем данные из бд
        List<String> data = new ArrayList<>(userCursor.getCount());
        if (userCursor.moveToFirst()) {
            while ( !userCursor.isAfterLast() ) {
                data.add(userCursor.getColumnName(0).toUpperCase()+":  "+userCursor.getString(0)
                        +"\n"+userCursor.getColumnName(1).toUpperCase()+":  "+userCursor.getString(1)
                        +"\n"+userCursor.getColumnName(2).toUpperCase()+":  "+userCursor.getString(2)
                );
                userCursor.moveToNext();
            }
        }
        return data;
    }
}