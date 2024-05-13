// Copyright (C) 2021 The Qt Company Ltd.
// SPDX-License-Identifier: LicenseRef-Qt-Commercial OR GPL-3.0-only

import QtQuick 6.2
import Carshare

Window {
    width: mainScreen.width
    height: mainScreen.height
    minimumWidth: Constants.width
    minimumHeight: Constants.height
    maximumWidth: Constants.width
    maximumHeight: Constants.height

    visible: true
    title: "Carshare"

    Screen01 {
        id: mainScreen
    }

}

