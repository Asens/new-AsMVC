package cn.asens.utils;

import org.junit.Test;

import java.io.File;

/**
 * Created by lenovo on 2016/5/16.
 */
public class StringUtils {
    public static String modifyPackagePath(String packageName)
    {
        if(packageName.indexOf(".")>-1)
            return packageName.replace(".", File.separator);
        else
            return packageName;
    }


}
