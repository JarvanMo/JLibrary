package com.jarvanmo.jlibrary.system;

import android.util.Log;

/**
 * Created by mo on 16-3-26.
 *
 */
public class SystemSetting {


    public final static class JLogConfig{

        private JLogConfig(){
            throw new AssertionError("No instances.");
        }

        public static String unknownTag = "JLog";
        public static boolean isLoggable = true;
        public static int logLevel = Log.ASSERT;
    }


































    private SystemSetting() {
        throw new AssertionError("No instances.");
    }

}
