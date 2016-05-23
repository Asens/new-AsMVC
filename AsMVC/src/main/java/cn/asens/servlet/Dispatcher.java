package cn.asens.servlet;

import cn.asens.method.MethodResolver;
import cn.asens.classcollection.ClassCollection;
import cn.asens.config.Config;
import cn.asens.structure.MethodPro;
import cn.asens.structure.ModelMap;
import cn.asens.utils.CollectionUtils;
import org.junit.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/5/15.
 */
@WebServlet(urlPatterns = "*.do" ,loadOnStartup = 0)
public class Dispatcher extends HttpServlet{
    private  Map<String,MethodPro> methodProMap;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        long start=System.currentTimeMillis();
        ClassCollection.scanClassSetByPackage(Config.getConfig("base-package"));
//        ServletContext servletContext=servletConfig.getServletContext();
//        ServletRegistration jspServlet=servletContext.getServletRegistration("jsp");
//        jspServlet.addMapping(Config.getConfig("pagePath")+"*");
//        ServletRegistration defaultServlet=servletContext.getServletRegistration("default");
//        defaultServlet.addMapping(Config.getConfig("sourcePath")+"*");
        System.out.println("class initalized in " + (System.currentTimeMillis() - start) + " ms");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,MethodPro> methodProMap=ClassCollection.getMethodMap();
        Map<String,Class<?>> classMap=ClassCollection.getClassMap();
        if(methodProMap==null)
            throw new RuntimeException("the methodProMap is not inited");
        String pathInfo=req.getPathInfo()==null?req.getServletPath():req.getPathInfo();
        if(methodProMap.containsKey(pathInfo)) {
            MethodPro methodPro = methodProMap.get(pathInfo);
            Method method=methodPro.getMethod();
            try {
                Class<?> clazz=classMap.get(pathInfo);
                List<String> paraNames= MethodResolver.getMethodNames(clazz.getName(),methodPro.getName());
                List<String> classNames= CollectionUtils.classArrToStringList(method.getParameterTypes());
                ModelMap model=new ModelMap();
               Object[] args=MethodResolver.makeArgs(paraNames,classNames,req,resp,model);

                Object result=method.invoke(clazz.newInstance(),args);

                Map<String,Object> map=model.getMap();

                for(String key:map.keySet())
                {
                    req.setAttribute(key,map.get(key));
                }

                if(result instanceof String)
                {
                    req.getRequestDispatcher(Config.getConfig("pagePath")+ File.separator+result.toString()+Config.getConfig("suffix")).forward(req, resp);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
//        else{
//            throw new RuntimeException("none url annotated");
//        }


    }


}
