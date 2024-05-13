

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
        x: 215
        y: 21
        width: 219
        height: 53
        text: qsTr("View cars!")
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
        y: 147
        width: 111
        height: 21
        text: qsTr("Model:")
    }

    Rectangle {
        id: rectangle4
        x: 278
        y: 143
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
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

    Label {
        id: label3
        x: 135
        y: 187
        width: 111
        height: 21
        text: qsTr("Release year:")
    }

    Rectangle {
        id: rectangle6
        x: 278
        y: 183
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
    }

    Rectangle {
        id: rectangle2
        x: 278
        y: 256
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
    }

    Label {
        id: label4
        x: 135
        y: 259
        width: 111
        height: 22
        text: qsTr("Licence Plate:")
    }

    Label {
        id: label5
        x: 135
        y: 299
        width: 111
        height: 21
        text: qsTr("Model:")
    }

    Rectangle {
        id: rectangle7
        x: 278
        y: 295
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
    }

    Rectangle {
        id: rectangle8
        x: 278
        y: 295
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
    }

    Label {
        id: label7
        x: 135
        y: 339
        width: 111
        height: 21
        text: qsTr("Release year:")
    }

    Rectangle {
        id: rectangle9
        x: 278
        y: 335
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
    }

    Label {
        id: label6
        x: 135
        y: 421
        width: 111
        height: 22
        text: qsTr("Licence Plate:")
    }

    Rectangle {
        id: rectangle3
        x: 278
        y: 418
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
    }

    Label {
        id: label8
        x: 135
        y: 461
        width: 111
        height: 21
        text: qsTr("Model:")
    }

    Rectangle {
        id: rectangle10
        x: 278
        y: 457
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
    }

    Rectangle {
        id: rectangle11
        x: 278
        y: 457
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
    }

    Label {
        id: label9
        x: 135
        y: 501
        width: 111
        height: 21
        text: qsTr("Release year:")
    }

    Rectangle {
        id: rectangle12
        x: 278
        y: 497
        width: 265
        height: 28
        color: "#ffffff"
        radius: 12
    }

    states: [
        State {
            name: "clicked"
        }
    ]
}
