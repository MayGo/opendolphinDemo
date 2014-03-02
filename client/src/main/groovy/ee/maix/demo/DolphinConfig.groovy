package ee.maix.demo;


import org.opendolphin.core.client.comm.JavaFXUiThreadHandler
import org.opendolphin.LogConfig
import org.opendolphin.core.client.ClientDolphin
import org.opendolphin.core.client.ClientModelStore
import org.opendolphin.core.client.comm.ClientConnector
import org.opendolphin.core.comm.JsonCodec
import org.opendolphin.core.client.comm.HttpClientConnector

class DolphinConfig  {
	ClientDolphin clientDolphin = new ClientDolphin()

    DolphinConfig() {
		LogConfig.logCommunication()

        clientDolphin.clientModelStore = new ClientModelStore(clientDolphin)
     

		HttpClientConnector connector = new HttpClientConnector(clientDolphin, "http://localhost:8080/opendophinserver/dolphin");
        connector.setCodec(new JsonCodec());
		clientDolphin.clientConnector = connector

        //clientDolphin.clientConnector.sleepMillis = 100


        clientDolphin.clientConnector.uiThreadHandler = new JavaFXUiThreadHandler()

    }

}