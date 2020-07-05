package com.infoshareacademy.repository;


import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Rating;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface RatingRepository {

    Optional<Rating> findByDrinkId(Long drinkId);

    Rating updateRating(Long drinkId, Long vote);

    void saveRating(Rating rating);
}