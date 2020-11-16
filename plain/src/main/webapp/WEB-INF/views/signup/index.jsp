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
<link href = "${resources}/myapp.css" rel = "stylesheet">
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
<div class="profileform" style="">
<sf:form
method="post"
action="${Root}/signup/signup_process"
modelAttribute="account"
>
  <div class="form-group">
    <label for="exampleInputEmail1">Full Name</label>
    <sf:input path="fullName" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="XXX@yahoo.com"/>
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">Email Address</label>
    <sf:input path="email" type="text" class="form-control" id="exampleInputPassword1" placeholder="email"/>
  </div>
   <div class="form-group">
    <label for="exampleInputEmail1">Username</label>
    <sf:input path="username" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="username"/>
  </div>
   <div class="form-group">
    <label for="password">Password</label>
    <sf:input path="password" type="password" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="password"/>
  </div>
   <div class="form-group">
    <label for="exampleInputEmail1">Phone</label>
    <sf:input path="phone" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="XXX-XXX-XXX"/>
  </div>
   <div class="form-group">
    <label for="exampleInputEmail1">Address</label>
    <sf:input path="address" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Address"/>
  </div>
  
  <div class="form-group">
    <label for="exampleInputEmail1">Role</label>
      <sf:select path="task" type="text" class="form-control" id="inputCity">
    <sf:options 
    items="${task}"
    itemValue="id"
    itemLabel="name"
    />
    </sf:select>
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Submit</button>
  <sf:hidden path="status"/>
    <sf:hidden path="id"/>
  
</sf:form>
</div>
</div>
</div>
</body>
</html>