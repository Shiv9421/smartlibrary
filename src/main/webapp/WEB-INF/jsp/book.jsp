<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <%@include file="common/header.jspf"%>
    <%@include file="common/navigation.jspf"%>
    <h5><a href="/list-books"> Click For List Of Books</a></h5>
    <head>
        <link href="AddBook.css" rel="stylesheet">
    </head>

    <div align="center">

        <section class="get-in-touch">
            <h1 class="title">Add New Book Here</h1>
    <form:form method="post" modelAttribute="book" cssClass="contact-form row">
	    <div class="form-field col-lg-5">
	        <form:label path="bookName" cssClass="lable">Enter Book Name: </form:label>
	    </div>
	       <div class="form-field col-lg-5">
	        <form:input path="bookName" required="required" cssClass="input-text js-input" style="width:500px;"></form:input>
	    </div>

        <div class="form-field col-lg-5">
            <form:label path="bookAuthor" cssClass="lable">Enter Author Name:</form:label>
        </div>
        <div class="form-field col-lg-5">
            <form:input path="bookAuthor" type="text" required="required" cssClass="input-text js-input" style="width:500px;"/>
        </div>

        
       <div class="form-field col-lg-5">
            <form:label path="totalQuantity" cssClass="lable">Enter Book Quantity:</form:label>
             </div>
        <div class="form-field col-lg-5">
            <form:input path="totalQuantity" type="text" required="required" cssClass="input-text js-input" style="width:500px;"/>
        </div>

        
        <div class="form-field col-lg-12">
            <input class="submit-btn" type="submit" value="Add Book">
        </div>

    </form:form>
        </section>


        <%@include file="common/footer.jspf"%>
    </div>
</div>
