
/**
 * This interface provides methods for core db change.
 * It contains methods for load , update or delete of records. * 
 */
package com.mobifever.we4u.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mobifever.we4u.exception.We4UException;

public interface BaseDao {
	
		public Object save(Object obj) throws We4UException ;
		
		void update(Update obj, Query searchQuery) throws We4UException;

		void delete(Query deleteQuery) throws We4UException;

		Integer getId(String id);

		Object load(Query searchQuery) throws We4UException;

		<T> List<T> loadAll(Query searchQuery) throws We4UException;

}



