package com.std.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

// @Controller
@Controller
public class HomeController {
    @GetMapping("/home/plus")
    @ResponseBody
    //http://localhost:8080/home/plus?first=10&second=20
    //http://localhost:8080/home/plus?first=20&second=
    //http://localhost:8080/home/plus?first=20&second=%E3%85%87%E3%85%85    // 오류
    public int plus(@RequestParam(value = "first", defaultValue = "0") int a,
                    @RequestParam(value = "second", defaultValue = "0") int b
    ) {
        return a + b;
    }

    @GetMapping("/home/ArithmeticOperations")
    @ResponseBody
    //http://localhost:8080/home/ArithmeticOperations?first=10&second=20&Operation=-
    //http://localhost:8080/home/ArithmeticOperations?first=20&second=
    //http://localhost:8080/home/ArithmeticOperations?first=20&second=%E3%85%87%E3%85%85    // 오류
    public int ArithmeticOperations(
            @RequestParam(value = "first", defaultValue = "0") int a,
            @RequestParam(value = "second", defaultValue = "0") int b,
            @RequestParam(value = "Operation") String c
    ) {
        // return c;
        int t = 0;
        if (c.equals("m")) return a - b;
        if (c.equals("+")) return a + b;
        return -1; //anyway you need it
    }

    @GetMapping("/home/returnCar")
    @ResponseBody
    public Car showReturnCar() {
        Car car = new Car(1, 100, "벤츠", new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
        }});

        car.setId(2);
        car.setSpeed(200);
        car.setName("아우디");
        car.setIds(new ArrayList<>() {{
            add(4);
            add(5);
            add(6);
        }});

        System.out.println(car.getId());
        System.out.println(car.getSpeed());
        System.out.println(car.getName());
        System.out.println(car.getIds());

        return car;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    class Car {
        private int id;
        private int speed;
        private String name;
        private List<Integer> ids;
    }
}