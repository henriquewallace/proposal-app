package com.wallace.proposalapp.converter;

import com.wallace.proposalapp.domain.User;
import com.wallace.proposalapp.dto.UserDTO;

public class UserConverter {

    public User from(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getNome());
        user.setLastName(userDTO.getSobrenome());
        user.setCpf(userDTO.getCpf());
        user.setPhoneNumber(userDTO.getTelefone());
        user.setIncome(userDTO.getRenda());
        user.setProposal(userDTO.getProposta());

        return user;
    }

    public  UserDTO from(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNome(user.getName());
        userDTO.setSobrenome(user.getLastName());
        userDTO.setCpf(user.getCpf());
        userDTO.setTelefone(user.getPhoneNumber());
        userDTO.setRenda(user.getIncome());
        userDTO.setProposta(user.getProposal());

        return userDTO;
    }
}
