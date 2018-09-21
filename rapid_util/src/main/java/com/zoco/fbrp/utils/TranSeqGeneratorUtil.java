package com.zoco.fbrp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class TranSeqGeneratorUtil {

	public static String generateTranseq(String gysdm, String clientNo, String swjgdm) {
		// TODO Auto-generated method stub
		// 生产三方接入需要的交易流水号
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String transeq = "";
		String datestr = formatter.format(new Date());
		if (swjgdm == null || "".equals(swjgdm) || swjgdm.length() < 5) {
			swjgdm = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase(Locale.ENGLISH).substring(0, 5);
		} else {
			swjgdm = swjgdm.substring(0, 5);
		}
		String perfix = "";
		if (clientNo == null || "".equals(clientNo) || clientNo.length() != 11) {
			perfix = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase(Locale.ENGLISH).substring(0, 11);
		} else {
			perfix = clientNo;
		}
		transeq = gysdm + perfix + swjgdm + datestr + generateShortUuid();
		return transeq;
	}

	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };

	public static void main(String[] args) {
		System.out.println(generateTranseq("frse", "99999999999", "14400000000"));
	}

}
