

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
        text: qsTr("Add maintenance record to car!")
        font.pixelSize: 30
        verticalAlignment: Text.AlignVCenter
    }

    Rectangle {
        id: rectangle1
        x: 228
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

    Rectangle {
        id: rectangle3
        x: 228
        y: 227
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12

        TextInput {
            id: textInput2
            x: 8
            y: 0
            width: 249
            height: 28
            font.pixelSize: 16
        }
    }

    Label {
        id: label
        x: 101
        y: 107
        width: 111
        height: 22
        text: qsTr("Licence Plate:")
    }

    Label {
        id: label2
        x: 101
        y: 233
        width: 111
        height: 17
        text: qsTr("Maintenance price:")
    }

    Label {
        id: label1
        x: 101
        y: 171
        width: 111
        height: 21
        text: qsTr("Maintenance type:")
    }

    Button {
        id: button1
        x: 278
        y: 359
        text: qsTr("Submit")
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
            id: textInput1
            x: 8
            y: 0
            width: 249
            height: 28
            font.pixelSize: 16
            verticalAlignment: Text.AlignVCenter
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

    Rectangle {
        id: rectangle5
        x: 228
        y: 295
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
        TextInput {
            id: textInput4
            x: 8
            y: 0
            width: 249
            height: 28
            font.pixelSize: 16
        }
    }

    Label {
        id: label3
        x: 101
        y: 301
        width: 111
        height: 17
        text: qsTr("Maintenance date:")
    }

    states: [
        State {
            name: "clicked"
        }
    ]
}
