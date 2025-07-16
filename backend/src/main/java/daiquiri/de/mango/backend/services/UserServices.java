package daiquiri.de.mango.backend.services;

import daiquiri.de.mango.backend.model.User;
import daiquiri.de.mango.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;


    public String registerUser (User user){
        //validacion para que no se repita el carne
        if (userRepository.existsById(user.getId())){
            return "Error: El numero de carne ya se encuentra registrado";
        }
        //validacion para que el email no se repita
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            return "Error: El email ya se encuentra en uso.";

        }

        userRepository.save(user);
        return "El usuario se registro correctamente";
    }

    public List<User> getAllUsers (){
        return this.userRepository.findAll();

    }

    public Optional<User> getByCarne (String id){
        return this.userRepository.findById(id);


    }

    public void deleteByCarne (String id){
        this.userRepository.deleteById(id);


    }

}
