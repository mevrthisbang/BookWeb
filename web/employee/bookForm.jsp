<%-- 
    Document   : bookForm
    Created on : Oct 22, 2020, 9:59:31 PM
    Author     : mevrthisbang
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<div>
    <s:form action="createBook" method="POST" theme="simple" enctype="multipart/form-data">
        <div class="container">
            <h2 style="text-align: center">Book Form</h2>
            <div class="row">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputGroupFileAddon01">Thumbnail Image</label>
                        <div class="custom-file">
                            <s:file theme="simple" cssClass="custom-file-input" id="customFile" name="thumbnailImg" accept="image/*"/>
                            <font color="red">
                        <s:property value="%{#attr.INVALID.thumnailImgError}"/>
                        </font>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputGroupFileAddon01">Main Image</label>
                        <div class="custom-file">
                            <s:file theme="simple" cssClass="custom-file-input" id="customFile" multiple="" name="mainImg" accept="image/*"/>
                            <font color="red">
                        <s:property value="%{#attr.INVALID.mainImgError}"/>
                        </font>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="inputTitle">Title</label>
                        <s:textfield theme="simple" cssClass="form-control" id="inputTitle" placeholder="Input Title" name="title" value="%{book.title}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.titleError}"/>
                        </font>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputAuthor">Author</label>
                        <s:textfield cssClass="form-control" id="inputAuthor" placeholder="Input Author" name="author" value="%{book.author}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.authorError}"/>
                        </font>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputPrice">Price</label>
                        <s:textfield cssClass="form-control" id="inputPrice" placeholder="Input Price" name="price" value="%{book.price}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.priceError}"/>
                        </font>
                    </div>

                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="inputReleasedDate">Released Date</label>
                        <s:textfield theme="simple" cssClass="form-control" id="inputReleasedDate" placeholder="Input Released Date" name="releasedDate" value="%{book.releasedDate}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.releasedDate}"/>
                        </font>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputIssuers">Issuers</label>
                        <s:textfield cssClass="form-control" id="inputIssuers" placeholder="Input Issuers" name="issuers" value="%{book.issuers}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.issuersError}"/>
                        </font>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputPublishingCompany">Publishing Company</label>
                        <s:textfield cssClass="form-control" id="inputPublishingCompany" placeholder="Input Publishing Company" name="publishingCompany" value="%{book.publishingCompany}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.publishingCompanyError}"/>
                        </font>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputCategory">Category</label>
                        <s:select list="%{#application.listCategories}" listKey="%{id}" listValue="%{name}" cssClass="form-control" id="inputCategory" name="categoryID" value="%{book.categoryID}"/>
                    </div>

                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="inputSize">Size</label>
                        <s:textfield theme="simple" cssClass="form-control" id="inputSize" placeholder="Input Size" name="size"  value="%{book.size}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.sizeError}"/>
                        </font>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputCoverType">Cover Type</label>
                        <s:textfield cssClass="form-control" id="inputCoverType" placeholder="Input Cover Type" name="coverType"  value="%{book.coverType}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.coverTypeError}"/>
                        </font>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputNumberofPage">Number of Page</label>
                        <s:textfield cssClass="form-control" id="inputNumberofPage" placeholder="Input Number of Page" name="numOfPage" value="%{book.numOfPage}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.numOfPageError}"/>
                        </font>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputShortDescription">Short Description</label>
                        <s:textarea theme="simple" cssClass="form-control" id="inputShortDescription" placeholder="Input Short Description" name="shortDescription" value="%{book.shortDescription}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.shortDescriptionError}"/>
                        </font>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputDescription">Description</label>
                        <s:textarea cssClass="form-control" id="inputDescription" placeholder="Input Description" name="description" value="%{book.description}"/>
                        <font color="red">
                        <s:property value="%{#attr.INVALID.descriptionError}"/>
                        </font>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <s:submit cssClass="btn btn-large btn-primary" value="Create new Book"/>
                        </div>
                    </div>
                </div>
            </div>
    </s:form>
</div>
