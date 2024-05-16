// Copyright (C) 2021 The Qt Company Ltd.
// SPDX-License-Identifier: LicenseRef-Qt-Commercial OR GPL-3.0-only

import QtQuick 6.2
import Carshare
import QtQuick.Controls 6.2

Window {
    width: modifyCarScreen.width
    height: modifyCarScreen.height
    minimumWidth: Constants.width
    minimumHeight: Constants.height
    maximumWidth: Constants.width
    maximumHeight: Constants.height

    visible: true
    title: "Cars"

    Rectangle {
        id: rectangle
        width: Constants.width
        height: Constants.height
        anchors.fill: parent

        color: Constants.backgroundColor

        Text {
            id: viewCars__Title
            x: 215
            y: 21
            width: 145
            height: 53
            text: qsTr("View Cars!")
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
            text: qsTr("Model")
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
            onClicked: close()
        }

        Item {
            id: __materialLibrary__
        }

        Label {
            id: label2
            x: 135
            y: 189
            width: 111
            height: 21
            text: qsTr("Release year:")
        }

        Rectangle {
            id: rectangle5
            x: 278
            y: 185
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
        }

        Rectangle {
            id: rectangle2
            x: 278
            y: 260
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
        }

        Label {
            id: label3
            x: 135
            y: 263
            width: 111
            height: 22
            text: qsTr("Licence Plate:")
        }

        Label {
            id: label4
            x: 135
            y: 303
            width: 111
            height: 21
            text: qsTr("Model")
        }

        Rectangle {
            id: rectangle6
            x: 278
            y: 299
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
        }

        Label {
            id: label5
            x: 135
            y: 345
            width: 111
            height: 21
            text: qsTr("Release year:")
        }

        Rectangle {
            id: rectangle7
            x: 278
            y: 341
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
        }

        Rectangle {
            id: rectangle3
            x: 278
            y: 411
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
        }

        Label {
            id: label6
            x: 135
            y: 414
            width: 111
            height: 22
            text: qsTr("Licence Plate:")
        }

        Label {
            id: label7
            x: 135
            y: 454
            width: 111
            height: 21
            text: qsTr("Model")
        }

        Rectangle {
            id: rectangle8
            x: 278
            y: 450
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
        }

        Label {
            id: label8
            x: 135
            y: 496
            width: 111
            height: 21
            text: qsTr("Release year:")
        }

        Rectangle {
            id: rectangle9
            x: 278
            y: 492
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
}

