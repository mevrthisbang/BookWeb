<div>
    <div class="container">
        <div class="row">
            <%@include file="left.jsp" %>
            <div class="span9">
                <ul class="breadcrumb">
                    <!--url dan toi homepage-->
                    <s:url action="" id="homeLink"/>
                    <li><s:a href="%{homeLink}">Home</s:a> <span class="divider">/</span></li>
                        <!--url dan toi load cac book cua category-->
                    <s:url action="listBookByCategory" id="bookCateIDlink">
                        <s:param name="cateID" value="category.id"/>
                    </s:url>
                    <li><s:a href="%{bookCateIDlink}"><s:property value="%{category.name}"/></s:a> <span class="divider">/</span></li>
                        <li class="active">Book Details</li>
                    </ul>	
                    <div class="row">	  
                        <div id="gallery" class="span3">
                            <div id="differentview" class="moreOptopm carousel slide">
                                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                    <ol class="carousel-indicators">
                                    <s:iterator value="%{listImg}" status="counter">
                                        <li data-target="#carouselExampleIndicators" data-slide-to="<s:property value="%{#counter.index}"/>" <s:if test="%{#counter.index==0}">class="active"</s:if></li>

                                    </s:iterator>
                                </ol>

                                <div class="carousel-inner">
                                    <s:iterator value="%{listImg}" status="counter">
                                        <div class="carousel-item <s:if test="%{#counter.index==0}">active</s:if>">
                                            <img style="width:100%; height: 300px;" src="${pageContext.request.contextPath}/<s:property value="%{listImg[#counter.index]}"/>">
                                        </div>
                                    </s:iterator>
                                </div>
                                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>

                        <div class="btn-toolbar">
                        </div>
                    </div>
                    <div class="span6">
                        <h3><s:property value="%{book.title}"/></h3>
                        <hr class="soft"/>
                        <s:form cssClass="form-horizontal qtyFrm" action="addToCart" theme="simple" method="POST">
                            <div class="control-group">
                                <label class="control-label"><span><h2 style="background: #cccccc"><s:property value="%{book.price}"/>$</h2></span></label>

                                <div style="margin-top: 100px">
                                    <s:hidden name="fromBookDetail" value="Yes"/>
                                    <s:hidden name="bookID" value="%{bookID}"/>
                                    <s:textfield type="number" name="quantity" min="1" step="1" value="1"/>
                                    <s:submit cssClass="btn btn-large btn-primary pull-right" value="Add to Cart" theme="simple"/>
                                </div>
                            </div>
                        </s:form>
                        <hr class="soft clr"/>
                        <p>
                            <s:property value="%{book.shortDescription}"/>

                        </p>
                        <a class="btn btn-small pull-right" href="#detail">More Details</a>
                        <br class="clr"/>
                        <a href="#" name="detail"></a>
                        <hr class="soft"/>
                    </div>

                    <div class="span9">
                        <ul id="productDetail" class="nav nav-tabs">
                            <li class="active"><a href="#home" data-toggle="tab">Book Details</a></li>
                            <li><a href="#profile" data-toggle="tab">Related Products</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane active in" id="home">
                                <h4>Product Information</h4>
                                <table class="table table-bordered">
                                    <tbody>
                                        <tr class="techSpecRow"><th colspan="2">Book Details</th></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Issuers: </td><td class="techSpecTD2"><s:property value="%{book.issuers}"/></td></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Released Date:</td><td class="techSpecTD2"><s:property value="%{book.releasedDate}"/></td></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Size:</td><td class="techSpecTD2"><s:property value="%{book.size}"/></td></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Cover Type:</td><td class="techSpecTD2"><s:property value="%{book.coverType}"/></td></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Number of Page:</td><td class="techSpecTD2"><s:property value="%{book.numOfPage}"/></td></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Publishing Company:</td><td class="techSpecTD2"><s:property value="%{book.publishingCompany}"/></td></tr>
                                    </tbody>
                                </table>

                                <h5>Description</h5>
                                <p>
                                    <s:property value="%{book.descritption}"/>
                                </p>
                            </div>
                            <br class="clr">
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div> 
</div>