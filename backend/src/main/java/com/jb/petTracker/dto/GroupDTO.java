package com.jb.petTracker.dto;

import org.bson.types.ObjectId;

import com.jb.petTracker.model.Group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class GroupDTO {

	private ObjectId groupId;
	private String groupName;
	
	public GroupDTO(Group group) {
		this.groupId = group.getId();
		this.groupName = group.getName();
	}
}