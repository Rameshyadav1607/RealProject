package com.tapso.dispatchsection.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserResponseDTO {
    private String locCode;
    private String userId;
    private String userName;
    private String roleId;
    private char status;
    private String createdBy;
    private LocalDate createdDate;
    private LocalDateTime lastUpdatedDate;
    private String deptCode;

    // Getters and Setters
}
