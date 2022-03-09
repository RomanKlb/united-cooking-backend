package fr.open.roman.unitedcooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.open.roman.unitedcooking.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

}
