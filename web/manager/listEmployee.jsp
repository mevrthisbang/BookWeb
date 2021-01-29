<%-- 
    Document   : listEmployee
    Created on : Nov 3, 2020, 3:16:43 PM
    Author     : mevrthisbang
--%>

<div>
    <div class="container">
        <div class="row">
            <%@include file="leftManager.jsp" %>
            <font color="red"><s:property value="%{#parameters.disableError}"/></font>
            <table border="1" style="width: 800px; margin-left: 400px; margin-top: 40px; text-align: center;">
                <thead>
                    <tr>
                        <th>Full name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Gender</th>
                        <th>Update</th>
                        <th>Disable</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="listEmployees">
                        <s:form action="loadAccountByPrimaryKey" method="POST" theme="simple">
                    <tr>
                        <td>
                            <s:property value="%{lastname}"/> <s:property value="%{firstname}"/>
                        </td>
                        <td>
                            <s:property value="%{email}"/>
                        </td>
                        <td>
                            <s:property value="%{phone}"/>
                        </td>
                        <td>
                            <s:property value="%{gender}"/>
                        </td>
                        <td>
                            <s:hidden name="username" value="%{username}"/>
                            <s:submit value="Update"/>
                        </td>
                        <td>
                            <s:url action="disableEmployee" id="disableEmployeeLink">
                                <s:param name="username" value="%{username}"/>
                            </s:url>
                            <s:a href="%{disableEmployeeLink}">Disable</s:a>
                        </td>
                    </tr>
                        </s:form>
                    </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
</div>
