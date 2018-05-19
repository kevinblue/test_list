package com.tenwa.kernal.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author tracywindy 2012-3-28 上午09:40:17 email:tracywindy@126.com
 * 
 */
public class ArrayUtil {
	public static int[] strArray2intArray(String... arr) {
		int[] intArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			intArr[i] = Integer.parseInt(arr[i]);
		}
		return intArr;
	}
}
