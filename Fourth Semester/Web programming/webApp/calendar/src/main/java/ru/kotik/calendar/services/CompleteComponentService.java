package ru.kotik.calendar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kotik.calendar.repositories.CompleteComponentsRepository;

@Service
public class CompleteComponentService {

    @Autowired
    private CompleteComponentsRepository completeComponentsRepository;
}
