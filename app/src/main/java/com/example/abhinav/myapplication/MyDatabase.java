package com.example.abhinav.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabase {


    MyHelper m;
    SQLiteDatabase s;
    private String TAG = MyDatabase.class.getName();

    public MyDatabase(Context c) {
        m = new MyHelper(c, "abhinav.db", null, 1);
    }//null-cursor factory


    public void opendb() {
        s = m.getWritableDatabase();
    }
    public void close() {
       m.close();
    }

    public Cursor getPatientDataUsingCaseNo(String caseno) {
        Cursor c = null;
        c = s.query("patientData", null, "caseno=?", new String[]{"" + caseno}, null, null, null);
        return c;
    }

    public Cursor getAllPatientData() {
        Cursor c = null;
        c = s.query("patientData", null, null, null, null, null, null);
        return c;
    }

    public void deleteAllPatientData()
    {
        s.delete("patientData",null,null);
    }

    public void deletePatientData(String uuid)
    {
        s.delete("patientData","uuid=?",new String[]{""+uuid});
    }

    public Cursor searchPatientData(String key, String value)
    {
        Cursor c = null;
        c = s.query("patientData",null, key+" LIKE ?",new String[]{value+"%"},null,null,null);
        return c;
    }
    public Cursor getUserAll()
    {
        Cursor c = null;
        c = s.query("userData",null,null, null,null,null,null);
        return c;
    }
    public long getNoOfRows(String table) {
        long rows = DatabaseUtils.queryNumEntries(s, table);
        return rows;
    }

    public void updatePatientData(String uuid, String caseno,String date, String name, String mobile, String age, String address, String weight, String kco,String co, String bp, String spo2, String p,String t, String rs, String cvs, String cns, String bo, String uo,  String rbs, String rx) {
        ContentValues c = new ContentValues();
        c.put("uuid",uuid);
        c.put("caseno", caseno);
        c.put("date", date);
        c.put("name", name);
        c.put("mobile", mobile);
        c.put("age", age);
        c.put("address", address);
        c.put("weight", weight);
        c.put("kco", kco);
        c.put("co", co);
        c.put("bp", bp);
        c.put("spo2", spo2);
        c.put("p", p);
        c.put("t", t);
        c.put("rs", rs);
        c.put("cvs", cvs);
        c.put("cns", cns);
        c.put("bo", bo);
        c.put("uo", uo);
        c.put("rbs", rbs);
        c.put("rx", rx);
        s.update("patientData", c, "uuid=?", new String[]{"" + uuid});
    }
    public void insertUser(String _id, String name,String date, String amount, String currency, String type, String vendor){//,String co, String bp, String spo2, String p,String t, String rs, String cvs, String cns, String bo, String uo,  String rbs, String rx) {
        ContentValues c = new ContentValues();
        c.put("_id",_id);
        c.put("name",name);
        c.put("amount",amount);
        c.put("currency",currency);
        c.put("type",type);
        c.put("vendor",vendor);
        c.put("date",date);
        s.insert("userData", null, c);
        Log.i(TAG,"data inserted...");
    }
     private class MyHelper extends SQLiteOpenHelper {

         MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

//            db.execSQL("create table patientData(_id integer primary key, uuid text, caseno text, date text, name text, mobile text, age text, address text, weight text, kco text, co text, bp text, spo2 text, p text, t text, rs text, cvs text, cns text, bo text, uo text, rbs text, rx text);");
//            db.execSQL("create table upDown(_id integer primary key, lastUpload text, lastDownload text);");
              db.execSQL("create table userData(_id string primary key,name text,amount text,currency text,type text,vendor text,date text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


}