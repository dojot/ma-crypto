package br.com.dojot.mutualauthentication.cryptointegration.messaging.api;

import br.com.dojot.mutualauthentication.cryptointegration.beans.dto.LoggingDTO;

public interface LoggingProcessingProducerService {
	
	void produce(LoggingDTO dto);
}
