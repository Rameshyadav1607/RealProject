package com.tapso.dispatchsection.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class DateRangeQueryDTO {
	private LocalDate startDate;
	private LocalDate endDate;
	private String type;

}
