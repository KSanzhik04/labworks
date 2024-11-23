package com.example.labwork12;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;import java.time.format.DateTimeFormatter;
import java.util.Random;import java.util.List;
import java.util.ArrayList;
@RestController
public class Controller {
    @GetMapping("/currentTime")
    public String getCurrentTime() {        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss w");
        return LocalDateTime.now().format(formatter);    }

    @GetMapping("/random_number")
    public double getRandomNumber() {        Random random = new Random();
        return 1 + random.nextDouble() * 499;    }


    @GetMapping("/{string}")
    public String reverseString(@PathVariable String string) {
        return string;    }

    private final int secretNumber = new Random().nextInt(100) + 1;
    @GetMapping("/game")    public String guessNumber(@RequestParam(name = "n") int guessedNumber) {
        if (guessedNumber < secretNumber) {            return "Ваше число меньше загаданного числа.";
        } else if (guessedNumber > secretNumber) {            return "Ваше число больше загаданного числа.";
        } else {            return "Поздравляю! Вы угадали число!";
        }    }

    @GetMapping("/number")
    public String getNumber(@RequestParam(name = "n") int n) {        if (n >= 1 && n <= 10) {
        return "Ваше число: " + n;        } else {
        return "у вас цифра выходит за рамки дозволенного !";        }
    }@GetMapping("/api")
    public List<Integer> getNumbers(@RequestParam(name = "q") int n) {        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {            numbers.add(i);
        }        return numbers;
    }    @GetMapping("/fib")
    public long getFibonacci(@RequestParam(name = "number")int n){        if (n<=0) return 0;
        if (n==1) return 1;
        long[] fib = new long[n + 1];        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i<=n; i++){            fib[i] = fib[i-1]+fib[i-2];
        }        return fib[n];
    }
    @GetMapping("/primes")
    public List<Integer> getPrimes(@RequestParam(name = "n") int n) {        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {            if (isPrime(i)) {
            primes.add(i);            }
        }        return primes;
    }
    private boolean isPrime(int num) {
        if (num < 2) return false;        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {                return false;
            }        }
        return true;    }
}
