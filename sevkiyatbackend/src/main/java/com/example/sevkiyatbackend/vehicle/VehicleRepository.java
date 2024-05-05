package com.example.sevkiyatbackend.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
