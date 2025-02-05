package com.example.webserviceEjercicio.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.webserviceEjercicio.models.Productos;

@Repository
public interface ProductoRepository extends MongoRepository<Productos, String> {
}

