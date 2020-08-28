<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>LOGIN PAGE</h1>
<c:if test="${not empty operation}">
<div class="alert alert-danger" role="alert">
  ${operation}
</div>
</c:if>

<form
action="${Root}/login"
method="post"
>
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input name="username" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="XXX@yahoo.com">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
  <button type="submit" class="btn btn-primary">Log In</button>
</form>