<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <%@include file="common/header.jspf"%>
    <%@include file="common/navigation.jspf"%>
    <h5><a href="/list-books"> Click For Membership Details</a></h5>
    <head>
        <link href="AddBook.css" rel="stylesheet">
    </head>

    <div align="center">

        <section class="get-in-touch">
            <h1 class="title">Add New Member Here</h1>
    <form:form method="post" modelAttribute="member" cssClass="contact-form row">
	    <div class="form-field col-lg-5">
	        <form:label path="memberName" cssClass="label">Enter Member Name: </form:label>
	    </div>
	       <div class="form-field col-lg-5">
	        <form:input path="memberName" required="required" cssClass="input-text js-input" style="width:500px;"></form:input>
	    </div>

        <div class="form-field col-lg-5">
            <form:label path="memberAddress" cssClass="label">Enter Address:</form:label>
        </div>
        <div class="form-field col-lg-5">
            <form:input path="memberAddress" type="text" required="required" cssClass="input-text js-input" style="width:500px;"/>
        </div>

        
       <div class="form-field col-lg-5">
            <form:label path="mobileNumber" cssClass="label">Enter Mobile Number:</form:label>
             </div>
        <div class="form-field col-lg-5">
            <form:input path="mobileNumber" type="text" required="required" cssClass="input-text js-input" style="width:500px;"/>
        </div>

        
        <div class="form-field col-lg-12">
            <input class="submit-btn" type="submit" value="Add Membership">
        </div>

    </form:form>
        </section>


        <%@include file="common/footer.jspf"%>
    </div>
</div>
