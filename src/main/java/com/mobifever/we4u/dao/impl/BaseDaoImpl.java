package com.mobifever.we4u.dao.impl;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mobifever.we4u.constant.Constants;
import com.mobifever.we4u.dao.BaseDao;
import com.mobifever.we4u.exception.We4UException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Repository
public abstract class BaseDaoImpl implements BaseDao {

	@Autowired
	private MongoOperations mongoTemplate;
	
	@SuppressWarnings("rawtypes")
	public abstract String getCollectionName();

	@SuppressWarnings("rawtypes")
	public abstract Class getEntityClass();

	@Override
	public Object save(Object obj) throws We4UException {
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(obj);
			mongoTemplate.save(json, getCollectionName());
			return Constants.ltc_SUCCESSCODE;
		} catch (JsonGenerationException e) {
			throw new We4UException(e.getMessage());
		} catch (JsonMappingException e) {
			throw new We4UException(e.getMessage());
		} catch (IOException e) {
			throw new We4UException(e.getMessage());
		}
	}

	@Override
	public void update(Update obj, Query searchQuery)
			throws We4UException {
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(obj);
			mongoTemplate.updateFirst(searchQuery, obj, getEntityClass(),
					getCollectionName());
		} catch (JsonGenerationException e) {
			throw new We4UException(e.getMessage());
		} catch (JsonMappingException e) {
			throw new We4UException(e.getMessage());
			
		} catch (IOException e) {
			throw new We4UException(e.getMessage());
			
		}
	}
	
	

	@Override
	public void delete(Query deleteQuery) throws We4UException {
		try{
		mongoTemplate.remove(deleteQuery, getEntityClass(),
				getCollectionName());
		}
		catch(Exception e){
			throw new We4UException(e.getMessage());
		}
	}

	@Override
	public Integer getId(String id) {
		String sequence_collection = "seq"; // the name of the sequence
											// collection
		String sequence_field = "seq"; // the name of the field which holds the
										// sequence
		DBObject query = new BasicDBObject();
		query.put("_id", id); // where _id = the input sequence name

		DBObject change = new BasicDBObject(sequence_field, 1);
		DBObject update = new BasicDBObject("$inc", change);
		DBCollection seq = mongoTemplate.getCollection(sequence_collection);
		DBObject res = seq.findAndModify(query, new BasicDBObject(),
				new BasicDBObject(), false, update, true, true);

		System.out.println("id is"
				+ Integer.parseInt(res.get(sequence_field).toString()));
		return Integer.parseInt(res.get(sequence_field).toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object load(Query searchQuery) throws We4UException {		
		try {
			
			return mongoTemplate.findOne(searchQuery, getEntityClass(),
					getCollectionName());
		} catch (Exception e) {
			throw new We4UException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> loadAll(Query searchQuery) throws We4UException {
		try {
			return mongoTemplate.find(searchQuery, getEntityClass(),
					getCollectionName());
		} catch (Exception e) {
			throw new We4UException(e.getMessage());
		}
	}

}
