package com.github.chaoswang.learning.java.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;


public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin){
		int length = origin.length;
		for(int i=0;i<length/2;i++){
			int tmp = origin[i];
			origin[i] = origin[length - 1 - i];
			origin[length - 1 - i] = tmp;
		}
		return origin;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		List<Integer> list = new ArrayList<Integer>();
		for(int value : oldArray){
			if(value != 0){
				list.add(value);
			}
		}
		return returnByIntArray(list);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		TreeSet<Integer> ts1 = new TreeSet<Integer>(Arrays.asList(convertToIntegerArray(array1)));
		TreeSet<Integer> ts2 = new TreeSet<Integer>(Arrays.asList(convertToIntegerArray(array2)));
		ts2.addAll(ts1);
		return  returnByIntArray(ts2);
	}
	
	private static Integer[] convertToIntegerArray(int[] array){
		Integer[] returnArray = new Integer[array.length];
		for(int i=0;i<array.length;i++){
			returnArray[i] = array[i];
		}
		return returnArray;
	}
	
	private static int[] returnByIntArray(Collection<Integer> collection){
		int[] returnArray = new int[collection.size()];
		int i = 0;
		for(Iterator<Integer> it = collection.iterator(); it.hasNext();){
			returnArray[i] = it.next();
			i++;
		}
		return returnArray;
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public static int[] grow(int [] oldArray,  int size){
		int[] returnArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, returnArray, 0, oldArray.length);
		return returnArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		if(max <= 1){
			return new int[0];
		}
		Integer[] init = {1,1};
		LinkedList<Integer> result = new LinkedList<Integer>(Arrays.asList(init));
		for(int tmp = -1; tmp <= max;){
			tmp = generateFibonacci(result);
		}
		result.removeLast();
		return returnByIntArray(result);
	}
	
	private static int generateFibonacci(LinkedList<Integer> result){
		int a = result.getLast();
		int b = result.get(result.size()-2);
		result.add(a + b);
		return result.getLast();
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		List<Integer> list = new LinkedList<Integer>();
		for(int i=2;i<max;i++){
			if(isPrime(i)){
				list.add(i);
			}
		}
		return returnByIntArray(list);
	}
	
	private static boolean isPrime(int number) {
		for(int i=2;i<number;i++){
			if(number%i == 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		List<Integer> list = new LinkedList<Integer>();
		for(int i=2;i<=max;i++){
			if(isPerfectNumber(i)){
				list.add(i);
			}
		}
		return returnByIntArray(list);
	}
	
	private static boolean isPerfectNumber(int number) {
		int sum = 0;
		for(int i=1;i<number;i++){
			if(number%i == 0){
				sum += i;
			}
		}
		
		if(sum == number){
			return true;
		}
		return false;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator){
		StringBuffer sb = new StringBuffer();
		for(int number : array){
			sb.append(number);
			sb.append(seperator);
		}
		String returnStr = sb.toString();
		return returnStr.substring(0, returnStr.length() - seperator.length());
	}
	

}