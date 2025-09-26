package org.liu.demo.mongodb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexEnum {

    MAN("0", ""), FEMALE("1", "");

    private final String code;
    private final String description;

}
