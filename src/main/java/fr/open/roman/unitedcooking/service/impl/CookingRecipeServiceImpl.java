package fr.open.roman.unitedcooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.Category;
import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.Device;
import fr.open.roman.unitedcooking.models.Ingredient;
import fr.open.roman.unitedcooking.models.Member;
import fr.open.roman.unitedcooking.models.Type;
import fr.open.roman.unitedcooking.models.dto.CookingRecipeReceipt;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCategoryException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundMemberException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundTypeException;
import fr.open.roman.unitedcooking.repositories.CookingRecipeRepository;
import fr.open.roman.unitedcooking.service.CategoryService;
import fr.open.roman.unitedcooking.service.CookingRecipeService;
import fr.open.roman.unitedcooking.service.DeviceService;
import fr.open.roman.unitedcooking.service.IngredientService;
import fr.open.roman.unitedcooking.service.MemberService;
import fr.open.roman.unitedcooking.service.TypeService;

@Service
public class CookingRecipeServiceImpl implements CookingRecipeService{

	private final CookingRecipeRepository cookingRecipeRepository;
	private final TypeService typeService;
	private final IngredientService ingredientService;
	private final DeviceService deviceService;
	private final CategoryService categoryService;
	private final MemberService	memberService;

	public CookingRecipeServiceImpl(CookingRecipeRepository cookingRecipeRepository, TypeService typeService, 
			IngredientService ingredientService, DeviceService deviceService, CategoryService categoryService, MemberService memberService) {
		super();
		this.cookingRecipeRepository = cookingRecipeRepository;
		this.typeService = typeService;
		this.ingredientService = ingredientService;
		this.deviceService = deviceService;
		this.categoryService = categoryService;
		this.memberService = memberService;
	}


	@Override
	public CookingRecipe createCookingRecipeAndAddInListOfCreatedRecipesOfMember(@Valid CookingRecipeReceipt dto) 
			throws NotFoundCategoryException, NotFoundTypeException, NotFoundMemberException {
		
		Member member = RecuperationOfMember(dto.getMemberPseudo());
		
		CookingRecipe cookingRecipe = cookingRecipeRepository.save(new CookingRecipe(
				dto.getName(), 
				dto.getPreparationTime(), 
				dto.getCookingTime(), 
				dto.getDescription(), 
				selectionOfIngredients(dto.getIngredients()), 
				selectionOfDevices(dto.getDevices()), 
				RecuperationOfCategory(dto.getCategoryName()), 
				RecuperationOfType(dto.getTypeName()), 
				member
				));

		memberService.addToListOfCreatedCookingRecipes(member, cookingRecipe);
		
		return cookingRecipe;
	}

	private Member RecuperationOfMember(String memberPseudo) throws NotFoundMemberException {
		if(memberService.recoveryMemberByPseudo(memberPseudo).isPresent()) {
			return memberService.recoveryMemberByPseudo(memberPseudo).get();
		}
		throw new NotFoundMemberException("Le membre n'a pas été trouvé !"); 

	}
	private Type RecuperationOfType(String typeName) throws NotFoundTypeException {
		if(typeService.recoveryTypeByName(typeName).isPresent()) {
			return typeService.recoveryTypeByName(typeName).get();
		}
		throw new NotFoundTypeException("Le type n'a pas été trouvé !"); 
	}
	private Category RecuperationOfCategory(String categoryName) throws NotFoundCategoryException {
		if(categoryService.recoveryCategoryByName(categoryName).isPresent()) {
			return categoryService.recoveryCategoryByName(categoryName).get();
		} 
		throw new NotFoundCategoryException("La catégorie n'a pas été trouvé !");
	}
	private List<Device> selectionOfDevices(List<String> devices) {
		List<Device> listOfDevicesSelected = new ArrayList<Device>();
		for (String device : devices) {
			if(deviceService.recoveryCategoryByName(device).isPresent()) {
				listOfDevicesSelected.add(deviceService.recoveryCategoryByName(device).get());
			} else {
				deviceService.createDevice(device);
			}

		}
		return listOfDevicesSelected;
	}
	private List<Ingredient> selectionOfIngredients(List<String> ingredients) {
		List<Ingredient> listOfIngredientsSelected = new ArrayList<Ingredient>();
		for (String ingredient : ingredients) {
			if(ingredientService.recoveryIngredientByName(ingredient).isPresent()) {
				listOfIngredientsSelected.add(ingredientService.recoveryIngredientByName(ingredient).get());
			} else {
				ingredientService.createIngredient(ingredient);
			}
		}
		return listOfIngredientsSelected;
	}
	
	@Override
	public CookingRecipe patchModerateByAdmin(CookingRecipe cookingRecipe) {
		return cookingRecipeRepository.save(cookingRecipe);
	}

	@Override
	public Optional<CookingRecipe> recoveryCookingRecipeById(Long id) {
		return cookingRecipeRepository.findById(id);
	}

	@Override
	public List<CookingRecipe> recoveryAllCookingRecipes() {
		return cookingRecipeRepository.findAll();
	}
	
	@Override
	public List<CookingRecipe> recoveryAllCookingRecipesModerateByAdmin() {
		return cookingRecipeRepository.findAll().stream().filter(admin -> admin.getAdmin() != null).toList();
	}

	@Override
	public boolean deleteCookingRecipe(Long id) {
		if(cookingRecipeRepository.existsById(id)) {
			Member member = cookingRecipeRepository.findById(id).get().getMember();
			memberService.deleteToListOfCreatedCookingRecipes(member, recoveryCookingRecipeById(id).get());
			cookingRecipeRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean existsByName(String name) {
		return cookingRecipeRepository.existsByName(name);
	}

	@Override
	public Boolean existsById(Long id) {
		return cookingRecipeRepository.existsById(id);
	}


}
