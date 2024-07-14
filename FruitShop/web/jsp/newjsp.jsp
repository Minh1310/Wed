
<%-- 
    Document   : jspSyntag
    Created on : Jan 29, 2024, 1:20:31 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <!-- script -->
        <% // code jave here
            int Max = 300;  //local
            out.print("<h1>max = "+Max+"</h1>");
        %>
        
        <!-- express -->
        <h1 style="color:red"> Max = <%=Max*4%> </h1>
        <%for(int i = 10; i<=Max; i+=10){%>
            <hr width="<%=i%>" />          
        <%}%>
        
        
        
        <!--DECLARE-->
        <%! int Min = 500; //global%>
        <%!
            public int getValue(){
                return Min;
            }
        %>
        
        <h3>Min value = <%=getValue()%></h3>
        
        
    </body>
</html>
