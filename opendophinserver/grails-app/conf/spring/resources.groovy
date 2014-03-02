import org.opendolphin.core.ModelStore
import org.opendolphin.core.comm.JsonCodec
import org.opendolphin.core.comm.ZippedJsonCodec
import org.opendolphin.core.server.EventBus
import org.opendolphin.core.server.ServerDolphin
import org.opendolphin.core.server.comm.ServerConnector

beans = {



    modelStore(ModelStore) { bean ->
        bean.scope = 'session' // every session must have its own model store
    }

    serverConnector(ServerConnector) { bean ->
        bean.scope = 'session'  // could be shared among sessions but since the registry is set, this is safer...
        codec = new JsonCodec()
    }

    serverDolphin(ServerDolphin, ref('modelStore'), ref('serverConnector')) { bean ->
        bean.scope = 'session'
    }

    dolphinBean(
        DolphinSpringBean,
        ref('serverDolphin')                // more services would come here...

    ) { bean ->
        bean.scope = 'session'
    }
}