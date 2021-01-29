<%-- 
    Document   : cart
    Created on : Oct 23, 2020, 10:42:54 PM
    Author     : mevrthisbang
--%>
<div>
    <div class="container">
        <div class="row">
            <%@include file="left.jsp" %>
            <div class="span9">

                <ul class="breadcrumb">
                    <li><s:a href="/AssignmentBookOnlineShopping/.action">Home</s:a> <span class="divider">/</span></li>
                        <li class="active"> SHOPPING CART</li>
                    </ul>
                    <h3 style="margin-bottom: 20px">  SHOPPING CART<s:a href="/AssignmentBookOnlineShopping/.action" cssClass="btn btn-large pull-right" theme="simple"><i class="icon-arrow-left"></i> Continue Shopping </s:a></h3>
                    
                    <hr class="soft"/>
                    <font color="red"><s:property value="%{#attr.ERROR}"/></font>
                    <font color="blue"><s:property value="%{#attr.SUCCESS}"/></font>
                <s:form action="order" method="POST">
                    <table class="table table-bordered" style="margin: 20px">
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="%{#session.shoppingCart.cart}" var="mapList">
                                <s:iterator>
                                    <tr>
                                        <td><s:property value="value.title"/></td>
                                        <td>
                                            <s:property value="value.quantity"/>
                                        </td>
                                        <td><s:property value="value.price"/></td>
                                        <td><s:property value="%{value.price*value.quantity}"/></td>
                                        <td>
                                            <s:url action="deleteBookFromCart" id="deteleBookFromCartLink">
                                                <s:param name="bookID" value="value.id"/>
                                            </s:url>
                                            <s:a href="%{deteleBookFromCartLink}">Delete</s:a>
                                            </td>
                                        </tr>
                                </s:iterator>
                            </s:iterator>
                            <tr>
                                <td colspan="4" style="text-align:right">Total Price: </td>
                                <td> <s:property value="#session.shoppingCart.total"/></td>
                            </tr>
                        </tbody>
                    </table> 
                    <s:submit theme="simple" cssClass="btn btn-large btn-primary pull-right" value="Order"/>
                </s:form>

            </div>
        </div>
    </div>
</div>
