package com.gai_app.gai_owners.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import com.gai_app.gai_owners.entity.Gender;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@Component
public class OwnerDto {

    private Long id;


    @NotBlank(message = "Name cannot be null or blank")
    @Pattern(regexp = "^[a-zA-zа-яА-Я-. ]+$",
            message = "Name must contain only letters, dashes, points and spaces")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;


    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dob;

    @NotNull(message = "Gender of birth cannot be null")
    private Gender gender;

    @AssertTrue(message = "Owner must be at least 18 and younger 200 years old")
    @JsonIgnore
    public boolean isAdult() {
        int age = Period.between(dob, LocalDate.now()).getYears();
        return age >= 18 && age <= 200;
    }
}
