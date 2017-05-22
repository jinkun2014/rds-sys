package me.jinkun.rds.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by Administrator on 2017/5/22.
 */
@Controller
public class IconController {
    @RequestMapping(value = "/icons", method = RequestMethod.GET)
    @ResponseBody
    public Object list(HttpServletRequest request) {
        String imagePath = request.getSession().getServletContext().getRealPath("/resources/images/32x32");
        File dirs = new File(imagePath);
        List<String> imageList = new ArrayList<String>();
        for (String name : dirs.list()) {
            imageList.add("/resources/images/32x32/"+name);
        }
        return imageList;
    }
}
