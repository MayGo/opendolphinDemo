package ee.maix.demo

import org.opendolphin.core.client.ClientDolphin
import org.opendolphin.core.client.ClientPresentationModel
import groovyx.javafx.SceneGraphBuilder
import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.event.EventHandler

import java.beans.PropertyChangeListener

import static org.opendolphin.binding.JFXBinder.bind
import static groovyx.javafx.GroovyFX.start

class MainView {

    static show(ClientDolphin clientDolphin) {

        ObservableList<ClientPresentationModel> observableSelectionList = FXCollections.observableArrayList()

        def firstPm = clientDolphin.presentationModel('firstPm', ['selected'])
        firstPm.selected.value = false

        def secondPm = clientDolphin.presentationModel('secondPm', ['selected'])
        secondPm.selected.value = false

        start { app ->
            SceneGraphBuilder sgb = delegate
            stage {
                scene width: 400, height: 300, {
                    borderPane {
                        top margin:10, {
                            hbox{
                                label text:'selected items:'
                                label id:'selectedItemCount'
                            }
                        }
                        stackPane {
                            group effect: dropShadow(offsetY: 2, offsetX: 2, radius: 3, input: lighting{distant(azimuth: -135.0)}), {
                                rectangle(id: 'first',  x: 0,  y: 0, width: 40, height: 40, fill: white)
                                rectangle(id: 'second', x: 60, y: 0, width: 40, height: 40, fill: white)
            }   }   }   }   }

            observableSelectionList.addListener( { selectedItemCount.text = observableSelectionList.size() } as ListChangeListener )

            def selectionColor = { it ? sgb.yellow : sgb.white }
            bind 'selected' of firstPm  to 'fill' of first,  selectionColor
            bind 'selected' of secondPm to 'fill' of second, selectionColor

            def selectionUpdater = {pm, evt ->
                observableSelectionList.removeAll(pm) // be defensive
                if (evt.newValue) { observableSelectionList.add(pm) }
            }
            firstPm.selected.addPropertyChangeListener( selectionUpdater.curry(firstPm) as PropertyChangeListener)
            secondPm.selected.addPropertyChangeListener( selectionUpdater.curry(secondPm) as PropertyChangeListener)

            first.onMouseClicked  = { firstPm.selected.value  = ! firstPm.selected.value } as EventHandler
            second.onMouseClicked = { secondPm.selected.value = ! secondPm.selected.value } as EventHandler

            primaryStage.show()
        }
    }
}