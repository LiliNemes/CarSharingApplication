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

    visible: true
    title: "Modify car details..."

    Rectangle {
        id: rectangle
        width: Constants.width
        height: Constants.height
        anchors.fill: parent

        color: Constants.backgroundColor

        Text {
            id: modifyCarDetails__Title
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
                id: releaseYear__input
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
            id: submit__button
            x: 245
            y: 294
            text: qsTr("Submit")
            onClicked: {
                close()
                //TODO if sikerult modositani adatbazisba dobjon egy successful buborekot
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
                id: carModel__input
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
            onClicked: close()
        }

        states: [
            State {
                name: "clicked"
            }
        ]
    }
}

