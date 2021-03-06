
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


<!DOCTYPE html>
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
<div class="media">
  <img class="productframe-big align-self-start mr-3" src="${photos}/${product.id}.jpg" alt="Generic placeholder image">
  <div class="media-body">
    <h5 class="mt-0">${product.name }</h5>
    <p>${product.description }</p>
    <p>&#8358;${product.price }</p>
    <p>In Stock: ${product.quantity }</p>
  </div>
</div>
<div style="width: 70px; position: absolute; left:800px; top: 280px;">
<sf:form
method="post"
action="${Root}/product/add_to_cart/${product.id }"
modelAttribute="cartline"
>
<sf:select path="quantity" type="text" class="form-control col-xs-2" id="inputCity">
      <sf:options items="${productSelect}"/>
      </sf:select>
       <sf:input type="hidden" path="id"/>
  <sf:input type="hidden" path="price"/>
   <sf:input type="hidden" path="name"/>
  <sf:input type="hidden" path="account"/>
  <sf:input type="hidden" path="product"/>
    <sf:input type="hidden" path="supplier"/>
 <br>
   <button type="submit" class="glyphicon glyphicon-shopping-cart btn btn-primary">Add to Cart</button>
 </div>
      
</sf:form>

</div>
</body>
</html>