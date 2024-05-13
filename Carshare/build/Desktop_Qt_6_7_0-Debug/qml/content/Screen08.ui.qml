

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
        text: qsTr("Modify Car Details!")
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
        id: button1
        x: 245
        y: 294
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

    states: [
        State {
            name: "clicked"
        }
    ]
}
