import QtQuick 6.2
import QtQuick.Controls 6.2

Page {
    id: ownerCarsView

    visible: true
    title: "Cars"

    Dialog {
        id: confirmDeleteDialog
        anchors.centerIn: parent
        property string carId: ""
        title: "Delete car"
        modal: true
        standardButtons: Dialog.Apply | Dialog.Cancel
        closePolicy: Popup.NoAutoClose
        onApplied: {
            communication.deleteCar(carId);
            confirmDeleteDialog.accept();
        }
    }

    Button {
        id: newCarButton
        text: "Add New Car"
        anchors.top: parent.top
        anchors.horizontalCenter: parent.horizontalCenter
        onClicked: {
            stack.replace("AddNewCar.qml");
        }
    }

    ListView {
        id: ownerCarsListView
        anchors.top: newCarButton.bottom
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.bottom: parent.bottom
        delegate: Item {
            width: parent.width
            height: 120

            Rectangle {
                id: carsListViewDelegateRect1
                width: parent.width
                height: parent.height
                color: "transparent"
                property string carid: licensePlate

                Column {
                    anchors.fill: parent
                    anchors.margins: 10
                    spacing: 5

                    Row {
                        spacing: 10
                        Text { text: qsTr("Licence Plate:") }
                        Text { text: licensePlate }
                    }

                    Row {
                        spacing: 10
                        Text { text: qsTr("Model Name:") }
                        Text { text: model }
                    }

                    Row {
                        spacing: 10
                        Text { text: qsTr("Price per minute:") }
                        Text { text: price }
                    }
                }

                Button {
                    id: buttonDelete1
                    anchors.right: parent.right
                    anchors.bottom: parent.bottom
                    width: 60
                    height: 25
                    text: "Delete"
                    onClicked: {
                        confirmDeleteDialog.contentItem.text = qsTr("Do you want to delete car: %1?").arg(model.licensePlate);
                        confirmDeleteDialog.carId = licensePlate;
                        confirmDeleteDialog.open();
                    }
                }
            }
        }

        model: ListModel {
            id: ownerCarsListViewModel
        }

        Component.onCompleted: {
            ownerCarsListView.refresh();
        }

        function refresh() {
            ownerCarsListViewModel.clear();
            communication.fetchCarsForOwner();
        }

        Connections {
            target: communication

            function onCarsForOwnerFetched(model, location, price, owner, licensePlate, releaseYear){
                ownerCarsListViewModel.append({"model": model, "location": location, "price": price,
                                             "owner": owner, "licensePlate": licensePlate, "releaseYear": releaseYear})
            }
            function onCarDeleted(){
                ownerCarsListViewModel.clear();
                communication.fetchCarsForOwner();
            }
        }
    }
}
