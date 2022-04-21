

<div class="container">
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
	<div>
		<a type="button" class="btn btn-primary btn-md" href="add-borrower">Issue Book</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of Book's Issued</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th >Book Name</th>
						<th>Member Name</th>
						<th>Issued Date</th>
						<th>Due Date</th>
						<th>Returned Status</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${borrowers}" var="borrower">
						<tr>
							<td>${borrower.bookName}</td>
							<td>${borrower.memberName}</td>
							<td>${borrower.borrowedDate}</td>
							<td>${borrower.dueDate}</td>
							<td>${borrower.returned}</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>