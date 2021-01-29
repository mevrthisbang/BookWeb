<%-- 
    Document   : register
    Created on : Sep 7, 2020, 9:49:49 AM
    Author     : Admin
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Register</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" >
        <script
            src="https://kit.fontawesome.com/64d58efce2.js"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="register-box">
            <img src="img/avatar.png" class="avatar">
            <h1>REGISTER</h1>
            <div>
                <s:form action="registerAccount" method="POST" theme="simple">
                    <s:textfield cssClass="firstname" placeholder="First Name" name="firstname" value="%{account.firstname}"/>
                    <s:textfield cssClass="lastname" placeholder="Last Name" name="lastname" value="%{account.lastname}"/>
                    <font color="red"><s:property value="%{#attr.INVALID.lastnameError}"/></font>
                    <s:textfield cssClass="normal" placeholder="Enter Email" name="email" value="%{account.email}"/>
                    <font color="red"><s:property value="%{#attr.INVALID.emailError}"/></font>
                    <s:password cssClass="normal" placeholder="Enter Password" name="password" value=""/>
                    <font color="red"><s:property value="%{#attr.INVALID.passwordError}"/></font>
                    <s:password cssClass="normal" placeholder="Re-enter Password" name="confirm" value=""/>
                    <font color="red"><s:property value="%{#attr.INVALID.confirmPasswordError}"/></font>
                    <s:textfield cssClass="normal" type="text" placeholder="Enter Phone" name="phone" value="%{account.phone}"/>
                    <font color="red"><s:property value="%{#attr.INVALID.phoneError}"/></font>
                    <br>
                    <div class="register-form">
                        <label>Gender</label><br>
                        <s:select list="#{'F':'Female', 'M':'Male'}" theme="simple" id="inputGender" name="gender" value="%{#attr.ACCOUNT.gender}"/>
                    </div>
                        <s:submit cssClass="btn" value="Sign Up"/><br>
                        Already have account?<s:a href="login.jsp" theme="simple">Login</s:a>
                </s:form>
            </div>

        </div>

    </body>
</html>

