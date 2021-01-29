<%-- 
    Document   : orderForm
    Created on : Oct 22, 2020, 10:01:33 PM
    Author     : mevrthisbang
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<div><s:form action="submitOrder" method="POST" theme="simple">
        <div class="container">
            <h2 style="text-align: center">Order Form</h2>

            <div class="row">
                <div class="form-row">
                    <div class="form-group col-md-4">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputName">Full name</label>
                        <s:textfield cssClass="form-control" id="inputName" value="%{#attr.ORDER.lastname}" name="fullname"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.fullnameError}"/>
                        </font>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputPhone">Phone</label>
                        <s:textfield cssClass="form-control" id="inputPhone" value="%{#attr.ORDER.phone}" name="phone"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.phoneError}"/>
                        </font>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputAddress">Address</label>
                        <s:textfield cssClass="form-control" id="inputAddress" value="%{#attr.ORDER.address}" name="address"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.addressError}"/>
                        </font>
                    </div>
                </div>  
                <div class="form-row">
                    <div class="form-group col-md-4">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="inputPaymentMethod">Payment Method</label>
                        <s:select cssClass="form-control" id="inputPaymentMethod" list="#{'cash':'Cash', 'card':'Card', 'cod':'COD'}" name="paymentMethod"/>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <s:submit cssClass="btn btn-large btn-primary" value="Confirm" theme="simple"/>
                    </div>
                </div>
            </div>

        </div>
    </s:form>
</div>

