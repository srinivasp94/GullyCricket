package sample.ramya.com.exampleapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elancer on 6/30/2017.
 */

public class Dbhandler extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gullycricketscoresDir";

    public static final String TABLE_NAME = "teamADetails";

    public static final String ID = "id";
    public static final String PLAYER_NAME = "player_name";
    public static final String PLAYER_SCORE = "player_score";
    public static final String PLAYER_TEAM = "player_team";
    public static final String IMAGE_PROFILE_A = "profilepic_a";
    public static final String OUT_STATUS_A = "out_status";
    public static final String CHECKED_STATUS_A = "checked_status";

    public static final String TABLE_NAME_TEAM_B = "teamBDetails";
    public static final String IDB = "idB";
    public static final String TEAM_B_PLAYER_NAME = "player_nameB";
    public static final String TEAM_B_PLAYER_SCORE = "player_scoreB";
    public static final String TEAM_B_PLAYER_TEAM = "player_teamB";
    public static final String OUT_STATUS = "out_status";
    public static final String CHECKED_STATUS = "checked_status";
    public static final String IMAGE_PROFILE_B = "profilepic_b";


    public Dbhandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ENTRIES = " CREATE TABLE " + TABLE_NAME + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PLAYER_NAME + " TEXT NOT NULL, "
                + PLAYER_SCORE + " TEXT NOT NULL, "
                + PLAYER_TEAM + " TEXT NOT NULL, "
                + OUT_STATUS_A + " INTEGER DEFAULT 0 ,"
                + CHECKED_STATUS_A + " INTEGER DEFAULT 0 ,"
                + IMAGE_PROFILE_A + " TEXT); ";

        final String SQL_CREATE_ENTRIES_FOR_TEAM_B = " CREATE TABLE " + TABLE_NAME_TEAM_B + " (" + IDB
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TEAM_B_PLAYER_NAME + " TEXT NOT NULL, "
                + TEAM_B_PLAYER_SCORE + " TEXT NOT NULL, "
                + TEAM_B_PLAYER_TEAM + " TEXT NOT NULL , "
                + OUT_STATUS + " INTEGER DEFAULT 0 ,"
                + CHECKED_STATUS + " INTEGER DEFAULT 0 ,"
                + IMAGE_PROFILE_B + " TEXT); ";

        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_FOR_TEAM_B);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TEAM_B);
// Creating tables again
        onCreate(db);
    }

    public void addPlayerDetails(ModelPlayer modelPlayer) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PLAYER_NAME, modelPlayer.getPlayer_name());
        values.put(PLAYER_SCORE, modelPlayer.getPlayer_score());
        values.put(PLAYER_TEAM, modelPlayer.getPlayer_team());
        values.put(OUT_STATUS_A, modelPlayer.getIsOut());
        values.put(CHECKED_STATUS_A, modelPlayer.getIsChecked());
        values.put(IMAGE_PROFILE_A, modelPlayer.getImagebytes());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public void addPlayerDetailsTeamB(TeamBModel bModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEAM_B_PLAYER_NAME, bModel.getPlayer_nameB());
        values.put(TEAM_B_PLAYER_SCORE, bModel.getPlayer_scoreB());
        values.put(TEAM_B_PLAYER_TEAM, bModel.getPlayer_teamB());
        values.put(OUT_STATUS, bModel.getIsOut());
        values.put(CHECKED_STATUS, bModel.getIsChecked());
        values.put(IMAGE_PROFILE_B, bModel.getImagebytes());

        db.insert(TABLE_NAME_TEAM_B, null, values);
        db.close();
    }

    public List<ModelPlayer> getAllPlayers() {
        List<ModelPlayer> listPlayer = null;
        listPlayer = new ArrayList<ModelPlayer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Log.d("DATABASE @ @ ", selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ModelPlayer modelPlayer = new ModelPlayer();
                modelPlayer.setId(Integer.parseInt(cursor.getString(0)));
                modelPlayer.setPlayer_name(cursor.getString(1));
                modelPlayer.setPlayer_score(cursor.getString(2));
                modelPlayer.setPlayer_team(cursor.getString(3));
                modelPlayer.setIsOut(cursor.getInt(4));
                modelPlayer.setIsChecked(cursor.getInt(5));
                modelPlayer.setImagebytes(cursor.getString(6));

                Log.d("###listData", cursor.getString(1));
                // Adding contact to list

                listPlayer.add(modelPlayer);

            } while (cursor.moveToNext());
        }

        // return contact list
        return listPlayer;
    }

    public List<TeamBModel> getAllTeamBPlayers() {
        List<TeamBModel> list = null;
        list = new ArrayList<TeamBModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_TEAM_B;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TeamBModel bModel = new TeamBModel();
                bModel.setIdB(Integer.parseInt(cursor.getString(0)));
                bModel.setPlayer_nameB(cursor.getString(1));
                bModel.setPlayer_scoreB(cursor.getString(2));
                bModel.setPlayer_teamB(cursor.getString(3));
                bModel.setIsOut(cursor.getInt(4));
                bModel.setIsChecked(cursor.getInt(5));
                bModel.setImagebytes(cursor.getString(6));

                Log.d("###listData", cursor.getString(1));
                // Adding contact to list

                list.add(bModel);

            } while (cursor.moveToNext());
        }

        // return contact list
        return list;
    }

    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public void updateScore(ModelPlayer modelPlayer) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLAYER_SCORE, modelPlayer.getPlayer_score());
        contentValues.put(OUT_STATUS_A, modelPlayer.getIsOut());
        db.update(TABLE_NAME, contentValues, ID + " = ?", new String[]{String.valueOf(modelPlayer.getId())});
        db.close();
    }

    public void updateScoreTeamB(TeamBModel bModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TEAM_B_PLAYER_SCORE, bModel.getPlayer_scoreB());
        cv.put(OUT_STATUS, bModel.getIsOut());
        db.update(TABLE_NAME_TEAM_B, cv, IDB + " = ?", new String[]{String.valueOf(bModel.getIdB())});
        db.close();
    }

    public void deleteTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.execSQL("delete from " + TABLE_NAME_TEAM_B);
    }

    public void updateCheckstatusA(ModelPlayer player) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CHECKED_STATUS_A, player.getIsChecked());
        db.update(TABLE_NAME, cv, ID + " = ?", new String[]{String.valueOf(player.getId())});
        db.close();

    }

    public void updateCheckstatusB(TeamBModel bModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CHECKED_STATUS, bModel.getIsChecked());
        db.update(TABLE_NAME_TEAM_B, cv, IDB + " = ?", new String[]{String.valueOf(bModel.getIdB())});
        db.close();

    }

    public int getScoreA() {
        SQLiteDatabase db = this.getReadableDatabase();
        int score_sum=0;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ModelPlayer player = new ModelPlayer();
                player.setPlayer_score(cursor.getString(2));
                String myString = player.getPlayer_score();

                if(!TextUtils.isEmpty(myString)) {
                    score_sum = score_sum + Integer.parseInt(player.getPlayer_score());
                }
            } while (cursor.moveToNext());

        }
        return score_sum;
    }

    public int getScoreB( ) {
        SQLiteDatabase db = this.getReadableDatabase();
        int score_sum = 0;

        String selectQuery = "SELECT  * FROM " + TABLE_NAME_TEAM_B;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TeamBModel player = new TeamBModel();
                player.setPlayer_scoreB(cursor.getString(2));
                if(player.getPlayer_scoreB() != null) {
                    score_sum = score_sum + Integer.parseInt(player.getPlayer_scoreB());
                }
            } while (cursor.moveToNext());

        }
        return score_sum;
    }


}

