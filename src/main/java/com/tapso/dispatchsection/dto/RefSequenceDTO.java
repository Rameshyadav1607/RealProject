package com.tapso.dispatchsection.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class RefSequenceDTO {

    private String locCode;
    private Long inSequenceNo;
    private Long outSequenceNo;
}
