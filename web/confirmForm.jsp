<%-- 
    Document   : confirmForm
    Created on : Nov 8, 2020, 11:43:31 AM
    Author     : mevrthisbang
--%>

<%-- 
    Document   : manageProfile
    Created on : Oct 24, 2020, 9:11:41 AM
    Author     : mevrthisbang
--%>

<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vn">
    <head>
        <meta charset="utf-8">
        <script
            src="https://kit.fontawesome.com/64d58efce2.js"
            crossorigin="anonymous"
        ></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>
            Home
        </title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" media="screen"/>
        <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/style2.css" type="text/css" media="screen"/>
        <link id="callCss" rel="stylesheet" href="${pageContext.request.contextPath}/themes/bootshop/bootstrap.min.css" media="screen"/>
        <link href="${pageContext.request.contextPath}/themes/css/base.css" rel="stylesheet" media="screen"/>
        <!-- Bootstrap style responsive -->	
        <link href="${pageContext.request.contextPath}/themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/themes/css/font-awesome.css" rel="stylesheet" type="text/css">
        <!-- Google-code-prettify -->	
        <link href="${pageContext.request.contextPath}/themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>
        <!-- fav and touch icons -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/themes/images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/themes/images/ico/book-icon-144-191918.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/themes/images/ico/book-icon-114-191918.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/themes/images/ico/book-icon-72-191918.png">
        <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/themes/images/ico/book-icon-57-191918.png">
        <style type="text/css" id="enject"></style>

    </head>
    <body>
        <div>
            <s:form action="disableAccount" method="POST" theme="simple" enctype="multipart/form-data">
                <div class="container">
                    <h2 style="text-align: center">Confirm form</h2>
                    <div class="row">
                        <div class="form-row">
                            <div class="form-group col-md-4">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="inputEmail">Email</label>
                                <s:textfield cssClass="form-control" id="inputEmail" value="%{#session.USER.email}" name="email"/>
                                <font color="red">
                                <s:property value="%{#attr.INVALID.emailError}"/>
                                </font>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="inputCurrentPassword">Password</label>
                                <s:password cssClass="form-control" id="inputPassword" name="currentPassword"/>
                                <font color="red">
                                <s:property value="%{#attr.INVALID.currentPasswordError}"/>
                                </font>
                            </div>
                        </div>

                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col text-center">
                                <s:submit cssClass="btn btn-large btn-primary" value="Confirm"/>
                                <s:a cssClass="btn btn-large btn-primary" href="/AssignmentBookOnlineShopping/.action">Back to Homepage</s:a>
                            </div>
                        </div>
                    </div>
                </div>
            </s:form>
            
        </div>
    </body>
</html>


