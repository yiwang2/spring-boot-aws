package com.genesys.application.utils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperUtil {

	private static ModelMapper modelMapper = new ModelMapper();

	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}
	
	public static <S, T> T mapDTO (S source, Class<T> targetClass) {
		return  modelMapper.map(source, targetClass);
	}
}
