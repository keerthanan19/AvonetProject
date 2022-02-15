package com.assignment.avonetproject.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.assignment.avonetproject.Object.Expense;

import java.util.ArrayList;

public class DBUtils {
    private static db_manager db_manager;
    private static final String TAG = "DBUtils";

    public static boolean insert_expense(Expense data, Context mContext){
        boolean isSuccess = false;
        db_manager = new db_manager(mContext);
        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {

            ContentValues cv = new ContentValues();
            cv.put(db_tables.EXPENSE_DESCRIPTION, data.getDescription());
            cv.put(db_tables.EXPENSE_DATE, data.getDate());
            cv.put(db_tables.EXPENSE_TYPE, data.getType());
            cv.put(db_tables.EXPENSE_AMOUNT, data.getAmount());
            cv.put(db_tables.EXPENSE_BILL_NO, data.getBill_no());

            try{
                long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_EXPENSE, null, cv);
                if (rowCount != -1){
                    Log.d(TAG, "insert_users " + " inserted");
                    isSuccess = true;
                }else{
                    Log.e(TAG, "insert_users " + " insert failed");
                    isSuccess = false;
                }
            }catch (Exception e){
                try{
                    long rowCount = db.replaceOrThrow(db_tables.TABLE_NAME_EXPENSE, null, cv);
                    if (rowCount != -1){
                        Log.d(TAG, "insert_expense " + " replaced");
                        isSuccess = true;
                    }else{
                        Log.e(TAG, "insert_expense " + " replaced failed");
                        isSuccess = false;
                    }
                }catch (Exception ie){
                    Log.e(TAG, "insert_expense " + " inserted or replaced failed  :"+ ie.getMessage());
                    isSuccess = false;
                }
                isSuccess = false;
            }
        }
        return isSuccess;
    }


    @SuppressLint("Range")
    public static ArrayList<Expense> getAllExpense(Context mContext) {
        ArrayList<Expense>  ExpenseArrayList = new ArrayList<>();
        db_manager = new db_manager(mContext);
        Cursor c;
        String qty = "0";

        StringBuilder query = new StringBuilder("SELECT * FROM " + db_tables.TABLE_NAME_EXPENSE
                + " ORDER BY " + db_tables.EXPENSE_DATE + " ASC"
        );

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);
            if (c.moveToFirst()) {
                do {
                    Expense Expense = new Expense();

                    Expense.setId(c.getColumnIndex(db_tables.EXPENSE_ID));
                    Expense.setDescription(c.getString(c.getColumnIndex(db_tables.EXPENSE_DESCRIPTION)));
                    Expense.setDate(c.getString(c.getColumnIndex(db_tables.EXPENSE_DATE)));
                    Expense.setType(c.getString(c.getColumnIndex(db_tables.EXPENSE_TYPE)));
                    Expense.setAmount(c.getString(c.getColumnIndex(db_tables.EXPENSE_AMOUNT)));
                    Expense.setBill_no(c.getString(c.getColumnIndex(db_tables.EXPENSE_BILL_NO)));

                    ExpenseArrayList.add(Expense);
                } while (c.moveToNext());
            }
            return ExpenseArrayList;
        } catch (Exception e) {
            Log.e(TAG, "getAllexpense " + e.toString());
        }
        return ExpenseArrayList;
    }

    @SuppressLint("Range")
    public static ArrayList<Expense> getAllExpenseByType(Context mContext) {
        ArrayList<Expense>  ExpenseArrayList = new ArrayList<>();
        db_manager = new db_manager(mContext);
        Cursor c;
        String qty = "0";

        StringBuilder query = new StringBuilder("SELECT * FROM " + db_tables.TABLE_NAME_EXPENSE
                + " GROUP BY " + db_tables.EXPENSE_TYPE
                + " ORDER BY " + db_tables.EXPENSE_AMOUNT + " ASC"
        );

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);
            if (c.moveToFirst()) {
                do {
                    Expense Expense = new Expense();

                    Expense.setId(c.getColumnIndex(db_tables.EXPENSE_ID));
                    Expense.setDescription(c.getString(c.getColumnIndex(db_tables.EXPENSE_DESCRIPTION)));
                    Expense.setDate(c.getString(c.getColumnIndex(db_tables.EXPENSE_DATE)));
                    Expense.setType(c.getString(c.getColumnIndex(db_tables.EXPENSE_TYPE)));
                    Expense.setAmount(c.getString(c.getColumnIndex(db_tables.EXPENSE_AMOUNT)));
                    Expense.setBill_no(c.getString(c.getColumnIndex(db_tables.EXPENSE_BILL_NO)));

                    ExpenseArrayList.add(Expense);
                } while (c.moveToNext());
            }
            return ExpenseArrayList;
        } catch (Exception e) {
            Log.e(TAG, "getAllexpense " + e.toString());
        }
        return ExpenseArrayList;
    }

    @SuppressLint("Range")
    public static Expense getEXPENSEByID(Context mContext, String EXPENSEID) {
        db_manager = new db_manager(mContext);
        Cursor c;
        String qty = "0";
        Expense Expense = new Expense();

        StringBuilder query = new StringBuilder("SELECT * FROM " + db_tables.TABLE_NAME_EXPENSE
                + " where " + db_tables.EXPENSE_ID + "='" + EXPENSEID +"'"
                + " ORDER BY " + db_tables.EXPENSE_DATE + " ASC"
        );

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);
            if (c.moveToFirst()) {
                do {


                    Expense.setId(c.getColumnIndex(db_tables.EXPENSE_ID));
                    Expense.setDescription(c.getString(c.getColumnIndex(db_tables.EXPENSE_DESCRIPTION)));
                    Expense.setDate(c.getString(c.getColumnIndex(db_tables.EXPENSE_DATE)));
                    Expense.setType(c.getString(c.getColumnIndex(db_tables.EXPENSE_TYPE)));
                    Expense.setAmount(c.getString(c.getColumnIndex(db_tables.EXPENSE_AMOUNT)));
                    Expense.setBill_no(c.getString(c.getColumnIndex(db_tables.EXPENSE_BILL_NO)));

                } while (c.moveToNext());
            }
            return Expense;
        } catch (Exception e) {
            Log.e(TAG, "getEXPENSEByID " + e.toString());
        }
        return Expense;
    }


    @SuppressLint("Range")
    public static Double  getAllExpenseAmount(Context mContext) {
        Double amount = 0.00;
        db_manager = new db_manager(mContext);
        Cursor c;
        String qty = "0";

        StringBuilder query = new StringBuilder("SELECT * FROM " + db_tables.TABLE_NAME_EXPENSE
                + " ORDER BY " + db_tables.EXPENSE_DATE + " ASC"
        );

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);
            if (c.moveToFirst()) {
                do {

                    amount = amount + Double.parseDouble(c.getString(c.getColumnIndex(db_tables.EXPENSE_AMOUNT)));


                } while (c.moveToNext());
            }
            return amount;
        } catch (Exception e) {
            Log.e(TAG, "getAllexpense " + e.toString());
        }
        return amount;
    }






    public static void deleteExpense(Context context,String EXPENSEID) {
        db_manager = new db_manager(context);
        SQLiteDatabase db = db_manager.getReadableDatabase();
        db.execSQL("delete from " + db_tables.TABLE_NAME_EXPENSE
                + " where " + db_tables.EXPENSE_ID + "='" + EXPENSEID +"'");
    }
}

