package daiquiri.de.mango.backend.controller;

import daiquiri.de.mango.backend.model.User;
import daiquiri.de.mango.backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping ("/api/users")
@CrossOrigin (origins = "*")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity <?> registerUser (@Validated @RequestBody User user){
        //se crea un objeto de tipo String, que va a utilizar el metodo para guardar el usuario
        String result = this.userServices.registerUser(user);

        //se crea una condicion basada en las validaciones anteriores
        if (result.startsWith("Error")) {
            return ResponseEntity.badRequest().body(result);
        }
        if (user.getEmail() == null || user.getName() == null || user.getPassword() == null || user.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos los campos son obligatorios.");

        }


        return ResponseEntity.ok(result);

    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<?> updateUser (@Validated @PathVariable String id, @RequestBody User userDets, BindingResult result){
        if(result.hasErrors()){
            Map <String, String> errors = new HashMap<>();

            for (FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Optional<User> userOp = this.userServices.getByCarne(id);
        if (userOp.isPresent()){
            User user = userOp.get();
            user.setName(userDets.getName());
            user.setEmail(userDets.getEmail());
            user.setPassword(userDets.getPassword());
            String updated = this.userServices.registerUser(user);
            return ResponseEntity.ok("El usuario se actualizo con exito" +updated );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado.");

    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity <?> deleteUser (@PathVariable String id) {
        Optional<User> userOp =this.userServices.getByCarne(id);

        if (userOp.isPresent()){
            this.userServices.deleteByCarne(id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado");
    }



    @GetMapping ("/get/{id}")
    public ResponseEntity<?> getById (@PathVariable String id){
        Optional<User> u = this.userServices.getByCarne(id);
        if (u.isPresent()){
            return ResponseEntity.ok(u);

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado.");
    }

    @GetMapping ("/get/All")
    public ResponseEntity<?> getAll (){
        List <User> u =this.userServices.getAllUsers();

        if (u.isEmpty()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se han registrado usuarios aun. ");
        }

        return ResponseEntity.ok(u);

    }

}
