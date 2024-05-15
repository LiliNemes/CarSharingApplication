import QtQuick 6.2
import QtQuick.Controls 6.2
import QtQuick.Layouts

Page {
    id: registrationview
    visible: true

    Dialog {
        id: dialogPasswordReq
        modal: true
        padding: 20
        x: infoRoundButton.x - dialogPasswordReq.width + infoRoundButton.width / 2
        y: infoRoundButton.y - (dialogPasswordReq.height + 5)
        contentItem: Label {
            text: qsTr("Password must meet the following requirements:" +
                       "<ul>" +
                       "<li>at least 8 characters long</li>" +
                       "<li>must contain lowercase character</li>" +
                       "<li>must contain uppercase character</li>" +
                       "<li>must contain number</li>" +
                       "<li>must contain special character</li>" +
                       "</ul>")
            textFormat: Text.RichText
        }
    }

    ColumnLayout {
        anchors.centerIn: parent
        spacing: 10

        Text {
            id: textTitle
            text: qsTr("Registration")
            font.pixelSize: 16
            Layout.alignment: Qt.AlignHCenter
        }

        RowLayout {
            spacing: 10

            Label {
                text: "First Name"
                Layout.preferredWidth: 103
                Layout.alignment: Qt.AlignVCenter
            }

            Rectangle {
                id: rectFirstNameReg
                width: 220
                height: 28
                border.color: "black"
                color: "white"

                TextInput {
                    id: inputFirstNameReg
                    anchors.fill: parent
                    anchors.margins: 2
                    horizontalAlignment: Text.AlignLeft
                    cursorVisible: true
                    activeFocusOnPress: true
                    clip: true
                }
            }
        }

        RowLayout {
            spacing: 10

            Label {
                text: "Last Name"
                Layout.preferredWidth: 103
                Layout.alignment: Qt.AlignVCenter
            }

            Rectangle {
                id: rectLastNameReg
                width: 220
                height: 28
                border.color: "black"
                color: "white"

                TextInput {
                    id: inputLastNameReg
                    anchors.fill: parent
                    anchors.margins: 2
                    horizontalAlignment: Text.AlignLeft
                    cursorVisible: true
                    activeFocusOnPress: true
                    clip: true
                }
            }
        }

        RowLayout {
            spacing: 10

            Label {
                text: "E-Mail address"
                Layout.preferredWidth: 103
                Layout.alignment: Qt.AlignVCenter
            }

            Rectangle {
                id: rectEmailReg
                width: 220
                height: 28
                border.color: "black"
                color: "white"

                TextInput {
                    id: inputEmailReg
                    anchors.fill: parent
                    anchors.margins: 2
                    horizontalAlignment: Text.AlignLeft
                    cursorVisible: true
                    activeFocusOnPress: true
                    clip: true
                }
            }
        }

        RowLayout {
            spacing: 10

            Label {
                text: "Password"
                Layout.preferredWidth: 103
                Layout.alignment: Qt.AlignVCenter
            }

            Rectangle {
                id: rectPwdReg
                width: 220
                height: 28
                border.color: "black"
                color: "white"

                TextInput {
                    id: inputPwdReg
                    anchors.fill: parent
                    anchors.margins: 2
                    horizontalAlignment: Text.AlignLeft
                    cursorVisible: true
                    activeFocusOnPress: true
                    echoMode: TextInput.Password
                    clip: true
                }
            }

            RoundButton {
                id: infoRoundButton
                width: 28
                height: 28
                radius: 14
                text: "i"
                font.italic: true
                onClicked: {
                    dialogPasswordReq.open();
                }
            }
        }

        RowLayout {
            spacing: 10

            Label {
                text: "Repeat password"
                Layout.preferredWidth: 103
                Layout.alignment: Qt.AlignVCenter
            }

            Rectangle {
                id: rectPwdRepeatReg
                width: 220
                height: 28
                border.color: "black"
                color: "white"

                TextInput {
                    id: inputPwdRepeatReg
                    anchors.fill: parent
                    anchors.margins: 2
                    horizontalAlignment: Text.AlignLeft
                    cursorVisible: true
                    activeFocusOnPress: true
                    echoMode: TextInput.Password
                    clip: true
                }
            }
        }

        Text {
            id: textFirstNameErrReg
            visible: false
            width: 300
            color: "#ff0000"
            text: "First name can contain only uppercase and lowercase alphabetic characters. No whitespaces are allowed."
        }

        Text {
            id: textLastNameErrReg
            visible: false
            width: 300
            color: "#ff0000"
            text: "Last name can contain only uppercase and lowercase alphabetic characters. No whitespaces are allowed."
        }

        Text {
            id: textEmailErrReg
            visible: false
            width: 300
            color: "#ff0000"
            text: "Invalid E-Mail format"
        }

        Text {
            id: textPwdErrReg
            visible: false
            width: 300
            color: "#ff0000"
            text: "Please check password requirements by clicking <em>i</em> button"
            textFormat: Text.RichText
        }

        Text {
            id: textPwdRepeatErrReg
            visible: false
            width: 300
            color: "#ff0000"
            text: "The given passwords don't match"
        }

        Button {
            id: buttonCreateAccount
            width: 127
            height: 25
            text: "Create account"
            onClicked: {
                textFirstNameErrReg.visible = false;
                rectFirstNameReg.border.color = "black";
                textLastNameErrReg.visible = false;
                rectLastNameReg.border.color = "black";
                textEmailErrReg.visible = false;
                rectEmailReg.border.color = "black";
                textPwdErrReg.visible = false;
                rectPwdReg.border.color = "black";
                textPwdRepeatErrReg.visible = false;
                rectPwdRepeatReg.border.color = "black";

                if (!userinputvalidator.validateName(inputFirstNameReg.text)) {
                    textFirstNameErrReg.visible = true;
                    rectFirstNameReg.border.color = "red";
                }

                if (!userinputvalidator.validateName(inputLastNameReg.text)) {
                    textLastNameErrReg.visible = true;
                    rectLastNameReg.border.color = "red";
                }

                if (!userinputvalidator.validateEmail(inputEmailReg.text)) {
                    textEmailErrReg.visible = true;
                    rectEmailReg.border.color = "red";
                }

                if (!userinputvalidator.validatePassword(inputPwdReg.text)) {
                    textPwdErrReg.visible = true;
                    rectPwdReg.border.color = "red";
                } else {
                    if (inputPwdReg.text !== inputPwdRepeatReg.text) {
                        textPwdRepeatErrReg.visible = true;
                        rectPwdRepeatReg.border.color = "red";
                    }
                }

                if (!textFirstNameErrReg.visible && !textLastNameErrReg.visible && !textEmailErrReg.visible && !textPwdErrReg.visible && !textPwdRepeatErrReg.visible) {
                    httpcommunication.createAccount(inputFirstNameReg.text, inputLastNameReg.text, inputEmailReg.text, inputPwdReg.text);
                }
            }
        }

        Button {
            id: buttonBack
            width: 68
            height: 25
            text: "Back"
            Layout.alignment: Qt.AlignHCenter
            onClicked: {
                stack.showLoginView();
            }
        }
    }
}
