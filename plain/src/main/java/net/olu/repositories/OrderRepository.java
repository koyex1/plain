package net.olu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.olu.models.Account;
import net.olu.models.Orderline;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Orderline, Integer> {

public List<Orderline> findBySupplier(Account supplier);
}
