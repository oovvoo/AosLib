package com.scode.commlib;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class PrefManager {

    public static final String PREFERENCES_NAME = "PrefManager";
    private static final String DEFAULT_VALUE_STRING = "";
    private static final boolean DEFAULT_VALUE_BOOLEAN = false;
    private static final int DEFAULT_VALUE_INT = -1;
    private static final long DEFAULT_VALUE_LONG = -1L;
    private static final float DEFAULT_VALUE_FLOAT = -1F;



    private static SharedPreferences getPreferences(Context context) {
        String masterKeyAlias = null;
        try {
            masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            return EncryptedSharedPreferences.create(PREFERENCES_NAME,masterKeyAlias,context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        }  catch (Exception e) {
            e.printStackTrace();
            Log.e("PrefManager","EncryptedSharedPrefrences Create Failed!!");
        }
        return null;
    }
    /**
     * String 값 저장
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setString(Context context, String key, String value)  {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Arraylist<String> 값 저장
     * @param context
     * @param key
     * @param list
     */
    public static void setStringArrayList(Context context, String key, ArrayList<String> list)  {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for(int i = 0 ; i< list.size();i++)
            a.put(list.get(i));

        if(!list.isEmpty())
            editor.putString(key,a.toString());
        else
            editor.putString(key,null);

        editor.commit();
    }

    /**
     * 비트맵 이미지 저장
     * @param context
     * @param key
     * @param bitmap
     */
    public static void setBitmap(Context context, String key, Bitmap bitmap)  {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,30,baos);
        byte[] b  = baos.toByteArray();
        String temp = Base64.encodeToString(b,Base64.DEFAULT);
        setString(context,key,temp);
    }

    /**
     * boolean 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setBoolean(Context context, String key, boolean value)  {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    /**
     * int 값 저장
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setInt(Context context, String key, int value)  {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * long 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setLong(Context context, String key, long value)  {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.commit();
    }


    /**
     * float 값 저장
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setFloat(Context context, String key, float value)  {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * Arraylist
     * @param context
     * @param key
     * @return
     */
    public static ArrayList<String> getStringArrayList(Context context,String key)  {
        SharedPreferences prefs = getPreferences(context);
        String json = prefs.getString(key,null );
        ArrayList<String> list = new ArrayList<>();
        if(json != null){
            JSONArray a= null;
            try {
                a = new JSONArray(json);
                for(int i = 0 ; i < a.length();i++)
                    list.add(a.optString(i));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return list;
    }

    /**
     * bitmap
     * @param context
     * @param key
     * @return
     */
    public static Bitmap getBitmap(Context context, String key)  {
        String temp = getString(context,key);
        try {
            byte[] encodeByte = Base64.decode(temp, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch (Exception e)
        {
            return null;
        }

    }

    /**
     * String 값 로드
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key)  {
        SharedPreferences prefs = getPreferences(context);
        String value = prefs.getString(key, DEFAULT_VALUE_STRING);
        return value;
    }


    /**
     * boolean 값 로드
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key)  {
        SharedPreferences prefs = getPreferences(context);
        boolean value = prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN);
        return value;
    }


    /**
     * int 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static int getInt(Context context, String key)  {
        SharedPreferences prefs = getPreferences(context);
        int value = prefs.getInt(key, DEFAULT_VALUE_INT);
        return value;
    }


    /**
     * long 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static long getLong(Context context, String key)  {
        SharedPreferences prefs = getPreferences(context);
        long value = prefs.getLong(key, DEFAULT_VALUE_LONG);
        return value;
    }


    /**
     * float 값 로드
     *
     * @param context
     * @param key
     * @return
     */

    public static float getFloat(Context context, String key)  {
        SharedPreferences prefs = getPreferences(context);
        float value = prefs.getFloat(key, DEFAULT_VALUE_FLOAT);
        return value;
    }


    /**
     * 키 값 삭제
     *
     * @param context
     * @param key
     */

    public static void removeKey(Context context, String key)  {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.remove(key);
        edit.commit();
    }


    /**
     * 모든 저장 데이터 삭제
     *
     * @param context
     */

    public static void clear(Context context)  {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.clear();
        edit.commit();
    }

}
