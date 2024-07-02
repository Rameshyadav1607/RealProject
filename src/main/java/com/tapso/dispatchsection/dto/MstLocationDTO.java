package com.tapso.dispatchsection.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class MstLocationDTO {
    private String locCode;
    private String locName;
    private String stateOfficeCode;
    private String regionOfficeCode;
    private String locAddress;
    private String locState;
    private String locPin;

}
