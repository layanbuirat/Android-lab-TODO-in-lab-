package edu.birzeit.todo3;
import android.content.Context;
import android.content.SharedPreferences;
public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "StudentProfilePref";
    private static final int SHARED_PREF_PRIVATE = Context.MODE_PRIVATE;
    private static SharedPrefManager ourInstance = null;
    private static SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;
    // Keys for profile data
    public static final String KEY_FULL_NAME = "full_name";
    public static final String KEY_STUDENT_ID = "student_id";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_DEPARTMENT = "department";
    public static final String KEY_YEAR = "year";

    public static SharedPrefManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new SharedPrefManager(context);
        }
        return ourInstance;
    }

    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, SHARED_PREF_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean writeString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    public String readString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public boolean clearProfile() {
        editor.remove(KEY_FULL_NAME);
        editor.remove(KEY_STUDENT_ID);
        editor.remove(KEY_EMAIL);
        editor.remove(KEY_PHONE);
        editor.remove(KEY_DEPARTMENT);
        editor.remove(KEY_YEAR);
        return editor.commit();
    }
}
