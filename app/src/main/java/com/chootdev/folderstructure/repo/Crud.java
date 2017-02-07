package com.chootdev.folderstructure.repo;

import java.util.List;

/**
 * Created by Lakmal Weerasekara on 13/09/16.
 * Fidenz (pvt) Ltd.
 * lakmal.w@fidenz.com
 */
public interface Crud {
    public int create(Object item);
    public int update(Object item);
    public int delete(Object item);
    public Object findById(int id);
    public List<?> findAll();
}
