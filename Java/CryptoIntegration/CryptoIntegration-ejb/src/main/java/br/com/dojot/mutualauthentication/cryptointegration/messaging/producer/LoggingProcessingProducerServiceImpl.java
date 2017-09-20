package br.com.dojot.mutualauthentication.cryptointegration.messaging.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.dojot.mutualauthentication.communication.constants.CommunicationKeysConstants;
import br.com.dojot.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.dojot.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;
import br.com.dojot.mutualauthentication.cryptointegration.beans.dto.LoggingDTO;
import br.com.dojot.mutualauthentication.cryptointegration.dao.api.ConfigDAO;
import br.com.dojot.mutualauthentication.cryptointegration.messaging.api.LoggingProcessingProducerService;
import br.com.dojot.mutualauthentication.cryptointegration.util.CryptoIntegrationConstants;

@Startup
@Singleton
public class LoggingProcessingProducerServiceImpl extends Thread implements LoggingProcessingProducerService {
	private ProducerServiceImpl producer;
	
	@EJB
	private ConfigDAO configDAO;
    
	@PostConstruct
	public void init() {
		CommunicationFacade facade = new CommunicationFacadeBean();
		producer = new ProducerServiceImpl(configDAO.findParameterByKey(CryptoIntegrationConstants.PARAM_KAFKA_BOOTSTRAP_SERVERS),
				CryptoIntegrationConstants.TOPIC_LOGGING_PROCESSING,
				(String) facade.requestNodeConfigs().get(CommunicationKeysConstants.KEY_VERSION), "cry.logprocessing.");
	}
	
	@PreDestroy
	public void close() {
		producer.close();
	}

	@Override
	public void produce(LoggingDTO dto) {
		producer.produce(dto);
	}

}