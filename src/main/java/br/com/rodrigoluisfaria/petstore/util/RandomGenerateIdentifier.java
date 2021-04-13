package br.com.rodrigoluisfaria.petstore.util;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class RandomGenerateIdentifier {

    private static final Integer MAX_ID = 150000;
    private final Random random = new Random();
    private final Set<Integer> generatedNumbers = new HashSet<>();

    public Integer generateId() {
        Integer id;

        do {
            id = random.nextInt(MAX_ID);
        } while (generatedNumbers.contains(id));

        generatedNumbers.add(id);
        return id;
    }
}
