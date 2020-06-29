package id.fajarproject.training.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Create by Fajar Adi Prasetyo on 28/06/2020.
 */
public class AppPreference {

    public static void writeToPreference(Context context, String prefName, int prefValue) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(prefName, prefValue);
        editor.apply();
    }

    public static void writeToPreference(Context context, String prefName, String prefValue) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(prefName, prefValue);
        editor.apply();
    }

    public static void writeToPreference(Context context, String prefName, long prefValue) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(prefName, prefValue);
        editor.apply();
    }

    public static String getStringPreferenceByName(Context context, String prefName) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPref.getString(prefName, "");
    }

    public static int getIntPreferenceByName(Context context, String prefName) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPref.getInt(prefName, 0);
    }

    public static long getLongPreferenceByName(Context context, String prefName) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPref.getLong(prefName, -1);
    }
}
