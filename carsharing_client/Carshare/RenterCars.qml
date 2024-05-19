import QtQuick 6.2
import QtQuick.Controls 2.12
import networkaccess 1.0

Page {
    id: listCarsView

    visible: true
    title: "Cars"
    Dialog {
        id: confirmBookingDialog
        anchors.centerIn: parent
        property string carId: ""
        title: "Booking"
        Text { text: "You will have to pay! The amount will be subtracted from your account." }
        modal: true
        standardButtons: Dialog.Apply | Dialog.Cancel
        closePolicy: Popup.NoAutoClose
        onApplied: {
            communication.makeBooking(carId);
            confirmBookingDialog.accept();
        }
    }

    ListView {
        id: listViewCars
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.top: parent.top
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
                        Text { text: qsTr("Release Year:") }
                        Text { text: releaseYear }
                    }
                    Row {
                        spacing: 10
                        Text { text: qsTr("Price per minute:") }
                        Text { text: price }
                    }
                }
                Button {
                    id: buttonBook
                    anchors.right: parent.right
                    anchors.bottom: parent.bottom
                    width: 60
                    height: 25
                    text: "Book"
                    onClicked: {
                        confirmBookingDialog.contentItem.text = qsTr("Do you want to make this booking: %1?").arg(model.licensePlate);
                        confirmBookingDialog.carId = licensePlate;
                        confirmBookingDialog.open();
                    }
                }
                Button {
                    id: buttonReviews
                    anchors.right: buttonBook.left
                    anchors.bottom: parent.bottom
                    width: 60
                    height: 25
                    text: "Reviews"
                    onClicked: {
                        communication.fetchStatistics(licensePlate)
                        stack.showReviewStatistics()
                    }
                }
            }
        }

        model: ListModel {
            id: listViewCarsModel
        }

        Component.onCompleted: {
            refresh();
        }

        function refresh() {
            listViewCarsModel.clear();
            communication.fetchCarsForRent();
        }

        Connections {
            target: communication

            function onBookingSent() {
                listViewCars.refresh()
            }

            function onCarsForRentFetched(model, location, price, owner, licensePlate, releaseYear) {
                listViewCarsModel.append({
                    "model": model,
                    "location": location,
                    "price": price,
                    "owner": owner,
                    "licensePlate": licensePlate,
                    "releaseYear": releaseYear
                });
            }

            function onCarDeleted() {
                listViewCarsModel.clear();
                communication.fetchCarsForRent();
            }
        }
    }


}

