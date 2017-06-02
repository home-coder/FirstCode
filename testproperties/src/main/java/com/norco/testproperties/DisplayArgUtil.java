package com.norco.testproperties;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by java on 16-11-8.
 */
public class DisplayArgUtil {
        public static String getBootInitPath()
        {
            return "/home/java/opt/kk/build/Release/boot.ini";
            //PropertyReaderUtil.getValue("/home/java/opt/kk/build/Release/boot.ini", "bootpath", true);
        }

        public static boolean setDisplayArg(String displayArgKey, String displayArg)
        {
            if (isStrTrimNotNull(displayArgKey) && isStrTrimNotNull(displayArg))
            {
                String bootargs = getBootargs();
                if (isStrTrimNotNull(bootargs))
                {
                    String oldDisplayArg = getDisplayArg(displayArgKey, bootargs);
                    if (isStrTrimNotNull(oldDisplayArg))
                    {
                        bootargs = bootargs.replaceAll(oldDisplayArg, displayArg.trim());
                        PropertyReaderUtil.updateValue(getBootInitPath(), "bootargs", bootargs);
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean setDisplayArg(String displayArg)
        {
            if (isStrTrimNotNull(displayArg))
            {
                String displayArgKey = getOnlyKey(displayArg.trim());
                return setDisplayArg(displayArgKey, displayArg);
            }
            return false;
        }

        public static String getDisplayArg(String displayArgKey, String bootargs)
        {
            Map<String,String> displayArgMap = getDisplayArgMap(bootargs);
            if (displayArgMap != null)
            {
                return displayArgMap.get(displayArgKey.trim());
            }
            return null;
        }

        public static String getDisplayArg(String displayArgKey)
        {
            Map<String,String> displayArgMap = getDisplayArgMap(getBootargs());
            if (displayArgMap != null)
            {
                return displayArgMap.get(displayArgKey);
            }
            return null;
        }

        public static String getBootargs()
        {
            String bootpath = getBootInitPath();
            Log.e("bootargs", "======" + bootpath);
            if (isStrTrimNotNull(bootpath))
            {
                Log.e("bootargs", "======");
                String bootargs = PropertyReaderUtil.getValue(bootpath, "bootargs", true);
                Log.e("bootargs", "=2=====");
                if(bootargs == null)
                    Log.e("bootargs", "=3=====");
                return bootargs;
            }
            return null;
        }

        public static Map<String,String> getDisplayArgMap(String bootargs)
        {
            if (isStrTrimNotNull(bootargs))
            {
                String videoArgs = bootargs.substring(bootargs.indexOf("video"));
                if (isStrTrimNotNull(videoArgs))
                {
                    Map<String,String> displayArgMap = new HashMap<String,String>();
                    List<String> videoArgList = new ArrayList<String>();
                    while (videoArgs.indexOf("video") != -1)
                    {
                        int lastIndex = videoArgs.lastIndexOf("video");
                        String videoArg = videoArgs.substring(lastIndex);
                        videoArgs = videoArgs.substring(0, lastIndex);
                        videoArgList.add(videoArg.trim());
                    }
                    for (String videoArg : videoArgList)
                    {
                        if (isStrTrimNotNull(videoArg))
                        {
                            String key = getOnlyKey(videoArg);
                            if (isStrTrimNotNull(key))
                            {
                                displayArgMap.put(key, videoArg);
                            }
                        }
                    }
                    return displayArgMap;
                }
            }
            return null;
        }

        public static String getOnlyKey(String videoArg)
        {
            if (isStrTrimNotNull(videoArg))
            {
                return videoArg.substring(videoArg.indexOf("=")+1, videoArg.indexOf(":"));
            }
            return null;
        }

        public static boolean isStrTrimNotNull(String str)
        {
            return str != null && str.trim().length() > 0;
        }
/*        public static void main(String[] args)
        {
            String bootpath = getBootInitPath();
            System.out.println("boot.init文件地址：" + bootpath);
            System.out.println("**************分隔线***************");
            String bootargs = getBootargs();
            System.out.println(bootargs);
            System.out.println("**************分隔线***************");
            String mxcfb0 = getDisplayArg("mxcfb0", bootargs);
            System.out.println(mxcfb0);
            System.out.println("**************分隔线***************");
            mxcfb0 = getDisplayArg("mxcfb0");
            System.out.println(mxcfb0);
            System.out.println("**************分隔线***************");
            bootargs = getBootargs();
            System.out.println(bootargs);
            setDisplayArg("mxcfb0", "video=mxcfb0:dev=lcd,810x480M@120,if=RGB48,bpp=33");
            System.out.println(getBootargs());
            System.out.println("**************分隔线***************");
            setDisplayArg("video=mxcfb0:dev=lcd11,8122x4833M@123,if=RGB48,bpp=33");
            System.out.println(getBootargs());
        }*/
}
