package fr.open.roman.unitedcooking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.open.roman.unitedcooking.models.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>{

	Optional<Device> findByName(String name);

	boolean existsByName(String name);
	
}
