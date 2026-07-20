package com.esnt.ferreconst.mapper;

import com.esnt.ferreconst.dto.response.AuthResponseDto;
import com.esnt.ferreconst.dto.response.RefreshResponseDto;
import com.esnt.ferreconst.dto.response.UserResponseDto;
import com.esnt.ferreconst.model.AuthResponse;
import com.esnt.ferreconst.model.TokenResponse;
import com.esnt.ferreconst.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    @Autowired
    private CompanyMapper mapper;

    public AuthResponse toAuthResponse(AuthResponseDto dto) {
        if (dto == null) return null;

        AuthResponse response = new AuthResponse();
        response.setAccessToken(dto.getAccessToken());
        response.setRefreshToken(dto.getRefreshToken());
        response.setCompany(mapper.toCompany(dto.getCompany()));
        response.setUser(toUser(dto.getUser()));
        return response;
    }

    public User toUser(UserResponseDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setCompanyId(dto.getCompanyId());
        user.setName(dto.getName());
        user.setNickname(dto.getNickname());
        user.setEmail(dto.getEmail());
        user.setImage(dto.getImage());
        user.setRole(dto.getRole());
        user.setDateRegister(dto.getDateRegister());
        user.setStatus(dto.getStatus());
        return user;
    }

    public TokenResponse toTokenResponse(RefreshResponseDto dto) {
        if (dto == null) return null;
        TokenResponse response = new TokenResponse();
        response.setAccessToken(dto.getAccessToken());
        response.setRefreshToken(dto.getRefreshToken());
        return response;
    }
}
