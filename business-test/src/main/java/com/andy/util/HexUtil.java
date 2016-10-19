package com.andy.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 进制转换
 * 
 * 二进制 Binary system 八进制 Octal number system 十进制 Decimal system 十六进制 Hexadecimal
 * 
 * @author CNkongfu
 * @date 2016年9月28日
 */
public class HexUtil {

	/**
	 * 二进制到八进制
	 */
	public static void binaryToDecimal(int data) {

	}

	/**
	 * 二进制到十六进制
	 */
	public static void binaryToHexadecimal(int data) {

	}

	/**
	 * 八进制到二进制
	 */
	public static void octalToBinary(int data) {

	}

	/**
	 * 八进制到十六进制
	 */
	public static void octalToHexadecimal(int data) {

	}

	/**
	 * 十六进制到二进制
	 */
	public static void hexadecimalToBinary(int data) {

	}

	/**
	 * 十六进制到八进制
	 */
	public static void hexadecimalToOctal(int data) {

	}

	/**
	 * k进制到十进制
	 * 
	 * @param k进制
	 * 
	 */
	public static void kToDecimal(int k, int data) {

	}

	/**
	 * 十进制到k进制
	 * 
	 * @param k
	 *            k进制
	 * @param data
	 *            十进制数
	 * 
	 */
	public static String decimalToK(int k, int data) {
		List<Integer> result = new ArrayList<Integer>();
		int yu = -1;
		StringBuilder sb = new StringBuilder();
		do {
			yu = data % k;
			data = data / k;
			result.add(yu);
		} while (data > 0);

		for (int i = result.size() - 1; i >= 0; i--) {
			sb.append(result.get(i));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// 十进制到k进制
		System.out.println(decimalToK(2, 1009));
		System.out.println(10 / 2);
	}

}
