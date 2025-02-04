package com.example.webserviceEjercicio;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Productos, String> {
}

