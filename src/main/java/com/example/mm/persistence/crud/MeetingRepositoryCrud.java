package com.example.mm.persistence.crud;

import com.example.mm.model.Meeting;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Martin on 29-Jun-17.
 */
public interface MeetingRepositoryCrud extends CrudRepository<Meeting, Long> {

}
