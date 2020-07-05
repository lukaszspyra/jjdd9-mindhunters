package com.infoshareacademy.service;

import com.infoshareacademy.domain.Rating;
import com.infoshareacademy.repository.DrinkRepository;
import com.infoshareacademy.repository.RatingRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.Optional;

@Stateless
public class RatingService {

    @EJB
    private RatingRepository ratingRepository;

    @EJB
    private DrinkRepository drinkRepository;


    public Rating getRatingByDrinkId(Long drinkId) {

        return ratingRepository.findByDrinkId(drinkId).orElseGet(() -> createEmptyRating(drinkId));

    }


    public Double getCalculatedRatingByDrinkId(Long drinkId) {

        final Rating rating = getRatingByDrinkId(drinkId);

        if (rating.getSum() == 0) {
            return 0.0;
        }

        return (double) rating.getSum() / rating.getNumberOfRatings();

    }


    private Rating createEmptyRating(Long drinkId) {

        Rating initialRating = new Rating();
        initialRating.setNumberOfRatings(0L);
        initialRating.setSum(0L);
        initialRating.setDrink(drinkRepository.findDrinkById(drinkId));

        ratingRepository.saveRating(initialRating);

        return initialRating;
    }

    @Transactional
    public Rating updateRating(Long drinkId, String ratingParam) {

        return ratingRepository.updateRating(drinkId, Long.valueOf(ratingParam));

    }

}