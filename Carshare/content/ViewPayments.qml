// Copyright (C) 2021 The Qt Company Ltd.
// SPDX-License-Identifier: LicenseRef-Qt-Commercial OR GPL-3.0-only

import QtQuick 6.2
import Carshare
import QtQuick.Controls 6.2

Window {
    width: Constants.width
    height: Constants.height
    minimumWidth: Constants.width
    minimumHeight: Constants.height
    maximumWidth: Constants.width
    maximumHeight: Constants.height

    visible: true
    title: "Payments"

    Rectangle {
        id: rectangle
        width: Constants.width
        height: Constants.height
        anchors.fill: parent

        color: Constants.backgroundColor

        Text {
            id: viewPayments__Title
            x: 215
            y: 21
            width: 219
            height: 53
            text: qsTr("View payments!")
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
            text: qsTr("Payment cost:")
        }

        Label {
            id: label1
            x: 135
            y: 147
            width: 111
            height: 21
            text: qsTr("Payment date")
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

        Rectangle {
            id: rectangle2
            x: 278
            y: 209
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
        }

        Label {
            id: label2
            x: 135
            y: 212
            width: 111
            height: 22
            text: qsTr("Payment cost:")
        }

        Label {
            id: label3
            x: 135
            y: 252
            width: 111
            height: 21
            text: qsTr("Payment date")
        }

        Rectangle {
            id: rectangle5
            x: 278
            y: 248
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
        }

        Rectangle {
            id: rectangle3
            x: 278
            y: 317
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
        }

        Label {
            id: label4
            x: 135
            y: 320
            width: 111
            height: 22
            text: qsTr("Payment cost:")
        }

        Label {
            id: label5
            x: 135
            y: 360
            width: 111
            height: 21
            text: qsTr("Payment date")
        }

        Rectangle {
            id: rectangle6
            x: 278
            y: 356
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

