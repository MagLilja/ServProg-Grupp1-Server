package se.yrgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;

/**
 * @Author - Magnus Lilja
 */

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String textBody;
//
//    @ManyToOne
//    @JoinColumn(name = "profile_id")
//    private Profile author;
    @ManyToOne
    @JoinColumn(name = "profile_fk")
    private Profile author;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public void setAuthor(Profile author) {
        this.author = author;
    }

    public Profile getAuthor() {
        return author;
    }
}
