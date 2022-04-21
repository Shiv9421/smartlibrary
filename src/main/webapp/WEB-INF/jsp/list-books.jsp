

<div class="container">
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
	<div>
		<a type="button" class="btn btn-primary btn-md" href="add-book">Add Books</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of Book's</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th >Book Name</th>
						<th>Author Name</th>
						<th>Total Quantity</th>
						<th> Available Quantity</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${books}" var="book">
						<tr>
							<td>${book.bookName}</td>
							<td>${book.bookAuthor}</td>
							<td>${book.totalQuantity}</td>
							<td>${book.availableQuantity}</td>
							<td><a type="button" class="btn btn-success"
								href="/update-book?id=${book.id}">Update</a>
							<a type="button" class="btn btn-warning"
								href="/delete-book?id=${book.id}">Delete</a>
							<a type="button" class="btn btn-success"
								href="/check-availability?id=${book.id}">Check Status</a>
								</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>