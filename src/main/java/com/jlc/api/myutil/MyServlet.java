package com.jlc.api.myutil;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by menghan on 2017/3/19.
 */
public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = null;
        System.out.println(session = req.getSession(false));
        System.out.println(session.getId());
        System.out.println("inin");
        System.out.println("servlet");
        System.out.println("servlet");
        System.out.println("servlet");
    }


}
