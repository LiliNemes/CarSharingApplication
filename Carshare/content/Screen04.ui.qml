

/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/
import QtQuick 6.2
import QtQuick.Controls 6.2
import Carshare

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height
    anchors.fill: parent

    color: Constants.backgroundColor

    Text {
        id: text1
        x: 200
        y: 21
        width: 257
        height: 53
        text: qsTr("Delete a Car!")
        font.pixelSize: 30
        verticalAlignment: Text.AlignVCenter
    }

    Rectangle {
        id: rectangle1
        x: 233
        y: 116
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12

        TextInput {
            id: textInput
            x: 8
            y: 0
            width: 249
            height: 28
            font.pixelSize: 16
            verticalAlignment: Text.AlignVCenter
        }
    }

    Label {
        id: label
        x: 131
        y: 119
        width: 80
        height: 22
        text: qsTr("Licence Plate:")
    }

    Button {
        id: button1
        x: 233
        y: 180
        text: qsTr("Submit")
    }

    Button {
        id: button
        x: 17
        y: 21
        text: qsTr("Back")
    }

    states: [
        State {
            name: "clicked"
        }
    ]
}
