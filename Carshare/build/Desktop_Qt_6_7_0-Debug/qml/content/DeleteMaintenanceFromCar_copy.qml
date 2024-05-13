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
    title: "Carshare"

    Rectangle {
        id: rectangle
        width: Constants.width
        height: Constants.height
        anchors.fill: parent

        color: Constants.backgroundColor

        Text {
            id: deleteMaintenanceRecordFromCar__Title
            x: 114
            y: 21
            width: 510
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
            id: submit__button
            x: 278
            y: 226
            text: qsTr("Submit")
            onClicked: {
                close()
                //TODO if sikerult torolni adatbazisbol dobjon egy successful buborekot
            }
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
                id: maintenanceId__input
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

