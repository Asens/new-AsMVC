package cn.asens.test.sad;

import cn.asens.annotation.Controller;
import cn.asens.annotation.MapURL;
import cn.asens.structure.MethodPro;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 2016/5/19.
 */
@Controller
public class Sa {
    @MapURL(value = "/mm.jspx")
    public void sfadf(HttpServletRequest req,HttpServletResponse resp,Integer aa,String bb,MethodPro mp) throws IOException {
        System.out.println(aa);
        System.out.println(bb);

        resp.getWriter().write(mp.getName());
    }
}
