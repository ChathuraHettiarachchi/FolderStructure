package com.chootdev.folderstructure.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.chootdev.folderstructure.models.Account;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Choota on 1/20/17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Sample.sqlite";

    private Dao<Account, Integer> accountDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<Account, Integer> getAccountDao() {
        if (null == accountDao) {
            try {
                accountDao = getDao(Account.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return accountDao;
    }

    public boolean truncateTable(Class<?> object) {
        boolean isDone = false;
        try {
            int i = TableUtils.dropTable(getConnectionSource(), object, true);
            i = TableUtils.createTable(getConnectionSource(), object);

            if (i > 0) {
                isDone = true;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return isDone;
    }
}
