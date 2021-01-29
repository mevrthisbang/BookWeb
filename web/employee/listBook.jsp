<%-- 
    Document   : listBook
    Created on : Nov 2, 2020, 10:05:25 PM
    Author     : mevrthisbang
--%>

<%-- 
    Document   : listBook
    Created on : Oct 22, 2020, 10:03:38 PM
    Author     : mevrthisbang
--%>
<div>
    <div class="container">
        <div class="row">
            <%@include file="..\left.jsp" %>

            <ul class="thumbnails">
                <s:iterator value="listBooks">
                    <li class="span3">
                        <div class="thumbnail">
                            <img style="width: 100%; height: 15em;"src="${pageContext.request.contextPath}/<s:property value="%{thumbnailImg}"/>" alt="Thumbnail Image"/>
                            <div class="caption" style="height: 15em;">
                                <h5><s:property value="%{title}"/></h5>
                                <p> 
                                    <s:property value="%{shortDescription}"/>
                                </p>
                                <s:form action="bookByID" method="POST" theme="simple">
                                    <h4 style="text-align:center">
                                        <s:hidden name="bookID" value="%{id}"/>
                                        <s:submit type="submit" cssClass="btn btn-primary" value="Update" theme="simple"/>
                                        <p><s:property value="%{price}"/>$</p></h4>
                                    </s:form>
                            </div>
                        </div>
                    </li>


                </s:iterator>
            </ul>


        </div>
    </div>
</div>
