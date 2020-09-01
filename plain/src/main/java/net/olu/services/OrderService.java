package net.olu.services;

import java.util.List;

import net.olu.models.Cartline;
import net.olu.models.Orderline;

public interface OrderService {
public List<Orderline> setOrderline(List<Cartline> cartline);
}
