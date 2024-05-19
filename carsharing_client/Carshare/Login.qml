import QtQuick 6.2
import QtQuick.Controls 6.2
import QtQuick.Layouts 6.2

Page {
    id: loginview

    ColumnLayout {
        anchors.centerIn: parent
        spacing: 10

        Text {
            id: textTitle
            text: qsTr("Log in to the Carsharing System")
            font.pixelSize: 16
            Layout.alignment: Qt.AlignHCenter
        }

        RowLayout {
            spacing: 10
            Layout.alignment: Qt.AlignHCenter

            Label {
                id: labelEmail
                text: "E-Mail:"
                Layout.alignment: Qt.AlignVCenter
                Layout.preferredWidth: 70 // Set a fixed width for alignment
            }

            Rectangle {
                id: rectEmail
                width: 200
                height: 28
                border.color: "black"
                color: "white"
                Layout.fillWidth: true

                TextInput {
                    id: inputEmail
                    anchors.fill: parent
                    anchors.margins: 2
                    horizontalAlignment: Text.AlignLeft
                    verticalAlignment: Text.AlignVCenter
                    activeFocusOnPress: true
                    cursorVisible: true
                }
            }
        }

        RowLayout {
            spacing: 10
            Layout.alignment: Qt.AlignHCenter

            Label {
                id: labelPassword
                text: "Password:"
                Layout.alignment: Qt.AlignVCenter
                Layout.preferredWidth: 70 // Set a fixed width for alignment
            }

            Rectangle {
                id: rectPassword
                width: 200
                height: 28
                border.color: "black"
                color: "white"
                Layout.fillWidth: true

                TextInput {
                    id: inputPassword
                    anchors.fill: parent
                    anchors.margins: 2
                    horizontalAlignment: Text.AlignLeft
                    verticalAlignment: Text.AlignVCenter
                    activeFocusOnPress: true
                    cursorVisible: true
                    echoMode: TextInput.Password
                    passwordMaskDelay: 1000
                }
            }
        }

        Text {
            id: textPasswordError
            width: 200
            height: 18
            visible: false
            text: "Password cannot be empty"
            horizontalAlignment: Text.AlignHCenter
            color: "red"
            Layout.alignment: Qt.AlignHCenter
        }

        Button {
            id: buttonLogin
            text: "Log in"
            focus: true
            Layout.alignment: Qt.AlignHCenter
            onClicked: {
                textPasswordError.visible = false;
                rectPassword.border.color = "black";
                rectEmail.border.color = "black";

                communication.login(inputEmail.text, inputPassword.text);
            }
        }

        Label {
            id: labelRegister
            text: "<a href='link_registration'>Register a new account</a>"
            textFormat: Text.RichText
            onLinkActivated: {
                stack.showRegistrationView();
            }
            Layout.alignment: Qt.AlignHCenter
        }
    }

    Connections {
        target: stack

        function onLoginFailed(errorMessage) {
            textPasswordError.text = errorMessage;
            textPasswordError.visible = true;
        }
    }
}
