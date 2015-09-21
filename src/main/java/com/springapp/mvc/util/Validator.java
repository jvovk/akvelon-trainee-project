package com.springapp.mvc.util;

import com.springapp.mvc.bean.FilmBean;
import com.springapp.mvc.domain.Film;
import com.springapp.mvc.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Y. Vovk on 20.09.15.
 */
@Component
public class Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);

    public Film validate(FilmBean obj) {
        Builder builder = new Builder();

        if (Utils.isEmptyString(obj.getName())) {
            builder.append("Name can't be empty! ");
        }

        if (Utils.isEmptyString(obj.getGenre())) {
            builder.append("Genre can't be empty! ");
        }

        if (!Utils.isInteger(obj.getMark()) ||
                !Utils.isValidNumberRange(Integer.parseInt(obj.getMark()), 1, 10)) {
            builder.append("Mark must be a number from 1 to 10! ");
        }

        if (!Utils.isInteger(obj.getMark()) ||
                !Utils.isValidNumberRange(Integer.parseInt(obj.getYear()), 1901, 2015)) {
            builder.append("Year must be a number from 1901 to 2015! ");
        }

        if (!Utils.isValidDate(obj.getDateSeen())) {
            builder.append("Date must be yyyy-MM-dd format!");
        }

        String error = builder.build();
        if (!error.isEmpty()) {
            throw new ValidatorException(error);
        }

        return FilmBeanToFilmConverter.converter(obj);
    }
}