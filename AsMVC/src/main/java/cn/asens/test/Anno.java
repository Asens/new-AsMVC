package cn.asens.test;

import cn.asens.annotation.Controller;
import cn.asens.annotation.MapURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by lenovo on 2016/5/15.
 */
@Controller
public class Anno {

    private static final Logger LOGGER= LoggerFactory.getLogger(Anno.class);

    @MapURL(value = "/mm.do")
    public String mm(HttpServletRequest req, HttpServletResponse resp)
    {
        System.out.println(req.getRequestURL());
        System.out.println("ss");
        return "test.jsp";
    }
}
