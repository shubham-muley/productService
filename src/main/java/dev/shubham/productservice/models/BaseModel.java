package dev.shubham.productservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    private String id;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;
}
