package com.gai_app.gai_owners.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import com.gai_app.gai_owners.entity.Gender;

import java.time.LocalDate;


@Component
@Data
public class OwnerModel {
    private Long id;
    private String name;
    private LocalDate dob;
    private Gender gender;
}
