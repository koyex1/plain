<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<h1>THIS IS THE CATRGORY PAGE</h1>
	<c:if test="${not empty operation}">
		<div class="alert alert-success" role="alert">${operation}</div>
	</c:if>

	<c:forEach items= "${products}" var="item">
<a href="${Root}/product/${item.id}" >

		<div class="card border-success mb-3" style="max-width: 18rem;">
			<div class="card-body text-success">
				<h5 class="card-title">${item.name}</h5>
				<div class="card-text">
					<img class="card-img-top" src="..." alt="Card image cap">
				</div>
			</div>
			<div class="card-footer bg-transparent border-success">
				&#8358; ${item.price}</div>
		</div>
		</a>
	</c:forEach>


</div>