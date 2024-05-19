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
                id: sellerText
                text: "Seller mode?"
                Layout.alignment: Qt.AlignVCenter
            }
            Switch {
                id: sellerSwitch
                text: qsTr("Seller mode")
            }

        }


        Button {
            id: buttonCreateAccount
            width: 127
            height: 25
            text: "Create account"
            Layout.alignment: Qt.AlignCenter
            onClicked: {
                textEmailErrReg.visible = false;
                rectEmailReg.border.color = "black";
                textPwdErrReg.visible = false;
                rectPwdReg.border.color = "black";
                textPwdRepeatErrReg.visible = false;
                rectPwdRepeatReg.border.color = "black";

                communication.createAccount(inputEmailReg.text, inputPwdReg.text, sellerSwitch.checked);
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
