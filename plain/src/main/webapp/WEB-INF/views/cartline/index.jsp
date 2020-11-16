<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
    
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!-- setting constant values -->
<c:set var="Root" value="${pageContext.request.contextPath}"/>
<spring:url var="resources" value="${pageContext.request.contextPath}/resources"/>
<c:set var="photos" value="${resources}/photos"/>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title></title>
<!-- BOOTSTRAP 3 CSS and JS -->
<link href = "${resources}/css/bootstrap.min.css" rel = "stylesheet">
<!-- MY SPECIAL CSS -->
<link href = "${resources}/myapp.css" rel = "stylesheet">
<!-- GLYPHICON CSS -->
<link href = "${resources}/fonts.css" rel = "stylesheet">
</head>
<body>
<script src = "${resources}/js/bootstrap.js"></script>

<div  class="container">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
    <c:forEach items="${categories}" var="item" >
      <li class="nav-item active">
        <a class="nav-link" href="${Root}/category/${item.id}"> ${item.name}<span class="badge badge-primary badge-pill">${item.quantity} </span></a>
      </li>
     </c:forEach>
     
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search for Product" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  
</nav>







<nav class="nav nav-pills nav-justified">
  <a class="nav-link active" href="${Root}/home">Home</a>
  <a class="nav-link" href="#">Profile</a>
   <a class="nav-link " href="#">ManageProducts</a>
   <a class="nav-link " href="${Root}/dashboard">Dashboard</a>
   
   <sec:authorize access="isAnonymous()">
    <a class="nav-link " href="${Root}/signup">SignUp</a>   
  <a class="nav-link" href="${Root}/login">Login</a>
  </sec:authorize>
  <sec:authorize access="isAuthenticated()">
  <a class="nav-link " href="${Root}/logout">Logout</a>
  </sec:authorize>
  
     <a class="nav-link " href="${Root}/dashboard"> <h5> ${accountGlobal.fullName}</h5></a>
     
    <a class="nav-link glyphicon glyphicon-shopping-cart" href="${Root}/cartline"> Cart: 
    <div> Qty:${cart.quantity}  </div>
    <div> &#8358;${cart.totalPrice }</div>
    </a>  

  
  
</nav>


<!-- CART message -->
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
      <th scope="col">Item</th>
       
       <th scope="col"> Still in Stock</th>
         <th scope="col">single item</th>
      <th scope="col">Your Quantity</th>
      <th scope="col">Total Price of Item</th>
    </tr>
  </thead>
  <tbody>
         <sf:form
method="post"
action="${Root}/cartline"
modelAttribute="cartlineForm"
>
  <c:forEach items="${cartlineForm.cartlineList}" var="item" varStatus="status">
    <tr>
      <th scope="row"></th>
      <td><img class="productframe" src="${photos}/${item.product.id}.jpg"></td>
      <td>${item.name }</td>
      
      <td>${item.inStock}</td>
  <td>&#8358;${item.price }</td>

<!-- PROBLEM -->

<td>
<sf:input value="${item.quantity}" path="cartlineList[${status.index}].quantity" type="number" min="0" max="${item.inStock}" class="form-control col-xs-2" id="inputCity"/>


  <sf:input type="hidden" path="cartlineList[${status.index}].id"/>
  <sf:input type="hidden" path="cartlineList[${status.index}].price"/>
   <sf:input type="hidden" path="cartlineList[${status.index}].name"/>
  <sf:input type="hidden" path="cartlineList[${status.index}].account"/>

  <button type="submit" class="glyphicon glyphicon-refresh btn btn-primary col-xs-1"></button>
 </td>


<!-- PROBLEM -->
 <td>&#8358;${item.totalPrice}</td>
 <br>
 <td>
        <a href="${Root}/cartline/removeItem/${item.id}" class="btn btn-danger " ><span class="
glyphicon glyphicon-remove">Remove</span></a></td>
        
  
    </tr>
    
    </c:forEach>
    </sf:form>
    
    </tbody>
    </table>
    <div>
    <div style="position:absolute; left:70%">
    <h5>Total Price:&#8358; ${totalprice}</h5>
    <div >
        <a href="${Root}/cartline/removeAllItems" class="btn btn-danger">Remove All</a>
    
    <a href="${Root}/cartline/checkout" class="btn btn-success">Check Out</a>
    </div>
	</div>
	</div>
















</div>
</body>
</html>