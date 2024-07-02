package com.tapso.dispatchsection.entity;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MstDepartmentPK implements Serializable{


	    private String locCode;
	    private String deptCode;

//	    // Default constructor
//	    public MstDepartmentPK() {
//	    }
//
//	    // Constructor
//	    public MstDepartmentPK(String locCode, String deptCode) {
//	        this.locCode = locCode;
//	        this.deptCode = deptCode;
//	    }
}