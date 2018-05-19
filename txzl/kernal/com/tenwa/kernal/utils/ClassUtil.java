package com.tenwa.kernal.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;

/**
 * 类相关的工具类
 * 
 * 
 * 
 */
public class ClassUtil {
	private static final Log log = LogFactory.getLog(ClassUtil.class);

	/*
	 * 取得某一类所在包的所有类名 不含迭代
	 */
	public static String[] getPackageAllClassName(String classLocation, String packageName) {
		// 将packageName分解
		String[] packagePathSplit = packageName.split("[.]");
		String realClassLocation = classLocation;
		int packageLength = packagePathSplit.length;
		for (int i = 0; i < packageLength; i++) {
			realClassLocation = realClassLocation + File.separator + packagePathSplit[i];
		}
		File packeageDir = new File(realClassLocation);
		if (packeageDir.isDirectory()) {
			String[] allClassName = packeageDir.list();
			return allClassName;
		}
		return null;
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static Set<Class<?>> getClasses(Package pack) {

		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

		boolean recursive = true;

		String packageName = pack.getName();
		String packageDirName = packageName.replace('.', '/');

		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					JarFile jar;
					try {
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						Enumeration<JarEntry> entries = jar.entries();
						while (entries.hasMoreElements()) {
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							if (name.charAt(0) == '/') {
								name = name.substring(1);
							}

							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								if (idx != -1) {
									packageName = name.substring(0, idx).replace('/', '.');
								}

								if ((idx != -1) || recursive) {
									if (name.endsWith(".class") && !entry.isDirectory()) {
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											log.error("", e);
										}
									}
								}
							}
						}
					} catch (IOException e) {
						log.error("", e);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {

		File dir = new File(packagePath);

		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}

		File[] dirfiles = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});

		for (File file : dirfiles) {

			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
			} else {

				String className = file.getName().substring(0, file.getName().length() - 6);
				try {

					classes.add(Class.forName(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					log.error("", e);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className, Object args[]) throws Exception {
		Class<?> param[] = null;
		if (param == null && args != null) {
			param = new Class[args.length];
			for (int i = 0; i < param.length; i++) {
				if (args[i] != null) {
					param[i] = args[i].getClass();
				} else {
					param[i] = null;
				}
			}
		}
		Constructor<T> c = null;
		T instance = null;
		c = (Constructor<T>) Class.forName(className).getConstructor(param);
		if (c != null) {
			instance = (T) c.newInstance(args);
		}
		return instance;

	}
	
	public static String getPackageClassNames( String[] packageNames){
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for(String packageName : packageNames){
			classes.addAll(FileUtil.getClasses(packageName)) ;
		}
		JSONArray classNames = new JSONArray();
		for(Class<?> clazz : classes){
			JSONObject object = new JSONObject();
			object.put("text", clazz.getSimpleName());
			object.put("value", clazz.getName());
			classNames.put(object);
		}
		return classNames.toString();
	}
	
	public static String getAllEnumsAndBase(String[] packageNames){
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for(String packageName : packageNames){
			classes.addAll(FileUtil.getClasses(packageName)) ;
		}
		JSONArray classNames = new JSONArray();
		for(Class<?> clazz : classes){
			if(clazz.isEnum()){
				JSONObject object = new JSONObject();
				object.put("text", clazz.getSimpleName());
				object.put("value", clazz.getName());
				classNames.put(object);
			}
		}
		return classNames.toString();
	}
}