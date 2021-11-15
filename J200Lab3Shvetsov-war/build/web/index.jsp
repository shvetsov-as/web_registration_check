<%-- 
    Document   : index
    Created on : Mar 20, 2021, 11:49:51 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book club</title>
    </head>
    <style>
        #img1{
            display: block;
            margin: auto;
            border-left:  2px solid green;
            border-right: 2px solid green;
            border-top: 2px solid green;
        }
        body{
            background-image: url("img/fonn.jpg");
        }
        #table1{
            border-left:  2px solid green;
            border-right: 2px solid green;
            align-content: center;
            margin: auto;
            background-color: white;
            text-align: center;
            background-image: url("img/log2.jpg");
            background-size: 110%;
        }
        #table2{
            border-left:  2px solid green;
            border-right: 2px solid green;
            border-bottom: 2px solid green;
            align-content: center;
            margin: auto;
            background-color: white;
            text-align: center;
            background-image: url("img/log2.jpg");
            background-size: 110%;

        }
        #btn{
            height:24px;
            width:80px;
        }
    </style>
    <body>

        <img src="img/log.jpg" width="400" height="330" id="img1" /> 

        <form action="RegServlet" method="POST">

            <table border="0" width="403" height="100" cellpadding="7" id="table1">
                <tbody>

                    <tr>
                        <td>Enter login</td>
                        <td> <input type="text" name="login" value="" /></td>
                        <td><input type="submit" name="sign" value="Sign in" id="btn"/></td>
                    </tr>

                    <tr>
                        <td>Enter password</td>
                        <td><input type="password" name="psw" value="" /></td>
                        <td><input type="submit" value="Message" name="msg" id="btn"/></td>
                    </tr>
                </tbody>
            </table>

            <table border="0" width="403" cellpadding="7" id="table2">

                <tbody>
                    <tr>
                        <td><%String msg = null;%>
                            <%msg = (String) request.getAttribute("msg");%>
                            <%if (msg == null) {%>
                            <h4> Always glad to see You.</h4>
                            <%} else {%>
                            <h4> Message: <%= msg%> </h4>
                            <%}%>
                        </td>
                    </tr>
                </tbody>
            </table>

        </form>

    </body>
</html>
