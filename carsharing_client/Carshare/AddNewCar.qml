import QtQuick 6.2
import QtQuick.Controls 6.2

Page {

    visible: true
    title: "Add a new car"

    Rectangle {
        id: rectangle
        anchors.fill: parent

        Label {
            id: addNewCar__Title
            x: 200
            y: 21
            width: 257
            height: 53
            text: qsTr("Add a new car")
            font.pixelSize: 30
            horizontalAlignment: Text.AlignHCenter
            verticalAlignment: Text.AlignVCenter
            clip: false
        }

        Rectangle {
            id: rectangle3
            x: 228
            y: 227
            width: 265
            height: 28
            color: "#ffffff"
            border.color: "black"

            TextInput {
                id: releaseYear__input
                anchors.fill: parent
                anchors.margins: 2
                horizontalAlignment: Text.AlignLeft
                verticalAlignment: Text.AlignVCenter
                activeFocusOnPress: true
                cursorVisible: true
                font.pixelSize: 16
            }
        }

        Label {
            id: label
            x: 132
            y: 107
            width: 80
            height: 22
            text: qsTr("Licence Plate:")
        }

        Label {
            id: label2
            x: 132
            y: 233
            width: 80
            height: 17
            text: qsTr("Release year:")
        }

        Label {
            id: label1
            x: 132
            y: 171
            width: 80
            height: 21
            text: qsTr("Model:")
        }

        Label {
            id: labelPrice
            x: 132
            y: 287
            width: 80
            height: 21
            text: qsTr("Price for a minute:")
        }

        Label {
            id: labelLocation
            x: 132
            y: 341
            width: 80
            height: 21
            text: qsTr("Location:")
        }

        Label {
            id: labelAddress
            x: 202
            y: 341
            width: 80
            height: 21
            text: qsTr("Address:")
        }

        Rectangle {
            id: rectangleAddress
            x: 282
            y: 341
            width: 265
            height: 28
            color: "white"
            border.color: "black"
            TextInput {
                id: carAddress__input
                anchors.fill: parent
                anchors.margins: 2
                horizontalAlignment: Text.AlignLeft
                verticalAlignment: Text.AlignVCenter
                activeFocusOnPress: true
                cursorVisible: true
                font.pixelSize: 16
            }
        }

        Label {
            id: labelGps_x
            x: 202
            y: 395
            width: 80
            height: 21
            text: qsTr("GPS x:")
        }

        Rectangle {
            id: rectangleGps_x
            x: 282
            y: 395
            width: 265
            height: 28
            color: "#ffffff"
            border.color: "black"
            TextInput {
                id: carGps_x__input
                anchors.fill: parent
                anchors.margins: 2
                horizontalAlignment: Text.AlignLeft
                verticalAlignment: Text.AlignVCenter
                activeFocusOnPress: true
                cursorVisible: true
                font.pixelSize: 16
            }
        }

        Label {
            id: labelGps_y
            x: 202
            y: 449
            width: 80
            height: 21
            text: qsTr("GPS y:")
        }

        Rectangle {
            id: rectangleGps_y
            x: 282
            y: 449
            width: 265
            height: 28
            color: "#ffffff"
            border.color: "black"
            TextInput {
                id: carGps_y__input
                anchors.fill: parent
                anchors.margins: 2
                horizontalAlignment: Text.AlignLeft
                verticalAlignment: Text.AlignVCenter
                activeFocusOnPress: true
                cursorVisible: true
                font.pixelSize: 16
            }
        }


        Rectangle {
            id: rectangle2
            x: 228
            y: 104
            width: 265
            height: 28
            color: "#ffffff"
            border.color: "black"
            TextInput {
                id: licencePlate__input
                anchors.fill: parent
                anchors.margins: 2
                horizontalAlignment: Text.AlignLeft
                verticalAlignment: Text.AlignVCenter
                activeFocusOnPress: true
                cursorVisible: true
                font.pixelSize: 16
            }
        }

        Rectangle {
            id: rectangle4
            x: 228
            y: 167
            width: 265
            height: 28
            color: "#ffffff"
            border.color: "black"
            TextInput {
                id: carModel__input
                anchors.fill: parent
                anchors.margins: 2
                horizontalAlignment: Text.AlignLeft
                verticalAlignment: Text.AlignVCenter
                activeFocusOnPress: true
                cursorVisible: true
                font.pixelSize: 16
            }
        }

        Rectangle {
            id: rectangle5
            x: 228
            y: 287
            width: 265
            height: 28
            color: "#ffffff"
            border.color: "black"
            TextInput {
                id: carPrice__input
                anchors.fill: parent
                anchors.margins: 2
                horizontalAlignment: Text.AlignLeft
                verticalAlignment: Text.AlignVCenter
                activeFocusOnPress: true
                cursorVisible: true
                font.pixelSize: 16
            }
        }

        Button {
            id: button
            x: 17
            y: 21
            text: qsTr("Back")
            onClicked: {
                stack.showOwnerCarsView();
            }
        }
        Button {
            id: submit__button
            x: 245
            y: 503
            text: qsTr("Submit")
            onClicked: {
                communication.addNewCar(licencePlate__input.text, carModel__input.text, releaseYear__input.text, carPrice__input.text,
                 carAddress__input.text, carGps_x__input.text, carGps_y__input.text)
            }
        }

        states: [
            State {
                name: "clicked"
            }
        ]
    }
}
