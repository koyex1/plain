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
        <a class="nav-link" href="${Root}/category/${item.id}"> ${item.name}<span class="badge badge-primary badge-pill">${item.quantity } </span></a>
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
</nav>




<div>
<c:if test="${not empty operation}">
<div class="alert alert-success" role="alert">
  ${operation}
</div>
</c:if>


<div class="row">
<!-- COLUMN ONE -->
    <div class="col-sm">
<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Description</th>
      <th scope="col">Price</th>
      <th scope="col">Quantity</th>
      <th scope="col">Category</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${users_products}" var="item">
    <tr>
      <th scope="row"></th>
      <td>${item.name }</td>
      <td>${item.description }</td>
      <td>${item.price }</td>
       <td>${item.quantity }</td>
      <td>${item.category.name }</td>
        <td><a href="${Root}/dashboard/manageproducts/product_edit/${item.id}" class="btn btn-success" ><span class="glyphicon glyphicon-pencil">Edit</span></a>
        <br>
        <a href="${Root}/dashboard/manageproducts/product_delete/${item.id}" class="btn btn-danger " ><span class="glyphicon glyphicon-trash">Delete</span></a></td>
        
  
    </tr>
    </c:forEach>
    </tbody>
    </table>

  </div>
  
  <!-- COLUMN 2 -->
    <div class="col-lg">
<sf:form
method="post"
action="${Root}/dashboard/manageproducts_product_process"
modelAttribute="product">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Name</label>
      <sf:input path="name" type="text" class="form-control" id="inputEmail4" />
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Description</label>
      <sf:input path="description" type="text" class="form-control" id="inputPassword4"/>
    </div>
  </div>
  <div class="form-group">
    <label for="inputAddress">Price</label>
    <sf:input path="price" type="text" class="form-control" id="inputAddress" />
  </div>
  <div class="form-group">
    <label for="inputAddress2">Quantity</label>
    <sf:input path="quantity" class="form-control" id="inputAddress2" />
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputCity">Category</label>
      <sf:select path="category" type="text" class="form-control" id="inputCity">
      
      <sf:options
      items="${categories }"
      itemValue="id"
      itemLabel="name"
      />
      
      </sf:select>
    </div>
  </div>
  <sf:input type="hidden" path="id"/>
  <sf:input type="hidden" path="account"/>
  <button type="submit" class="btn btn-primary">${buttonProduct}</button>
</sf:form>

<br>
 <br>
 <sf:form
method="post"
action="${Root}/dashboard/manageproducts_category_process"
modelAttribute="category">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4"> New Category</label>
      <sf:input path="name" type="text" class="form-control" id="inputEmail4" />
    </div>
    </div>
    <sf:hidden path="id"/>
        <sf:hidden path="quantity"/>
    
      <button type="submit" class="btn btn-primary">${buttonCategory} Category</button>
    
    </sf:form>
    
    <br>
    
    <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Category Name</th>
      <th scope="col">Quantity</th>

    </tr>
  </thead>
  <tbody>
  <c:forEach items="${categories }" var="item">
    <tr>
      <th scope="row"></th>
      <td>${item.name }</td>
      <td>${item.quantity }</td>
            <td><a href="${Root}/dashboard/manageproducts/category_edit/${item.id}" class="btn btn-success glyphicon glyphicon-pencil"><span>Edit</span></a></td>
        <td><a href="${Root}/dashboard/manageproducts/category_delete/${item.id}" class="btn btn-danger glyphicon glyphicon-trash"><span>Delete</span></a></td>
      
    </tr>
    </c:forEach>
    </tbody>
    </table>

 </div>
 
    
 
    </div>



</div>




</div>

</body>


</html>