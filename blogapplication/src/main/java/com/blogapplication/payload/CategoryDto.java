package com.blogapplication.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {


        private Integer categoryId;
        @NotBlank
        @Size(min=4,message = "minium category title size should be 4")
        private String categoryTitle;

        @NotBlank
        @Size(min=10,message = "minimum category description length is 10")
        private String categoryDescription;

    }


