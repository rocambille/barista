package #####;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ${Entity} {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeleted;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    void onPrePersist() {
        this.dateCreated = new Date();
        this.dateModified = new Date();
    }

    @PreUpdate
    void onPreUpdate() {
        this.dateModified = new Date();
    }

    @PreRemove
    void onPreRemove() {
        this.dateDeleted = new Date();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

}
