package microservice.module.eurekaclientuser.controller;

import microservice.module.eurekaclientuser.entity.UserEntity;
import microservice.module.eurekaclientuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration (@RequestBody UserEntity user)
    {
        try {
            userService.registration(user);
            return ResponseEntity.ok("User is successfully saved!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getOneUser (@RequestParam Long id)
    {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser (@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong!" + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateUser (@RequestParam Long id, @RequestBody UserEntity user)
    {
        try {
            return ResponseEntity.ok(userService.updateUserByID(id, user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something wrong! " + e.getMessage());
        }
    }
}

/* @CONTROLLER && @RESPONSEBODY

* Клас "controller" содержит в себе логику (для веб приложений) по обработке HTTP запросов (RESTful API)
* Простой контролер отправляет HTML файлы, а RestController работает с телом запроса/ответа (обьектом)
* "@RestController = @Controller + @ResponseBody" где @ResponseBody указывает Spring что
* возвращаемій методом результат должен быть преобразован в тело ответа HTTP,
* вместо отображения в представление, как это делает @Controller.
* @RequestMapping("/users") говорит о том что все запросы в этом классе будут начинаться
* с URL "/users"

* @REQUESTMAPPING

* @RequestMapping используется для сопоставления HTTP запросов с методами контроллера.
* Аннотация может быть использована как на уровне классов так и методов.
* Так же можно указывать дополнительные параметры аннотации, что бы
* ограничить ее возможности (method = RequestMethod.GET)
* Для функций есть так же аннотации @GetMapping @PostMapping @PutMapping @DeleteMapping

* @AUTOWIRED

* Автоинициализация объекта.
* Аннотация @Autowired в Spring используется для автоматической инъекции зависимостей.
* Это означает, что Spring будет автоматически находить зависимости,
* которые требуются для работы вашего класса, и автоматически их связывать.
* Для автоматического связывания можно помечать поле, метод или конструктор.

* (@RequestBody UserEntity user)

* Аннотация @RequestBody в Spring используется для связывания тела HTTP-запроса с объектом
* в методе контроллера. Она указывает, что метод должен использовать тело запроса для создания объекта,
* переданного в качестве параметра метода.
* Например: JSON: {"username": "user", "password": "pass"} переданный в качестве тела запроса,
* будет автоматически преобразован в обьект UserEntity user.

* (@RequestParam Long id)

* Аннотация @RequestParam в Spring Framework используется для привязки параметров
* запроса HTTP к параметрам метода контроллера.
* Например: (@RequestParam(name = "name", defaultValue = "World") String name)
*/