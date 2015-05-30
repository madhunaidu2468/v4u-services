package com.mobifever.we4u.utils;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class DozerListMapping {

	public DozerListMapping() {
		// TODO Auto-generated constructor stub
	}
public static<S,D> List<D> mapList(List<S> source, Class<D> destType){
	
	List<D> destList=new ArrayList<D>();
	Mapper mapper=new DozerBeanMapper();
	for(S element: source){
		destList.add(mapper.map(element, destType));
	}
	return destList;
}

public static<S,D> D map(S source, Class<D> dest){
	Mapper mapper=new DozerBeanMapper();
	return mapper.map(source, dest);
}
}
