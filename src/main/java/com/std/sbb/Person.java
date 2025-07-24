package com.std.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString // instance address info가 아닌 real value를 가져오기 위해
public class Person {
        private int id;
        private int age;
        private String name;
}
