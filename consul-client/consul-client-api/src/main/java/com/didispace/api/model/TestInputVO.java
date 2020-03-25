package com.didispace.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class TestInputVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "name can not be blank!")
    private String name;
}
