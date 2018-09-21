package com.zoco.swy.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author maipeitian
 * 
 */
public class SerializeUtil {
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		// 序列化
		baos = new ByteArrayOutputStream();
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("=========序列化session信息出错，请确保存如session中的数据都是可序列化的！=======", e);
		}
		byte[] bytes = baos.toByteArray();
		return bytes;
	}

	public static Object unserialize(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		ByteArrayInputStream bais = null;
		// 反序列化
		bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(bais);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("=========反序列化session信息出错，请确保存如session中的数据都是可序列化的！=======", e);
		}
		try {
			return ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("=========序列化session信息出错，请确保存如session中的数据都是可序列化的！=======", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("=========序列化session信息出错，请确保存如session中的数据都是可序列化的！=======", e);
		}
	}
}