package fr.open.roman.unitedcooking.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.Device;
import fr.open.roman.unitedcooking.repositories.DeviceRepository;
import fr.open.roman.unitedcooking.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService{

	private final DeviceRepository deviceRepository;

	public DeviceServiceImpl(DeviceRepository deviceRepository) {
		super();
		this.deviceRepository = deviceRepository;
	}

	
	@Override
	public Device createDevice(@Valid String name) {
		return deviceRepository.save(new Device(name));
	}

	@Override
	public Optional<Device> recoveryCategoryByName(String name) {
		return deviceRepository.findByName(name);
	}

	@Override
	public Optional<Device> recoveryCategoryById(Long id) {
		return deviceRepository.findById(id);
	}

	@Override
	public List<Device> recoveryAllDevices() {
		return deviceRepository.findAll();
	}

	@Override
	public boolean deleteDevice(Long id) {
		if(deviceRepository.existsById(id)) {
			deviceRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}


	@Override
	public boolean existsByName(String name) {
		return deviceRepository.existsByName(name);
	}
	
	
	
}
