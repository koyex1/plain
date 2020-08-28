<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div>
<c:if test="${not empty operation}">
<div class="alert alert-success" role="alert">
  ${operation}
</div>
</c:if>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">User</th>
				<th scope="col">Name</th>
				<th scope="col">Description</th>
				<th scope="col">Price</th>
				<th scope="col">Quantity</th>
				<th scope="col">Category</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${suppliers}" var="item">

				<tr>
					<td>${item.account.fullName}</td>
					<td>${item.name}</td>
					<td>${item.description}</td>
					<td>${item.quantity}</td>
					<td>${item.price}</td>
					<td>${item.category.name}</td>
					<td><a  href="${Root}/dashboard/usersdata/supplier_delete/${item.id}"class="btn btn-danger glyphicon glyphicon-trash"><span>Delete</span></a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>



</div>