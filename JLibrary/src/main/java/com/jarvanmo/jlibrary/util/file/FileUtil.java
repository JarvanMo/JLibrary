package com.jarvanmo.jlibrary.util.file;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
    /**
     * 获取 raw, drawable 等文件夹中的文件的输入流
     *
     * @param context  上下文
     * @param rawResId R.raw.certain_db, R.drawable.ic_launcher
     * @return 文件的输入流
     */
    public static InputStream getInputStream(Context context, int rawResId) {
        InputStream is = context.getResources().openRawResource(rawResId);
        return is;
    }

    /**
     * 获取 assets 文件夹中的文件的输入流
     *
     * @param context    上下文
     * @param assetsPath "db/certain_db.sqlite"
     * @return 文件的输入流
     * @throws IOException
     */
    public static InputStream getInputStream(Context context, String assetsPath) throws IOException {
        InputStream is = context.getResources().getAssets().open(assetsPath);
        return is;
    }

    /**
     * 基础文件夹 <br />
     * 存在外置存储卡: /SdCard/Soft2T/com.soft2t.imk2tbaseframework/<br />
     * 或者<br />
     * 没有外置存储卡: /data/data/com.soft2t.imk2tbaseframework/files/<br />
     *
     * @return 保存文件的路径前缀
     */
    public static String getBaseSavePath(Context context) {
        String baseSavePath;

        if (isSDCardEnable()) {
            baseSavePath = getSDCardPath_M1();
        } else {
            baseSavePath = context.getFilesDir().getAbsolutePath();
        }

        return baseSavePath;
    }

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取系统(内部)存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 获取内置SD卡(sdcrad0)路径
     *
     * @return
     */
    public static String getSDCardPath_M1() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取内置SD卡(sdcrad0)路径
     *
     * @return
     */
    @Deprecated
    public static String getSDCardPath_M2() {
        final String[] pathArray = new String[]{"/storage/sdcard0", "/mnt/sdcard0", "/mnt/sdcard"};

        // String sdCard_1 = recursion_M1(pathArray, 0);

        String sdCard_1 = recursion_M2(pathArray);

        return sdCard_1;
    }

    /**
     * 获取外置SD卡(sdcrad1)路径
     *
     * @return
     */
    public static String getSDCard2Path_M1() {
        String sdcard_path = null;
        String sd_default = Environment.getExternalStorageDirectory().getAbsolutePath();
        // Log.d("text", sd_default);

        if (sd_default.endsWith("/")) {
            sd_default = sd_default.substring(0, sd_default.length() - 1);
        }

        // 得到路径
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("secure")) {
                    continue;
                }

                if (line.contains("asec")) {
                    continue;
                }

                if (line.contains("fat") && line.contains("/mnt/")) {
                    String columns[] = line.split(" ");

                    if (columns != null && columns.length > 1) {
                        if (sd_default.trim().equals(columns[1].trim())) {
                            continue;
                        }

                        sdcard_path = columns[1];
                    }
                } else if (line.contains("fuse") && line.contains("/mnt/")) {
                    String columns[] = line.split(" ");

                    if (columns != null && columns.length > 1) {
                        if (sd_default.trim().equals(columns[1].trim())) {
                            continue;
                        }

                        sdcard_path = columns[1];
                    }
                }
            }

            is.close();
            isr.close();
            br.close();
            proc.destroy();// FIXME
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Log.d("text", sdcard_path);
        return sdcard_path;
    }

    /**
     * 获取外置SD卡(sdcrad1)路径
     *
     * @return
     */
    public static String getSDCard2Path_M2() {
        final String[] pathArray = new String[]{"/storage/sdcard1", "/storage/usbcard1/", //
                "/mnt/extSdCard/", "/mnt/external_sd/", "/mnt/sdcard-ext", "/mnt/sdcard1", "/mnt/sdcard2"};

        // String sdCard_2 = recursion_M1(pathArray, 0);

        String sdCard_2 = recursion_M2(pathArray);

        return sdCard_2;
    }

    public static String recursion_M1(String[] pathArray, int currentIndex) {

        if (pathArray == null || pathArray.length <= currentIndex) {
            return "";
        }

        String tempPath = pathArray[currentIndex];

        File fileTemp = new File(tempPath);

        if (fileTemp.exists()) {
            return tempPath;
        } else {
            return recursion_M1(pathArray, ++currentIndex);
        }
    }

    public static String recursion_M2(String[] pathArray) {

        String result = "";

        if (pathArray != null) {
            int size = pathArray.length;

            for (int i = 0; i < size; i++) {
                String tempPath = pathArray[i];

                File fileTemp = new File(tempPath);

                if (fileTemp.exists()) {
                    return tempPath;
                }
            }
        }

        return result;
    }

    /**
     * 获取 指定路径 所在空间的 剩余可用容量 字节数, 单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间, 内部存储可用空间
     */
    @SuppressWarnings("deprecation")
    public static long getFreeBytes(String filePath) {
        StatFs stat = new StatFs(filePath);

        // 获取空闲的数据块的数量
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;

        // 获取单个数据块的大小(byte)
        long blockSize = stat.getBlockSize();

        return blockSize * availableBlocks;
    }

    /**
     * 把字节数组保存为一个文件
     *
     * @param b
     * @param outputFile
     * @return
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        File ret = null;
        BufferedOutputStream stream = null;
        try {
            ret = new File(outputFile);
            if (!ret.exists()) {
                ret.mkdirs();
            }
            FileOutputStream fstream = new FileOutputStream(ret);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            // log.error("helper:get file from byte process error!");
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // log.error("helper:get file from byte process error!");
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
}
