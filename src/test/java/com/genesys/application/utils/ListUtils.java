package com.genesys.application.utils;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {

	
	public static boolean ifTwoIntegerListsAreIdentical (List<Integer> list1, List<Integer> list2) {
		
		return list1.size() == list2.size() && list1.stream()
	            .filter(id -> !list2.contains(id))
	            .collect(Collectors.toList()).size() == 0;
	}
	
    public static boolean ifTwoStringListsAreIdentical (List<String> list1, List<String> list2) {
		
		return list1.size() == list2.size() && list1.stream()
	            .filter(id -> !list2.contains(id))
	            .collect(Collectors.toList()).size() == 0;
	}
}
