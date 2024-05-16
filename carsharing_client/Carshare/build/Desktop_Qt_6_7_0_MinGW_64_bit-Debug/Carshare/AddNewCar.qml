import QtQuick 6.2
import QtQuick.Controls 6.2

Page {
    width: Constants.width
    height: Constants.height

    visible: true
    title: "Add a new car"

    Rectangle {
        id: rectangle
        width: Constants.width
        height: Constants.height
        anchors.fill: parent

        color: Constants.backgroundColor

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
            radius: 12

            TextInput {
                id: releaseYear__input
                anchors.fill: parent
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

        Button {
            id: submit__button
            x: 245
            y: 294
            text: qsTr("Submit")
            onClicked: {
                communication.addNewCar(licencePlate__input.text, carModel__input.text, releaseYear__input.text)
            }
        }

        Rectangle {
            id: rectangle2
            x: 228
            y: 104
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
            TextInput {
                id: licencePlate__input
                anchors.fill: parent
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
            radius: 12
            TextInput {
                id: carModel__input
                anchors.fill: parent
                font.pixelSize: 16
            }
        }

        Button {
            id: button
            x: 17
            y: 21
            text: qsTr("Back")
            onClicked: {
                stack.showLenderCarsView();
            }
        }

        states: [
            State {
                name: "clicked"
            }
        ]
    }
}
