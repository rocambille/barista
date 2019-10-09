package #####;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ${Entity} {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date creationTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletionTimestamp;

    @PrePersist
    protected final void onCreate() {
        this.creationTimestamp = new Date();
        this.createdBy = AbstractBaseEntity.currentUserId();
    }

    @PreUpdate
    protected final void onUpdate() {
        this.updateTimestamp = new Date();
        this.updatedBy = AbstractBaseEntity.currentUserId();
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public Date getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public Date getDeletionTimestamp() {
        return deletionTimestamp;
    }

    public void setDeletionTimestamp(Date deletionTimestamp) {
        this.deletionTimestamp = deletionTimestamp;
    }
}
