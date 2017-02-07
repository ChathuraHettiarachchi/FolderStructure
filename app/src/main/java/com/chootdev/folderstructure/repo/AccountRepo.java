package com.chootdev.folderstructure.repo;

/**
 * Created by Choota on 1/20/17.
 */


import com.chootdev.folderstructure.database.DatabaseHelper;
import com.chootdev.folderstructure.database.DatabaseManager;
import com.chootdev.folderstructure.models.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountRepo implements Crud {
    private DatabaseHelper mHelper;

    public AccountRepo() {
        mHelper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = 1;
        Account object = (Account) item;
        try {
            return mHelper.getAccountDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Object item) {
        int index = -1;

        Account object = (Account) item;

        try {
            mHelper.getAccountDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;

        Account object = (Account) item;

        try {
            mHelper.getAccountDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;

    }

    @Override
    public Account findById(int id) {
        Account item = null;
        try {
            item = mHelper.getAccountDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<?> findAll() {

        List<Account> items = null;

        try {
            items = mHelper.getAccountDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public boolean truncate(Class<?> myclass) {
        boolean isDone = this.mHelper.truncateTable(myclass);
        return isDone;
    }
}
