<%-- 
    Document   : userHeader
    Created on : Oct 19, 2020, 9:43:54 PM
    Author     : mevrthisbang
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    <body class="Home">
        <header id="ttr_header">
            <div id="ttr_header_inner">
                <div class="innermenu"><nav id="ttr_menu" class="navbar navbar-default navbar-fixed-top">
                        <div id="ttr_menu_inner_in">
                            <div class="menuforeground">
                            </div>
                            <div class="ttr_menushape1">
                                <s:a href="/AssignmentBookOnlineShopping/.action" theme="simple"><div class="html_content"><p style="margin:0em 0.36em 0em 0em;text-align:Center;line-height:0.695774647887324;"><br style="font-family:'Roboto','Impact';font-weight:700;font-size:0.143em;color:rgba(52,52,52,1);" /></p><p style="margin:0em 0.36em 0.36em 0em;text-align:Center;line-height:0.695774647887324;"><span style="font-family:'Roboto','Impact';font-weight:700;font-size:1.571em;color:rgba(52,52,52,1);">BOOKLIFE <i class="fas fa-book"></i></span></p></div></s:a>
                                </div>
                                <div id="navigationmenu">
                                    <div class="menu-center collapse navbar-collapse">
                                    <s:form cssClass="form-inline navbar-search" method="post" action="searchBook" theme="simple">
                                        <s:textfield cssClass="srchTxt" placeholder="Search Book..." theme="simple" name="search"/>
                                        <s:submit cssClass="srchBtn" value="Search"/>
                                    </s:form>
                                    <ul class="ttr_menu_items nav navbar-nav navbar-right">
                                        <li class="ttr_menu_items_parent dropdown"><s:a href="/AssignmentBookOnlineShopping/user/userCart.jsp" cssClass="ttr_menu_items_parent_link"><span class="menuchildicon"><i class="fas fa-shopping-cart"></i></span> Cart</s:a>
                                                <hr class ="horiz_separator"/>
                                            </li>
                                        <s:url action="loadAccountByPrimaryKey" id="loadAccountLink">
                                            <s:param name="username" value="%{#session.USER.username}"/>
                                        </s:url>
                                        <li class="ttr_menu_items_parent dropdown"><s:a href="%{loadAccountLink}" cssClass="ttr_menu_items_parent_link"><span class="menuchildicon"><i class="fas fa-user"></i></span> Profile</s:a>
                                                <hr class ="horiz_separator"/>
                                            </li>
                                            <li class="ttr_menu_items_parent dropdown"><s:a href="/AssignmentBookOnlineShopping/logout.action" cssClass="ttr_menu_items_parent_link"><span class="menuchildicon"><i class="fas fa-sign-out-alt"></i></span> Logout</s:a>
                                            <hr class ="horiz_separator"/>
                                        </li>
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </header>
