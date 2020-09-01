package net.olu.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.olu.models.Account;
import net.olu.models.Orderline;
import net.olu.repositories.AccountRepository;
import net.olu.repositories.OrderRepository;

@Controller
@RequestMapping("/dashboard")
public class OrdersController {

	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("/orders")
	public String handleorders(ModelMap mp, Authentication auth
			,@RequestParam(name="operation", required=false) String operation) {
	Account account= accountRepository.findByUsername(auth.getName());
	mp.put("orders", orderRepository.findBySupplier(account));
	
	if(operation!=null) {
		if(operation.equals("order_deleted")) {
			mp.put("operation","order successfully deleted");
		}
	}
		
	return "dashboard.orders.index" ;
	}
	
	@GetMapping("order/deleteOrder/{id}")
	public String handleorderdelete(@PathVariable("id")int id) {
		Orderline orderline= orderRepository.getOne(id);
		orderRepository.delete(orderline);
		return "redirect:/dashboard/orders?operation=order_deleted";
		
	}
}
