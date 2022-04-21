

<div class="container">
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
	<div>
		<a type="button" class="btn btn-primary btn-md" href="add-member">Add Membership</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of Member's</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th >Name</th>
						<th>Address</th>
						<th>Mobile Number</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${members}" var="member">
						<tr>
							<td>${member.memberName}</td>
							<td>${member.memberAddress}</td>
							<td>${member.mobileNumber}</td>

							<td><a type="button" class="btn btn-success"
								href="/update-member?id=${member.id}">Edit</a>
							<a type="button" class="btn btn-warning"
								href="/delete-member?id=${member.id}">Remove</a>
							<a type="button" class="btn btn-success"
								href="/get-books?id=${member.id}">Books Issued</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>