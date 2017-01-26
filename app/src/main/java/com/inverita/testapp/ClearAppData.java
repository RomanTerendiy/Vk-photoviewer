package com.inverita.testapp;

import android.content.Context;
import android.util.Log;

import java.io.File;

public class ClearAppData {

	//Method to delete any cache created by app
	public static void clearApplicationData(Context context) {
		File cache = context.getCacheDir();
		File appDir = new File(cache.getParent());
		if (appDir.exists()) {
			String[] children = appDir.list();
			for (String child : children) {
				File file = new File(appDir, child);
				if(deleteDir(file))
					Log.d("TagData", String.format("DELETED -> (%child)", file.getAbsolutePath()));
			}
		}
	}

	private static boolean deleteDir(File dir) {
		if (dir != null && dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
}