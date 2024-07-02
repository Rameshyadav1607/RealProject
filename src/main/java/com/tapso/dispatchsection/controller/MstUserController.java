package com.tapso.dispatchsection.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tapso.dispatchsection.dto.MstUserDTO;
import com.tapso.dispatchsection.dto.UserResponseDTO;
import com.tapso.dispatchsection.entity.MstUser;
import com.tapso.dispatchsection.service.MstUserService;

@RestController
@RequestMapping("/api/v1/user")
public class MstUserController {
	@Autowired
	private MstUserService service;

	@PostMapping("/save")
	public ResponseEntity<UserResponseDTO> saveUser(@RequestBody MstUserDTO mstUserDTO){
		
		             UserResponseDTO    user=service.saveUser(mstUserDTO);
		return new ResponseEntity<UserResponseDTO>(user,HttpStatus.CREATED);
		
	}
	@PutMapping("/update/{locCode}/{userId}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String locCode,@PathVariable String userId,@RequestBody MstUserDTO dto){
		    
		return  service.updateUser(locCode,userId,dto);
		
	}
	 @GetMapping("/all")
	    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
	        List<UserResponseDTO> users = service.getAllUsers();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
	 @GetMapping("/department/{deptCode}")
	    public ResponseEntity<List<MstUser>> getUsersByDeptCode(@PathVariable String deptCode) {
	        List<MstUser> users = service.getUsersByDeptCode(deptCode);
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
	 
	 @DeleteMapping("/{userId}")
	    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
	        service.deleteUser(userId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
