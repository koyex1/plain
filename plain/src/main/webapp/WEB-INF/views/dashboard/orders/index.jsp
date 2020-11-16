<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="resources" value="${pageContext.request.contextPath}/resources"/>
<c:set var="photos" value="${resources}/photos"/>

<div>

<c:if test="${not empty operation}">
<div class="alert alert-success" role="alert">
  ${operation}
</div>
</c:if>


<table class="table">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col">Customers Name</th>
      <th scope="col">Address</th>
      <th scope="col">Item</th>
      <th scope="col">Quantity</th>
      <th scope="col">Price</th>
      <th scope="col">TotalPrice</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${orders}" var="item">
    <tr>
      <th scope="row"></th>
      <td><img class="productframe" src="${photos}/${item.id}.jpg"></td>
      <td>${item.fullName}</td>
       <td>${item.address}</td>
      <td>${item.name}</td>
       <td>${item.quantity }</td>
      <td>&#8358;${item.price }</td>
      <td>&#8358;${item.totalPrice }</td>
      <td>  <a href="${Root}/dashboard/order/deleteOrder/${item.id}" class="btn btn-danger " ><span class="glyphicon glyphicon-trash">Delete</span></a></td>
        
  
    </tr>
    </c:forEach>
    </tbody>
    </table>


</div>