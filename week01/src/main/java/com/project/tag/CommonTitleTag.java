package com.project.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CommonTitleTag extends SimpleTagSupport {

    private String name;

    private String message;

    @Override
    public void doTag()  {
        JspContext context = getJspContext();
        JspWriter writer = null;
        try{
            String result = "";
            String name = (String) context.getAttribute("name");
            String message = (String) context.getAttribute("message");
            
            if(null != name && !"".equals(name)) {
                result = "Hello " + name + ". ";
            }

            if(null != message && !"".equals(message)) {
                result += message;
            }
            writer = context.getOut();
            if(null != writer) {
                writer.write(result);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
