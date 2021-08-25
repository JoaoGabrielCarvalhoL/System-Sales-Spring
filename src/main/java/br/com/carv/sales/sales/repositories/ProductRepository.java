package br.com.carv.sales.sales.repositories;

import br.com.carv.sales.sales.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
