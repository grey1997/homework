## 第一周作业

参考 com.salesmanager.shop.tags.CommonResponseHeadersTag 实现一个自定义的 Tag，将 Hard Code 的 Header 名值对，变为属性配置的方式。
1. 实现自定义标签
2. 编写自定义标签 tld 文件 
3. 部署到 Servlet 应用

###自定义标签 commonTitle
#####自定义标签 tld 文件：src/main/webapp/WEB-INF/common-tags.tld
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>

<taglib xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0"
xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd">
<description><![CDATA["Shopizer tag libs"]]></description>
<display-name>"Common Tags"</display-name>
<tlib-version>0.1</tlib-version>
<short-name>common</short-name>
<uri>/common-tag</uri>

    <tag>
        <!-- Builds the image URL (in admin section only)-->
        <name>commonTitle</name>
        <tag-class>com.project.tag.CommonTitleTag</tag-class>
        <body-content>empty</body-content>
        <attribute>
            <name>name</name>
            <required>false</required>
            <type>java.lang.String</type>
        </attribute>
        <attribute>
            <name>message</name>
            <required>false</required>
            <type>java.lang.String</type>
        </attribute>
    </tag>

</taglib>
```

#####标签实现类：src/main/java/com/project/tag/CommonTitleTag.java
```java
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
```

#####使用 commonTitle 标签的 jsp 文件：src/main/webapp/index.jsp
```html
<%@ taglib uri="/WEB-INF/common-tags.tld" prefix="common" %>

<%
    String name = "Grey";
    String message = "Your request had been processed!";
    pageContext.setAttribute("name",name);
    pageContext.setAttribute("message",message);
%>
<common:commonTitle name="name" message="message" />

<html>
<body>
<h2>Hello World!</h2>
</body>
</html>
```

#####输出结果
在页面上显示 Hello Grey. Your request had been processed! 