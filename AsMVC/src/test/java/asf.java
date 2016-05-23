import cn.asens.structure.MethodPro;
import cn.asens.utils.ReflectUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lenovo on 2016/5/21.
 */
public class asf {
    @Test
    public void main() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz=ReflectUtils.loadClass("cn.asens.structure.MethodPro");
        Field[] fields=clazz.getDeclaredFields();
        Object o=clazz.newInstance();
        for(Field field:fields)
        {
            if(field.getName().equals("name")) {
                field.setAccessible(true);
                field.set(o,"mmmm" );
            }
        }
        Class<?> clazz1=ReflectUtils.loadClass("HG");
        //   Method method11=clazz.getMethod("say(java.lang.String)");
        Method[] methods=clazz1.getMethods();
        for(Method method:methods){
            if(method.getName().equals("say")){
                Object o1=clazz1.newInstance();
                method.invoke(o1,"asfasfe",o);
            }
        }
    }

    //@Test
    public void asf() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz=ReflectUtils.loadClass("HG");
     //   Method method11=clazz.getMethod("say(java.lang.String)");
        Method[] methods=clazz.getMethods();
        for(Method method:methods){
            System.out.println(method);
            if(method.getName().equals("say")){
                Object o=clazz.newInstance();
                method.invoke(o,"asfasfe");
            }
        }
    }
}
