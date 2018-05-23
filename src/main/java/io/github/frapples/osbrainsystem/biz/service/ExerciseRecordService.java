package io.github.frapples.osbrainsystem.biz.service;

import io.github.frapples.osbrainsystem.dal.repository.ExerciseBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseRecordService {

    @Autowired
    ExerciseBookRepository exerciseBookRepository;

}
