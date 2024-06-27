package com.example.restaurantapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION ) {

    companion object{
        private val DATABASE_NAME = "restaurant.db"
        private val DATABASE_VERSION = 1

        val TABLE_FOOD_ITEMS = "food_items"
        val COLUMN_ITEM_ID = "item_id"
        val COLUMN_ITEM_NAME = "item_name"
        val COLUMN_ITEM_PRICE = "item_price"

        val TABLE_FOOD_DETAILS = "food_details"
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_CATEGORY = "category"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createFoodItemsTable = "CREATE TABLE $TABLE_FOOD_ITEMS (" +
                "$COLUMN_ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_ITEM_NAME TEXT, " +
                "$COLUMN_ITEM_PRICE REAL)"

        val createFoodDetailsTable = "CREATE TABLE $TABLE_FOOD_DETAILS (" +
                "$COLUMN_ITEM_NAME TEXT, " +
                "$COLUMN_DESCRIPTION TEXT, " +
                "$COLUMN_CATEGORY TEXT, " +
                "FOREIGN KEY($COLUMN_ITEM_NAME) REFERENCES $TABLE_FOOD_ITEMS($COLUMN_ITEM_NAME))"

        db?.execSQL(createFoodItemsTable)
        db?.execSQL(createFoodDetailsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_FOOD_ITEMS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_FOOD_DETAILS")
        onCreate(db)
    }

    fun deleteItem(itemName: String): Boolean {
        val db = writableDatabase
        val whereClause = "${DatabaseHelper.COLUMN_ITEM_NAME} = ?"
        val whereArgs = arrayOf(itemName)

        // Returns the number of rows deleted, which should be 1 if successful
        val deletedRows = db.delete(DatabaseHelper.TABLE_FOOD_ITEMS, whereClause, whereArgs)

        return deletedRows > 0
    }
}