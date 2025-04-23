package com.enam.bid.seller.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 255, updatable = false)
    private String createdBy;

    @Column(name = "updated_by", length = 255)
    private String updatedBy;

    @Column(name = "is_active")
    private Boolean isActive = true; // Default based on schema DDL

     @PrePersist
     protected void onCreate() {
         createdAt = LocalDateTime.now();
         updatedAt = LocalDateTime.now();
         // Consider setting createdBy/updatedBy from security context if available
     }

     @PreUpdate
     protected void onUpdate() {
         updatedAt = LocalDateTime.now();
         // Consider setting updatedBy from security context if available
     }
}
