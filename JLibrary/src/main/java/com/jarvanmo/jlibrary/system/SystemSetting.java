package com.jarvanmo.jlibrary.system;

import android.util.Log;

import com.jarvanmo.jlibrary.R;

import java.util.HashSet;
import java.util.Set;

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



    public final static class  SystemExitConfig{

        public static Set<String> exitNames = new HashSet<>();
        public static String defaultExitName = "MainActivity";
        public static int exitNotice = R.string.system_exit_notice;
        public static Set<Runnable> doTheseBeforeExit = new HashSet<>();

    }






























    private SystemSetting() {
        throw new AssertionError("No instances.");
    }

}
