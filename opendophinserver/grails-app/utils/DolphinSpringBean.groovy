import groovyx.gpars.agent.Agent
import org.opendolphin.LogConfig
import org.opendolphin.core.server.DTO
import org.opendolphin.core.server.EventBus
import org.opendolphin.core.server.ServerDolphin

import groovy.util.logging.Log


import java.util.logging.Level
import java.util.logging.Logger
import ee.maix.demo.DemoActions

@Log
class DolphinSpringBean {

	private static final teamHistory = new Agent<List<DTO>>(new LinkedList<DTO>());

	DolphinSpringBean(
		ServerDolphin dolphin
	) {

		Logger.getLogger("").level = Level.INFO
		LogConfig.logCommunication()

		log.info "creating new dolphin session"

		dolphin.registerDefaultActions()

		dolphin.register(new DemoActions())
	

	}
}
