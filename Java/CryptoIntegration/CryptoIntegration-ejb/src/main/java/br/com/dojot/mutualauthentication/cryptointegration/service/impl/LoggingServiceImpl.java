package br.com.dojot.mutualauthentication.cryptointegration.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.dojot.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.dojot.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;
import br.com.dojot.mutualauthentication.cryptointegration.beans.dto.LoggingDTO;
//import br.com.dojot.mutualauthentication.cryptointegration.messaging.api.LoggingProcessingProducerService;
import br.com.dojot.mutualauthentication.cryptointegration.service.api.LoggingService;

@Stateless
public class LoggingServiceImpl implements LoggingService {

	@Override
	public void saveLogging(Level level, String component, String username, String details) {
		System.out.printf("[%s] %s. %s. %s.\n", level.toString(), component, username, details);
	}
	
}
