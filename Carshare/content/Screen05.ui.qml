

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
        x: 114
        y: 21
        width: 423
        height: 53
        text: qsTr("Delete maintenance record from car!")
        font.pixelSize: 30
        verticalAlignment: Text.AlignVCenter
    }

    Rectangle {
        id: rectangle1
        x: 278
        y: 104
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
        x: 135
        y: 107
        width: 111
        height: 22
        text: qsTr("Licence Plate:")
    }

    Label {
        id: label1
        x: 135
        y: 175
        width: 111
        height: 21
        text: qsTr("Maintenance id:")
    }

    Button {
        id: button1
        x: 278
        y: 226
        text: qsTr("Submit")
    }

    Rectangle {
        id: rectangle4
        x: 278
        y: 171
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
        TextInput {
            id: textInput3
            x: 8
            y: 0
            width: 249
            height: 28
            font.pixelSize: 16
            verticalAlignment: Text.AlignVCenter
        }
    }

    Button {
        id: button
        x: 17
        y: 21
        text: qsTr("Back")
    }

    Item {
        id: __materialLibrary__
    }

    states: [
        State {
            name: "clicked"
        }
    ]
}
