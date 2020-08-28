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
</head>
<body>
<script src = "${resources}/js/bootstrap.js"></script>
<div  class="container">
<nav class="nav nav-pills nav-justified">
  <a class="nav-link active" href="${Root}/home">Home</a>
  <a class="nav-link" href="#">Profile</a>
   <a class="nav-link " href="#">ManageProducts</a>
    <a class="nav-link " href="#">Products</a>
   <a class="nav-link " href="${Root}/dashboard">Dashboard</a>
    <a class="nav-link " href="#">SignUp</a>   
  <a class="nav-link" href="${Root}/login">Login</a>
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

<sf:form
method="post"
action="${Root}/dashboard/profile_submit"
modelAttribute="details">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">FullName</label>
      <sf:input path="fullName" type="text" class="form-control" id="inputEmail4" />
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Username</label>
      <sf:input path="username" type="text" class="form-control" id="inputPassword4"/>
    </div>
  </div>
  <div class="form-group">
    <label for="inputAddress">Email</label>
    <sf:input path="email" type="text" class="form-control" id="inputAddress" />
  </div>
  <div class="form-group">
    <label for="inputAddress2">Password</label>
    <sf:password path="password" class="form-control" id="inputAddress2" />
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputCity">Address</label>
      <sf:input path="address" type="text" class="form-control" id="inputCity"/>
    </div>
    
    <div class="form-group col-md-2">
      <label for="inputZip">Phone</label>
      <sf:input path="phone" type="text" class="form-control" id="inputZip"/>
    </div>
  </div>
  <sf:input type="hidden" path="id"/>
  <sf:input type="hidden" path="status"/>
  <sf:input type="hidden" path="task"/>
  <button type="submit" class="btn btn-primary">Update</button>
</sf:form>
</div>




</div>

</body>


</html>