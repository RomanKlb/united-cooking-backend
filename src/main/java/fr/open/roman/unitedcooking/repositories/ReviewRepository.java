package fr.open.roman.unitedcooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.open.roman.unitedcooking.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
