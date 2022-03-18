package fr.open.roman.unitedcooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.open.roman.unitedcooking.models.Device;
import fr.open.roman.unitedcooking.models.dto.DeviceReceipt;
import fr.open.roman.unitedcooking.models.exception.AlreadyTakenException;
import fr.open.roman.unitedcooking.service.DeviceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unitedcooking/device")
public class DeviceController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final DeviceService deviceService;

	public DeviceController(DeviceService deviceService) {
		super();
		this.deviceService = deviceService;
	}
	
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
	public List<String> processInvalidDataWithDetails(ConstraintViolationException exception) {
		return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
	}
	@ExceptionHandler(fr.open.roman.unitedcooking.models.exception.AlreadyTakenException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String processAlreadyTakenException(Exception exception) {
		return exception.getMessage();
	}
	

	@PostMapping("/save")
	public Device addDevice(@Valid @RequestBody DeviceReceipt dto, BindingResult result) throws AlreadyTakenException {
		log.info("addDevice in DeviceController est appelée");
		if (deviceService.existsByName(dto.getName())) {
			log.info("le nom de cet appareil est déjà pris : " + dto.getName());
			throw new AlreadyTakenException("Le nom de cet appareil est déjà pris !");
		}
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors) {
				log.error("Validation error ->" + objectError.getDefaultMessage());
			}
		}
		return deviceService.createDevice(dto.getName());
	}

	@GetMapping("/all")
	public List<Device> recoveryAllCategories() {
		log.info("recoveryAllDevices in DeviceController est appelée");
		return deviceService.recoveryAllDevices();
	}
}
