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

    visible: true
    title: "Delete car from the system..."

    Rectangle {
        id: rectangle
        width: Constants.width
        height: Constants.height
        anchors.fill: parent

        color: Constants.backgroundColor

        Text {
            id: deleteCar_Title
            x: 200
            y: 21
            width: 176
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
                id: licencePlate__input
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
            id: submit__button
            x: 233
            y: 180
            text: qsTr("Submit")
            onClicked: {
                close()
                //TODO if sikerult torolni adatbazisbol dobjon egy successful buborekot
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

