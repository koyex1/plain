package net.olu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.olu.models.Cartline;

@Repository("cartlineRepository")
public interface CartlineRepository extends JpaRepository<Cartline, Integer>{

}
