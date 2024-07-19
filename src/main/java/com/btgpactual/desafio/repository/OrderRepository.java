package com.btgpactual.desafio.repository;

import com.btgpactual.desafio.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity,Long> {
}
