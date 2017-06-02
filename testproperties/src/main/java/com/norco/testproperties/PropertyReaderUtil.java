package com.norco.testproperties;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by java on 16-11-8.
 */
public class PropertyReaderUtil {
    //缓存列表
    private static Hashtable<String,Properties>	pptContainer	= new Hashtable<String,Properties>();

    /**
     * 获得属性
     * @param propertyFilePath
     * @param key
     * @return
     */
    public final static String getValue(String propertyFilePath, String key)
    {
        Properties ppts = getProperties(propertyFilePath);
        return ppts == null ? null : ppts.getProperty(key);
    }

    /**
     * 获得属性文件中Key所对应的值
     * @param propertyFilePath
     * @param key
     * @param isAbsolutePath 绝对路径
     * @return
     */
    public final static String getValue(String propertyFilePath, String key, boolean isAbsolutePath)
    {
        if (isAbsolutePath)
        {
            Properties ppts = getPropertiesByFs(propertyFilePath);
            return ppts == null ? null : ppts.getProperty(key);
        }
        return getValue(propertyFilePath, key);
    }


    /**
     * 获得属性文件的属性
     * @param propertyFilePath
     * @return
     */
    public final static Properties getProperties(String propertyFilePath)
    {
        if (propertyFilePath == null)
        {
            return null;
        }
        Properties ppts = pptContainer.get(propertyFilePath);
        if (ppts == null)
        {

            ppts = loadPropertyFile(propertyFilePath);
            if (ppts != null)
            {
                pptContainer.put(propertyFilePath, ppts);
            }
        }
        return ppts;
    }

    /**
     * 获得属性文件的属性
     * @param propertyFilePath
     * @return
     */
    public final static Properties getPropertiesByFs(String propertyFilePath)
    {
        if (propertyFilePath == null)
        {
            return null;
        }
        Log.e("--------", "====" + propertyFilePath);
        Properties ppts = pptContainer.get(propertyFilePath);
        if (ppts == null)
        {
            ppts = loadPropertyFileByFileSystem(propertyFilePath);
            if (ppts != null)
            {
                pptContainer.put(propertyFilePath, ppts);
            }
        }
        return ppts;
    }


