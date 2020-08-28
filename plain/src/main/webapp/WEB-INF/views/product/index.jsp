


<div>

<div class="media">
  <img class="align-self-start mr-3" src="..." alt="Generic placeholder image">
  <div class="media-body">
    <h5 class="mt-0">${product.name }</h5>
    <p>${prodcut.description }</p>
    <p>${product.price }</p>
    <p>${product.quantity }</p>
  </div>
</div>
<sf:form
method="post"
action="${Root}/product/add_to_cart/${product.id }}"
modelAttribute="cartline"
>
<sf:select path="quantity" type="text" class="form-control col-xs-2" id="inputCity">
      <sf:options
      items="${productSelect}"
      itemValue="quantity"
      itemLabel="quantity"
      />
      
      </sf:select>
       <sf:input type="hidden" path="id"/>
  <sf:input type="hidden" path="price"/>
   <sf:input type="hidden" path="name"/>
  <sf:input type="hidden" path="account"/>
  <button type="submit" class="glyphicon glyphicon-shopping-cart btn btn-primary">Add to Cart</button>
      
</sf:form>

</div>