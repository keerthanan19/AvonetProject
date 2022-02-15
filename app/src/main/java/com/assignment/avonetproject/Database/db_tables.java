package com.assignment.avonetproject.Database;

public class db_tables {

    static final String TABLE_NAME_EXPENSE = "EXPENSE";

    public  static final  String EXPENSE_ID        = "_id";
    static  final  String EXPENSE_DESCRIPTION      = "expense_description,";
    static  final  String EXPENSE_DATE             = "expense_date";
    static  final  String EXPENSE_TYPE             = "expense_type";
    static  final  String EXPENSE_AMOUNT           = "expense_amount";
    static  final  String EXPENSE_BILL_NO          = "expense_bill_no";


    static String CREATE_EXPENSE_TABLE = "CREATE TABLE " + TABLE_NAME_EXPENSE + "("
            + EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EXPENSE_DESCRIPTION + " TEXT,"
            + EXPENSE_DATE + " TEXT,"
            + EXPENSE_TYPE + " TEXT,"
            + EXPENSE_AMOUNT + " TEXT,"
            + EXPENSE_BILL_NO + " TEXT" + ")";


}

