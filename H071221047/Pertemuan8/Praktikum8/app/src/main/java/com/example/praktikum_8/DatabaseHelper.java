package com.example.praktikum_8;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Note.db";
    private static final Integer DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT , description TEXT, date TEXT, time TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "notes");
        onCreate(db);

    }

    public void addNote(NoteModel noteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", noteModel.getTitle());
        values.put("description", noteModel.getDescription());
        values.put("date", "Created at " + noteModel.getDate());
        values.put("time", noteModel.getTime());
        db.insert("notes", null, values);
    }

    @SuppressLint("Range")
    public List<NoteModel> getNote(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<NoteModel> data = new ArrayList<>();
        Cursor cursor = db.query("notes", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                noteModel.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                noteModel.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                noteModel.setDate(cursor.getString(cursor.getColumnIndex("date")));
                noteModel.setTime(cursor.getString(cursor.getColumnIndex("time")));

                data.add(noteModel);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    public void updateNote(NoteModel noteModel, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", noteModel.getTitle());
        values.put("description", noteModel.getDescription());
        values.put( "date", "Updated at " + noteModel.getDate());
        values.put("time", noteModel.getTime());
        db.update("notes", values, "id=?", new String[]{String.valueOf(id)});

    }

    public void deleteNote(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("notes", "id=?",new String[]{String.valueOf(id)} );
        db.close();

    }

    @SuppressLint("Range")
    public List<NoteModel> searchNotes(String query) {
        List<NoteModel> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notes WHERE title LIKE ?", new String[]{"%" + query + "%"});
        if (cursor.moveToFirst()) {
            do {
                NoteModel note = new NoteModel();
                note.setId(cursor.getInt(cursor.getColumnIndex("id")));
                note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                note.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                note.setDate(cursor.getString(cursor.getColumnIndex("date")));
                note.setTime(cursor.getString(cursor.getColumnIndex("time")));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return noteList;
    }

}
