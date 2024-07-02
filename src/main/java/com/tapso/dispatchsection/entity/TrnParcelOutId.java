package com.tapso.dispatchsection.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrnParcelOutId implements Serializable {

	private String senderLocCode;
    private Long outTrackingId;
}