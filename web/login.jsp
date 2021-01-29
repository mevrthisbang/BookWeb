<%-- 
    Document   : login
    Created on : Oct 19, 2020, 8:57:45 PM
    Author     : mevrthisbang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" >
        <script
            src="https://kit.fontawesome.com/64d58efce2.js"
            crossorigin="anonymous"
        ></script>
        <title>Login</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <div class="login-box">
        <img src="img/avatar.png" class="avatar">
        <h1>LOGIN</h1>
        <div>
            <font color="red"><s:property value="%{#attr.ERROR}"/></font>
            <s:form action="login" method="POST" theme="simple">
                <p>Email</p>
                <div class="textbox">
                    <s:textfield placeholder="Enter Email" name="email" value="%{account.email}"/>
                    <i class="fas fa-user fa-lg fa-fw" aria-hidden="true"></i>

                </div>
                <p >Password</p>
                <div class="textbox">
                    <s:password placeholder="Enter Password" name="password" value=""/>
                    <i class="fas fa-lock fa-lg fa-fw" aria-hidden="true"></i>

                </div>
                <s:submit cssClass="btn" value="Sign In"/>
                Don't have an account?<a href="register.jsp"> Sign Up</a><br>
                Home Page?<s:a theme="simple" href="/AssignmentBookOnlineShopping/.action">Back to Home Page</s:a>
                <s:hidden name="fromGuest" value="%{#parameters.fromGuest}"/>
            </s:form>
        </div>


    </div>
</body>
</html>
