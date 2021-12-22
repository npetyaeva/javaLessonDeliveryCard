package ru.netology.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class DataGenerator {
    private final Faker faker = new Faker(new Locale("ru"));
    List<String> cities = Arrays.asList("Екатеринбург", "Йошкар-Ола", "Калининград", "Калуга", "Кострома", "Краснодар",
            "Красноярск", "Курган", "Махачкала", "Петропавловск-Камчатский", "Сыктывкар", "Чебоксары",
            "Владикавказ", "Абакан", "Москва", "Казань", "Майкоп");

    public DataGenerator() {
    }

    public String generateCity() {
        Random random = new Random();
        return cities.get(random.nextInt(cities.size()));
    }

    public String generateName() {
        //return faker.name().fullName();
        return faker.name().lastName().replace("ё", "е") + " " + faker.name().firstName().replace("ё", "е");
    }

    public String generatePhone() {
        return faker.numerify("+7##########");
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
