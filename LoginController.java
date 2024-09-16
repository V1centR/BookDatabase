package com.bdb.bookdatabase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdb.bookdatabase.UserRepository;
import com.bdb.bookdatabase.model.User;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;


    /* Aqui neste método vc pode colocar o objeto "User" diretamente na função sem precisar do @RequestBody
     * O @RequestBody é específico para request json, por isso o erro de enctype, pois ele suporta somente raw do json.
     * 
     * */
    @PostMapping(value = "/register")
    public ResponseEntity<String> registerUser(User user) {
    	
        if (userRepository.findByName(user.getName()) != null) {
            return new ResponseEntity<>("Nome de Usuario já existe", HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>("Usuario registrado com sucesso", HttpStatus.CREATED);
    	
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> loginUser(User loginUser) {
    	
    	System.out.println("LOGIN EXEC ####");
    	System.out.println(loginUser.getEmail());
    	System.out.println(loginUser.getPassword());
    	
        User user = userRepository.findByName(loginUser.getName()); //email ou name? ####################
        if (user == null || !user.getPassword().equals(loginUser.getPassword())) {
            return new ResponseEntity<>("Invalido usuario ou senha", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Login efetuado com sucesso", HttpStatus.OK);
    }
}
