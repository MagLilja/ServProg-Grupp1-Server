package se.yrgo.service;

import se.yrgo.dataaccess.MessageDataAccess;
import se.yrgo.domain.Message;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
/**
 * @Author - Magnus Lilja
 */
@Stateless
public class MessageManagementImplementation implements MessageManagementService {

	@Inject
	private MessageDataAccess dao;



	@Override
	public void registerMessage(Message message) {
		dao.insert(message);
	}

	@Override
	public List<Message> getAllMessages() {
		return dao.findAll();
	}

	@Override
	public Message getById(int id) {
		return dao.findById(id);
	}


}
