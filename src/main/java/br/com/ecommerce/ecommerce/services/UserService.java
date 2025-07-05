package br.com.ecommerce.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.ecommerce.dtos.request.NewUserDTO;
import br.com.ecommerce.ecommerce.dtos.response.UserDTO;
import br.com.ecommerce.ecommerce.models.User;
import br.com.ecommerce.ecommerce.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private void saveUser(User user){
        userRepository.save(user);
    }

    public UserDTO creatUser(NewUserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User(userDTO);
        saveUser(user);
        UserDTO returnUser = new UserDTO(user.getName(), user.getEmail());
        return returnUser;
    }

    
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> returnUsers = users.stream().map(user -> new UserDTO(user.getName(), user.getEmail())).toList();
        return returnUsers;
    }

    public UserDTO getUserById(String userId){
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        UserDTO returnUser = new UserDTO(user.getName(), user.getEmail());
        return returnUser;
    }
    
      public void deleteUserById(String userId){
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        deleteUser(user);
    }

    private void deleteUser(User user){
        userRepository.delete(user);
    }
}
