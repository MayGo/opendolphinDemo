package ee.maix.demo

import org.opendolphin.core.client.comm.JavaFXUiThreadHandler
import org.opendolphin.core.comm.DefaultInMemoryConfig
import ee.maix.demo.MainRegistrarAction
import ee.maix.demo.MainView

def config = new DefaultInMemoryConfig()
config.clientDolphin.clientConnector.uiThreadHandler = new JavaFXUiThreadHandler()
config.serverDolphin.registerDefaultActions()

config.serverDolphin.register(new MainRegistrarAction())

MainView.show(config.clientDolphin)
