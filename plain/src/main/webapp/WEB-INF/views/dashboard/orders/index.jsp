<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
      <td>${item.fullName }</td>
       <td>${item.Address }</td>
      <td>${item.name }</td>
       <td>${item.quantity }</td>
      <td>${item.price }</td>
      <td>${item.totalPrice }</td>
      <td>  <a href="${Root}/dashboard/order/deleteOrder/${item.id}" class="btn btn-danger " ><span class="glyphicon glyphicon-trash">Delete</span></a></td>
        
  
    </tr>
    </c:forEach>
    </tbody>
    </table>


</div>