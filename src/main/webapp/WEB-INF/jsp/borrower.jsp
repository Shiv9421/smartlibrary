<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <%@include file="common/header.jspf"%>
    <%@include file="common/navigation.jspf"%>
    <h5><a href="/list-books"> Click For Issued Details</a></h5>
    <head>
        <link href="AddBook.css" rel="stylesheet">
    </head>

    <div align="center">

        <section class="get-in-touch">
            <h1 class="title">Issue Book</h1>
    <form:form method="post" modelAttribute="borrower" cssClass="contact-form row">
	    <div class="form-field col-lg-5">
	        <form:label path="memberName" cssClass="label">Enter Member Name: </form:label>
	    </div>
	       <div class="form-field col-lg-5">
	        <select class="form-control" id="filtering" name="memberName">
                            <option selected hidden> Choose Member </option>
                            <c:forEach items="${memberList}" var="member">
                            <option>${member.memberName}</option>

                            </c:forEach>
                        </select>
	        
	    </div>
<br>
<br>
        <div class="form-field col-lg-5">
            <form:label path="bookName" cssClass="label">Enter Book Name:</form:label>
        </div>
        <div class="form-field col-lg-5">
              <select class="form-control" id="filtering" name="bookName">
                            <option selected hidden> Choose Book </option>
                            <c:forEach items="${bookList}" var="book">
                            <option>${book.bookName}</option>

                            </c:forEach>
                        </select>
        </div>

       <br>
<br> 
       <div class="form-field col-lg-5">
            <form:label path="borrowedDate" cssClass="label">Enter Borrowed Date:</form:label>
             </div>
        <div class="form-field col-lg-5">
            <form:input path="borrowedDate" type="text" required="required" cssClass="input-text js-input" style="width:440px;"/>
        </div>

        
        <div class="form-field col-lg-12">
            <input class="submit-btn" type="submit" value="Issue Book">
        </div>

    </form:form>
        </section>


        <%@include file="common/footer.jspf"%>
    </div>
</div>
