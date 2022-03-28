package se.yrgo.dataaccess;

import se.yrgo.domain.Message;
import se.yrgo.domain.Profile;

import javax.ejb.Local;
import java.util.List;

/**
 * @Author - Magnus Lilja
 */
@Local
public interface MessageDataAccess {

    public  void insert(Message newMessage);

    public  List<Message> findAll();

    public  List<Message> findByAuthor(Profile author);

    public Message findById(int id);
}