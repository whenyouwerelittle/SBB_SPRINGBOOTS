package com.std.sbb;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
/*
* 요청 : http://localhost:8080/person/add?name=홍길동&age=11
응답 : 1번 사람이 추가되었습니다.
요청 : http://localhost:8080/person/add?name=홍길순&age=22
응답 : 2번 사람이 추가되었습니다.
요청 : http://localhost:8080/person/add?name=임꺽정&age=33
응답 : 3번 사람이 추가되었습니다.
요청 : http://localhost:8080/person/people
응답
* */

@Controller // SpringBoot request/response 첫 시작점임을 알리는 곳
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
        // Person p = new Person(lastId, name, age);
        Person p = new Person(lastId, age, name); // error
        people.add(p);

        return String.format("%d번째 사람이 추가되었습니다.", p.getId());
    }

    @GetMapping("/person/people")
    @ResponseBody
    public List<Person>getPeople() {
        System.out.println(people);
        return people;  // lombok @ToString이 적용됨
    }
}


@AllArgsConstructor
@Getter
@Setter
@ToString // instance address info가 아닌 real value를 가져오기 위해
class Person {
    private int id;
    private int age;
    private String name;
}
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
