<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div>
	<h1>DASHBOARD </h1>

	
	<nav id="spacex" class="nav nav-pills nav-justified">
  <a class="nav-link active" href="${Root}/dashboard/profile">Profile</a>
   <a class="nav-link active" href="${Root}/dashboard/manageproducts">Manage Product</a>
      <a class="nav-link active" href="${Root}/dashboard/usersdata">Access Users Data</a>
            <a class="nav-link active" href="${Root}/dashboard/orders">Orders</a>
         <a class="nav-link active" href="${Root}/dashboard/websiteUI">Website UI</a>
   </nav>
</div>