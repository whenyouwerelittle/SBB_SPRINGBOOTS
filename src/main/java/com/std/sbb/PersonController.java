package com.std.sbb;

import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    int lastId;
    List<Person> people;

    PersonController() {
        lastId = 0;
        people = new ArrayList<>();
    }

    @GetMapping("/person/add")
    @ResponseBody
    public String addPerson(
            @RequestParam("name") String name,
            @RequestParam("age") int age) {
        lastId++;
        Person p = new Person(lastId, age, name);
        people.add(p);

        return String.format("%d번째 사람이 추가되었습니다.", p.getId());
    }

    @GetMapping("/person/people")
    @ResponseBody
    public List<Person> getPeople() {
        System.out.println(people);
        return people;
    }

    @GetMapping("/person/remove")
    @ResponseBody
    public String removePerson(@RequestParam("id") int id) {
        boolean removed = false;

        for (Person person : people) {
            if (person.getId() == id) {
                people.remove(person);
                removed = true;
            }
        }

        if ( removed == false ) {
            return id + "번 사람이 존재하지 않습니다.";
        }

        return id + "번 사람이 삭제되었습니다.";
    }

    @GetMapping("/person/remove2")
    @ResponseBody
    public String removePerson2(@RequestParam("id") int id) {
        boolean removed = people.removeIf(person -> person.getId() == id);

        if ( removed == false ) {
            return id + "번 사람이 존재하지 않습니다.";
        }

        return id + "번 사람이 삭제되었습니다.";
    }
}
/*

@AllArgsConstructor //모든 필드를 받는 생성자 자동 등록
@Getter // 각 필드에 대한 getter/setter 자동 생성
@Setter
@ToString // - 객체를 출력할 때 주소값이 아닌 실제 필드 값을 보여줌 instance address info가 아닌 real value를 가져오기 위해
class Person {      // 사람의 정보를 담는 데이터 모델 클래스
    private int id;
    private int age;
    private String name;
}
*/

/*
    //  @AllArgsConstructor @Getter, @Setter 를 사용하지 않은 경우 직접 작성
    //  @ToString 관련 객체의 값을 반환할 때 instance address가 아닌 real value 를 반환하기 위해 적용
    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter methods
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
 */
