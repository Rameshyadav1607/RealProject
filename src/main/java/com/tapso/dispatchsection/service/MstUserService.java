package com.tapso.dispatchsection.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tapso.dispatchsection.dto.MstUserDTO;
import com.tapso.dispatchsection.dto.UserResponseDTO;
import com.tapso.dispatchsection.entity.MstUser;
import com.tapso.dispatchsection.repository.MstUserRepository;

@Service
public class MstUserService {

    @Autowired
    private MstUserRepository mstUserRepository;

    @Autowired
    private ModelMapper modelMapper;

	public UserResponseDTO saveUser(MstUserDTO mstUserDTO) {
		// TODO Auto-generated method stub
		ModelMapper mapper=new ModelMapper();
		      MstUser  user=mapper.map(mstUserDTO,MstUser.class);
		    //  user.set
		      user.setCreatedBy("rrr");
		     // user.setCreatedDate();
		     // user.setLastUpdatedDate();
		     MstUser    userSaved=mstUserRepository.save(user);
		    UserResponseDTO responseDTO=mapper.map(userSaved,UserResponseDTO.class);
		    
		return responseDTO;
	}

	public ResponseEntity<UserResponseDTO>  updateUser(String locCode,String userId, MstUserDTO dto) {
		  ModelMapper mapper=new ModelMapper();
		          MstUser       mstUser=mstUserRepository.findByLocCodeAndUserId(locCode,userId);
		     if(mstUser==null)
		     {
		    	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		     }
		     else {
		    	       MstUser      user=mapper.map(dto,MstUser.class);
		    	       user.setUserId(mstUser.getUserId());
		    	       user.setLocCode(mstUser.getLocCode());
		    	       user.setCreatedBy("ramesh");
			    	   user.setLastUpdatedDate(LocalDateTime.now());
		    	     MstUser  userupdated=mstUserRepository.save(user);
		    	     user.setCreatedBy("ramesh");
		    	     user.setLastUpdatedDate(LocalDateTime.now());
		    	UserResponseDTO      userResponse=mapper.map(userupdated,UserResponseDTO.class);
		    	 
		    	 return new ResponseEntity<UserResponseDTO>(userResponse,HttpStatus.OK);
		     }
		     
		
		
	}

	public List<UserResponseDTO> getAllUsers() {
        return mstUserRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

	public List<MstUser> getUsersByDeptCode(String deptCode) {
        return mstUserRepository.findByDeptCode(deptCode);
	}

	 public void deleteUser(String userId) {
	        Optional<MstUser> existingUser = mstUserRepository.findByUserId(userId);
	        if (existingUser.isPresent()) {
	            mstUserRepository.delete(existingUser.get());
	        } else {
	            // Optionally, you can log the event or handle it as needed
	            // For now, it will just do nothing if the user is not found
	        }
	    }

}
