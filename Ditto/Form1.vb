'
' My first Visual Basic program
'

Imports System.Reflection.Emit
Imports System.Windows.Forms.VisualStyles.VisualStyleElement

Public Class Form1

    'Title Label
    Private Sub labelTitle_Click(sender As Object, e As EventArgs) Handles labelTitle.Click

    End Sub

    'Label1
    Private Sub labelRealTime_Click(sender As Object, e As EventArgs) Handles labelRealTime.Click

    End Sub

    'Textbox for Label1
    Private Sub boxRealTime_TextChanged(sender As Object, e As EventArgs) Handles boxRealTime.TextChanged
        labelTitle.Text = boxRealTime.Text
    End Sub

    'Label2
    Private Sub labelDelayed_Click(sender As Object, e As EventArgs) Handles labelDelayed.Click

    End Sub

    'Textbox for Label2
    Private Sub boxDelayed_TextChanged(sender As Object, e As EventArgs) Handles boxDelayed.TextChanged

    End Sub

    'Display Button
    Private Sub buttonDisplay_Click(sender As Object, e As EventArgs) Handles buttonDisplay.Click
        labelTitle.Text = boxDelayed.Text
    End Sub

    'Closing Event
    Private Sub Form1_FormClosing(ByVal sender As System.Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles MyBase.FormClosing

        Dim result As DialogResult = MessageBox.Show("Are you sure you want to exit?", "Terminate Program", MessageBoxButtons.YesNo)

        If result = DialogResult.No Then
            e.Cancel = True
        End If
    End Sub

End Class