import QtQuick 6.2
import QtQuick.Controls 6.2

Page {
    id: lenderCarsView
    width: Constants.width
    height: Constants.height

    visible: true
    title: "Cars"

    Dialog {
        id: confirmDeleteDialog
        anchors.centerIn: parent
        property int carId: -1
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
        id: carsListView
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
                property int carid: model.carid

                Column {
                    anchors.fill: parent
                    anchors.margins: 10
                    spacing: 5

                    Row {
                        spacing: 10
                        Text { text: qsTr("Licence Plate:") }
                        Text { text: model.licensePlate }
                    }

                    Row {
                        spacing: 10
                        Text { text: qsTr("Model Name:") }
                        Text { text: model.modelName }
                    }

                    Row {
                        spacing: 10
                        Text { text: qsTr("Release Year:") }
                        Text { text: model.releaseYear }
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
                        confirmDeleteDialog.carId = model.carid;
                        confirmDeleteDialog.open();
                    }
                }
            }
        }

        model: ListModel {
            id: carsListViewModel
        }

        Component.onCompleted: {
            refresh();
        }

        function refresh() {
            carsListViewModel.clear();
            communication.getCars();
        }

        Connections {
            target: communication

            function onCarsUpdateNeeded() {
                refresh();
            }

            function onAddCarsListItem(id, license, name, release) {
                carsListViewModel.append({"carid": id, "licensePlate": license, "modelName": name, "releaseYear": release});
            }
        }
    }
}
