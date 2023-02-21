package com.keremcengiz0.creditapplicationsystem.services.concretes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ScoreServiceTest {

    @InjectMocks
    private ScoreService scoreService;

    @Test
    void generateRandomScore() {
        int score = scoreService.generateRandomScore("12345678912");
        assertTrue(score > 300 && score <2000);
    }

    @Test
    void getScore() {
        scoreService.generateRandomScore("12345678901");
        int score = scoreService.getScore("12345678912");
        assertTrue(score >= 300 && score <= 1999);
    }
}