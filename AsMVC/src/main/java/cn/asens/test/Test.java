package cn.asens.test;

import cn.asens.annotation.Controller;
import cn.asens.annotation.MapURL;
import cn.asens.classcollection.ClassCollection;
import cn.asens.config.Config;
import cn.asens.structure.MethodPro;
import cn.asens.test.Anno;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by lenovo on 2016/5/15.
 */
public class Test {
   // @org.junit.Test
    public void main() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Anno anno=new Anno();
        Class clazz=anno.getClass();
        ClassLoader cl=Thread.currentThread().getContextClassLoader();
        Class<?> cls=Class.forName("cn.asens.test.Anno",true,cl);

        Method method=clazz.getMethod("mm");
        if(method.isAnnotationPresent(MapURL.class))
        {
            MapURL mapURL=method.getAnnotation(MapURL.class);
            System.out.println(mapURL.value());
            System.out.println(mapURL.method());
        }
    }

    @org.junit.Test
    public void mm()
    {


    }
}
