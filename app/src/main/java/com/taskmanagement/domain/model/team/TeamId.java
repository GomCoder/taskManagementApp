package com.taskmanagement.domain.model.team;

import com.taskmanagement.domain.common.model.AbstractBaseId;

import java.io.Serializable;

public class TeamId extends AbstractBaseId implements Serializable {

  private static final long serialVersionUID = -1537825903788044759L;

  public TeamId(long id) {
    super(id);
    System.out.println("TeamId() 생성자 호출: " + id);
  }
}
