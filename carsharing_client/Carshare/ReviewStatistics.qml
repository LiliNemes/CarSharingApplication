import QtQuick 2.15
import QtCharts 2.15
import QtQuick.Controls 2.15

Page {
    anchors.fill: parent
    property var othersSlice: null

    Rectangle {
        anchors.fill: parent

        ChartView {
            id: chart
            title: "Ratings of car"
            anchors.fill: parent
            legend.alignment: Qt.AlignBottom
            antialiasing: true

            property variant othersSlice: 0

            PieSeries {
                id: pieSeries
            }


            Connections {
                target: communication
                function onRatingsFetchingFinished(one, two, three, four, five)
                {
                    pieSeries.clear()
                    pieSeries.append("1", one)
                    pieSeries.append("2", two)
                    pieSeries.append("3", three)
                    pieSeries.append("4", four)
                    pieSeries.append("5", five)
                }
            }

        }
    }
}
