package com.jarvanmo.jlibrary.util.file;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.File;

/**
 * Created by mo on 15-11-26.
 *
 *
 */
public class CacheFileUtil {


    public static String getBasePackagePath(Context context) {



        String rootPath ;



        if (FileUtil.isSDCardEnable()) {
            rootPath = FileUtil.getSDCardPath_M1();
        } else {
            rootPath = FileUtil.getRootDirectoryPath();
        }


        StringBuilder desPath = null;
        try {


            PackageInfo info = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String packageName = info.packageName;

//            String[] result = packageName.split('.');

            packageName = packageName.replace('.', '/');

            StringBuilder sb = new StringBuilder(packageName);
            sb.append(File.separator);
            sb.insert(0, "/");
            desPath = sb;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (desPath == null) {
                desPath = new StringBuilder();
                desPath.append("/jarvanmo/unknown");
            }

        }



        return rootPath + desPath.toString();

    }

    public static String getBaseCachePath(Context context) {
        return getBasePackagePath(context) + "/caches" ;
    }


    public static String getImageCachePath(Context context) {
        return getBaseCachePath(context) + "/images";
    }

    public static File getImageCacheDirectory(Context context){

        return createDirectory(getImageCachePath(context));
    }

    public static String getCameraCachePath(Context context){
        return getBasePackagePath(context) + "/camera" ;
    }

    public static File getCameraCachePathDirectory(Context context){
        return createDirectory(getCameraCachePath(context));
    }

    public static String getUpdateCachePath(Context context){
        return getBasePackagePath(context) + "/update" ;
    }

    public static File getUpdateCacheDirectory(Context context){
        return createDirectory(getUpdateCachePath(context)) ;
    }

    public static String getCrashCachePath(Context context){
        return getBasePackagePath(context) + "/crash" ;
    }

    public static File getCrashCacheDirectory(Context context){
        return createDirectory(getCrashCachePath(context)) ;
    }


    private static File createDirectory(String path){

        File f = new File(path);

        if(!f.exists()){
            if(!f.mkdirs()){
                return  null;
            }
        }

        return f;

    }

}
