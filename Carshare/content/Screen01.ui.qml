

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
        x: 163
        y: 21
        width: 309
        height: 53
        text: qsTr("Welcome to car share!")
        font.pixelSize: 30
        verticalAlignment: Text.AlignVCenter
    }

    Button {
        id: button1
        x: 147
        y: 89
        width: 325
        height: 52
        text: qsTr("Add new car")

        Connections {
            target: button1
            onClicked: if (condition) {
                           button1.forceActiveFocus()
                       }
        }
    }

    Button {
        id: button2
        x: 147
        y: 205
        width: 325
        height: 52
        text: qsTr("Delete car")
    }

    Button {
        id: button3
        x: 147
        y: 263
        width: 325
        height: 52
        text: qsTr("Add maintenance record to car")
    }

    Button {
        id: button4
        x: 147
        y: 321
        width: 325
        height: 52
        text: qsTr("Delete maintenance record from car")
    }

    Button {
        id: button5
        x: 147
        y: 379
        width: 325
        height: 52
        text: qsTr("View cars")
    }

    Button {
        id: button6
        x: 147
        y: 437
        width: 325
        height: 52
        text: qsTr("View payments")
    }

    Button {
        id: button7
        x: 147
        y: 147
        width: 325
        height: 52
        text: qsTr("Modify car")
    }

    Button {
        id: button8
        x: 147
        y: 495
        width: 325
        height: 52
        text: qsTr("View bookings")
    }

    states: [
        State {
            name: "clicked"
        }
    ]
}
