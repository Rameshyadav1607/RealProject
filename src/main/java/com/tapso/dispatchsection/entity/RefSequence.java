package com.tapso.dispatchsection.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ref_sequence", schema = "public")
public class RefSequence {

    @Id
    @Column(name = "loc_code", length = 6, nullable = false)
    private String locCode;

    @Column(name = "in_sequence_no", nullable = false)
    private Long inSequenceNo;

    @Column(name = "out_sequence_no", nullable = false)
    private Long outSequenceNo;

}
