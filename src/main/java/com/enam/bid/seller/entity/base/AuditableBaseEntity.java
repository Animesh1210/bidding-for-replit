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
public abstract class AuditableBaseEntity {

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive = true; // Default based on schema DDL where applicable

    @PrePersist
    protected void onCreate() {
         createdAt = LocalDateTime.now();
         updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
         updatedAt = LocalDateTime.now();
    }
}
