<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="resources" value="${pageContext.request.contextPath}/resources"/>
<c:set var="photos" value="${resources}/photos"/>

<div>
	<h1>${category.name}</h1>
	<c:if test="${not empty operation}">
		<div class="alert alert-success" role="alert">${operation}</div>
	</c:if>

<div class= "nav">
	<c:forEach items= "${products}" var="item">
<a href="${Root}/product/${item.id}" >

		<div style="margin: 10px; height:300px; width:250px;" class="card border-success mb-3" style= "max-width: 18rem;">
			<div class="card-body text-success">
				<h5 class="card-title">${item.name}</h5>
				<div class="card-text">
					<img style="height: 170px; width: 150px;" class="card-img-top" src="${photos}/${item.id}.jpg" alt="Card image cap">
				</div>
			</div>
			<div class="card-footer bg-transparent border-success">
				&#8358; ${item.price}</div>
		</div>
		</a>
	</c:forEach>
</div>

</div>