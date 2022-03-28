package se.yrgo.service;

import se.yrgo.domain.Message;

import javax.ejb.Local;
import java.util.List;
/**
 * @Author - Magnus Lilja
 */
@Local
public interface MessageManagementService {
	public void registerMessage(Message message);
	public List<Message> getAllMessages();

	public Message getById(int id);
}
