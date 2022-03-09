package fr.open.roman.unitedcooking.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.open.roman.unitedcooking.models.Type;
import fr.open.roman.unitedcooking.service.TypeService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TypeServiceIT {

	@Autowired
	private TypeService typeService;


	//	private static ValidatorFactory validatorFactory;
	//	private static Validator validator;
	//
	//	@BeforeAll
	//	public static void createValidator() {
	//		validatorFactory = Validation.buildDefaultValidatorFactory();
	//		validator = validatorFactory.getValidator();
	//	}
	//
	//	@AfterAll
	//	public static void close() {
	//		validatorFactory.close();
	//	}

	@Test
	@Order(1)
	void createType_perfectName_shouldSuccess() {
		Type type = typeService.createType("PornFood");

		assertNotNull(type);
		assertEquals("PornFood", type.getName());

		int size = typeService.recoveryAllTypes().size();
		assertEquals("PornFood", typeService.recoveryTypeById((long)size).get().getName());
	}

	@Test
	@Order(2)
	void createType_noName_shouldFail() {
		//		Type type = typeService.createType("");
		//		
		//	    Set<ConstraintViolation<Type>> violations = validator.validate(type);
		//System.out.println("HERE VIOLATIONS : =>>>>>>>>>          " + violations);
		//	    assertFalse(violations.isEmpty());
	}

	//	@Test
	//	@Order(3)
	//	void recoveryTypeByName_knownName_shouldSuccess() {
	//		
	//	}
	//	
	//	@Test
	//	@Order(4)
	//	void recoveryTypeByName_unknownName_shouldFail() {
	//		
	//	}
	//	
	//	@Test
	//	@Order(5)
	//	void recoveryTypeById_knownId_shouldSuccess() {
	//		
	//	}
	//	
	//	@Test
	//	@Order(6)
	//	void recoveryTypeById_unknownId_shouldFail() {
	//		
	//	}
	//	
	//	@Test
	//	@Order(7)
	//	void recoveryAllTypes_recoverTwoElement_shouldSuccess() {
	//		
	//	}
	//	
	//	@Test
	//	@Order(8)
	//	void recoveryAllTypes_recoverOneElement_shouldFail() {
	//		
	//	}
}
