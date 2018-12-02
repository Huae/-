package com.example.bestbroadcastpratice;

import java.util.ArrayList;

import android.app.Activity;

public class ActivityController {
	private static ArrayList<Activity> activites = new ArrayList<Activity>();

	static {
	}

	public static void addActivity(Activity activity) {
		activites.add(activity);
	}

	public static void removeActivity(Activity activity) {
		activites.remove(activity);
	}

	public static void finishAll() {
		for (Activity activity : activites) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}
}
