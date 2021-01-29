<%-- 
    Document   : employeeForm
    Created on : Oct 22, 2020, 9:59:45 PM
    Author     : mevrthisbang
--%>

<%@taglib uri="/struts-tags" prefix="s" %>
<div>
    <s:form action="updateEmployee" method="POST" theme="simple">
        <div class="container">
            <h2 style="text-align: center">Manage Employee</h2>
            <div class="row">
                <s:hidden name="username" value="%{account.username}"/>
                <font color="red"><s:property value="%{#attr.ERROR}"/></font>
                <div class="form-row">
                    <div class="form-group col-md-2">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputEmail">Email</label>
                        <s:textfield theme="simple" cssClass="form-control" id="inputEmail" value="%{account.email}" name="email"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.emailError}"/>
                        </font>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputFirstname">First name</label>
                        <s:textfield theme="simple" cssClass="form-control" id="inputFirstname" value="%{account.firstname}" name="firstname"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.firstnameError}"/>
                        </font>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputLastname">Last name</label>
                        <s:textfield cssClass="form-control" id="inputLastname" value="%{account.lastname}" name="lastname"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.lastnameError}"/>
                        </font>
                    </div>
                </div>
                    
                <div class="form-row">
                    <div class="form-group col-md-2">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputPhone">Phone</label>
                        <s:textfield theme="simple" cssClass="form-control" id="inputPhone" placeholder="Input Phone" value="%{account.phone}" name="phone"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.phoneError}"/>
                        </font>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="inputGender">Gender</label>
                        <s:select list="#{'F':'Female', 'M':'Male'}" theme="simple" cssClass="form-control" id="inputGender" value="%{account.gender}" name="gender"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputAddress">Address</label>
                        <s:textfield cssClass="form-control" id="inputAddress" value="%{account.address}" name="address"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.addressError}"/>
                        </font>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputDescription">Description</label>
                        <s:textfield cssClass="form-control" id="inputDescription" placeholder="Description" value="%{account.description}" name="description"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.descriptionError}"/>
                        </font>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputIsWorking">Is Working</label>
                        <s:checkbox cssClass="form-control" id="inputIsWorking" name="isWorking" value="%{account.isStillWorking}"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="inputRole">Role</label>
                        <s:select list="#{'employee':'Employee', 'manager':'Manager'}" theme="simple" cssClass="form-control" id="inputGender" value="%{account.role}" name="role"/>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <s:submit cssClass="btn btn-large btn-primary" value="Update Employee"/>
                    </div>
                </div>
            </div>
        </div>

    </s:form>
</div>




