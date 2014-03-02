package ee.maix.demo

import org.opendolphin.core.comm.Command
import org.opendolphin.core.comm.ValueChangedCommand
import org.opendolphin.core.server.DTO
import org.opendolphin.core.server.ServerAttribute
import org.opendolphin.core.server.ServerPresentationModel
import org.opendolphin.core.server.Slot
import org.opendolphin.core.server.action.DolphinServerAction
import org.opendolphin.core.server.comm.ActionRegistry
import org.opendolphin.core.server.comm.CommandHandler


import static ApplicationConstants.*;

class DemoActions extends DolphinServerAction{

	private int count = 0

	public void registerIn(ActionRegistry actionRegistry) {
		actionRegistry.register(COMMAND_ID, new CommandHandler<Command>() {
					public void handleCommand(Command command, List<Command> response) {

						count++
						
						def pm = getServerDolphin().findPresentationModelById(PM_APP)
						if (null == pm) {
							pm = new ServerPresentationModel(PM_APP, [])
						}
						
						
//						
//						def attribute //= getServerDolphin().serverModelStore.findAttributeById(ATT_ATTR_ID)
//						if (!attribute){
//						println "nononoonononnnnnnnnnnnnnnnnnnn"
//						 attribute = new ServerAttribute(ATT_ATTR_ID, "hihhhaa")
//						}
//						
					//	presentationModel(PM_APP, attribute);
						presentationModel(PM_APP+".0."+ATT_ATTR_ID, PM_APP, new DTO(
								new Slot(ATT_ATTR_ID, count as String, PM_APP+".0."+ATT_ATTR_ID)
								))
						println "Value Changed..............."
					}
				})

	}
}
