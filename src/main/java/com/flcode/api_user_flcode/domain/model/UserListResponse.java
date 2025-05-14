package com.flcode.api_user_flcode.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserListResponse {
    private List<User> data;
    private ErrorResponse errorResponse;
}
