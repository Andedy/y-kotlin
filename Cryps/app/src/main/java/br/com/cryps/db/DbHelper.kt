package br.com.cryps.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper (context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase) {
        val sql ="CREATE TABLE $TABLE_ATIVOS($ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $ITEM_NOME TEXT, $ITEM_SIGLA TEXT,$ITEM_VALOR REAL, $ITEM_QTD REAL, $ITEM_TOTAL REAL, $ITEM_DATA TEXT)"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATIVOS)
        onCreate(db)
    }
}