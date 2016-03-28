package com.jarvanmo.jlibrary.system;

import android.util.Log;

/**
 * Created by mo on 16-3-26.
 *
 */
public final class SystemSetting {


    public final static class JLogConfig{

        private JLogConfig(){
            throw new AssertionError("No instances.");
        }

        public static String unknownTag = "JLog";
        public static boolean isLoggable = true;
        public static int logLevel = Log.VERBOSE;
    }


    public final static  class CrashHandlerConfig{
        private CrashHandlerConfig(){
            throw new AssertionError("No instances.");
        }

        public static boolean isDefaultHandle = true;

    }

































    private SystemSetting() {
        throw new AssertionError("No instances.");
    }

}
