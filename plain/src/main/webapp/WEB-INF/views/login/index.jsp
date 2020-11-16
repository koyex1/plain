<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${not empty operation}">
<div class="alert alert-success" role="alert">
  ${operation}
</div>
</c:if>

<div class="d-flex justify-content-center" style="height: 300px;" >
<div style="border:  2px solid green; padding:30px; margin-top:110px; width: 300px; height: 350px" class="d-flex justify-content-center" >
<form
action="${Root}/login"
method="post"
>
<h5>Login</h5>
  <div class="form-group">
  <div class="glyphicon glyphicon-user"></div>
    <label  for="exampleInputEmail1">Username</label>
    <input name="username" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="XXX@yahoo.com">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
  <button type="submit" class="btn btn-primary">Log In</button>
</form>
</div>
</div>