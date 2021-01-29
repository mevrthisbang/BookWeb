<%-- 
    Document   : left
    Created on : Nov 2, 2020, 10:07:34 PM
    Author     : mevrthisbang
--%>

<%-- 
    Document   : left
    Created on : Oct 23, 2020, 10:43:22 PM
    Author     : mevrthisbang
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<div id="sidebar" class="span3">
    <form>
        <ul id="sideManu" class="nav nav-tabs nav-stacked">
            <!--Load book category, nha phat hanh, tac gia-->
            <li class="subMenu open"><a> Type</a>
                <ul>
                        <li><a href="/AssignmentBookOnlineShopping/.action" class="active"><i class="icon-chevron-right"></i>Book </a></li>
                        <s:url action="loadEmployee" id="loadEmployeeLink"/>
                        <li><s:a href="%{loadEmployeeLink}"><i class="icon-chevron-right"></i>Employee </s:a></li>
                </ul>
            </li>
            <br>
            <li class="subMenu open"><a> Category</a>
                <ul>
                    <s:iterator value="%{#application.listCategories}">
                        <s:url action="listBookByCategory" id="bookCateIDlink">
                            <s:param name="cateID" value="id"/>
                        </s:url>
                        <li><s:a href="%{bookCateIDlink}"><i class="icon-chevron-right"></i><s:property value="%{name}"/> </s:a></li>
                    </s:iterator>
                </ul>
            </li>
        </ul>
    </form>
    <br/>
</div>