    /**
     * 加载属性
     * @param propertyFilePath
     * @return
     */
    private static Properties loadPropertyFile(String propertyFilePath)
    {
        java.io.InputStream is = PropertyReaderUtil.class.getResourceAsStream(propertyFilePath);
        if (is == null)
        {
            return loadPropertyFileByFileSystem(propertyFilePath);
        }
        Properties ppts = new Properties();
        try
        {
            ppts.load(is);
            return ppts;
        } catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 从文件系统加载属性文件
     * @param propertyFilePath
     * @return
     */
    private static Properties loadPropertyFileByFileSystem(final String propertyFilePath)
    {
        InputStream streamReader = null;   //文件输入流
        try
        {
            Properties ppts = new Properties();
            Log.e("--------", "====" + propertyFilePath);
           // ppts.load(new java.io.FileInputStream(propertyFilePath));
            //new java.io.FileInputStream(propertyFilePath);

            streamReader = new FileInputStream(new File("/home/java/opt/kk/build/Release/aaa"));
            return ppts;
        } catch (FileNotFoundException e)
        {
            Log.e("=====", "ai.....");
            return null;
        } catch (IOException e)
        {
            Log.e("=====", "ya.....");
            return null;
        }
    }


    /**
     * 对存在的属性文件中添加键值对并保存
     * @param propertyFilePath
     * @param htKeyValue
     * @return
     */
    public final static boolean setValueAndStore(String propertyFilePath, Hashtable<String,String> htKeyValue)
    {
        return setValueAndStore(propertyFilePath, htKeyValue, null);
    }


    /**
     * 对存在的属性文件中添加键值对并保存
     * @param propertyFilePath
     * @param htKeyValue
     * @param storeMsg
     * @return
     */
    public final static boolean setValueAndStore(String propertyFilePath, Hashtable<String,String> htKeyValue, String storeMsg)
    {
        Properties ppts = getProperties(propertyFilePath);
        if (ppts == null || htKeyValue == null)
        {
            return false;
        }
        ppts.putAll(htKeyValue);
        java.io.OutputStream stream = null;
        try
        {
            stream = new java.io.FileOutputStream(propertyFilePath);
        } catch (FileNotFoundException e)
        {
            String path = PropertyReaderUtil.class.getResource(propertyFilePath).getPath();
            try
            {
                stream = new java.io.FileOutputStream(path);
            } catch (FileNotFoundException e1)
            {
                return false;
            }
        }
        if (stream == null)
        {
            return false;
        }
        try
        {
            ppts.store(stream, storeMsg != null ? storeMsg : "set value and store.");
            return true;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            if (stream != null)
            {
                try
                {
                    stream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 创建属性文件
     * @param propertyFilePath
     * @param htKeyValue
     * @return
     */
    public final static boolean createPropertiesFile(String propertyFilePath, Hashtable<String,String> htKeyValue)
    {
        java.io.File file = new java.io.File(propertyFilePath);
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return setValueAndStore(propertyFilePath, htKeyValue, "create properties file:" + file.getName());
    }


    /**
     * 设置属性值
     * @param propertyFilePath
     * @param key
     * @param value
     * @return
     */
    public final static boolean setValue(String propertyFilePath, String key, String value)
    {
        Properties ppts = getProperties(propertyFilePath);
        if (ppts == null)
        {
            return false;
        }
        ppts.put(key, value);
        return true;
    }


    /**
     * 保存属性文件对象
     * @param properties
     * @param propertyFilePath
     * @param msg
     */
    public final static void store(Properties properties, String propertyFilePath, String msg)
    {
        try
        {
            java.io.OutputStream stream = new java.io.FileOutputStream(propertyFilePath);
            properties.store(stream, msg);
        } catch (FileNotFoundException e)
        {
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 删除属性值
     * @param propertyFilePath
     * @param key
     * @return
     */
    public final static String removeValue(String propertyFilePath, String key)
    {
        Properties ppts = getProperties(propertyFilePath);
        if (ppts == null)
        {
            return null;
        }
        return (String) ppts.remove(key);
    }


    /**
     * 删除属性文件中的Key数组所对应的键值对
     * @param propertyFilePath
     * @param key
     * @return
     */
    public final static Properties removeValue(String propertyFilePath, String[] key)
    {
        if (key == null)
        {
            return null;
        }
        Properties ppts = getProperties(propertyFilePath);
        if (ppts == null)
        {
            return null;
        }
        for (String strKey : key)
        {
            ppts.remove(strKey);
        }
        return ppts;
    }


    /**
     * 删除属性文件中的Key数组所对应的键值对，并将属性文件对象持久化（即保存）
     * @param propertyFilePath
     * @param key
     * @return
     */
    public final static boolean removeValueAndStore(String propertyFilePath, String[] key)
    {
        Properties ppts = removeValue(propertyFilePath, key);
        if (ppts == null)
        {
            return false;
        }
        store(ppts, propertyFilePath, "batch remove key value!");
        return true;
    }


    /**
     * 更新指定路径的属性文件中的键所对应的值
     * @param propertyFilePath
     * @param key
     * @param newValue
     * @return
     */
    public final static boolean updateValue(String propertyFilePath, String key, String newValue)
    {
        if (key == null || newValue == null)
        {
            return false;
        }
        Hashtable<String,String> ht = new Hashtable<String,String>();
        ht.put(key, newValue);
        return setValueAndStore(propertyFilePath, ht, "update " + key + "'s value!");
    }


    /**
     * 批量更新指定路径的属性文件中的键所对应的值
     * @param propertyFilePath
     * @param htKeyValue
     * @return
     */
    public final static boolean batchUpdateValue(String propertyFilePath, Hashtable<String,String> htKeyValue)
    {
        if (propertyFilePath == null || htKeyValue == null)
        {
            return false;
        }
        return setValueAndStore(propertyFilePath, htKeyValue, "batch update key value!");
    }


    /**
     * 移除加载的属性文件
     * @param propertyFilePath
     * @return
     */
    public final static Properties removePropertyFile(String propertyFilePath)
    {
        return pptContainer.remove(propertyFilePath);
    }


    /**
     * 重新加载某个Property文件
     * @param propertyFilePath
     */
    public final static void reloadPropertyFile(String propertyFilePath)
    {
        pptContainer.remove(propertyFilePath);
        loadPropertyFile(propertyFilePath);
    }


    /**
     * 获得属性文件的路径
     * @param pkg
     * @param propertyFileName
     * @return
     */
    public final static String getPpropertyFilePath(String pkg, String propertyFileName)
    {
        pkg = pkg == null ? "" : pkg.replaceAll("\\.", "/");
        propertyFileName = propertyFileName.endsWith(".properties") ? propertyFileName : (propertyFileName + ".properties");
        return "/" + pkg + "/" + propertyFileName;
    }

/*
    public static void main(String[] args)
    {
        String path = "/config/jdbc.properties";
        String v = PropertyReaderUtil.getValue(path, "jdbc.driverClassName");
        Hashtable<String,String> ht = new Hashtable<String,String>();
        ht.put("name", "dengcd");
        PropertyReaderUtil.setValueAndStore(path, ht);
        String v_ = PropertyReaderUtil.getValue(path, "name");
        PropertyReaderUtil.reloadPropertyFile(path);
        String v2_ = PropertyReaderUtil.getValue(path, "name");
    }*/
}
