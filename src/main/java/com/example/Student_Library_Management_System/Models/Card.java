package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

    // plan is to save this card in db
    // before saving I have to set its attribute

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp  // Auto timestamp the time when an entry is created
    private Date created;

    @UpdateTimestamp // Sets time when an entry is updated
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING) // Telling sql to keep it in string type
    private CardStatus cardStatus;

    @OneToOne     // Mapping relation between two entities
    @JoinColumn   // You are telling  : add a foreign key column ---> define the column name of the parent class.
    private Student student;  // This variable is used in parent class while doing bidirectional mapping

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Book> bookIssued = new ArrayList<>();

    private boolean isIssued;

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
