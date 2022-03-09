package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.Device;


public interface DeviceService {

	Device createDevice(@Valid String name);
	
	Optional<Device> recoveryCategoryByName(String name);
	Optional<Device> recoveryCategoryById(Long id);
	
	List<Device> recoveryAllDevices();
}
