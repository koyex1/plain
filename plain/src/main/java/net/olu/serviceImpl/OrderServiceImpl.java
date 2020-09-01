package net.olu.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.olu.models.Cartline;
import net.olu.models.Orderline;
import net.olu.services.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Override
	public List<Orderline> setOrderline(List<Cartline> cartline) {
		List<Orderline> orderline= new ArrayList<Orderline>();
		
		for(int i=0; i<cartline.size(); i++) {
			Orderline o= new Orderline();
			o.setName(cartline.get(i).getName());
			o.setPrice(cartline.get(i).getPrice());
			o.setQuantity(cartline.get(i).getQuantity());
			o.setTotalPrice(cartline.get(i).getTotalPrice());
			o.setSupplier(cartline.get(i).getSupplier());
			o.setFullName(cartline.get(i).getSupplier().getFullName());
			o.setAddress(cartline.get(i).getSupplier().getAddress());
			orderline.add(o);
		}
		
		return orderline;
	}

}
