package cn.asens.classcollection;

import cn.asens.annotation.Controller;
import cn.asens.annotation.MapURL;
import cn.asens.config.Config;
import cn.asens.structure.MethodPro;
import cn.asens.utils.FileUtils;
import cn.asens.utils.StringUtils;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lenovo on 2016/5/16.
 */
public class ClassCollection {
    public static Map<String,MethodPro> methodMap;
    public static Set<Class<?>> classSet;
    public static Map<String,Class<?>> classMap;
    public static void  scanClassSetByPackage(String packageName)
    {
        methodMap=new HashMap<String, MethodPro>();
        classMap=new HashMap<String, Class<?>>();
        classSet=new HashSet<Class<?>>();
        String filePath=Config.getProPath()+ StringUtils.modifyPackagePath(packageName);
        FileUtils.getClassSet(filePath,classSet,packageName);
        for(Class<?> clazz:classSet)
        {
            if(clazz.isAnnotationPresent(Controller.class))
            {
                Method[] methods=clazz.getDeclaredMethods();
                for(Method method:methods)
                {
                    if(method.isAnnotationPresent(MapURL.class))
                    {
                        MapURL mapURL=method.getAnnotation(MapURL.class);
                        MethodPro mp=new MethodPro(method,mapURL.value(),mapURL.method());
                        methodMap.put(mapURL.value(),mp);
                        classMap.put(mapURL.value(),clazz);
                    }
                }
            }
        }
     }

    public static Set<Class<?>> getClassSet() {
        return classSet;
    }



    public static Map<String, MethodPro> getMethodMap() {
        return methodMap;
    }

    public static Map<String, Class<?>> getClassMap() {
        return classMap;
    }


}
