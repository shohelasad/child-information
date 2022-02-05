package com.bd.childinfo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collector;


@SqlResultSetMapping(name = "Registration", classes = {@ConstructorResult(targetClass = Registration.class, columns = {
        @ColumnResult(name = "formData", type = String.class)})})

@TypeDefs({@TypeDef(name = "StringJsonbObject", typeClass = StringJsonbUserType.class)})
@Entity
@JsonIgnoreProperties
public class Registration extends BaseEntity<Long> implements NonDeletable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date registrationDate;

    private boolean deleted;

    @JsonIgnore
    @ManyToOne
    private User registrant;

    public Registration() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public User getRegistrant() {
        return registrant;
    }

    public void setRegistrant(User registrant) {
        this.registrant = registrant;
    }

    @Override
    public Boolean isDeleted() {
        return this.deleted;
    }

    @Override
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationDate=" + registrationDate +
                ", id=" + id +
                '}';
    }
}
